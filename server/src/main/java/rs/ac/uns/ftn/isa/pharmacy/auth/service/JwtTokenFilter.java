package rs.ac.uns.ftn.isa.pharmacy.auth.service;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.AuthToken;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.IdentityProvider;
import rs.ac.uns.ftn.isa.pharmacy.auth.repository.CredentialsRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CredentialsRepository credentialsRepository;

    public JwtTokenFilter(JwtService jwtService, CredentialsRepository credentialsRepository) {
        this.jwtService = jwtService;
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String jwtToken = header.split(" ")[1].trim();
        IdentityProvider requestToken;

        try {
            requestToken = jwtService.decrypt(jwtToken);
        }
        catch (IllegalArgumentException e) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        Credentials userDetails = credentialsRepository
                .findById(requestToken.getEmail())
                .orElse(null);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails == null ? List.of() : userDetails.getAuthorities()
                );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
package rs.ac.uns.ftn.isa.pharmacy.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.pharmacy.auth.HttpRequestUtil;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.PassChangeDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.dto.RegistrationDto;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Role;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.CredentialsService;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.CredentialsTokenMapper;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.JwtService;
import rs.ac.uns.ftn.isa.pharmacy.auth.model.Credentials;
import rs.ac.uns.ftn.isa.pharmacy.auth.service.RegistrationService;
import rs.ac.uns.ftn.isa.pharmacy.domain.supply.exceptions.MessageException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CredentialsService credentialsService;
    private final RegistrationService registrationService;
    private final CredentialsTokenMapper credentialsTokenMapper;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            CredentialsService credentialsService,
            RegistrationService registrationService,
            CredentialsTokenMapper credentialsTokenMapper
    ){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.credentialsService = credentialsService;
        this.registrationService = registrationService;
        this.credentialsTokenMapper = credentialsTokenMapper;
    }

    @PostMapping("login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)
                    );
            Credentials credentials = (Credentials) authenticate.getPrincipal();

            credentialsService.logLogin(loginDto.email);

            return ResponseEntity.ok()
                    .header(
                        HttpHeaders.AUTHORIZATION,
                        jwtService.encrypt(credentialsTokenMapper.createAuthToken(credentials))
                    ).build();
        }
        catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegistrationDto dto) {
        try {
            registrationService.register(dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("logged")
    @Secured(Role.SUPPLIER)
    public ResponseEntity<?> hasLoggedInBefore(HttpServletRequest request) {
        var identityProvider = HttpRequestUtil.getIdentity(request);
        return ResponseEntity.ok(credentialsService.hasLoggedIn(identityProvider.getEmail()));
    }

    @PostMapping("change-password")
    @Secured({Role.SUPPLIER})
    public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody PassChangeDto dto) {
        var identityProvider = HttpRequestUtil.getIdentity(request);
        try {
            credentialsService.changePassword(identityProvider.getEmail(), dto);
            return ResponseEntity.ok().build();
        }
        catch (MessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

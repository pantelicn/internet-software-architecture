package rs.ac.uns.ftn.isa.pharmacy.auth;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {

    private static final String IDENTITY_CLAIM_ATTR = "http_identity";

    public static void addIdentity(HttpServletRequest request, IdentityProvider obj) {
        request.setAttribute(IDENTITY_CLAIM_ATTR, obj);
    }

    public static IdentityProvider getIdentity(HttpServletRequest request) {
        return (IdentityProvider) request.getAttribute(IDENTITY_CLAIM_ATTR);
    }
}

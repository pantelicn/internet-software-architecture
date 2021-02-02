package rs.ac.uns.ftn.isa.pharmacy.auth;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {

    private static final String IDENTITY_CLAIM_ATTR = "http_identity";

    public static void addIdentityClaim(HttpServletRequest request, Object obj) {
        request.setAttribute(IDENTITY_CLAIM_ATTR, obj);
    }

    public static Object getIdentityClaim(HttpServletRequest request) {
        return request.getAttribute(IDENTITY_CLAIM_ATTR);
    }
}

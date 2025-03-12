package org.example.hana.global.util;

import java.security.Principal;

public class UserUtils {
    public static Long getUserIdFromPrincipal(Principal principal) {
        if (principal == null || principal.getName() == null) {
            return null;
        }
        try {
            return Long.valueOf(principal.getName());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

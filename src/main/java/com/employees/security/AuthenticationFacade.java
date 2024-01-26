
package com.employees.security;

/**
 *
 * @author SamuelBoada
 */
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
}
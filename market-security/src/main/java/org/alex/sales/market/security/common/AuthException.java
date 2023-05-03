package org.alex.sales.market.security.common;

public class AuthException extends RuntimeException {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String INVALID_PASSWORD = "invalid password";
    public static final String INVALID_TOKEN = "invalid JWT token";

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}

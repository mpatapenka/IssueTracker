package org.maxim.issuetracker.security;

public final class SecurityConstants {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_LEAD = "ROLE_LEAD";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ADMIN_POSITION = "Admin";

    public static final String HAS_ROLE_ADMIN = "hasRole('" + ROLE_ADMIN + "')";
    public static final String HAS_ROLE_USER = "hasRole('" + ROLE_USER + "')";
    public static final String IS_AUTHENTICATED = "isAuthenticated()";
    public static final String IS_ANONYMOUS = "isAnonymous()";

    private SecurityConstants() { }

}

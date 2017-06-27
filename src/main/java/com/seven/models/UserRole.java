package com.seven.models;

/**
 * Created by ruslankatsyuryna on 6/26/17.
 */
public enum UserRole {

    ADMIN("ADMIN"),
    COMPANY_OWNER("COMPANY_OWNER"),
    COMPANY_EMPLOYER("COMPANY_EMPLOYER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}

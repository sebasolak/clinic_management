package com.example.demo.security;

public enum AppPermissions {
    DOCTOR_READ("doctor:read"),
    DOCTOR_WRITE("doctor:write"),
    CHIEF_READ("chief:read"),
    CHIEF_WRITE("chief:write");


    private final String permission;

    AppPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

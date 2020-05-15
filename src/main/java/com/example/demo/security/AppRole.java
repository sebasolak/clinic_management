package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.AppPermissions.*;

public enum AppRole {

    DOC(Sets.newHashSet(DOCTOR_READ, DOCTOR_WRITE)),
    CHIEF_OF_MEDICINE(Sets.newHashSet(DOCTOR_READ, DOCTOR_WRITE, CHIEF_READ, CHIEF_WRITE));


    private final Set<AppPermissions> permissions;

    AppRole(Set<AppPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<AppPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }


}

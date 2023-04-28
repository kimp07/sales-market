package org.alex.sales.market.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ROOT_ADMIN("ROOT_ADMIN"),
    ROOT_MODERATOR("ROOT_MODERATOR"),
    ORGANIZATION_ADMIN("ORGANIZATION_ADMIN"),
    ORGANIZATION_USER("ORGANIZATION_USER"),

    ;

    private final String roleValue;

    @Override
    public String getAuthority() {
        return roleValue;
    }

}

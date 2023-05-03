package org.alex.sales.market.security.impl;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.alex.sales.market.security.model.JwtAuthentication;
import org.alex.sales.market.security.model.Role;
import org.alex.sales.market.security.model.TokenClaims;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUserLogin(claims.get(TokenClaims.USER_LOGIN.name(), String.class));
        jwtInfoToken.setUserName(claims.get(TokenClaims.USER_NAME.name(), String.class));
        jwtInfoToken.setOrganization(claims.get(TokenClaims.ORGANIZATION.name(), String.class));
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        @SuppressWarnings("unchecked")
        final List<String> roles = (List<String>) claims.get(TokenClaims.ROLES.name(), List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

}

package org.alex.sales.market.security;

import io.jsonwebtoken.Claims;
import org.alex.sales.market.domain.entity.User;

import javax.annotation.Nonnull;

public interface JwtProvider {

    String generateAccessToken(@Nonnull User user);

    String generateRefreshToken(@Nonnull User user);

    boolean validateAccessToken(@Nonnull String accessToken);

    boolean validateRefreshToken(@Nonnull String refreshToken);

    Claims getAccessClaims(@Nonnull String token);

    Claims getRefreshClaims(@Nonnull String token);

}

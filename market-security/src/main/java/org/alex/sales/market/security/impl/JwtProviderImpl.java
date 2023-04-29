package org.alex.sales.market.security.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alex.sales.market.domain.entity.User;
import org.alex.sales.market.security.api.JwtProvider;
import org.alex.sales.market.security.model.TokenClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtProviderImpl implements JwtProvider {

    private static final String TOKEN_EXPIRED_EXCEPTION = "Token expired";
    private static final String UNSUPPORTED_JWT_EXCEPTION = "Unsupported jwt";
    private static final String MALFORMED_JWT_EXCEPTION = "Malformed jwt";
    private static final String INVALID_SIGNATURE_EXCEPTION = "Invalid signature";
    private static final String INVALID_TOKEN_EXCEPTION = "invalid token";

    @Value("${sales.market.security.token.secret-access-key}")
    private final SecretKey jwtAccessSecret;

    @Value("${sales.market.security.token.secret-refresh-key}")
    private final SecretKey jwtRefreshSecret;

    public String generateAccessToken(@Nonnull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim(TokenClaims.ROLES.name(), user.getRoles())
                .claim(TokenClaims.USER_LOGIN.name(), user.getLogin())
                .claim(TokenClaims.ORGANIZATION.name(), user.getOrganization().getName())
                .compact();
    }

    public String generateRefreshToken(@Nonnull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(refreshExpiration)
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@Nonnull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@Nonnull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private boolean validateToken(@Nonnull String token, @Nonnull Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error(TOKEN_EXPIRED_EXCEPTION, expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error(UNSUPPORTED_JWT_EXCEPTION, unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error(MALFORMED_JWT_EXCEPTION, mjEx);
        } catch (SignatureException sEx) {
            log.error(INVALID_SIGNATURE_EXCEPTION, sEx);
        } catch (Exception e) {
            log.error(INVALID_TOKEN_EXCEPTION, e);
        }
        return false;
    }

    public Claims getAccessClaims(@Nonnull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@Nonnull String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(@Nonnull String token, @Nonnull Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}

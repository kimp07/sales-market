package org.alex.sales.market.security.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.alex.sales.market.domain.entity.User;
import org.alex.sales.market.security.AuthService;
import org.alex.sales.market.security.JwtProvider;
import org.alex.sales.market.security.common.AuthException;
import org.alex.sales.market.security.model.JwtAuthentication;
import org.alex.sales.market.security.model.JwtRequest;
import org.alex.sales.market.security.model.JwtResponse;
import org.alex.sales.market.service.data.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public JwtResponse login(@Nonnull JwtRequest authRequest) {
        final User user = userService.findByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException(AuthException.USER_NOT_FOUND));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return JwtResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new AuthException(AuthException.INVALID_PASSWORD);
        }
    }

    public JwtResponse getAccessToken(@Nonnull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.findByLogin(login)
                        .orElseThrow(() -> new AuthException(AuthException.USER_NOT_FOUND));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return JwtResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(null)
                        .build();
            }
        }
        return JwtResponse.builder()
                .accessToken(null)
                .refreshToken(null)
                .build();
    }

    public JwtResponse refresh(@Nonnull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.findByLogin(login)
                        .orElseThrow(() -> new AuthException(AuthException.USER_NOT_FOUND));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return JwtResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(newRefreshToken)
                        .build();
            }
        }
        throw new AuthException(AuthException.INVALID_TOKEN);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}

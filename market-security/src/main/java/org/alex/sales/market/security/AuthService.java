package org.alex.sales.market.security;

import jakarta.annotation.Nonnull;
import org.alex.sales.market.security.model.JwtAuthentication;
import org.alex.sales.market.security.model.JwtRequest;
import org.alex.sales.market.security.model.JwtResponse;

public interface AuthService {

    JwtResponse login(@Nonnull JwtRequest authRequest);

    JwtResponse getAccessToken(@Nonnull String refreshToken);

    JwtResponse refresh(@Nonnull String refreshToken);

    JwtAuthentication getAuthInfo();

}

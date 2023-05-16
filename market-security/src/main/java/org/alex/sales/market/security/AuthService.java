package org.alex.sales.market.security;

import org.alex.sales.market.security.model.JwtAuthentication;
import org.alex.sales.market.security.model.JwtRequest;
import org.alex.sales.market.security.model.JwtResponse;

import javax.annotation.Nonnull;

public interface AuthService {

    JwtResponse login(@Nonnull JwtRequest authRequest);

    JwtResponse getAccessToken(@Nonnull String refreshToken);

    JwtResponse refresh(@Nonnull String refreshToken);

    JwtAuthentication getAuthInfo();

}

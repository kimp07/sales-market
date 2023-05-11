package org.alex.sales.market.rest.api;

import lombok.RequiredArgsConstructor;
import org.alex.sales.market.security.AuthService;
import org.alex.sales.market.security.model.JwtRequest;
import org.alex.sales.market.security.model.JwtResponse;
import org.alex.sales.market.security.model.RefreshJwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthController.API_AUTH)
@RequiredArgsConstructor
public class AuthController {

    public static final String API_AUTH = "/api/auth";

    private static final String LOGIN = "/login";
    private static final String TOKEN = "/token";
    private static final String REFRESH = "/refresh";


    private final AuthService authService;

    @PostMapping(LOGIN)
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping(TOKEN)
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping(REFRESH)
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}

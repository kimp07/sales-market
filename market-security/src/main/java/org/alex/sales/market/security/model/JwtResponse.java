package org.alex.sales.market.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private static final String type = "Bearer";

    private String accessToken;
    private String refreshToken;

}

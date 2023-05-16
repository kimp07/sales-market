package org.alex.sales.market.security.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class SalesMarketSecurityConfig {

    @Value("${sales.market.security.token.secret-access-key}")
    private String jwtAccessSecretWord;
    @Value("${sales.market.security.token.secret-refresh-key}")
    private String jwtRefreshSecretWord;

    @Bean(name = "jwtAccessSecret")
    public SecretKey getJwtAccessSecret() {

        return new SecretKeySpec(
                Base64.getEncoder().encode(jwtAccessSecretWord.getBytes(StandardCharsets.UTF_8)),
                SignatureAlgorithm.HS512.getJcaName()
        );
    }

    @Bean(name = "jwtRefreshSecret")
    public SecretKey getJwtRefreshSecret() {
        return new SecretKeySpec(
                Base64.getEncoder().encode(jwtRefreshSecretWord.getBytes(StandardCharsets.UTF_8)),
                SignatureAlgorithm.HS512.getJcaName());
    }

}


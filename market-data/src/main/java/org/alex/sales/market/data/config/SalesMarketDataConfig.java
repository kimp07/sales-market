package org.alex.sales.market.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {"org.alex.sales.market.data"}
)
@EntityScan(basePackages = {"org.alex.sales.market.domain.entity"})
public class SalesMarketDataConfig {
}

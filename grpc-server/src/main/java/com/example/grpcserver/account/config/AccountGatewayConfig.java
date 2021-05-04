package com.example.grpcserver.account.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "account.gateway")
public class AccountGatewayConfig {

    private String baseUrl;
    private String path;
    private Integer port;

    public String getFormatedUrl() {
        return baseUrl.concat(":")
                .concat(Integer.toString(port))
                .concat(path);
    }

    public String getFormatedUrlById(Integer id) {
        return baseUrl.concat(":")
                .concat(Integer.toString(port))
                .concat(path)

                .concat("/")
                .concat(Integer.toString(id));
    }
}

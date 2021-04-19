package com.aggregator.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "aggregator")
@Getter
@Setter
@ToString
public class AggregatorConfigReader {

    private String userServiceHostUrl;
    private String orderServiceHostUrl;

}

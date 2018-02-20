package com.banana;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("bananaapi")
public class ApplicationSettings {

    private Integer oneTimeTokenLength;

    private Integer oneTimeTokenValiditySeconds;
}

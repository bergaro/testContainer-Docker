package ru.netology.springBootApp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springBootApp.app.DevProfile;
import ru.netology.springBootApp.app.ProductionProfile;
import ru.netology.springBootApp.app.SystemProfile;

@Configuration
public class ApplicationConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnMissingBean(name = "devProfile")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}

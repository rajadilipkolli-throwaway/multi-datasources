package com.example.multipledatasources.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfiguration {

    @Qualifier("second") @Bean(defaultCandidate = false)
    @ServiceConnection
    MySQLContainer<?> mySQLContainer() {
        return new MySQLContainer<>(DockerImageName.parse("mysql").withTag("9.1"));
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres").withTag("17.0-alpine"));
    }

    @Bean
    public DynamicPropertyRegistrar databaseProperties(@Qualifier("second") MySQLContainer<?> mySQLContainer) {
        return (properties) -> {
            // Connect our Spring application to our Testcontainers instances
            properties.add("app.datasource.cardholder.url", mySQLContainer::getJdbcUrl);
            properties.add("app.datasource.cardholder.username", mySQLContainer::getUsername);
            properties.add("app.datasource.cardholder.password", mySQLContainer::getPassword);
        };
    }
}

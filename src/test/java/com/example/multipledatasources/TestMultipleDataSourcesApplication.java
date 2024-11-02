package com.example.multipledatasources;

import com.example.multipledatasources.common.ContainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestMultipleDataSourcesApplication {

    public static void main(String[] args) {
        SpringApplication.from(MultipleDataSourcesApplication::main)
                .with(ContainersConfiguration.class)
                .run(args);
    }
}

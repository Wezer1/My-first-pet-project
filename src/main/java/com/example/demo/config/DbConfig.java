package com.example.demo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/habrdb");
        dataSourceBuilder.username("habrpguser");
        dataSourceBuilder.password("pgpwd4habr");
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        return dataSourceBuilder.build();
    }

}

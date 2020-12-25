package com.pnu.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JdbcTemplateConfig {
    @Value("${db.driver}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        //var 1
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(20);

        return dataSource;

        //var 2
//        HikariConfig hikariConfig = new HikariConfig();
//
//        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3310/spring-jdbc-db");
//        hikariConfig.setUsername("root");
//        hikariConfig.setPassword("root");
//
//        hikariConfig.setMinimumIdle(5);
//
//        hikariConfig.setMaximumPoolSize(1500);
//
//        return new HikariDataSource(hikariConfig);

    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}

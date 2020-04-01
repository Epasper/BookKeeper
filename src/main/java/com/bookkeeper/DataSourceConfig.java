package com.bookkeeper;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        dataSourceBuilder.url("jdbc:mysql://localhost/bookkeeper?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("Lyrad4wnbringer");
        return dataSourceBuilder.build();
    }
}

package com.bookkeeper;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

public class DataSourceCreator {

    private static final String driverClassName = com.mysql.cj.jdbc.Driver.class.getName();
    private static final String url = "jdbc:mysql://localhost:3306/bookkeeper?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
    private static final String dbUsername = "root";
    private static final String dbPassword = "Lyrad4wnbringer";

    public static DataSource getDataSource() {
/*
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);

        dataSource.setUrl(url);

        dataSource.setUsername(dbUsername);

        dataSource.setPassword(dbPassword);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");

        dataSource.setConnectionProperties(properties);

        return dataSource;*/
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(dbUsername);
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }

}
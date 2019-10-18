package ru.psu.taskforjob.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {

    public static DataSource createDataSource(){
        HikariConfig config=new HikariConfig();
        config.setJdbcUrl(JdbcConfig.URL);
        config.setUsername(JdbcConfig.USERNAME);
        config.setPassword(JdbcConfig.PASSWORD);
        config.setAutoCommit(true);
        config.setMaximumPoolSize(800);
        config.setIdleTimeout(60000);
        config.setConnectionTimeout(500);
        return new HikariDataSource(config);
    }
}

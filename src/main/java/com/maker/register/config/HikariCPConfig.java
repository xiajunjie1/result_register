package com.maker.register.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:config/database.properties")
public class HikariCPConfig {
    private static final Logger LOGGER= LoggerFactory.getLogger(HikariCPConfig.class);
    @Value("${mydb.database.pool.driverClassName}")
    private String driverClassName;
    @Value("${mydb.database.pool.jdbcUrl}")
    private String jdbcUrl;
    @Value("${mydb.database.pool.username}")
    private String username;
    @Value("${mydb.database.pool.password}")
    private String password;
    @Value("${mydb.database.pool.connectionTimeout}")
    private long connectionTimeout;
    @Value("${mydb.database.pool.readOnly}")
    private boolean readOnly;
    @Value("${mydb.database.pool.idleTimeout}")
    private long idleTimeout;
   @Value("${mydb.database.pool.maxLifeTime}")
    private long maxLifeTime;
    @Value("${mydb.database.pool.minimumIdle}")
    private int minimumIdle;
    @Bean
    public DataSource dataSource(){
        LOGGER.info("测试：{}",this.driverClassName);
        HikariDataSource dataSource=new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    dataSource.setConnectionTimeout(connectionTimeout);
      dataSource.setReadOnly(readOnly);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaxLifetime(maxLifeTime);
        dataSource.setMinimumIdle(minimumIdle);
        return dataSource;
    }
    /**
     * 针对@Value无法解析${}的时候来使用
     * */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}

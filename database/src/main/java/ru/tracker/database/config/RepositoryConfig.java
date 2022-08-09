package ru.tracker.database.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
        basePackages = "ru.tracker.database.mapper"
)
public class RepositoryConfig {

    @Bean("contextDataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties contextDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("contextDataSource")
    @ConfigurationProperties("spring.datasource.configuration")
    public HikariDataSource contextDataSource(
            @Qualifier("contextDataSourceProperties") DataSourceProperties rrDataSourceProperties) {
        return rrDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}

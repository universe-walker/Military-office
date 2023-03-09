package com.nm.Militaryoffice.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
open class DataSourceConfig {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    open fun hikariDataSource(): HikariDataSource {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    open fun namedParameterJdbcTemplate(@Autowired hikariDataSource: HikariDataSource): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(hikariDataSource())
    }
}
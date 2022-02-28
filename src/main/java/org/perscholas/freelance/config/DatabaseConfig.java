package org.perscholas.freelance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//  This class will tell Spring to look in the org.perscholas.freelance.database package for database items

@Configuration
@EnableJpaRepositories(basePackages = "org.perscholas.freelance.database")
public class DatabaseConfig {

}
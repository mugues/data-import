package com.sample;

import com.sample.config.DatabaseConfig;
import com.sample.dao.BusinessPartnerDao;
import org.h2.store.Data;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.TestPropertySource;

@Configuration
@ComponentScan(basePackages = {"com.sample"})
@TestPropertySource(locations = "classpath:application-test.properties")
@Profile({ "test" })
public class AppConfig {

}

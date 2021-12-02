package com.sample.dao;


import com.sample.AppConfig;
import com.sample.model.BusinessPartner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
public class BusinessPartnerDaoTest {

    @Autowired
    private BusinessPartnerDao businessPartnerDao;

    @Test
    /*
    @SqlGroup({
            @Sql("classpath:create-tables.sql"),
            @Sql("classpath:insert.sql")
    })*/
    public void listBusinessPartners() {
        List<BusinessPartner> businessPartners = businessPartnerDao.listBusinessPartners();
        System.out.println("Business: "+businessPartners.size());
        Assertions.assertEquals(2, businessPartners.size(), "i bp devono essere 2");

    }

}

package com.sample.service;

import com.sample.dao.BusinessPartnerDao;
import com.sample.dao.StoricoJsonDao;
import com.sample.dao.StoricoJsonDaoJdbc;
import com.sample.model.BusinessPartner;
import com.sample.model.StoricoJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component

public class ImportService {
    private final static Logger logger = LoggerFactory.getLogger(ImportService.class);

    @Autowired
    private BusinessPartnerDao businessPartnerDao;

    @Autowired
    private StoricoJsonDao storicoJsonDao;

    @Autowired
    private StoricoJsonDaoJdbc storicoJsonDaoJdbc;

    public List<BusinessPartner> listBusinessPartners() {
        logger.info("listBusinessPartners start");

        return businessPartnerDao.listBusinessPartners();
    }

    @Transactional
    public void insertStoricoJson ( List<StoricoJson> storicoJsons) {
        int messaggiInseriti = 0;
        for(StoricoJson s: storicoJsons){
            messaggiInseriti += storicoJsonDaoJdbc.insertStoricoJson(s);
        }
        logger.info("Totale Messaggi Sap inseriti : "+messaggiInseriti);
    }
}












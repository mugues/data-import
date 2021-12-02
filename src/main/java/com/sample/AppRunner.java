package com.sample;

import com.sample.model.BusinessPartner;
import com.sample.model.StoricoJson;
import com.sample.service.ImportService;
import com.sample.config.DatabaseConfig;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class AppRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);


    private final ImportService importService;

    public AppRunner(ImportService importService) {
        this.importService = importService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<BusinessPartner> businessPartners = importService.listBusinessPartners();
        logger.info("Inizio processamento Messaggi Sap per "+businessPartners.size()+" Business Partners");
        List<StoricoJson> storicoJsonList = new ArrayList<>();
        for (BusinessPartner bp : businessPartners) {

            List<String> splitted = Arrays.stream(bp.getNote().split("\\[SAP-API@")).map(String::trim).collect(Collectors.toList());
            for (String s : splitted) {
                if (StringUtils.isNotEmpty(s)) {
                    StoricoJson storicoJson = new StoricoJson();
                    storicoJson.setIdBp(bp.getId());
                    storicoJson.setDataInserimento(s.substring(0, 19));
                    storicoJson.setMessaggio(s.substring(21, s.length()));
                    storicoJsonList.add(storicoJson);
                }
            }
        }
        logger.info("Totale Messaggi Sap da inserire: "+storicoJsonList.size());
       importService.insertStoricoJson(storicoJsonList);
    }



}

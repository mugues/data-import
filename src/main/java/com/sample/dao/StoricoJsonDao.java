package com.sample.dao;

import com.sample.model.StoricoJson;

import java.util.List;

public interface StoricoJsonDao {
    List<StoricoJson> existsStoricoJson(String messaggio);
}

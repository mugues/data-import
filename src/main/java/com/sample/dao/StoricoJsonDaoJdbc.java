package com.sample.dao;

import com.sample.model.StoricoJson;
import com.sample.service.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class StoricoJsonDaoJdbc implements StoricoJsonDao {
    private final static Logger logger = LoggerFactory.getLogger(ImportService.class);
    private final JdbcTemplate jdbcTemplate;

    public StoricoJsonDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<StoricoJson> existsStoricoJson(String messaggio) {

        String sql = "SELECT * from storico_json where messaggio = ?";

        List<StoricoJson> storicoJsons = jdbcTemplate.query(sql, new Object[]{messaggio}, new StoricoJsonMapper());

        return storicoJsons;
    }

    public int insertStoricoJson(StoricoJson storicoJson) {
        if(existsStoricoJson(storicoJson.getMessaggio()).isEmpty()){
            jdbcTemplate.update("INSERT INTO storico_json (id_bp, data_inserimento, messaggio)  values (?, ?, ?)", storicoJson.getIdBp(), getSqlDate(storicoJson.getDataInserimento()), storicoJson.getMessaggio());
            return 1;
        }else{
            logger.warn("Si è tentato di inserire un messaggio già presente sulla tabella storico_json. Il messaggio non sarà persistito.");
            return 0;
        }
    }

    private static final class StoricoJsonMapper implements RowMapper<StoricoJson> {

        public StoricoJson mapRow(ResultSet rs, int rowNum) throws SQLException {
            StoricoJson storicoJson = new StoricoJson();
            storicoJson.setIdBp(rs.getLong("id_bp"));
            storicoJson.setMessaggio(rs.getString("messaggio"));
            return storicoJson;
        }
    }

    public Timestamp getSqlDate(String date) {
        java.sql.Timestamp sql = new Timestamp(System.currentTimeMillis());
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.util.Date dateTest = formatter.parse(date);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mydate = dateFormat.format(dateTest);
            sql = java.sql.Timestamp.valueOf(mydate);
        } catch (ParseException e) {
            logger.warn("Impossibile eseguire il parse per la data {}", date);
        }
        return sql;
    }


}

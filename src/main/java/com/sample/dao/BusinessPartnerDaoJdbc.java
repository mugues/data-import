package com.sample.dao;

import com.sample.model.BusinessPartner;
import com.sample.service.ImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BusinessPartnerDaoJdbc implements BusinessPartnerDao {
    private final static Logger logger = LoggerFactory.getLogger(ImportService.class);
    private final JdbcTemplate jdbcTemplate;

    public BusinessPartnerDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BusinessPartner> listBusinessPartners() {

        String sql = "select * from business_partner bp";

        List<BusinessPartner> businessPartners = jdbcTemplate.query(sql, new BusinessPartnerMapper());

        return businessPartners;
    }

    private static final class BusinessPartnerMapper implements RowMapper<BusinessPartner> {

        public BusinessPartner mapRow(ResultSet rs, int rowNum) throws SQLException {

            BusinessPartner businessPartner = new BusinessPartner();
            businessPartner.setId(rs.getLong("id"));
            businessPartner.setNote(rs.getString("note"));
            return businessPartner;
        }
    }

}

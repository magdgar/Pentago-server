package com.mgf.service.admin;

import com.mgf.database.JdbcConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Makda on 2016-01-25.
 */
    @RestController
    @RequestMapping(value = "/admins")
    public class AdminsController {
        @Autowired
        private JdbcConnection jdbcConnection;

        @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<List<String>> addPlayer(){
            return new ResponseEntity<List<String>>( getWonStats(), HttpStatus.OK );
        }

        public  List<String> getWonStats(){
            return jdbcConnection.query("SELECT * FROM pentago.admins;",new RowMapper<String>(){
                @Override
                public String mapRow(ResultSet rs, int rownumber) throws SQLException {
                    return rs.getString(1);
                }
            });
        }
    }

package com.mgf.service.admin;

import com.mgf.database.JdbcConnection;
import javafx.util.Pair;
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
 * Created by Makda on 2016-01-27.
 */
@RestController
@RequestMapping(value = "/usersbadge")
public class UsersFromCountryController  {
    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pair<String, String>>> addPlayer(){
        return new ResponseEntity<List<Pair<String, String>>>( getEnemyList(), HttpStatus.OK );
    }

    public  List<Pair<String, String>> getEnemyList(){
        return jdbcConnection.query("SELECT * FROM pentago.users_badges",new RowMapper<Pair<String, String>>(){
            @Override
            public Pair<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
                Pair p = new Pair(rs.getString(1), rs.getString(2));
                return p;
            }
        });
    }
}

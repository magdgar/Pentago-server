package com.mgf.service.user;

import com.mgf.database.JdbcConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Makda on 2016-01-25.
 */

@RestController
@RequestMapping(value = "/gametimestats")
public class GameTimeController {
    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Double>> addPlayer(@RequestParam(value = "name")String login){
        return new ResponseEntity<List<Double>>( getWonStats(login), HttpStatus.OK );
    }

    public  List<Double> getWonStats(String login){
        return jdbcConnection.query("call pentago.display_game_times(?);", new Object[] { login },new RowMapper<Double>(){
            @Override
            public Double mapRow(ResultSet rs, int rownumber) throws SQLException {
                return rs.getDouble(1);
            }
        });
    }
}

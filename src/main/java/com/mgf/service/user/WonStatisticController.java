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

/**
 * Created by Makda on 2016-01-25.
 */
@RestController
@RequestMapping(value = "/wonstatistic")
public class WonStatisticController {

    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Double> addPlayer(@RequestParam(value = "name")String login){
        return new ResponseEntity<Double>( getWonStats(login), HttpStatus.OK );
    }

    public Double getWonStats(String login){
        return  jdbcConnection.query("select pentago.get_game_won_stats(?)",new Object[] { login },new RowMapper<Double>(){
            @Override
            public Double mapRow(ResultSet rs, int rownumber) throws SQLException {
                Double a = rs.getDouble(1);
                return a;
            }
        }).get(0);
    }
}

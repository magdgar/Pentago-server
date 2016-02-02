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
 * Created by Makda on 2016-01-22.
 */
@RestController
@RequestMapping(value = "/partner")
public class PartnerController {

    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.PUT)
    public void addPlayer(@RequestParam(value = "login")String login,  @RequestParam(value = "player") String player){
        jdbcConnection.update("insert into pentago.chhsenplayer(login, player) values (?, ?)", login, player);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> addPlayer(@RequestParam(value = "login")String login){
        return new ResponseEntity<String>( getFavoriteEnemy(login), HttpStatus.OK );
    }

    public String getFavoriteEnemy(String login){
        return  jdbcConnection.query("select p.player from pentago.chhsenplayer join " +
                "(select player, count(player) sum from pentago.chhsenplayer  where login = ? group by player) as p on chhsenplayer.player = p.player LIMIT 1",new Object[] { login },new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet rs, int rownumber) throws SQLException {
                String a = rs.getString(1);
                return a;
            }
        }).get(0);
    }
}
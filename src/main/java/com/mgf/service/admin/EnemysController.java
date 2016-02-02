package com.mgf.service.admin;

import com.mgf.database.JdbcConnection;
import com.mgf.model.Player;
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
@RequestMapping(value = "/enemys")
public class EnemysController  {
    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Player>> addPlayer(){
        return new ResponseEntity<List<Player>>( getEnemyList(), HttpStatus.OK );
    }

    public  List<Player> getEnemyList(){
        return jdbcConnection.query("SELECT * FROM pentago.enemys",new RowMapper<Player>(){
            @Override
            public Player mapRow(ResultSet rs, int rownumber) throws SQLException {
                Player p = new Player(rs.getString(1), rs.getInt(2), rs.getInt(3));
                return p;
            }
        });
    }
}
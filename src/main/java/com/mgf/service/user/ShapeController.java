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
@RequestMapping(value = "/shape")
public class ShapeController {

    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.PUT)
    public void addShape(@RequestParam(value = "login")String login, @RequestParam(value = "shape") String boardShape){
        jdbcConnection.update("insert into pentago.choosenshape(login, shape) values (?, ?)", login, boardShape);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> addPlayer(@RequestParam(value = "login")String login){
        return new ResponseEntity<String>( getFavoriteShape(login), HttpStatus.OK );
    }

    public String getFavoriteShape(String login){
        return  jdbcConnection.query("select p.shape from \n" +
                "(select shape, count(shape) sum from pentago.choosenshape  where login = ? group by shape) as p LIMIT 1",new Object[] { login },new RowMapper<String>(){
            @Override
            public String mapRow(ResultSet rs, int rownumber) throws SQLException {
                String a = rs.getString(1);
                return a;
            }
        }).get(0);
    }

}
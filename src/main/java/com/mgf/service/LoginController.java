package com.mgf.service;

import com.mgf.database.JdbcConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private JdbcConnection jdbcConnection;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Boolean> login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password){
        return (checkIfInDatabase(name)) ? new ResponseEntity<Boolean>( checkIfUserIsAdmin(name), HttpStatus.OK ) :  new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND );
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createUser(@RequestParam(value = "name")String name, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email){
        if (! checkIfInDatabase(name))
            addUserToDatabase(name, password, email);
    }

    private Boolean checkIfUserIsAdmin(String login) {
        return jdbcConnection.queryForObject("select pentago.is_admin(?)", Integer.class , login)>0;
    }

    private boolean checkIfInDatabase(String login){
        return jdbcConnection.queryForObject("SELECT COUNT(*) from pentago.users where Login = ?", Integer.class , login) > 0;
    }

    private void addUserToDatabase(String login, String password, String email){
        jdbcConnection.update("insert into pentago.users(login, password, email) values (?, ?, ?)", login, password, email);
    }
}

package com.mgf.service;

import com.mgf.database.JdbcConnection;
import com.mgf.email.ConfirmationMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Makda on 2016-02-10.
 */
@RestController
@RequestMapping(value = "/confirm")
public class ConfirmUserController {
    @Autowired
    private JdbcConnection jdbcConnection;

    @Autowired
    private ConfirmationMailService confirmationMailService;

    @RequestMapping(value = "/{hash}", method = RequestMethod.GET)
    public ResponseEntity<String> confirmation(@PathVariable(value = "hash") String hash) {
        return (checkIfHashInDatabase(hash)) ? new ResponseEntity<String>("No brawo, udało się:)", HttpStatus.OK) : new ResponseEntity<String>("no sorka", HttpStatus.BAD_GATEWAY);
    }

    private Boolean checkIfHashInDatabase(String hash) {
        return jdbcConnection.queryForObject("select pentago.activate_user(?)", Integer.class , hash)>0;
    }
}
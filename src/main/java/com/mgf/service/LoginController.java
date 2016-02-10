package com.mgf.service;

import com.mgf.database.JdbcConnection;
import com.mgf.email.ConfirmationMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private JdbcConnection jdbcConnection;

    @Autowired
    private ConfirmationMailService confirmationMailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Boolean> login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password){
        return (checkIfActive(name, password)) ? new ResponseEntity<Boolean>( checkIfUserIsAdmin(name), HttpStatus.OK ) :  new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND );
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createUser(@RequestParam(value = "name")String name, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email)throws Exception{
        if (! checkIfInDatabase(name, password)) {
            String hash = toMD5(email);
            confirmationMailService.sendEmail(email, hash);
            addUserToDatabase(name, password, email, hash);
        }
    }

    private Boolean checkIfUserIsAdmin(String login) {
        return jdbcConnection.queryForObject("select pentago.is_admin(?)", Integer.class , login)>0;
    }

    private boolean checkIfInDatabase(String login, String password){
        return jdbcConnection.queryForObject("SELECT COUNT(*) from pentago.users where Login = ? and password = ?", Integer.class , login, password) > 0;
    }

    private boolean checkIfActive(String login,  String password){
        return jdbcConnection.queryForObject("SELECT COUNT(*) from pentago.users where Login = ? and password = ? and status = 'active'", Integer.class , login, password) > 0;
    }

    private void addUserToDatabase(String login, String password, String email, String hash){
        jdbcConnection.update("insert into pentago.users(login, password, email, hash) values (?, ?, ?, ?)", login, password, email, hash);
    }

    private String toMD5(String string) {
        try {
            string += String.valueOf(new Random().nextInt());
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(string.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

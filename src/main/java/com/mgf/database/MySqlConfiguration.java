package com.mgf.database;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
public class MySqlConfiguration extends DriverManagerDataSource {
    public MySqlConfiguration(){
        setDriverClassName("com.mysql.jdbc.Driver");
        setUrl("jdbc:mysql://localhost:3306/pentago");
        setUsername("root");
        setPassword("root");
    }
}

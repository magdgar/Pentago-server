package com.mgf.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcConnection extends JdbcTemplate{
    public JdbcConnection() {
        setDataSource(new MySqlConfiguration());
    }
}

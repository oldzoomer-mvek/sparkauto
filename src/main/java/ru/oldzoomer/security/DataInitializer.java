package ru.oldzoomer.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Check if admin exists
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE username = ?", new Object[]{"admin"}, Integer.class);
        if (count == null || count == 0) {
            String encodedPwd = passwordEncoder.encode("admin");
            jdbcTemplate.update("INSERT INTO users(username,password,enabled) VALUES(?,?,?)", "admin", encodedPwd, true);
            jdbcTemplate.update("INSERT INTO authorities(username,authority) VALUES(?,?)", "admin", "ROLE_ADMIN");
        }
    }
}



package com.example.spring.demo.projectmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/db")
public class DataBaseController {

    @Autowired
    private ApplicationContext applicationContext;


    @GetMapping("/connection-check")
    public ResponseEntity<?> checkDataBaseConnection() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM Project";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
            }
            return ResponseEntity.ok("The connection has been established successfully");
        } catch (SQLException | DataAccessException ex) {
            return ResponseEntity.badRequest().body("Failed to connect to the database" + ex.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Sorry, the request could not have been performed" + e.getMessage());

        }
    }
}

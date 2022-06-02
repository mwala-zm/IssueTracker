package com.zechariah.issuetrackerdal.dialect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DbInitConfig {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialise() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS user");
            statement.executeUpdate(
                    "CREATE TABLE UserLogin(" +
                            "id INTEGER Primary key, " +
                            "userName varchar(30) not null)"
            );
            statement.executeUpdate(
                    "INSERT INTO user " +
                            "(username) "
            );
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.exceptions.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    // реализуйте настройку соеденения с БД
    private Util() {

    }

    private static final String USER_NAME_KEY = "db.username";
    private static final String USER_PASS_KEY = "db.userpass";
    private static final String URL_KEY = "db.url";

    public static Connection getConnection() {
        Class<Driver> driverClass = Driver.class;
        try {
            return DriverManager.getConnection(PropertiesUtil.getByKey(URL_KEY),
                    PropertiesUtil.getByKey(USER_NAME_KEY),
                    PropertiesUtil.getByKey(USER_PASS_KEY));
        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
    }
}

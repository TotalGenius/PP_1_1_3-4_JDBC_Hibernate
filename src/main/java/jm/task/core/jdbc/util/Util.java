package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.exceptions.DBConnectionException;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Util {
    // реализуйте настройку соеденения с БД
    private Util() {

    }

    private static final String USER_NAME_KEY = "db.username";
    private static final String USER_PASS_KEY = "db.userpass";
    private static final String URL_KEY = "db.url";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_CONNECTION_DRIVER = "hibernate.connection.driver_class";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.connection.driver_class";
    private static final String HIBERNATE_HBM2DDL = "hibernate.hbm2ddl";

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

    public static SessionFactory getSessionFactory() {
        try {

            Properties properties = new Properties();
            properties.put(Environment.DRIVER, PropertiesUtil.getByKey(HIBERNATE_CONNECTION_DRIVER));
            properties.put(Environment.DIALECT, PropertiesUtil.getByKey(HIBERNATE_DIALECT));
            properties.put(Environment.URL, PropertiesUtil.getByKey(URL_KEY));
            properties.put(Environment.USER, PropertiesUtil.getByKey(USER_NAME_KEY));
            properties.put(Environment.PASS, PropertiesUtil.getByKey(USER_PASS_KEY));
//            properties.put(Environment.HBM2DDL_AUTO, PropertiesUtil.getByKey(HIBERNATE_HBM2DDL));
            properties.put(Environment.SHOW_SQL, PropertiesUtil.getByKey(HIBERNATE_SHOW_SQL));
            return new Configuration()
                    .addAnnotatedClass(User.class)
                    .setProperties(properties)
                    .buildSessionFactory();

        } catch (HibernateException e) {
            throw new DBConnectionException(e);
        }
    }
}

package rifqimuhammadaziz.bookinventory.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Properties properties;

//    public Properties getProperties() throws IOException {
//        properties.load(new FileInputStream("database.properties"));
//        return properties;
//    }

    private static final String DB_DRIVER_CLASS = "db.driver.class";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";


    public static Connection createConnection() throws IOException, ClassNotFoundException, SQLException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/database.properties"));
        Class.forName(properties.getProperty(DB_DRIVER_CLASS));
        Connection connection = DriverManager.getConnection(
                properties.getProperty(DB_URL),
                properties.getProperty(DB_USERNAME),
                properties.getProperty(DB_PASSWORD)
        );
        connection.setAutoCommit(false);
        return connection;
    }
}

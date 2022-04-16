package rifqimuhammadaziz.bookinventory.database;

import org.junit.Test;
import rifqimuhammadaziz.bookinventory.util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    @Test
    public void testConnection() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DatabaseConnection.createConnection();
        connection.createStatement();
    }
}

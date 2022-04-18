package rifqimuhammadaziz.bookinventory.service;

import rifqimuhammadaziz.bookinventory.model.User;
import rifqimuhammadaziz.bookinventory.util.DaoService;
import rifqimuhammadaziz.bookinventory.util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements DaoService<User> {

    String USER_NONACTIVE = "NON-ACTIVE";
    String USER_ACTIVE = "ACTIVE";

    @Override
    public List<User> findAll() throws SQLException, ClassNotFoundException, IOException {
        List<User> users = new ArrayList<>();
        String QUERY = "SELECT id, username, fullname, gender, address, phonenumber, status FROM user";
        try (Connection connection = DatabaseConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                try (ResultSet rs = ps.executeQuery()) { // result from query
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setFullName(rs.getString("fullname"));
                        user.setGender(rs.getString("gender"));
                        user.setAddress(rs.getString("address"));
                        user.setPhoneNumber(rs.getString("phonenumber"));
                        user.setStatus(rs.getString("status"));
                        users.add(user);
                    }
                }
            }
        }
        return users;
    }

    @Override
    public User findById(Integer id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<User> findByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<User> searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int addData(User user) throws SQLException, ClassNotFoundException, IOException {
        int result = 0;
        String QUERY = "INSERT INTO user(username, password, full_name, gender, address, phone_number, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.setString(4, user.getGender());
                ps.setString(5, user.getAddress());
                ps.setString(6, user.getPhoneNumber());
                ps.setString(7, USER_NONACTIVE);

                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(User user) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(User user) throws SQLException, ClassNotFoundException {
        return 0;
    }
}

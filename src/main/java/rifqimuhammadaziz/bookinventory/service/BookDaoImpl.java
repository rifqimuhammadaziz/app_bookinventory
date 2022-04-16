package rifqimuhammadaziz.bookinventory.service;

import rifqimuhammadaziz.bookinventory.model.Book;
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

public class BookDaoImpl implements DaoService<Book> {

    @Override
    public List<Book> findAll() throws SQLException, ClassNotFoundException, IOException {
        List<Book> books = new ArrayList<>();
        String QUERY = "SELECT id, username, fullname, gender, address, phonenumber, status FROM user";
        try (Connection connection = DatabaseConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                try (ResultSet rs = ps.executeQuery()) { // result from query
                    while (rs.next()) {
                        Book book = new Book();
                        book.setId(rs.getInt("id"));
                        book.setCode(rs.getString("code"));
                        book.setTitle(rs.getString("title"));
                        book.setWriter(rs.getString("writer"));
                        book.setPublisher(rs.getString("publisher"));
                        book.setIsbn(rs.getString("isbn"));
                        book.setPages(rs.getInt("isbn"));
                        book.setBuy_price(rs.getDouble("buy_price"));
                        book.setSell_price(rs.getDouble("buy_price"));
                        book.setDate_of_entry(rs.getString("date_of_entry"));
                        book.setStock(rs.getInt("stock"));
                        books.add(book);
                    }
                }
            }
        }
        return books;
    }

    @Override
    public Book findById(Integer id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Book> findByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Book> searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int addData(Book book) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int updateData(Book book) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(Book book) throws SQLException, ClassNotFoundException {
        return 0;
    }
}

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
        String QUERY = "SELECT * FROM book";
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
                        book.setPages(rs.getInt("pages"));
                        book.setBuy_price(rs.getDouble("buy_price"));
                        book.setSell_price(rs.getDouble("sell_price"));
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
    public int addData(Book book) throws SQLException, ClassNotFoundException, IOException {
        int result = 0;
        String QUERY = "INSERT INTO book(code, title, writer, publisher, isbn, pages, buy_price, sell_price, date_of_entry, stock) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setString(1, book.getCode());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getWriter());
                ps.setString(4, book.getPublisher());
                ps.setString(5, book.getIsbn());
                ps.setInt(6, book.getPages());
                ps.setDouble(7, book.getBuy_price());
                ps.setDouble(8, book.getSell_price());
                ps.setString(9, book.getDate_of_entry());
                ps.setInt(10, book.getStock());
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
    public int updateData(Book book) throws SQLException, ClassNotFoundException, IOException {
        int result = 0;
        String QUERY = "UPDATE book SET code=?, title=?, writer=?, publisher=?, isbn=?, pages=?, buy_price=?, sell_price=?, date_of_entry=?, stock=? WHERE id=?";
        try (Connection connection = DatabaseConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)){
                ps.setString(1, book.getCode());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getWriter());
                ps.setString(4, book.getPublisher());
                ps.setString(5, book.getIsbn());
                ps.setInt(6, book.getPages());
                ps.setDouble(7, book.getBuy_price());
                ps.setDouble(8, book.getSell_price());
                ps.setString(9, book.getDate_of_entry());
                ps.setInt(10, book.getStock());
                ps.setInt(11, book.getId());
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
    public int deleteData(Book book) throws SQLException, IOException, ClassNotFoundException {
        int result = 0;
        String QUERY = "DELETE FROM book WHERE id = ?";
        try (Connection connection = DatabaseConnection.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
                ps.setInt(1, book.getId());
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
}

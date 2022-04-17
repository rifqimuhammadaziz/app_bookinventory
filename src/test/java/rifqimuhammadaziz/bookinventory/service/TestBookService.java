package rifqimuhammadaziz.bookinventory.service;

import com.github.javafaker.Faker;
import org.junit.Test;
import rifqimuhammadaziz.bookinventory.model.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TestBookService {

    public BookDaoImpl bookDao;
    private List<Book> books;

    @Test
    public void testFindAll() throws SQLException, ClassNotFoundException, IOException {
        Book book = new Book();
        bookDao = new BookDaoImpl();

        Faker faker = new Faker();
        String code = faker.code().asin();
        String title = faker.book().title();
        String writer = faker.book().author();
        String publisher = faker.book().publisher();
        String isbn = faker.code().isbn10();
        Integer pages = faker.number().randomDigit();
        Double buy_price = faker.number().randomDouble(3, 2, 10);
        Double sell_price = faker.number().randomDouble(3, 2, 10);
        String date_of_entry = "4/16/2022";
        Integer stock = faker.number().randomDigit();

        book.setCode(code);
        book.setTitle(title);
        book.setWriter(writer);
        book.setPublisher(publisher);
        book.setIsbn(isbn);
        book.setPages(pages);
        book.setBuy_price(buy_price);
        book.setSell_price(sell_price);
        book.setDate_of_entry(date_of_entry);
        book.setStock(stock);

        System.out.println(book);
        bookDao.addData(book);
    }
}

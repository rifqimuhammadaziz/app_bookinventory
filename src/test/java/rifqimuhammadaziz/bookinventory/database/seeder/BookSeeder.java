package rifqimuhammadaziz.bookinventory.database.seeder;

import com.github.javafaker.Faker;
import org.junit.Test;
import rifqimuhammadaziz.bookinventory.model.Book;
import rifqimuhammadaziz.bookinventory.service.BookDaoImpl;

import java.io.IOException;
import java.sql.SQLException;

public class BookSeeder {

    public BookDaoImpl bookDao;

    @Test
    public void BookSeeder() throws SQLException, IOException, ClassNotFoundException {
        Book book = new Book();
        bookDao = new BookDaoImpl();

        for (int i = 0; i < 100; i++) {
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
}

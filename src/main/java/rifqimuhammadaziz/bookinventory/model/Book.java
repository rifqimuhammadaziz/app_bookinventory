package rifqimuhammadaziz.bookinventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    private Integer id;
    private String code;
    private String title;
    private String writer;
    private String publisher;
    private String isbn;
    private Integer pages;
    private Double buy_price;
    private Double sell_price;
    private String date_of_entry;
    private Integer stock;

}

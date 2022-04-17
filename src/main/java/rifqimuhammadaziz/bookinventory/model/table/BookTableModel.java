package rifqimuhammadaziz.bookinventory.model.table;

import rifqimuhammadaziz.bookinventory.model.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {

    private List<Book> books;
    private final String[] COLUMNS = {
            "ID",
            "CODE",
            "TITLE",
            "WRITER",
            "PUBLISHER",
            "ISBN",
            "PAGES",
            "BUY PRICE",
            "SELL PRICE",
            "DATE OF ENTRY",
            "STOCK"
    };

    public BookTableModel(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> books.get(rowIndex).getId();
            case 1 -> books.get(rowIndex).getCode();
            case 2 -> books.get(rowIndex).getTitle();
            case 3 -> books.get(rowIndex).getWriter();
            case 4 -> books.get(rowIndex).getPublisher();
            case 5 -> books.get(rowIndex).getIsbn();
            case 6 -> books.get(rowIndex).getPages();
            case 7 -> books.get(rowIndex).getBuy_price();
            case 8 -> books.get(rowIndex).getSell_price();
            case 9 -> books.get(rowIndex).getDate_of_entry();
            case 10 -> books.get(rowIndex).getStock();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        }
        return Object.class;
    }


}

package rifqimuhammadaziz.bookinventory.view.book;

import com.formdev.flatlaf.FlatLightLaf;
import rifqimuhammadaziz.bookinventory.model.Book;
import rifqimuhammadaziz.bookinventory.model.table.BookTableModel;
import rifqimuhammadaziz.bookinventory.service.BookDaoImpl;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainForm extends JFrame{
    private JButton loginButton;
    private JTextField txtID;
    private JTextField txtBookCode;
    private JTextField txtTitle;
    private JTextField txtWriter;
    private JTextField txtPublisher;
    private JPanel rootPanel;
    private JTextField txtISBN;
    private JTable tableBook;
    private JTextField txtBuyPrice;
    private JTextField txtSellPrice;
    private JTextField txtDateOfEntry;
    private JTextField txtStock;
    private JButton btnAddNew;
    private JButton btnSave;
    private JButton btnReset;
    private JButton btnUpdate;

    private BookDaoImpl bookDao;
    private List<Book> books;
    private BookTableModel bookTableModel;

    public MainForm() {
        books = new ArrayList<>();
        bookDao = new BookDaoImpl();

        // Fetch All Data
        try {
            books.addAll(bookDao.findAll());
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Get Book Data & Add to Table
        bookTableModel = new BookTableModel(books);
        tableBook.setModel(bookTableModel);
        tableBook.setAutoCreateRowSorter(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        UIManager.put( "Button.arc", 999 );
        UIManager.put( "Component.arc", 999 );
        UIManager.put( "ProgressBar.arc", 999 );
        UIManager.put( "TextComponent.arc", 999 );
        UIManager.put( "Component.focusWidth", 1 );
        UIManager.put( "Table.showHorizontalLines", true);

        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void beforeLogin() {
        txtID.setEditable(false);
        txtID.setEnabled(false);

        txtBookCode.setEditable(false);
        txtBookCode.setEnabled(false);

        txtTitle.setEditable(false);
        txtTitle.setEnabled(false);

        txtWriter.setEditable(false);
        txtWriter.setEnabled(false);

        txtPublisher.setEditable(false);
        txtPublisher.setEnabled(false);

        txtISBN.setEditable(false);
        txtISBN.setEnabled(false);

        txtBuyPrice.setEditable(false);
        txtBuyPrice.setEnabled(false);

        txtSellPrice.setEditable(false);
        txtSellPrice.setEnabled(false);

        txtDateOfEntry.setEditable(false);
        txtDateOfEntry.setEnabled(false);

        txtStock.setEditable(false);
        txtStock.setEnabled(false);

        btnAddNew.setEnabled(false);
        btnSave.setEnabled(false);
        btnReset.setEnabled(false);
        btnUpdate.setEnabled(false);

        tableBook.setEnabled(false);
        tableBook.setVisible(false);
    }
}

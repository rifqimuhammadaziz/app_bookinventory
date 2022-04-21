package rifqimuhammadaziz.bookinventory.view.book;

import com.formdev.flatlaf.FlatLightLaf;
import rifqimuhammadaziz.bookinventory.model.Book;
import rifqimuhammadaziz.bookinventory.model.table.BookTableModel;
import rifqimuhammadaziz.bookinventory.service.BookDaoImpl;
import rifqimuhammadaziz.bookinventory.view.user.LoginForm;

import javax.swing.*;
import java.awt.event.*;
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
    private JButton btnEdit;
    private JLabel lblUsername;
    private JTextField txtPages;

    private BookDaoImpl bookDao;
    private List<Book> books;
    private BookTableModel bookTableModel;
    private Book selectedBook;

    public MainForm() {
        prepareForm();

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

        // RootPanel Click
        rootPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tableBook.getSelectionModel().clearSelection();
                prepareForm();
            }
        });

        // Select Row Table
        tableBook.getSelectionModel().addListSelectionListener(e -> {
            if (!tableBook.getSelectionModel().isSelectionEmpty()) {
                int selectedIndex = tableBook.convertRowIndexToModel(tableBook.getSelectedRow());
                selectedBook = books.get(selectedIndex);
                if (selectedBook != null) {
                    prepareButtonForEdit();

                    txtID.setText(String.valueOf(selectedBook.getId()));
                    txtBookCode.setText(selectedBook.getCode());
                    txtTitle.setText(selectedBook.getTitle());
                    txtWriter.setText(selectedBook.getWriter());
                    txtPublisher.setText(selectedBook.getPublisher());
                    txtISBN.setText(selectedBook.getIsbn());
                    txtPages.setText(String.valueOf(selectedBook.getPages()));
                    txtBuyPrice.setText(String.valueOf(selectedBook.getBuy_price()));
                    txtSellPrice.setText(String.valueOf(selectedBook.getSell_price()));
                    txtDateOfEntry.setText(selectedBook.getDate_of_entry());
                    txtStock.setText(String.valueOf(selectedBook.getStock()));
                }
            }
        });

        // Button Login
        loginButton.addActionListener(e -> {
            LoginForm loginForm = new LoginForm();
            loginForm.pack();
            loginForm.setLocationRelativeTo(null);
        });

        // Button Add New | Cancel
        btnAddNew.addActionListener(e -> {
            if (btnAddNew.getText().equals("Add New")) {
                prepareButtonForAdd();
                prepareTexfield();
            } else {
                disableTexfield();
            }
        });

        // Button Save | Update
        btnSave.addActionListener(e -> {
            if (btnSave.getText().equals("Save")) {
                addData();
            } else if (btnSave.getText().equals("Update")) {
                updateData();
                prepareForm();
            }
        });

        // Button Reset
        btnReset.addActionListener(e -> {
            if (btnReset.getText().equals("Reset")) {
                resetTextField();
            } else if (btnReset.getText().equals("Delete")) {
                JOptionPane.showMessageDialog(this, "Ini Tombol Delete");
            }
        });

        // Button Edit
        btnEdit.addActionListener(e -> {
            if (btnEdit.getText().equals("Edit")) {
                prepareTexfield();
                prepareButtonForUpdate();
            } else if (btnEdit.getText().equals("Cancel")) {
                prepareForm();
            }
        });
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

    public void prepareForm() {
        btnAddNew.setText("Add New");
        btnSave.setText("Save");
        btnReset.setText("Reset");
        btnEdit.setText("Edit");

        btnAddNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnReset.setEnabled(false);
        btnEdit.setEnabled(false);

        resetTextField();
        disableTexfield();

        tableBook.setEnabled(true);
        tableBook.setVisible(true);
    }

    public void prepareButtonForAdd() {
        resetTextField();

        btnAddNew.setText("Cancel");
        btnSave.setEnabled(true);
        btnReset.setEnabled(true);
        tableBook.setEnabled(false);
    }

    public void prepareButtonForEdit() {
        btnAddNew.setEnabled(false);

        btnReset.setText("Delete");
        btnReset.setEnabled(true);

        btnEdit.setText("Edit");
        btnEdit.setEnabled(true);
    }

    public void prepareButtonForUpdate() {
        btnSave.setText("Update");
        btnSave.setEnabled(true);

        btnEdit.setText("Cancel");
        btnEdit.setEnabled(true);

        btnReset.setText("Delete");
        btnReset.setEnabled(true);
    }

    public void prepareTexfield() {
        txtBookCode.grabFocus();
        txtBookCode.setEditable(true);
        txtBookCode.setEnabled(true);

        txtTitle.setEditable(true);
        txtTitle.setEnabled(true);

        txtWriter.setEditable(true);
        txtWriter.setEnabled(true);

        txtPublisher.setEditable(true);
        txtPublisher.setEnabled(true);

        txtISBN.setEditable(true);
        txtISBN.setEnabled(true);

        txtPages.setEditable(true);
        txtPages.setEnabled(true);

        txtBuyPrice.setEditable(true);
        txtBuyPrice.setEnabled(true);

        txtSellPrice.setEditable(true);
        txtSellPrice.setEnabled(true);

        txtDateOfEntry.setEditable(true);
        txtDateOfEntry.setEnabled(true);

        txtStock.setEditable(true);
        txtStock.setEnabled(true);
    }

    public void addData() {
        if (    txtBookCode.getText().trim().isEmpty() ||
                txtTitle.getText().trim().isEmpty() ||
                txtWriter.getText().trim().isEmpty() ||
                txtPublisher.getText().trim().isEmpty() ||
                txtISBN.getText().trim().isEmpty() ||
                txtBuyPrice.getText().trim().isEmpty() ||
                txtSellPrice.getText().trim().isEmpty() ||
                txtDateOfEntry.getText().trim().isEmpty() ||
                txtStock.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill form correctly!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Book book = new Book();
            book.setCode(txtBookCode.getText());
            book.setTitle(txtTitle.getText());
            book.setWriter(txtWriter.getText());
            book.setPublisher(txtPublisher.getText());
            book.setIsbn(txtISBN.getText());
            book.setPages(Integer.valueOf(txtPages.getText()));
            book.setBuy_price(Double.valueOf(txtBuyPrice.getText()));
            book.setSell_price(Double.valueOf(txtSellPrice.getText()));
            book.setDate_of_entry(txtDateOfEntry.getText());
            book.setStock(Integer.valueOf(txtStock.getText()));

            try {
                if (bookDao.addData(book) == 1) {
                    books.clear();
                    books.addAll(bookDao.findAll());
                    bookTableModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(this, "Success Add Book : " + txtTitle.getText(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    resetTextField();
                    disableTexfield();
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateData() {
        if (    txtBookCode.getText().trim().isEmpty() ||
                txtTitle.getText().trim().isEmpty() ||
                txtWriter.getText().trim().isEmpty() ||
                txtPublisher.getText().trim().isEmpty() ||
                txtISBN.getText().trim().isEmpty() ||
                txtBuyPrice.getText().trim().isEmpty() ||
                txtSellPrice.getText().trim().isEmpty() ||
                txtDateOfEntry.getText().trim().isEmpty() ||
                txtStock.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill form correctly!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            selectedBook.setCode(txtBookCode.getText());
            selectedBook.setTitle(txtTitle.getText());
            selectedBook.setWriter(txtWriter.getText());
            selectedBook.setPublisher(txtPublisher.getText());
            selectedBook.setIsbn(txtISBN.getText());
            selectedBook.setPages(Integer.valueOf(txtPages.getText()));
            selectedBook.setBuy_price(Double.valueOf(txtBuyPrice.getText()));
            selectedBook.setSell_price(Double.valueOf(txtSellPrice.getText()));
            selectedBook.setDate_of_entry(txtDateOfEntry.getText());
            selectedBook.setStock(Integer.valueOf(txtStock.getText()));
            try {
                int validate = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure to Update Book : " + txtBookCode.getText(),
                        "Update Book",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (validate == JOptionPane.YES_OPTION) {
                    if (bookDao.updateData(selectedBook) == 1) {
                        books.clear();
                        books.addAll(bookDao.findAll());
                        bookTableModel.fireTableDataChanged();
                        JOptionPane.showMessageDialog(
                                this,
                                "Book " + txtBookCode.getText() + " updated.",
                                "Update Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void disableTexfield() {
        btnAddNew.setText("Add New");
        btnSave.setEnabled(false);
        btnReset.setEnabled(false);
        tableBook.setEnabled(true);

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

        txtPages.setEditable(false);
        txtPages.setEnabled(false);

        txtBuyPrice.setEditable(false);
        txtBuyPrice.setEnabled(false);

        txtSellPrice.setEditable(false);
        txtSellPrice.setEnabled(false);

        txtDateOfEntry.setEditable(false);
        txtDateOfEntry.setEnabled(false);

        txtStock.setEditable(false);
        txtStock.setEnabled(false);
    }

    public void resetTextField() {
        txtID.setText("");
        txtBookCode.grabFocus();
        txtBookCode.setText("");
        txtTitle.setText("");
        txtWriter.setText("");
        txtPublisher.setText("");
        txtISBN.setText("");
        txtPages.setText("");
        txtBuyPrice.setText("");
        txtSellPrice.setText("");
        txtDateOfEntry.setText("");
        txtStock.setText("");
    }
}

package rifqimuhammadaziz.bookinventory.view.book;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class MainForm {
    private JButton loginButton;
    private JTextField txtID;
    private JTextField txtBookCode;
    private JTextField txtTitle;
    private JTextField txtWriter;
    private JTextField txtPublisher;
    private JPanel rootPanel;
    private JTextField txtISBN;
    private JTable table1;
    private JTextField txtBuyPrice;
    private JTextField txtSellPrice;
    private JTextField txtDateOfEntry;
    private JTextField txtStock;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnReset;
    private JButton btnUpdate;

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
        frame.setVisible(true);
    }
}

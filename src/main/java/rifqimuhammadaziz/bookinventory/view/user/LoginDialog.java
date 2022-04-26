package rifqimuhammadaziz.bookinventory.view.user;

import com.sun.tools.javac.Main;
import rifqimuhammadaziz.bookinventory.model.User;
import rifqimuhammadaziz.bookinventory.service.UserDaoImpl;
import rifqimuhammadaziz.bookinventory.view.book.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class LoginDialog extends JDialog {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel rootPanel;

    private UserDaoImpl userDao;

    public LoginDialog() {
        setContentPane(rootPanel);
        setVisible(true);

        userDao = new UserDaoImpl();

        loginButton.addActionListener(e -> {
            if (txtUsername.getText().isEmpty() || String.valueOf(txtPassword.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill form correctly!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                User user = new User();
                user.setUsername(txtUsername.getText());
                user.setPassword(String.valueOf(txtPassword.getPassword()));
                try {
                    if (userDao.loginUser(user) == 1) {
                        JOptionPane.showMessageDialog(this, "Login as " + txtUsername.getText() + " Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new MainForm(user);
                    }
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginDialog");
        frame.setContentPane(new LoginDialog().rootPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

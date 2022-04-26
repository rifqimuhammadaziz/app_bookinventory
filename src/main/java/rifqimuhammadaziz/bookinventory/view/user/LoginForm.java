package rifqimuhammadaziz.bookinventory.view.user;

import com.sun.tools.javac.Main;
import rifqimuhammadaziz.bookinventory.model.User;
import rifqimuhammadaziz.bookinventory.service.UserDaoImpl;
import rifqimuhammadaziz.bookinventory.view.book.MainForm;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginForm extends JFrame{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel rootPanel;

    private UserDaoImpl userDao;

    public LoginForm() {
        setContentPane(rootPanel);
        setVisible(true);

        userDao = new UserDaoImpl();

        // Button Login
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
                        dispose();
                        // Send User to MainForm
                    }
                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Button Register
        registerButton.addActionListener(e -> {
            RegisterForm registerForm = new RegisterForm();
            registerForm.pack();
            registerForm.setLocationRelativeTo(null);
            dispose();
        });


    }
}

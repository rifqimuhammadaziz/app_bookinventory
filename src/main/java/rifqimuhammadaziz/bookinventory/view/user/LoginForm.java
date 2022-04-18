package rifqimuhammadaziz.bookinventory.view.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel rootPanel;

    public LoginForm() {
        setContentPane(rootPanel);
        setVisible(true);

        registerButton.addActionListener(e -> {
            RegisterForm registerForm = new RegisterForm();
            registerForm.pack();
            registerForm.setLocationRelativeTo(null);
            dispose();
        });
    }
}

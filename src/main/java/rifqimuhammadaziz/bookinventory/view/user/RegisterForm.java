package rifqimuhammadaziz.bookinventory.view.user;

import rifqimuhammadaziz.bookinventory.model.User;
import rifqimuhammadaziz.bookinventory.service.UserDaoImpl;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterForm extends JFrame{
    private JTextField txtUsername;
    private JPanel rootPanel;
    private JTextField txtFullName;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextArea txtAddress;
    private JTextField txtPhoneNumber;
    private JPanel LoginIdentity;
    private JButton btnRegister;
    private JButton btnReset;
    private JPasswordField txtPassword;
    private JPasswordField txtRetypePassword;

    private UserDaoImpl userDao;
    private List<User> users;

    public RegisterForm() {
        setContentPane(rootPanel);
        setVisible(true);

        userDao = new UserDaoImpl();
        users = new ArrayList<>();

        prepareRegisterForm();

        btnRegister.addActionListener(e -> {
            if (    txtUsername.getText().trim().isEmpty() ||
                    txtFullName.getText().trim().isEmpty() ||
                    txtAddress.getText().trim().isEmpty() ||
                    txtPhoneNumber.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Fill Form Correctly", "Form Validation", JOptionPane.ERROR_MESSAGE);
            } else {
                User user = new User();
                user.setUsername(txtUsername.getText().trim());
                user.setFullName(txtFullName.getText().trim());

                if (maleRadioButton.isSelected()) {
                    user.setGender("Male");
                } else if (femaleRadioButton.isSelected()) {
                    user.setGender("Female");
                }
                user.setAddress(txtAddress.getText().trim());
                user.setPhoneNumber(txtPhoneNumber.getText().trim());

                if (!String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtRetypePassword.getPassword()))) {
                    JOptionPane.showMessageDialog(this, "Retype Password is not match!", "Password Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    user.setPassword(String.valueOf(txtPassword.getPassword()));
                    try {
                        if (userDao.addData(user) == 1) {
                            users.clear();
                            JOptionPane.showMessageDialog(this, "Success Add Data : " + txtFullName.getText(), "Success", JOptionPane.INFORMATION_MESSAGE);
                            prepareRegisterForm();
                            dispose();
                        }
                    } catch (SQLException | ClassNotFoundException | IOException ex) {
                        JOptionPane.showMessageDialog(this, "Failed to Add Data \nUsername " + txtUsername.getText() + " already Registered", "Register Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void prepareRegisterForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtRetypePassword.setText("");
        txtFullName.setText("");
        maleRadioButton.setSelected(true);
        txtAddress.setText("");
        txtPhoneNumber.setText("");
    }
}

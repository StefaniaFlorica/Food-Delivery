package presentation.views;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {

    private final JPanel contentPane;
    private final JPasswordField confirmPassField;
    private final JTextField usernameField;
    private final JLabel lblEmail;
    private final JButton btnCreate;
    private final JPasswordField passwordField;

    public RegisterView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(100, 100, 388, 250);
        setTitle("Register");
        contentPane = new JPanel();
        contentPane.setBackground(Color.PINK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 58, 76, 19);
        contentPane.add(lblNewLabel);

        JLabel lblPassword = new JLabel("Confirm Password");
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setFont(new Font("Century Gothic", Font.BOLD, 14));
        lblPassword.setBounds(10, 140, 125, 19);
        contentPane.add(lblPassword);

        confirmPassField = new JPasswordField();
        confirmPassField.setBackground(new Color(255, 204, 204));
        confirmPassField.setBounds(189, 137, 175, 29);
        contentPane.add(confirmPassField);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(255, 228, 225));
        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        usernameField.setBounds(189, 54, 175, 29);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        btnCreate = new JButton("Create Account");
        btnCreate.setForeground(new Color(255, 255, 255));
        btnCreate.setBackground(new Color(204, 102, 102));
        btnCreate.setFont(new Font("Century Gothic", Font.BOLD, 16));
        btnCreate.setBounds(100, 176, 175, 29);
        contentPane.add(btnCreate);

        lblEmail = new JLabel("Password");
        lblEmail.setForeground(new Color(255, 255, 255));
        lblEmail.setBackground(new Color(255, 255, 255));
        lblEmail.setFont(new Font("Century Gothic", Font.BOLD, 14));
        lblEmail.setBounds(10, 101, 76, 19);
        contentPane.add(lblEmail);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(255, 204, 204));
        passwordField.setBounds(189, 98, 175, 29);
        contentPane.add(passwordField);

        JLabel lblNewLabel_1 = new JLabel("____________Create new account____________");
        lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(10, 10, 354, 19);
        contentPane.add(lblNewLabel_1);
        this.setVisible(true);
    }

    public String getConfirmPassField() {
        return String.valueOf(confirmPassField.getPassword());
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public void addCreateListener(ActionListener action)
    {
        btnCreate.addActionListener(action);

    }

    public void resetPasswords() {
        confirmPassField.setText("");
        passwordField.setText("");
    }

}


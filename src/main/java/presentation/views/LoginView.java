package presentation.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class LoginView extends JFrame {

    private final JPanel contentPane;
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JButton btnRegisterNow;
    private final JButton btnLogin;

    public LoginView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(100, 100, 420, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUser = new JLabel("Username");
        lblUser.setHorizontalAlignment(SwingConstants.CENTER);
        lblUser.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        lblUser.setBounds(70, 105, 85, 25);
        contentPane.add(lblUser);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        lblPassword.setBounds(70, 160, 85, 25);
        contentPane.add(lblPassword);

        textField = new JTextField();
        textField.setBounds(190, 106, 150, 25);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(190, 161, 150, 25);
        contentPane.add(passwordField);

        btnLogin = new JButton("Log in");
        btnLogin.setFont(new Font("Century Gothic", Font.BOLD, 14));
        btnLogin.setBounds(130, 235, 150, 35);
        contentPane.add(btnLogin);

        btnRegisterNow = new JButton("Register Now");
        btnRegisterNow.setFont(new Font("Century Gothic", Font.BOLD, 14));
        btnRegisterNow.setBounds(240, 318, 130, 35);
        contentPane.add(btnRegisterNow);

        JLabel lblNoAcc = new JLabel("Don't have an account yet?");
        lblNoAcc.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNoAcc.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        lblNoAcc.setBounds(48, 324, 187, 25);
        contentPane.add(lblNoAcc);

        JLabel lblWelcome = new JLabel("WELCOME :)");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Consolas", Font.BOLD, 20));
        lblWelcome.setBounds(130, 40, 165, 25);
        contentPane.add(lblWelcome);
        setVisible(true);
    }

    public String getUserField() {
        return textField.getText();
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener actionListener)
    {
        btnLogin.addActionListener(actionListener);
    }

    public void addRegisterListener(ActionListener actionListener)
    {
        btnRegisterNow.addActionListener(actionListener);
    }
}

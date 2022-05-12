package presentation.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class AdminPasswordView extends JFrame {

    private final JPanel contentPane;
    private final JPasswordField passwordField;
    private final JButton btnNewButton;

    public AdminPasswordView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(100, 100, 455, 303);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("___________Enter admin password___________");
        lblNewLabel.setForeground(new Color(32, 178, 170));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 10, 421, 42);
        contentPane.add(lblNewLabel);

        btnNewButton = new JButton("Log in");
        btnNewButton.setForeground(new Color(255, 250, 250));
        btnNewButton.setBackground(new Color(32, 178, 170));
        btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        btnNewButton.setBounds(118, 166, 192, 42);
        contentPane.add(btnNewButton);

        passwordField = new JPasswordField();
        passwordField.setForeground(new Color(32, 178, 170));
        passwordField.setBackground(new Color(255, 250, 250));
        passwordField.setBounds(118, 87, 192, 42);
        contentPane.add(passwordField);
        setVisible(true);
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public void addEnterListener(ActionListener actionListener)
    {
        btnNewButton.addActionListener(actionListener);
    }
}

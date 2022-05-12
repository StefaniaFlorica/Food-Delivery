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

public class NameFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JButton btnNewButton;

    public NameFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(100, 100, 455, 303);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("_______________Enter name_______________");
        lblNewLabel.setForeground(new Color(32, 178, 170));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 10, 421, 42);
        contentPane.add(lblNewLabel);

        btnNewButton = new JButton("Insert");
        btnNewButton.setForeground(new Color(255, 250, 250));
        btnNewButton.setBackground(new Color(32, 178, 170));
        btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 16));
        btnNewButton.setBounds(118, 166, 192, 42);
        contentPane.add(btnNewButton);

        textField = new JTextField();
        textField.setBounds(118, 102, 192, 42);
        contentPane.add(textField);
        textField.setColumns(10);

        setVisible(true);
    }

    public String getTextField() {
        return textField.getText();
    }

    public void addInsertListener(ActionListener actionListener)
    {
        btnNewButton.addActionListener(actionListener);
    }
}
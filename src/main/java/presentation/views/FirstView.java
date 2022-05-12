package presentation.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FirstView extends JFrame {

    private final JPanel contentPane;
    private final JComboBox comboBox;
    private final JButton goBtn;
    public FirstView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(250, 240, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Hello, I'm a");
        lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        lblNewLabel.setBounds(110, 100, 93, 38);
        contentPane.add(lblNewLabel);

        String[] options= {"administrator","client"};
        comboBox = new JComboBox(options);
        comboBox.setBackground(new Color(255, 239, 213));
        comboBox.setBounds(193, 111, 133, 21);
        contentPane.add(comboBox);

        goBtn = new JButton("GO!");
        goBtn.setBackground(new Color(255, 160, 122));
        goBtn.setBounds(180, 176, 85, 33);
        goBtn.setFocusPainted(false);
        contentPane.add(goBtn);
        setVisible(true);
    }

    public int getSelectedOption()
    {
        return comboBox.getSelectedIndex();
    }

    public void addGoListener(ActionListener action)
    {
        goBtn.addActionListener(action);
    }
}

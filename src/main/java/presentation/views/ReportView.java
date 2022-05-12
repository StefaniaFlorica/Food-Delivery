package presentation.views;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

public class ReportView extends JFrame {

    private final JPanel contentPane;
    private final JTextField textField11;
    private final JTextField textField12;
    private final JTextField textField2;
    private final JTextField textField31;
    private final JTextField textField32;
    private final JTextField textField41;
    private final JTextField textField42;
    private final JTextField textField43;
    private final JButton btnGen1;
    private final JButton btnGen2;
    private final JButton btnGen3;
    private final JButton btnGen4;

    public ReportView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 520, 320);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblReport1 = new JLabel("Report 1");
        lblReport1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblReport1.setBounds(30, 65, 75, 25);
        contentPane.add(lblReport1);

        JLabel lblReport2 = new JLabel("Report 2");
        lblReport2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblReport2.setBounds(30, 120, 75, 25);
        contentPane.add(lblReport2);

        textField11 = new JTextField();
        textField11.setBounds(130, 70, 75, 20);
        contentPane.add(textField11);
        textField11.setColumns(10);

        textField12 = new JTextField();
        textField12.setColumns(10);
        textField12.setBounds(215, 70, 75, 20);
        contentPane.add(textField12);

        JLabel lblTMin = new JLabel("t min");
        lblTMin.setHorizontalAlignment(SwingConstants.CENTER);
        lblTMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTMin.setBounds(130, 45, 75, 25);
        contentPane.add(lblTMin);

        JLabel lblTMax = new JLabel("t max");
        lblTMax.setHorizontalAlignment(SwingConstants.CENTER);
        lblTMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTMax.setBounds(215, 45, 75, 25);
        contentPane.add(lblTMax);

        textField2 = new JTextField();
        textField2.setColumns(10);
        textField2.setBounds(130, 125, 75, 20);
        contentPane.add(textField2);

        JLabel lblNrMin = new JLabel("nr min");
        lblNrMin.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrMin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrMin.setBounds(130, 100, 75, 25);
        contentPane.add(lblNrMin);

        JLabel lblReport3 = new JLabel("Report 3");
        lblReport3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblReport3.setBounds(30, 175, 75, 25);
        contentPane.add(lblReport3);

        textField31 = new JTextField();
        textField31.setColumns(10);
        textField31.setBounds(130, 180, 75, 20);
        contentPane.add(textField31);

        JLabel lblNrMin_1 = new JLabel("nr min");
        lblNrMin_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrMin_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrMin_1.setBounds(130, 155, 75, 25);
        contentPane.add(lblNrMin_1);

        JLabel lblNrMin_1_1 = new JLabel("price min");
        lblNrMin_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrMin_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrMin_1_1.setBounds(215, 155, 75, 25);
        contentPane.add(lblNrMin_1_1);

        textField32 = new JTextField();
        textField32.setColumns(10);
        textField32.setBounds(215, 180, 75, 20);
        contentPane.add(textField32);

        JLabel lblReport4 = new JLabel("Report 4");
        lblReport4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblReport4.setBounds(30, 230, 75, 25);
        contentPane.add(lblReport4);

        textField41 = new JTextField();
        textField41.setColumns(10);
        textField41.setBounds(130, 235, 75, 20);
        contentPane.add(textField41);

        textField42 = new JTextField();
        textField42.setColumns(10);
        textField42.setBounds(215, 235, 75, 20);
        contentPane.add(textField42);

        textField43 = new JTextField();
        textField43.setColumns(10);
        textField43.setBounds(300, 235, 75, 20);
        contentPane.add(textField43);

        JLabel lblNrMin_1_2 = new JLabel("day");
        lblNrMin_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrMin_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrMin_1_2.setBounds(130, 210, 75, 25);
        contentPane.add(lblNrMin_1_2);

        JLabel lblNrMin_1_3 = new JLabel("month");
        lblNrMin_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrMin_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrMin_1_3.setBounds(215, 210, 75, 25);
        contentPane.add(lblNrMin_1_3);

        JLabel lblNrMin_1_4 = new JLabel("year");
        lblNrMin_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNrMin_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNrMin_1_4.setBounds(300, 210, 75, 25);
        contentPane.add(lblNrMin_1_4);

        btnGen1 = new JButton("Generate ");
        btnGen1.setBackground(new Color(240, 248, 255));
        btnGen1.setBounds(381, 65, 115, 25);
        contentPane.add(btnGen1);

        btnGen2 = new JButton("Generate");
        btnGen2.setBackground(new Color(240, 255, 255));
        btnGen2.setBounds(381, 120, 115, 25);
        contentPane.add(btnGen2);

        btnGen3 = new JButton("Generate ");
        btnGen3.setBackground(new Color(240, 255, 255));
        btnGen3.setBounds(381, 175, 115, 25);
        contentPane.add(btnGen3);

        btnGen4 = new JButton("Generate ");
        btnGen4.setBackground(new Color(240, 255, 255));
        btnGen4.setBounds(381, 234, 115, 25);
        contentPane.add(btnGen4);

        JLabel lblNewLabel = new JLabel("REPORTS");
        lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(130, 10, 254, 25);
        contentPane.add(lblNewLabel);
        setVisible(true);
    }

    public int getTextField11() {
        return Integer.parseInt(textField11.getText());
    }

    public int getTextField12() {
        return Integer.parseInt(textField12.getText());
    }

    public int getTextField2() {
        return Integer.parseInt(textField2.getText());
    }

    public int getTextField31() {
        return Integer.parseInt(textField31.getText());
    }

    public int getTextField32() {
        return Integer.parseInt(textField32.getText());
    }

    public int getTextField41() {
        return Integer.parseInt(textField41.getText());
    }

    public int getTextField42() {
        return Integer.parseInt(textField42.getText());
    }

    public int getTextField43() {
        return Integer.parseInt(textField43.getText());
    }

    public void addGen1Listener(ActionListener action)
    {
        btnGen1.addActionListener(action);
    }
    public void addGen2Listener(ActionListener action)
    {
        btnGen2.addActionListener(action);
    }
    public void addGen3Listener(ActionListener action)
    {
        btnGen3.addActionListener(action);
    }
    public void addGen4Listener(ActionListener action)
    {
        btnGen4.addActionListener(action);
    }

}



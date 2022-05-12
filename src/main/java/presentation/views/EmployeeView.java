package presentation.views;

import bll.Observer;
import model.Order;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EmployeeView extends JFrame implements Observer{

    private final JPanel contentPane;
    private final JTextArea txtrMon;
    public EmployeeView() {
        setTitle("Employee Menu");
        setBounds(100, 100, 540, 444);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 102, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("___________________Orders___________________");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
        lblNewLabel.setBounds(10, 10, 506, 45);
        contentPane.add(lblNewLabel);

        txtrMon = new JTextArea();
        txtrMon.setFont(new Font("Century Gothic", Font.BOLD, 14));
        txtrMon.setForeground(Color.WHITE);
        txtrMon.setBackground(new Color(204, 102, 255));
        txtrMon.setBounds(20, 87, 496, 316);
        txtrMon.setEditable(false);
        contentPane.add(txtrMon);

        JLabel lblID = new JLabel("ID");
        lblID.setForeground(Color.WHITE);
        lblID.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
        lblID.setBackground(new Color(255, 255, 204));
        lblID.setBounds(10, 65, 50, 23);
        contentPane.add(lblID);

        JLabel lblClId = new JLabel("Client ID");
        lblClId.setForeground(Color.WHITE);
        lblClId.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
        lblClId.setBackground(new Color(255, 255, 204));
        lblClId.setBounds(97, 64, 98, 23);
        contentPane.add(lblClId);

        JLabel lblDate = new JLabel("Date");
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
        lblDate.setBackground(new Color(255, 255, 204));
        lblDate.setBounds(278, 64, 57, 23);
        contentPane.add(lblDate);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setForeground(Color.WHITE);
        lblPrice.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
        lblPrice.setBackground(new Color(255, 255, 204));
        lblPrice.setBounds(447, 65, 69, 23);
        contentPane.add(lblPrice);
    }

    public String getTextArea() {
        return txtrMon.getText();
    }

    @Override
    public void update(Object obj) {
        if(obj instanceof Order)
        {
            this.txtrMon.setText(getTextArea()+"\n"+obj.toString());
        }
    }
}

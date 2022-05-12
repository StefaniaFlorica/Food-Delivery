package presentation.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class FilterFrame extends JFrame {

    private final JPanel panel;
    private final JLabel labelKeyWord;
    private final JTextField keyWord;
    private final JLabel labelRatingMin;
    private final JTextField ratingMin;
    private final JLabel labelRatingMax;
    private final JTextField ratingMax;
    private final JLabel labelPriceMin;
    private final JTextField priceMin;
    private final JLabel labelPriceMax;
    private final JTextField priceMax;
    private final JLabel labelCaloriesMin;
    private final JTextField caloriesMin;
    private final JLabel labelCaloriesMax;
    private final JTextField caloriesMax;
    private final JLabel labelProteinMin;
    private final JTextField proteinMin;
    private final JLabel labelProteinMax;
    private final JTextField proteinMax;
    private final JLabel labelFatMin;
    private final JTextField fatMin;
    private final JLabel labelFatMax;
    private final JTextField fatMax;
    private final JLabel labelSodiumMin;
    private final JTextField sodiumMin;
    private final JLabel labelSodiumMax;
    private final JTextField sodiumMax;
    private final JButton btnFilter;

    public FilterFrame() {
        setTitle("Filters");
        setBounds(100, 100, 580, 430);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        labelKeyWord = new JLabel("Keyword: ");
        labelKeyWord.setHorizontalAlignment(SwingConstants.LEFT);
        labelKeyWord.setForeground(new Color(255, 255, 255));
        labelKeyWord.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelKeyWord.setBounds(30, 30, 250, 30);
        panel.add(labelKeyWord);

        keyWord = new JTextField();
        keyWord.setForeground(new Color(188, 143, 143));
        keyWord.setFont(new Font("Copper Black", Font.BOLD, 20));
        keyWord.setColumns(10);
        keyWord.setBounds(170, 30, 360, 30);
        panel.add(keyWord);

        labelRatingMin = new JLabel("Rating min: ");
        labelRatingMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelRatingMin.setForeground(new Color(255, 255, 255));
        labelRatingMin.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelRatingMin.setBounds(30, 80, 130, 30);
        panel.add(labelRatingMin);

        ratingMin = new JTextField();
        ratingMin.setForeground(new Color(188, 143, 143));
        ratingMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        ratingMin.setColumns(10);
        ratingMin.setBounds(170, 80, 140, 30);
        panel.add(ratingMin);

        labelRatingMax = new JLabel("max: ");
        labelRatingMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelRatingMax.setForeground(new Color(255, 255, 255));
        labelRatingMax.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelRatingMax.setBounds(330, 80, 100, 30);
        panel.add(labelRatingMax);

        ratingMax = new JTextField();
        ratingMax.setForeground(new Color(188, 143, 143));
        ratingMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        ratingMax.setColumns(10);
        ratingMax.setBounds(390, 80, 140, 30);
        panel.add(ratingMax);

        labelPriceMin = new JLabel("Price min: ");
        labelPriceMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelPriceMin.setForeground(new Color(255, 255, 255));
        labelPriceMin.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelPriceMin.setBounds(30, 288, 112, 30);
        panel.add(labelPriceMin);

        priceMin = new JTextField();
        priceMin.setForeground(new Color(188, 143, 143));
        priceMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        priceMin.setColumns(10);
        priceMin.setBounds(170, 288, 140, 30);
        panel.add(priceMin);

        labelPriceMax = new JLabel("max: ");
        labelPriceMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelPriceMax.setForeground(new Color(255, 255, 255));
        labelPriceMax.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelPriceMax.setBounds(330, 288, 50, 30);
        panel.add(labelPriceMax);

        priceMax = new JTextField();
        priceMax.setForeground(new Color(188, 143, 143));
        priceMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        priceMax.setColumns(10);
        priceMax.setBounds(390, 288, 140, 30);
        panel.add(priceMax);

        labelCaloriesMin = new JLabel("Calories min: ");
        labelCaloriesMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelCaloriesMin.setForeground(new Color(255, 255, 255));
        labelCaloriesMin.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelCaloriesMin.setBounds(30, 128, 130, 30);
        panel.add(labelCaloriesMin);

        caloriesMin = new JTextField();
        caloriesMin.setForeground(new Color(188, 143, 143));
        caloriesMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        caloriesMin.setColumns(10);
        caloriesMin.setBounds(170, 128, 140, 30);
        panel.add(caloriesMin);

        labelCaloriesMax = new JLabel("max: ");
        labelCaloriesMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelCaloriesMax.setForeground(new Color(255, 255, 255));
        labelCaloriesMax.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelCaloriesMax.setBounds(330, 128, 50, 30);
        panel.add(labelCaloriesMax);

        caloriesMax = new JTextField();
        caloriesMax.setForeground(new Color(188, 143, 143));
        caloriesMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        caloriesMax.setColumns(10);
        caloriesMax.setBounds(390, 128, 140, 30);
        panel.add(caloriesMax);

        labelProteinMin = new JLabel("Protein min: ");
        labelProteinMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelProteinMin.setForeground(new Color(255, 255, 255));
        labelProteinMin.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelProteinMin.setBounds(30, 168, 250, 30);
        panel.add(labelProteinMin);

        proteinMin = new JTextField();
        proteinMin.setForeground(new Color(188, 143, 143));
        proteinMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        proteinMin.setColumns(10);
        proteinMin.setBounds(170, 168, 140, 30);
        panel.add(proteinMin);

        labelProteinMax = new JLabel("max: ");
        labelProteinMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelProteinMax.setForeground(new Color(255, 255, 255));
        labelProteinMax.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelProteinMax.setBounds(330, 168, 50, 30);
        panel.add(labelProteinMax);

        proteinMax = new JTextField();
        proteinMax.setForeground(new Color(188, 143, 143));
        proteinMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        proteinMax.setColumns(10);
        proteinMax.setBounds(390, 168, 140, 30);
        panel.add(proteinMax);

        labelFatMin = new JLabel("Fats min: ");
        labelFatMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelFatMin.setForeground(new Color(255, 255, 255));
        labelFatMin.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelFatMin.setBounds(30, 208, 250, 30);
        panel.add(labelFatMin);

        fatMin = new JTextField();
        fatMin.setForeground(new Color(188, 143, 143));
        fatMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        fatMin.setColumns(10);
        fatMin.setBounds(170, 208, 140, 30);
        panel.add(fatMin);

        labelFatMax = new JLabel("max: ");
        labelFatMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelFatMax.setForeground(new Color(255, 255, 255));
        labelFatMax.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelFatMax.setBounds(330, 208, 50, 30);
        panel.add(labelFatMax);

        fatMax = new JTextField();
        fatMax.setForeground(new Color(188, 143, 143));
        fatMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        fatMax.setColumns(10);
        fatMax.setBounds(390, 208, 140, 30);
        panel.add(fatMax);

        labelSodiumMin = new JLabel("Sodium min: ");
        labelSodiumMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelSodiumMin.setForeground(new Color(255, 255, 255));
        labelSodiumMin.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelSodiumMin.setBounds(30, 248, 129, 30);
        panel.add(labelSodiumMin);

        sodiumMin = new JTextField();
        sodiumMin.setForeground(new Color(188, 143, 143));
        sodiumMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        sodiumMin.setColumns(10);
        sodiumMin.setBounds(170, 248, 140, 30);
        panel.add(sodiumMin);

        labelSodiumMax = new JLabel("max: ");
        labelSodiumMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelSodiumMax.setForeground(new Color(255, 255, 255));
        labelSodiumMax.setFont(new Font("Century Gothic", Font.BOLD, 18));
        labelSodiumMax.setBounds(330, 248, 50, 30);
        panel.add(labelSodiumMax);

        sodiumMax = new JTextField();
        sodiumMax.setForeground(new Color(188, 143, 143));
        sodiumMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        sodiumMax.setColumns(10);
        sodiumMax.setBounds(390, 248, 140, 30);
        panel.add(sodiumMax);

        btnFilter = new JButton("Filter");
        btnFilter.setForeground(Color.PINK);
        btnFilter.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 25));
        btnFilter.setBackground(new Color(224, 255, 255));
        btnFilter.setBounds(390, 328, 140, 41);
        panel.add(btnFilter);

    }

    public void addApplyFiltersListener(ActionListener actionListener)
    {
        btnFilter.addActionListener(actionListener);
    }

    public String getKeyWord() {
        return keyWord.getText();
    }

    public String getRatingMin() {
        return ratingMin.getText();
    }

    public String getRatingMax() {
        return ratingMax.getText();
    }

    public String getPriceMin() {
        return priceMin.getText();
    }

    public String getPriceMax() {
        return priceMax.getText();
    }

    public String getCaloriesMin() {
        return caloriesMin.getText();
    }

    public String getCaloriesMax() {
        return caloriesMax.getText();
    }

    public String getProteinMin() {
        return proteinMin.getText();
    }

    public String getProteinMax() {
        return proteinMax.getText();
    }

    public String getFatMin() {
        return fatMin.getText();
    }

    public String getFatMax() {
        return fatMax.getText();
    }

    public String getSodiumMin() {
        return sodiumMin.getText();
    }

    public String getSodiumMax() {
        return sodiumMax.getText();
    }


}


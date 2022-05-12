package presentation.views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class AdminView extends JFrame {

    private final JButton btnGenerateReports;
    private final JButton btnEditItem;
    private final JButton btnDeleteItem;
    private final JPanel contentPane;
    private final JTextField titluField;
    private final JTextField ratingField;
    private final JTextField calsField;
    private final JTextField proteinsField;
    private final JTextField fatsField;
    private final JTextField sodiumField;
    private final JTextField priceField;
    private final JButton btnNewComposite;
    private final JPanel panelTabel;
    private final JButton btnAddNewBase;
    private final JButton btnApplyChanges;
    private final JTable table;
    private final JPanel panelNewItem;

    public AdminView() {
        setTitle("Administrator Menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 640);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        panelTabel = new JPanel(new GridLayout());
        panelTabel.setBounds(0, 0, 1066, 394);
        contentPane.add(panelTabel);

        table= new JTable();
        panelTabel.add(new JScrollPane(table));
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(0, 395, 1066, 60);
        contentPane.add(panelButtons);
        panelButtons.setLayout(null);

        btnNewComposite = new JButton("Add New Composite Product");
        btnNewComposite.setBounds(828, 10, 228, 35);
        panelButtons.add(btnNewComposite);

        btnAddNewBase = new JButton("Add New Base Product");
        btnAddNewBase.setBounds(10, 10, 228, 35);
        panelButtons.add(btnAddNewBase);

        panelNewItem = new JPanel();
        panelNewItem.setBounds(0, 453, 804, 150);
        contentPane.add(panelNewItem);
        panelNewItem.setLayout(null);
        panelNewItem.setVisible(false);

        titluField = new JTextField();
        titluField.setBounds(10, 60, 206, 30);
        panelNewItem.add(titluField);
        titluField.setColumns(10);

        ratingField = new JTextField();
        ratingField.setColumns(10);
        ratingField.setBounds(226, 60, 50, 30);
        panelNewItem.add(ratingField);

        btnApplyChanges = new JButton("Apply Changes");
        btnApplyChanges.setBounds(406, 93, 168, 35);
        panelNewItem.add(btnApplyChanges);

        calsField = new JTextField();
        calsField.setColumns(10);
        calsField.setBounds(286, 60, 50, 30);
        panelNewItem.add(calsField);

        proteinsField = new JTextField();
        proteinsField.setColumns(10);
        proteinsField.setBounds(346, 60, 50, 30);
        panelNewItem.add(proteinsField);

        fatsField = new JTextField();
        fatsField.setColumns(10);
        fatsField.setBounds(406, 60, 50, 30);
        panelNewItem.add(fatsField);

        sodiumField = new JTextField();
        sodiumField.setColumns(10);
        sodiumField.setBounds(466, 60, 50, 30);
        panelNewItem.add(sodiumField);

        priceField = new JTextField();
        priceField.setColumns(10);
        priceField.setBounds(526, 60, 50, 30);
        panelNewItem.add(priceField);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(10, 20, 206, 30);
        panelNewItem.add(lblTitle);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setHorizontalAlignment(SwingConstants.CENTER);
        lblRating.setBounds(226, 20, 50, 30);
        panelNewItem.add(lblRating);

        JLabel lblCals = new JLabel("Cals");
        lblCals.setHorizontalAlignment(SwingConstants.CENTER);
        lblCals.setBounds(286, 20, 50, 30);
        panelNewItem.add(lblCals);

        JLabel lblProteins = new JLabel("Proteins");
        lblProteins.setHorizontalAlignment(SwingConstants.CENTER);
        lblProteins.setBounds(346, 20, 50, 30);
        panelNewItem.add(lblProteins);

        JLabel lblFats = new JLabel("Fats");
        lblFats.setHorizontalAlignment(SwingConstants.CENTER);
        lblFats.setBounds(406, 20, 50, 30);
        panelNewItem.add(lblFats);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setHorizontalAlignment(SwingConstants.CENTER);
        lblSodium.setBounds(466, 20, 50, 30);
        panelNewItem.add(lblSodium);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrice.setBounds(526, 20, 50, 30);
        panelNewItem.add(lblPrice);

        JPanel panelReports = new JPanel();
        panelReports.setBounds(814, 456, 252, 147);
        contentPane.add(panelReports);
        panelReports.setLayout(null);

        btnGenerateReports = new JButton("Generate reports");
        btnGenerateReports.setBackground(new Color(240, 248, 255));
        btnGenerateReports.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnGenerateReports.setForeground(new Color(205, 92, 92));
        btnGenerateReports.setBounds(14, 49, 228, 35);
        panelReports.add(btnGenerateReports);

        btnEditItem = new JButton("Edit item");
        btnEditItem.setBackground(new Color(192, 192, 192));
        btnEditItem.setBounds(397, 10, 131, 35);
        panelButtons.add(btnEditItem);

        btnDeleteItem = new JButton("Delete item");
        btnDeleteItem.setBackground(Color.LIGHT_GRAY);
        btnDeleteItem.setBounds(538, 10, 120, 35);
        panelButtons.add(btnDeleteItem);


        setVisible(true);
    }
    public int getSelectedIndex() {
        int selRow = table.getSelectedRow();
        int modelIndex = table.convertRowIndexToModel(selRow);
        return modelIndex;
    }
    public Vector getDataToModify()
    {

        int modelIndex = getSelectedIndex();
        List<Vector> vectors = ((DefaultTableModel) table.getModel()).getDataVector().stream().toList();
        return vectors.get(modelIndex);
    }

    public List<Integer> getSelectedIndexes()
    {
        int[] selRows=table.getSelectedRows();
        List<Integer> collect = Arrays.stream(selRows).boxed().map(e->table.convertRowIndexToModel(e)).collect(Collectors.toList());
        return collect;
    }

    public JTable getTable() {
        return table;
    }

    public <T> void generateTable(List<T> objects)
    {
        table.setModel(genericTableBuilder(objects));
        table.repaint();
        table.revalidate();
        adjustColumnWidth(table);

    }
    public <T> DefaultTableModel genericTableBuilder(List<T> objects)
    {
        Class type=objects.get(0).getClass().getSuperclass();
        Field[] fields=type.getDeclaredFields();
        String[] columns = new String[fields.length];
        for(int i=0;i<fields.length;i++)
        {
            columns[i]=fields[i].getName();
        }
        Object[] row = new Object[fields.length];
        DefaultTableModel model= new DefaultTableModel(columns,0);
        for (int j=0;j<objects.size();j++)
        {
            for(int i=0;i<fields.length;i++)
            {

                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fields[i].getName(),type);
                    Method getter= propertyDescriptor.getReadMethod();
                    Object cellValue=getter.invoke(objects.get(j));
                    row[i]=String.valueOf(cellValue);

                } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(row);
            model.fireTableDataChanged();
        }
        return model;
    }

    public void dataChanged()
    {
        ((DefaultTableModel)table.getModel()).fireTableDataChanged();
    }

    public static void adjustColumnWidth(JTable table)
    {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            for (int r = 0; r < table.getRowCount(); r++) {
                renderer = table.getCellRenderer(r, i);
                Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, i), false, false, r, i);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            col.setPreferredWidth(width + 10);
        }
    }


    public String getTitluField() {
        return titluField.getText();
    }

    public double getRatingField() {
        return Double.parseDouble(ratingField.getText());
    }

    public int getCalsField() {
        return Integer.parseInt(calsField.getText());
    }

    public int getProteinsField() {
        return Integer.parseInt(proteinsField.getText());
    }

    public int getFatsField() {
        return Integer.parseInt(fatsField.getText());
    }

    public int getSodiumField() {
        return Integer.parseInt(sodiumField.getText());
    }

    public double getPriceField() {
        return Double.parseDouble(priceField.getText());
    }

    public void addNewBaseListener(ActionListener action)
    {
        btnAddNewBase.addActionListener(action);
    }

    public void addNewCompositeListener(ActionListener action)
    {
        btnNewComposite.addActionListener(action);
    }
    public void addApplyChangesListener(ActionListener action)
    {
        btnApplyChanges.addActionListener(action);
    }

    public void showPanelNewItem() {
        panelNewItem.setVisible(true);
    }

    public void addGenerateReportsListener(ActionListener action)
    {
        btnGenerateReports.addActionListener(action);
    }
    public void addEditListener(ActionListener action)
    {
        btnEditItem.addActionListener(action);
    }
    public void addDeleteListener(ActionListener action)
    {
        btnDeleteItem.addActionListener(action);
    }
}


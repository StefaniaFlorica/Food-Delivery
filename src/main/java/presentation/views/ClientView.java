package presentation.views;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ClientView extends JFrame {

    private final JPanel contentPane;
    private final JButton btnPlaceOrder;
    private final JButton btnAdd;
    private final JButton btnClearAllFiltering;
    private final JButton btnFilter;
    private final JTable table;
    private final JPanel panelTable;


    public ClientView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1080, 621);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 0, 1066, 47);
        contentPane.add(panelTitle);

        JLabel lblNewLabel = new JLabel("MENU");
        lblNewLabel.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
        panelTitle.add(lblNewLabel);

        panelTable = new JPanel(new GridLayout());
        panelTable.setBounds(0, 45, 1066, 451);
        contentPane.add(panelTable);

        table= new JTable();
        panelTable.add(new JScrollPane(table));
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JPanel panelBtns = new JPanel();
        panelBtns.setBounds(0, 496, 1066, 88);
        contentPane.add(panelBtns);
        panelBtns.setLayout(null);

        btnFilter = new JButton("Filter");
        btnFilter.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        btnFilter.setBounds(10, 29, 138, 29);
        panelBtns.add(btnFilter);

        btnClearAllFiltering = new JButton("Clear all filters");
        btnClearAllFiltering.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        btnClearAllFiltering.setBounds(158, 29, 138, 29);
        panelBtns.add(btnClearAllFiltering);

        btnAdd = new JButton("Add to order");
        btnAdd.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        btnAdd.setBounds(770, 29, 138, 29);
        panelBtns.add(btnAdd);

        btnPlaceOrder = new JButton("PLACE ORDER");
        btnPlaceOrder.setFont(new Font("Century Gothic", Font.BOLD, 14));
        btnPlaceOrder.setBounds(918, 18, 138, 49);
        panelBtns.add(btnPlaceOrder);
        setVisible(true);
    }
    public int getSelectedIndex() {
        int selRow = table.getSelectedRow();
        int modelIndex = table.convertRowIndexToModel(selRow);
        return modelIndex;
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
        DefaultTableModel model= new DefaultTableModel(columns,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
        }
        return model;
    }

    public void addBtnAddListener(ActionListener actionListener)
    {
        btnAdd.addActionListener(actionListener);
    }
    public void addClearFiltersListener(ActionListener action)
    {
        btnClearAllFiltering.addActionListener(action);
    }
    public void addPlaceOrderListener(ActionListener action)
    {
        btnPlaceOrder.addActionListener(action);
    }
    public void addFilterListener(ActionListener action)
    {
        btnFilter.addActionListener(action);
    }
}

package presentation.controllers;

import bll.DeliveryService;
import model.BaseProduct;
import model.CompositeProduct;
import model.MenuItem;
import presentation.views.AdminView;
import presentation.views.ReportView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AdminController {
    private final AdminView adminView;
    private final DeliveryService service;

    public AdminController(AdminView adminView1,DeliveryService service1)
    {
        adminView= adminView1;
        service=service1;
        adminView.generateTable(service.getSortedMenuItemsList());
        adminView.addDeleteListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexProduct=adminView.getSelectedIndex();
                System.out.println("selected index for deletion" + indexProduct);
                service.deleteItem(indexProduct);
                adminView.generateTable(service.getSortedMenuItemsList());
                JOptionPane.showMessageDialog(adminView,"Selected item was deleted!");
            }
        });
        adminView.addEditListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indexProduct=adminView.getSelectedIndex();
                Vector itemVector=adminView.getDataToModify();
                System.out.println(itemVector);
                MenuItem baseProduct= new BaseProduct(String.valueOf(itemVector.get(0)),
                                                        Double.parseDouble(String.valueOf(itemVector.get(1))),
                                                        Integer.parseInt(String.valueOf(itemVector.get(2))),
                                                        Integer.parseInt(String.valueOf(itemVector.get(3))),
                                                        Integer.parseInt(String.valueOf(itemVector.get(4))),
                                                        Integer.parseInt(String.valueOf(itemVector.get(5))),
                                                        Double.parseDouble(String.valueOf(itemVector.get(6))));



                System.out.println("selected index" + indexProduct);
                service.modifyItem(indexProduct,baseProduct);
                adminView.generateTable(service.getSortedMenuItemsList());
            }
        });
        adminView.addNewBaseListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminView.showPanelNewItem();
            }
        });
        adminView.addApplyChangesListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuItem menuItem= new BaseProduct(adminView.getTitluField(),adminView.getRatingField(),adminView.getCalsField(),
                        adminView.getProteinsField(),adminView.getFatsField(),adminView.getSodiumField(),adminView.getPriceField());
                service.insertItem(menuItem);
                JOptionPane.showMessageDialog(adminView,"Item "+menuItem.getTitle()+" was inserted!");
                adminView.generateTable(service.getSortedMenuItemsList());
            }
        });
        adminView.addGenerateReportsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportController(new ReportView(),service);
            }
        });
        adminView.addNewCompositeListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.insertNewComposite(adminView.getSelectedIndexes());
                adminView.generateTable(service.getSortedMenuItemsList());
            }
        });
    }
}

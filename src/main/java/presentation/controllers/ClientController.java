package presentation.controllers;

import bll.DeliveryService;
import model.Client;
import model.MenuItem;
import presentation.views.ClientView;
import presentation.views.EmployeeView;
import presentation.views.FilterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientController {
    private final ClientView clientView;
    private final DeliveryService service;
    private final EmployeeView employeeView;
    private final FilterFrame filterFrame;
    private final Client myClient;

    public ClientController(ClientView clientView1,Client myClient1,EmployeeView employeeView1,DeliveryService service1)
    {
        clientView=clientView1;
        myClient=myClient1;
        filterFrame=new FilterFrame();
        employeeView=employeeView1;
        service=service1;
        clientView.generateTable(service.getSortedMenuItemsList());

        clientView.addClearFiltersListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientView.generateTable(service.getSortedMenuItemsList());
                service.resetFilterList();
                filterFrame.dispose();
            }
        });
        clientView.addBtnAddListener(new AddListener());
        clientView.addFilterListener(actionEvent->{
            filterFrame.setVisible(true);
            filterFrame.addApplyFiltersListener(new FilterListener());
        });

    }
    class AddListener implements ActionListener{

        List<MenuItem> orderedItems= new ArrayList<MenuItem>();
        AddListener()
        {
            clientView.addPlaceOrderListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    service.createNewOrder(myClient,orderedItems);
                    employeeView.setVisible(true);
                    orderedItems=new ArrayList<>();
                }
            });
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            orderedItems.add(service.getFilteredListItemByIndex(clientView.getSelectedIndex()));
            JOptionPane.showMessageDialog(clientView,orderedItems.get(orderedItems.size()-1).getTitle()+" added to cart!");
        }

    }
    class FilterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String[]fields=new String[13];
            fields[Criteria.KEYWORD]=filterFrame.getKeyWord();
            fields[Criteria.RATING_MIN]=filterFrame.getRatingMin();
            fields[Criteria.RATING_MAX]=filterFrame.getRatingMax();
            fields[Criteria.CALORIES_MIN]=filterFrame.getCaloriesMin();
            fields[Criteria.CALORIES_MAX]=filterFrame.getCaloriesMax();
            fields[Criteria.PROTEINS_MIN]=filterFrame.getProteinMin();
            fields[Criteria.PROTEINS_MAX]=filterFrame.getProteinMax();
            fields[Criteria.FATS_MIN]=filterFrame.getFatMin();
            fields[Criteria.FATS_MAX]=filterFrame.getFatMax();
            fields[Criteria.SODIUM_MIN]=filterFrame.getSodiumMin();
            fields[Criteria.SODIUM_MAX]=filterFrame.getSodiumMax();
            fields[Criteria.PRICE_MIN]=filterFrame.getPriceMin();
            fields[Criteria.PRICE_MAX]=filterFrame.getPriceMax();

            clientView.generateTable(service.filterAll(fields));

        }
    }
}

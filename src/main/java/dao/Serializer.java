package dao;

import model.Client;
import model.MenuItem;
import model.Order;

import java.io.*;
import java.util.*;

public class Serializer {

    public static final String FILE_NAME_ORDER= "order.ser";
    public static final String FILE_NAME_MENU="menu.ser";
    public static final String FILE_NAME_CLIENTS="clients.ser";

    public void serializeOrderHistory(Map<Order, List<MenuItem>> orders)
    {
        try {
            FileOutputStream fileOut= new FileOutputStream(FILE_NAME_ORDER);
            ObjectOutputStream out= new ObjectOutputStream(fileOut);
            out.writeObject(orders);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<Order, List<MenuItem>> deserializeOrderHistory()
    {
        Map<Order, List<MenuItem>> orders=new HashMap<>();
        try {
            FileInputStream fileIn= new FileInputStream(FILE_NAME_ORDER);
            ObjectInputStream in= new ObjectInputStream(fileIn);
            orders=(Map<Order, List<MenuItem>>) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void serializeClients(Set<Client> clients)
    {
        try {
            ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(FILE_NAME_CLIENTS));
            out.writeObject(clients);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Client> deserializeClients() {
        Set<Client> clients=new HashSet<>();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME_CLIENTS));
            clients=(Set<Client>) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Set<MenuItem> deserializeMenuItems()
    {
        Set<MenuItem> menuItems=new HashSet<>();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME_MENU));
            menuItems=(Set<MenuItem>) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public void serializeMenuItems(Set<MenuItem> menuItems)
    {
        try {
            ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(FILE_NAME_MENU));
            out.writeObject(menuItems);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

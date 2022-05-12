package dao;

import model.Client;
import model.MenuItem;
import model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReceiptWriter {
    private BufferedWriter bufferedWriter;
    public ReceiptWriter(String fileName)
    {
        try {
            bufferedWriter= new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeReceipt(Order order, List<MenuItem> items, Client client)
    {
        try {
            bufferedWriter.write("Order "+order.getOrderID()+" placed on "+order.getOrderDate()+" by client: "+client.getUser()+"\n\n");
            bufferedWriter.write("--->Order summary<---\n");
            items.forEach(i-> {
                try {
                    bufferedWriter.write(i+"\tx1\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bufferedWriter.write("Total price:\t"+order.getTotalPrice());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

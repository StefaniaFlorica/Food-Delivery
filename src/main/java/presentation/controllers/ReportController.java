package presentation.controllers;

import bll.DeliveryService;
import presentation.views.ReportView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ReportController {
    private final ReportView reportView;
    private final DeliveryService service;

    public ReportController(ReportView reportView1,DeliveryService service1)
    {
        reportView=reportView1;
        service=service1;
        reportView.addGen1Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.generateReport1(reportView.getTextField11(),reportView.getTextField12());
                JOptionPane.showMessageDialog(reportView, "Report 1 generated!");

            }
        });
        reportView.addGen2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.generateReport2(reportView.getTextField2());
                JOptionPane.showMessageDialog(reportView, "Report 2 generated!");
            }
        });
        reportView.addGen3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.generateReport3(reportView.getTextField31(),reportView.getTextField32());
                JOptionPane.showMessageDialog(reportView, "Report 3 generated!");
            }
        });
        reportView.addGen4Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.generateReport4(reportView.getTextField41(),reportView.getTextField42(),reportView.getTextField43());
                JOptionPane.showMessageDialog(reportView, "Report 4 generated!");
            }
        });
    }

}

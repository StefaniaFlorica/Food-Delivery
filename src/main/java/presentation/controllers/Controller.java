package presentation.controllers;

import bll.DeliveryService;
import model.Client;
import presentation.views.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private final FirstView firstView;
    private AdminView adminView;
    private ReportView reportView;
    private ClientView clientView;
    private LoginView loginView;
    private AdminPasswordView adminPasswordView;
    private final EmployeeView employeeView;
    private final DeliveryService service;

    public Controller(DeliveryService deliveryService)
    {
        service=deliveryService;
        firstView= new FirstView();
        employeeView= new EmployeeView();
        service.register(employeeView);
        firstView.addGoListener(e->{
            if ((firstView.getSelectedOption() == 0)) {
                adminPasswordView= new AdminPasswordView();
                adminPasswordView.addEnterListener(new EnterAdminMenuListener());

            } else {
                loginView=new LoginView();
                loginView.addLoginListener(new LoginListener());
                loginView.addRegisterListener(new RegisterListener());

            }
        });


    }

    class EnterAdminMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(adminPasswordView.getPasswordField().equals("secret*"))
            {
                adminPasswordView.dispose();
                adminView=new AdminView();
                //adminView.generateTable(service.getMenuItems().stream().toList());
                new AdminController(adminView,service);
            }
            else
            {
                JOptionPane.showMessageDialog(adminPasswordView,"Wrong admin password! Try again :(");
            }

        }
    }

    class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterView registerView= new RegisterView();
            registerView.addCreateListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username=registerView.getUsernameField();
                    String pass1=registerView.getPasswordField();
                    String pass2= registerView.getConfirmPassField();
                    if(!service.clientExits(username))
                    {
                        if(pass1.equals(pass2))
                        {
                            service.registerClient(username,pass1);
                            registerView.dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(registerView,"Passwords don't match, try again "+pass1+" "+pass2);
                            registerView.resetPasswords();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(registerView,"A client with this username already exists. Try again :(");
                    }
                }
            });
        }
    }
    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String password=loginView.getPasswordField();
            String username=loginView.getUserField();
            Client foundClient=service.loginClient(username,password);
            if(foundClient==null)
            {
                JOptionPane.showMessageDialog(loginView,"User not found. Please try again! Click Register if you don't have an account :)");
            }
            else
            {
                clientView=new ClientView();
                clientView.generateTable(service.getMenuItems().stream().toList());
                new ClientController(clientView,foundClient,employeeView,service);
                loginView.dispose();
            }
        }
    }

}

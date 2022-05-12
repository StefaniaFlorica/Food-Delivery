package presentation.views;

import bll.Observer;

import javax.swing.*;

public class ErrorMessage extends  Exception implements Observer {

    public ErrorMessage()
    {
        super();
    }

    public ErrorMessage(String message)
    {
        super(message);
    }

    @Override
    public void update(Object obj) {

        if(obj instanceof String)
        {
            ErrorMessage newException= new ErrorMessage(obj.toString());
            JOptionPane.showMessageDialog(null,newException.getMessage(),"!!!",JOptionPane.ERROR_MESSAGE);
        }

    }
}

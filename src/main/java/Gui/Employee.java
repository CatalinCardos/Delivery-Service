package Gui;

import BusinessLogic.DeliveryService;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Employee implements Observer {
    private JPanel panel1;
    private JTable tableEmployee;
    private JTextArea textArea1;
    private JButton delogareButton;


    public JPanel getPanel1() {
        return panel1;
    }

    public JTable getTableEmployee() {
        return tableEmployee;
    }

    @Override
    public void update(Observable o, Object arg) {
        DeliveryService deliveryService = (DeliveryService) arg;
        deliveryService.getEmployee().getTextArea1().append("\nA fost adaugata comanda cu numarul: " + (deliveryService.getIdOrderUniq()));

    }
    public void adddelogareButtonListener(ActionListener mal){delogareButton.addActionListener(mal);}

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public void setTableEmployee(JTable tableEmployee) {
        this.tableEmployee = tableEmployee;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JButton getDelogareButton() {
        return delogareButton;
    }

    public void setDelogareButton(JButton delogareButton) {
        this.delogareButton = delogareButton;
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }
}

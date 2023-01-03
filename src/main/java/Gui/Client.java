package Gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Client {
    private JList listComanda;
    private JComboBox comboBoxFilter;
    private JButton buttonFilter;
    private JButton buttonComanda;
    private JComboBox comboBoxAdaugaMancare;
    private JButton buttonAdauga;
    private JTextField textFieldTotal;
    private JTextField textFieldPret;
    private JPanel panelMainClient;
    private JTextField textFieldCautaNume;
    private JButton buttonCautareNume;
    private JButton buttonDelogareClient;
    private JTextArea textAreaProduse;
    private JButton buttonAdauga2;
    private JTextArea textAreaMeniuri;
    private JComboBox comboBoxAdaugaMancareMeniu;
    private JButton stergeDinListaButton;
    private JTextField textFieldNumarFiltru;

    public void addbuttonComandaListener(ActionListener mal){buttonComanda.addActionListener(mal);}
    public void addbuttonAdaugaListener(ActionListener mal){buttonAdauga.addActionListener(mal);}
    public void addbuttonFilterListener(ActionListener mal){buttonFilter.addActionListener(mal);}
    public void addbuttonCautareNumeListener(ActionListener mal){buttonCautareNume.addActionListener(mal);}
    public void addbuttonDelogareClientListener(ActionListener mal){buttonDelogareClient.addActionListener(mal);}
    public void addcomboBoxAdaugaMancareListener(ActionListener mal){comboBoxAdaugaMancare.addActionListener(mal);}
    public void addcomboBoxAdaugaMancareMeniuListener(ActionListener mal){comboBoxAdaugaMancareMeniu.addActionListener(mal);}
    public void addbuttonAdauga2Listener(ActionListener mal){buttonAdauga2.addActionListener(mal);}
    public void addstergeDinListaButtonListener(ActionListener mal){stergeDinListaButton.addActionListener(mal);}

    public JTextField getTextFieldNumarFiltru() {
        return textFieldNumarFiltru;
    }

    public JButton getStergeDinListaButton() {
        return stergeDinListaButton;
    }

    public JComboBox getComboBoxAdaugaMancareMeniu() {
        return comboBoxAdaugaMancareMeniu;
    }

    public JTextArea getTextAreaProduse() {
        return textAreaProduse;
    }

    public JButton getButtonAdauga2() {
        return buttonAdauga2;
    }

    public JTextArea getTextAreaMeniuri() {
        return textAreaMeniuri;
    }

    public JButton getButtonDelogareClient() {
        return buttonDelogareClient;
    }

    public JTextField getTextFieldCautaNume() {
        return textFieldCautaNume;
    }

    public JButton getButtonCautareNume() {
        return buttonCautareNume;
    }

    public JPanel getPanelMainClient() {
        return panelMainClient;
    }

    public JList getListComanda() {
        return listComanda;
    }

    public JComboBox getComboBoxFilter() {
        return comboBoxFilter;
    }

    public JButton getButtonFilter() {
        return buttonFilter;
    }

    public JButton getButtonComanda() {
        return buttonComanda;
    }

    public JComboBox getComboBoxAdaugaMancare() {
        return comboBoxAdaugaMancare;
    }

    public JButton getButtonAdauga() {
        return buttonAdauga;
    }

    public JTextField getTextFieldTotal() {
        return textFieldTotal;
    }

    public JTextField getTextFieldPret() {
        return textFieldPret;
    }
}

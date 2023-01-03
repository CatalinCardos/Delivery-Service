package Gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Login {
    private JPanel mainPanelLogin;
    private JTextField textFieldNumeLogin;
    private JPasswordField textFieldParolaLogin;
    private JButton buttonLogare;
    private JButton buttonInregistrare;
    private JPanel panelLogare;
    private JPanel panelInregistrare;
    private JTextField textFieldNumeInreg;
    private JPasswordField textFieldPassInreg;
    private JButton inregistrareButton;
    private JButton buttonInapoi;
    private JPasswordField textFieldPassInregConf;

    public void addbuttonLogareListener(ActionListener mal){buttonLogare.addActionListener(mal);}
    public void addbuttonInregistrareListener(ActionListener mal){buttonInregistrare.addActionListener(mal);}
    public void addinregistrareButtonListener(ActionListener mal){inregistrareButton.addActionListener(mal);}
    public void addbuttonInapoiListener(ActionListener mal){buttonInapoi.addActionListener(mal);}

    public JTextField getTextFieldNumeInreg() {
        return textFieldNumeInreg;
    }

    public JPasswordField getTextFieldPassInreg() {
        return textFieldPassInreg;
    }

    public JButton getButtonInapoi() {
        return buttonInapoi;
    }

    public JPasswordField getTextFieldPassInregConf() {
        return textFieldPassInregConf;
    }

    public JPanel getMainPanelLogin() {
        return mainPanelLogin;
    }

    public JTextField getTextFieldNumeLogin() {
        return textFieldNumeLogin;
    }

    public JPasswordField getTextFieldParolaLogin() {
        return textFieldParolaLogin;
    }

    public JButton getButtonLogare() {
        return buttonLogare;
    }

    public JButton getButtonInregistrare() {
        return buttonInregistrare;
    }

    public JPanel getPanelLogare() {
        return panelLogare;
    }

    public JPanel getPanelInregistrare() {
        return panelInregistrare;
    }

    public JTextField getTextField1() {
        return textFieldNumeInreg;
    }

    public JPasswordField getPasswordField1() {
        return textFieldPassInreg;
    }

    public JButton getInregistrareButton() {
        return inregistrareButton;
    }

    public JButton getInapoiButton() {
        return buttonInapoi;
    }
}

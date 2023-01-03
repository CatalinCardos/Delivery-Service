package Gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Administrator {
    private JPanel panelMainAdmin;
    private JPanel panelOfButtons;
    private JPanel panelDeleteProdToMenu;
    private JPanel panelModProdToMenu;
    private JPanel panelAddProdToMenu;
    private JButton adaugareProdusInMeniuButton;
    private JButton stergereProdusDinMeniuButton;
    private JButton modificareProdusDinMeniuButton;
    private JButton creareMeniuButton;
    private JPanel panelCreateMenu;
    private JComboBox comboBoxMenuStergere;
    private JButton stergeProdDinMenuButton;
    private JTextField textFieldNumeAdaug;
    private JTextField textFieldRatingAdaug;
    private JComboBox comboBoxCaloriiAdaug;
    private JComboBox comboBoxProteineAdaug;
    private JComboBox comboBoxGrasimeAdaug;
    private JComboBox comboBoxSodiuAdaug;
    private JButton adaugareButton;
    private JButton inapoiButton;
    private JButton inapoiButton1;
    private JComboBox comboBoxNumeModif;
    private JTextField textFieldRatingModif;
    private JTextField textFieldCaloriiModif;
    private JTextField textFielProteineModif;
    private JTextField textFieldGrasimeModif;
    private JTextField textFieldSodiuModif;
    private JTextField textFieldPretModif;
    private JButton inapoiButton2;
    private JButton modificareButton;
    private JComboBox comboBoxPretAdaug;
    private JButton buttonDelogare;
    private JComboBox comboBoxListaDeProd;
    private JTextArea textAreaDetaliiProdus;
    private JList listProduseAlese;
    private JButton buttonStergeDePeLista;
    private JButton inapoiButton3;
    private JButton buttonAdaugaMeniu;
    private JButton buttonAdaugaInLista;
    private JTextField textFieldNumeMeniu;
    private JButton incarcareProduseButton;
    private JButton buttonRaport;
    private JPanel panelRaport;
    private JComboBox comboBoxRaport;
    private JButton generareRaportButton;
    private JTextField textFieldRaport1;
    private JTextField textFieldRaport2;
    private JTextArea textAreaRaport;
    private JButton inapoiButton4;
    private JLabel labelRaport2;
    private JLabel labelRaport1;

    public void addbuttonadaugareProdusInMeniuListener(ActionListener mal){adaugareProdusInMeniuButton.addActionListener(mal);}
    public void addbuttonstergereProdusDinMeniuListener(ActionListener mal){stergereProdusDinMeniuButton.addActionListener(mal);}
    public void addbuttonmodificareProdusDinMeniuListener(ActionListener mal){modificareProdusDinMeniuButton.addActionListener(mal);}
    public void addbuttoncreareMeniuListener(ActionListener mal){creareMeniuButton.addActionListener(mal);}
    public void addbinapoiButtonListener(ActionListener mal){inapoiButton.addActionListener(mal);}
    public void addbinapoiButton1Listener(ActionListener mal){inapoiButton1.addActionListener(mal);}
    public void addinapoiButton2Listener(ActionListener mal){inapoiButton2.addActionListener(mal);}
    public void addinapoiButton3Listener(ActionListener mal){inapoiButton3.addActionListener(mal);inapoiButton4.addActionListener(mal);}
    public void addstergeProdDinMenuButtonListener(ActionListener mal){stergeProdDinMenuButton.addActionListener(mal);}
    public void addmodificareButtonListener(ActionListener mal){modificareButton.addActionListener(mal);}
    public void addcomboBoxNumeModifListener(ActionListener mal){comboBoxNumeModif.addActionListener(mal);}
    public void addadaugareButtonListener(ActionListener mal){adaugareButton.addActionListener(mal);}
    public void adddelogareButtonListener(ActionListener mal){buttonDelogare.addActionListener(mal);}
    public void addcomboBoxListaDeProdListener(ActionListener mal){comboBoxListaDeProd.addActionListener(mal);}
    public void addbuttonStergeDePeListaListener(ActionListener mal){buttonStergeDePeLista.addActionListener(mal);}
    public void addbuttonAdaugaMeniuListener(ActionListener mal){buttonAdaugaMeniu.addActionListener(mal);}
    public void addbuttonAdaugaInListaListener(ActionListener mal){buttonAdaugaInLista.addActionListener(mal);}
    public void addincarcareProduseButtonListener(ActionListener mal){incarcareProduseButton.addActionListener(mal);}
    public void addbuttonRaportListener(ActionListener mal){buttonRaport.addActionListener(mal);}
    public void addcomboBoxRaportListener(ActionListener mal){comboBoxRaport.addActionListener(mal);}
    public void addgenerareRaportButtonListener(ActionListener mal){generareRaportButton.addActionListener(mal);}

    public JLabel getLabelRaport2() {
        return labelRaport2;
    }

    public JLabel getLabelRaport1() {
        return labelRaport1;
    }

    public JButton getInapoiButton4() {
        return inapoiButton4;
    }

    public JButton getButtonRaport() {
        return buttonRaport;
    }

    public JPanel getPanelRaport() {
        return panelRaport;
    }

    public JComboBox getComboBoxRaport() {
        return comboBoxRaport;
    }

    public JButton getGenerareRaportButton() {
        return generareRaportButton;
    }

    public JTextField getTextFieldRaport1() {
        return textFieldRaport1;
    }

    public JTextField getTextFieldRaport2() {
        return textFieldRaport2;
    }

    public JTextArea getTextAreaRaport() {
        return textAreaRaport;
    }

    public JButton getIncarcareProduseButton() {
        return incarcareProduseButton;
    }

    public JTextField getTextFieldNumeMeniu() {
        return textFieldNumeMeniu;
    }

    public JButton getButtonAdaugaInLista() {
        return buttonAdaugaInLista;
    }

    public JButton getButtonDelogare() {
        return buttonDelogare;
    }

    public JComboBox getComboBoxListaDeProd() {
        return comboBoxListaDeProd;
    }

    public JTextArea getTextAreaDetaliiProdus() {
        return textAreaDetaliiProdus;
    }

    public JList getListProduseAlese() {
        return listProduseAlese;
    }

    public JButton getButtonStergeDePeLista() {
        return buttonStergeDePeLista;
    }

    public JButton getInapoiButton3() {
        return inapoiButton3;
    }

    public JButton getButtonAdaugaMeniu() {
        return buttonAdaugaMeniu;
    }

    public JPanel getPanelMainAdmin() {
        return panelMainAdmin;
    }

    public void setPanelMainAdmin(JPanel panelMainAdmin) {
        this.panelMainAdmin = panelMainAdmin;
    }

    public JPanel getPanelOfButtons() {
        return panelOfButtons;
    }

    public void setPanelOfButtons(JPanel panelOfButtons) {
        this.panelOfButtons = panelOfButtons;
    }

    public JPanel getPanelDeleteProdToMenu() {
        return panelDeleteProdToMenu;
    }

    public void setPanelDeleteProdToMenu(JPanel panelDeleteProdToMenu) {
        this.panelDeleteProdToMenu = panelDeleteProdToMenu;
    }

    public JPanel getPanelModProdToMenu() {
        return panelModProdToMenu;
    }

    public void setPanelModProdToMenu(JPanel panelModProdToMenu) {
        this.panelModProdToMenu = panelModProdToMenu;
    }

    public JPanel getPanelAddProdToMenu() {
        return panelAddProdToMenu;
    }

    public void setPanelAddProdToMenu(JPanel panelAddProdToMenu) {
        this.panelAddProdToMenu = panelAddProdToMenu;
    }

    public JButton getAdaugareProdusInMeniuButton() {
        return adaugareProdusInMeniuButton;
    }

    public void setAdaugareProdusInMeniuButton(JButton adaugareProdusInMeniuButton) {
        this.adaugareProdusInMeniuButton = adaugareProdusInMeniuButton;
    }

    public JButton getStergereProdusDinMeniuButton() {
        return stergereProdusDinMeniuButton;
    }

    public void setStergereProdusDinMeniuButton(JButton stergereProdusDinMeniuButton) {
        this.stergereProdusDinMeniuButton = stergereProdusDinMeniuButton;
    }

    public JButton getModificareProdusDinMeniuButton() {
        return modificareProdusDinMeniuButton;
    }

    public void setModificareProdusDinMeniuButton(JButton modificareProdusDinMeniuButton) {
        this.modificareProdusDinMeniuButton = modificareProdusDinMeniuButton;
    }

    public JButton getCreareMeniuButton() {
        return creareMeniuButton;
    }

    public void setCreareMeniuButton(JButton creareMeniuButton) {
        this.creareMeniuButton = creareMeniuButton;
    }

    public JPanel getPanelCreateMenu() {
        return panelCreateMenu;
    }

    public void setPanelCreateMenu(JPanel panelCreateMenu) {
        this.panelCreateMenu = panelCreateMenu;
    }

    public JComboBox getComboBoxMenuStergere() {
        return comboBoxMenuStergere;
    }

    public void setComboBoxMenuStergere(JComboBox comboBoxMenuStergere) {
        this.comboBoxMenuStergere = comboBoxMenuStergere;
    }

    public JButton getStergeProdDinMenuButton() {
        return stergeProdDinMenuButton;
    }

    public void setStergeProdDinMenuButton(JButton stergeProdDinMenuButton) {
        this.stergeProdDinMenuButton = stergeProdDinMenuButton;
    }

    public JTextField getTextFieldNumeAdaug() {
        return textFieldNumeAdaug;
    }

    public void setTextFieldNumeAdaug(JTextField textFieldNumeAdaug) {
        this.textFieldNumeAdaug = textFieldNumeAdaug;
    }

    public JTextField getTextFieldRatingAdaug() {
        return textFieldRatingAdaug;
    }

    public void setTextFieldRatingAdaug(JTextField textFieldRatingAdaug) {
        this.textFieldRatingAdaug = textFieldRatingAdaug;
    }

    public JComboBox getComboBoxCaloriiAdaug() {
        return comboBoxCaloriiAdaug;
    }

    public void setComboBoxCaloriiAdaug(JComboBox comboBoxCaloriiAdaug) {
        this.comboBoxCaloriiAdaug = comboBoxCaloriiAdaug;
    }

    public JComboBox getComboBoxProteineAdaug() {
        return comboBoxProteineAdaug;
    }

    public void setComboBoxProteineAdaug(JComboBox comboBoxProteineAdaug) {
        this.comboBoxProteineAdaug = comboBoxProteineAdaug;
    }

    public JComboBox getComboBoxGrasimeAdaug() {
        return comboBoxGrasimeAdaug;
    }

    public void setComboBoxGrasimeAdaug(JComboBox comboBoxGrasimeAdaug) {
        this.comboBoxGrasimeAdaug = comboBoxGrasimeAdaug;
    }

    public JComboBox getComboBoxSodiuAdaug() {
        return comboBoxSodiuAdaug;
    }

    public void setComboBoxSodiuAdaug(JComboBox comboBoxSodiuAdaug) {
        this.comboBoxSodiuAdaug = comboBoxSodiuAdaug;
    }

    public JButton getAdaugareButton() {
        return adaugareButton;
    }

    public void setAdaugareButton(JButton adaugareButton) {
        this.adaugareButton = adaugareButton;
    }

    public JButton getInapoiButton() {
        return inapoiButton;
    }

    public void setInapoiButton(JButton inapoiButton) {
        this.inapoiButton = inapoiButton;
    }

    public JButton getInapoiButton1() {
        return inapoiButton1;
    }

    public void setInapoiButton1(JButton inapoiButton1) {
        this.inapoiButton1 = inapoiButton1;
    }

    public JComboBox getComboBoxNumeModif() {
        return comboBoxNumeModif;
    }

    public void setComboBoxNumeModif(JComboBox comboBoxNumeModif) {
        this.comboBoxNumeModif = comboBoxNumeModif;
    }

    public JTextField getTextFieldRatingModif() {
        return textFieldRatingModif;
    }

    public void setTextFieldRatingModif(JTextField textFieldRatingModif) {
        this.textFieldRatingModif = textFieldRatingModif;
    }

    public JTextField getTextFieldCaloriiModif() {
        return textFieldCaloriiModif;
    }

    public void setTextFieldCaloriiModif(JTextField textFieldCaloriiModif) {
        this.textFieldCaloriiModif = textFieldCaloriiModif;
    }

    public JTextField getTextFielProteineModif() {
        return textFielProteineModif;
    }

    public void setTextFielProteineModif(JTextField textFielProteineModif) {
        this.textFielProteineModif = textFielProteineModif;
    }

    public JTextField getTextFieldGrasimeModif() {
        return textFieldGrasimeModif;
    }

    public void setTextFieldGrasimeModif(JTextField textFieldGrasimeModif) {
        this.textFieldGrasimeModif = textFieldGrasimeModif;
    }

    public JTextField getTextFieldSodiuModif() {
        return textFieldSodiuModif;
    }

    public void setTextFieldSodiuModif(JTextField textFieldSodiuModif) {
        this.textFieldSodiuModif = textFieldSodiuModif;
    }

    public JTextField getTextFieldPretModif() {
        return textFieldPretModif;
    }

    public void setTextFieldPretModif(JTextField textFieldPretModif) {
        this.textFieldPretModif = textFieldPretModif;
    }

    public JButton getInapoiButton2() {
        return inapoiButton2;
    }

    public void setInapoiButton2(JButton inapoiButton2) {
        this.inapoiButton2 = inapoiButton2;
    }

    public JButton getModificareButton() {
        return modificareButton;
    }

    public void setModificareButton(JButton modificareButton) {
        this.modificareButton = modificareButton;
    }

    public JComboBox getComboBoxPretAdaug() {
        return comboBoxPretAdaug;
    }

    public void setComboBoxPretAdaug(JComboBox comboBoxPretAdaug) {
        this.comboBoxPretAdaug = comboBoxPretAdaug;
    }
}

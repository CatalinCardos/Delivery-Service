package BusinessLogic;

import DataAccess.DataWriter;
import DataAccess.Serializator;
import Gui.Administrator;
import Gui.Client;
import Gui.Employee;
import Gui.Login;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;


/**
 * @invariant isWellFormed();
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing{
    private Employee employee;
    private Login login;
    private Client client;
    private Administrator administrator;
    private JFrame frame;
    Service service;
    private ArrayList<BasedProduct> basedProductsComanda;
    private ArrayList<CompositeProduct> compositeProductsComanda;
    private ArrayList<MenuItem> menuItems;
    private long totalPrice = 0;
    private int idOrderUniq = 0;
    private int idUniq = 1;
    private ClientData mainClient;
    private ArrayList<String> itemsName;
    private Serializator serializator;
    private List<Observer> observers;
    public DeliveryService() {
        employee = new Employee();
        login = new Login();
        client = new Client();
        service = new Service();
        observers = new ArrayList<>();
        serializator = new Serializator();
        administrator = new Administrator();
        basedProductsComanda = new ArrayList<>();
        compositeProductsComanda = new ArrayList<>();
        menuItems = new ArrayList<>();
        mainClient = new ClientData();
        service = (Service) serializator.readData("file.ser");
        assert service != null;
        idUniq = service.getClients().size() + 1;
        idOrderUniq = service.getOrders().size();
/*        service.getOrders().clear();
        serializator.writeData("file.ser",service);*/
        frame = new JFrame("Shop");
        frame.setContentPane(login.getMainPanelLogin());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        login.addbuttonLogareListener(new ButtonLogare());
        login.addinregistrareButtonListener(new ButtonInregistrareClient());
        login.addbuttonInregistrareListener(new ButtonInregistrare());
        login.addbuttonInapoiListener(new ButtonInapoi());
        administrator.addbuttonadaugareProdusInMeniuListener(new ButtonAdaugareProdusInMeniu());
        administrator.addbuttonstergereProdusDinMeniuListener(new ButtonStergereProdusInMeniu());
        administrator.addbuttonmodificareProdusDinMeniuListener(new ButtonModificareProdusInMeniu());
        administrator.addbuttoncreareMeniuListener(new ButtonCreareMeniu());
        administrator.addbinapoiButtonListener(new ButtonInapoiAdmin());
        administrator.addbinapoiButton1Listener(new ButtonInapoiAdmin());
        administrator.addinapoiButton2Listener(new ButtonInapoiAdmin());
        administrator.addinapoiButton3Listener(new ButtonInapoiAdmin());
        administrator.addstergeProdDinMenuButtonListener(new ButtonDeleteTheProd());
        administrator.addmodificareButtonListener(new ButtonModifForProd());
        administrator.addcomboBoxNumeModifListener(new ComboBoxSelected());
        administrator.addadaugareButtonListener(new AdaugareProdusInMeniu());
        administrator.adddelogareButtonListener(new Delogare());
        administrator.addcomboBoxListaDeProdListener(new AdaugareDetaliPeLista());
        administrator.addbuttonAdaugaInListaListener(new AdaugaProdusInLista());
        administrator.addbuttonStergeDePeListaListener(new StergereProdusDInLista());
        administrator.addbuttonAdaugaMeniuListener(new AdaugareMeniu());
        administrator.addincarcareProduseButtonListener(new ButtonIncarcareProduse());
        administrator.addbuttonRaportListener(new ButtonRaport());
        administrator.addcomboBoxRaportListener(new ComboBoxRaport());
        administrator.addgenerareRaportButtonListener(new GenerareRaport());
        client.addbuttonDelogareClientListener(new Delogare());
        client.addcomboBoxAdaugaMancareListener(new AdaugareDetaliiListaProduse());
        client.addcomboBoxAdaugaMancareMeniuListener(new AdaugareDetaliiListaMeniu());
        client.addbuttonCautareNumeListener(new CautareInFunctieDeNume());
        client.addbuttonFilterListener(new FiltrareaProduselor());
        client.addbuttonAdaugaListener(new AdaugaLaComandaPsodusul());
        client.addbuttonAdauga2Listener(new AdaugaLaComandaMeniul());
        client.addstergeDinListaButtonListener(new StergereDinListaDeComanda());
        client.addbuttonComandaListener(new ComandaProdus());
        employee.adddelogareButtonListener(new Delogare());
        addObserver(employee);
        assert isWellFormed();
    }
    protected boolean isWellFormed()
    {
        if(this.employee == null){
            return false;
        }
        if(this.login == null) {
            return false;
        }
        if(this.client == null)
            return false;
        if(this.service == null)
            return false;
        if(this.administrator == null)
            return false;
        if(this.basedProductsComanda == null)
            return false;
        if(this.compositeProductsComanda == null)
            return false;
        if(this.totalPrice != 0)
            return false;
        if(this.mainClient == null)
            return false;
        if(this.idOrderUniq < 0)
            return false;
        if(this.idUniq < 2)
            return false;
        return true;

    }
    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> it = observers.iterator();
        while (it.hasNext()) {
            Observer observer = it.next();
            observer.update(null, this);
        }
    }

    /**
     * Metoda genereaza raportul pentru administrator in legatura cu comenzile care se fac intr-un anumit interval de timp
     * Raportul este scris intr-un textArea aflat in interfata administratorului
     * @pre if (matcher.matches() && mathcer2.matches)
     * @post administrator.getTextAreaRaport().getText().equals("")
     */
    @Override
    public void intervalOfHour()
    {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(administrator.getTextFieldRaport1().getText());
        Matcher matcher2 = p.matcher(administrator.getTextFieldRaport2().getText());
        administrator.getTextAreaRaport().setText("");
        assert matcher.matches() && matcher2.matches();
        if(matcher.matches() && matcher2.matches())
        {

            service.getOrders().keySet().stream().filter(e -> e.getOrderDate().getHour() >= Integer.parseInt(administrator.getTextFieldRaport2().getText()) && e.getOrderDate().getHour() <= Integer.parseInt(administrator.getTextFieldRaport1().getText())).forEach(e ->administrator.getTextAreaRaport().append("Order cu id-ul: " + e.getOrderID() + "\n"));

        }
        else {
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu sa facut scrierea corecta a parametrilor de intrare!");
        }
        if(administrator.getTextAreaRaport().getText().equals("")){
            assert administrator.getTextAreaRaport().getText().equals("");
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu s-a gasit nicio comanda!");
        }
    }

    /**
     * Metoda genereaza raportul pentru administrator in legatura cu produsele care au fost comandate de un numar specificat de ori
     * Raportul este scris intr-un textArea aflat in interfata administratorului
     * @pre if(matcher2.matches())
     * @post flag == false
     */
    @Override
    public void productOrderedNTimes()
    {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher2 = p.matcher(administrator.getTextFieldRaport2().getText());
        administrator.getTextAreaRaport().setText("");
        Set<MenuItem> set = new HashSet<>();
        ArrayList<MenuItem> menuItems1= new ArrayList<>();
        boolean flag = false;
        if(matcher2.matches())
        {
            for (Order order: service.getOrders().keySet()) {
                set.addAll(service.getOrders().get(order));
                menuItems1.addAll(service.getOrders().get(order));
            }
            for (MenuItem item: set) {
                if(Collections.frequency(menuItems1,item) >= Integer.parseInt(administrator.getTextFieldRaport2().getText())){
                    administrator.getTextAreaRaport().append("Produsul cu numele: " + item.getTitle() + "\n");
                    flag = true;
                }

            }
        }
        else {
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu sa facut scrierea corecta a parametrilor de intrare!");
        }
        if(!flag){
            assert !flag;
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu s-a gasit niciun produs!");
        }
    }

    /**
     * Metoda genereaza raportul pentru administrator in legatura cu clientii care au comandat mai mult de un numar specificat de ori
     * iar valoarea comenzii a fost mai mare decat o valoare specificata
     * Raportul este scris intr-un textArea aflat in interfata administratorului
     * @pre if(matcher2.matches() && matcher.matches())
     * @post administrator.getTextFieldRaport1().getText().equals("");
     */
    @Override
    public void ordersOfTimeAndValue()
    {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(administrator.getTextFieldRaport1().getText());
        Matcher matcher2 = p.matcher(administrator.getTextFieldRaport2().getText());
        administrator.getTextAreaRaport().setText("");
        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> clientsID= new ArrayList<>();
        assert matcher2.matches() && matcher.matches();
        if(matcher2.matches() && matcher.matches())
        {
            for (Order order: service.getOrders().keySet()) {
                set.add(order.getIdClient());
                clientsID.add(order.getIdClient());
            }
            for (Integer item: set) {
                if(Collections.frequency(clientsID,item) >= Integer.parseInt(administrator.getTextFieldRaport2().getText())){
                    if(service.getOrders().keySet().stream().anyMatch(e -> e.getIdClient() == item && e.getTotalPrice() >= Integer.parseInt(administrator.getTextFieldRaport1().getText())))
                    {
                        administrator.getTextAreaRaport().append("Clientul: " + service.getClients().get(item - 1).getName() + "\n");
                    }

                }
            }
        }
        else {
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu sa facut scrierea corecta a parametrilor de intrare!");
        }

        if(administrator.getTextFieldRaport1().getText().equals("")){
            assert administrator.getTextFieldRaport1().getText().equals("");
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu s-a gasit niciun client!");
        }
    }

    /**
     * Metoda genereaza raportul pentru administrator in legatura cu comenzile care s-au facut intr-o anumita zi a lunii.
     * Raportul este scris intr-un textArea aflat in interfata administratorului
     * @pre if(matcher2.matches())
     * @post flag == false
     */
    @Override
    public void orderDay()
    {
        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher2 = p.matcher(administrator.getTextFieldRaport2().getText());
        administrator.getTextAreaRaport().setText("");
        Set<MenuItem> set = new HashSet<>();
        ArrayList<MenuItem> menuItems1= new ArrayList<>();
        boolean flag = false;
        assert matcher2.matches();
        if(matcher2.matches()) {
            service.getOrders().keySet().stream().filter(e -> e.getOrderDate().getDayOfMonth() == Integer.parseInt(administrator.getTextFieldRaport2().getText())).forEach(e -> set.addAll(service.getOrders().get(e)));
            service.getOrders().keySet().stream().filter(e -> e.getOrderDate().getDayOfMonth() == Integer.parseInt(administrator.getTextFieldRaport2().getText())).forEach(e -> menuItems1.addAll(service.getOrders().get(e)));

            if (!menuItems1.isEmpty()) {
                for (MenuItem item : set) {
                        administrator.getTextAreaRaport().append("Produsul cu numele: " + item.getTitle() + " fiind comandat de " + Collections.frequency(menuItems1, item) + "\n");
                        flag = true;
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu sa facut scrierea corecta a parametrilor de intrare!");
        }
        if(!flag){
            JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nu s-a gasit niciun produs!");
        }
    }

    class GenerareRaport implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Interval de timp pentru comenzi")){
                intervalOfHour();
            }
            else
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Produsele comandate de un numar specificat")){
                productOrderedNTimes();
            }
            else
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Clienti care au comandat mai mult de un numar de ori si valoare specificata")){
                ordersOfTimeAndValue();
            }
            else
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Produsele comandate dintr-o zi specificata")){
                orderDay();
            }
        }
    }
    class ComboBoxRaport implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Interval de timp pentru comenzi")){
                administrator.getTextFieldRaport1().setVisible(true);
                administrator.getTextFieldRaport2().setVisible(true);
                administrator.getLabelRaport2().setVisible(true);
                administrator.getLabelRaport1().setVisible(true);
                administrator.getLabelRaport2().setText("Ora de inceput");
                administrator.getLabelRaport1().setText("Ora finala");
            }
            else
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Produsele comandate de un numar specificat")){
                administrator.getTextFieldRaport1().setVisible(false);
                administrator.getTextFieldRaport2().setVisible(true);
                administrator.getLabelRaport2().setVisible(true);
                administrator.getLabelRaport1().setVisible(false);
                administrator.getLabelRaport2().setText("Valoarea specificata");
            }
            else
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Clienti care au comandat mai mult de un numar de ori si valoare specificata")){
                administrator.getTextFieldRaport1().setVisible(true);
                administrator.getTextFieldRaport2().setVisible(true);
                administrator.getLabelRaport2().setVisible(true);
                administrator.getLabelRaport1().setVisible(true);
                administrator.getLabelRaport2().setText("Numarul de comezi");
                administrator.getLabelRaport1().setText("Valoarea specificata");
            }
            else
            if(administrator.getComboBoxRaport().getSelectedItem().toString().equals("Produsele comandate dintr-o zi specificata")){
                administrator.getTextFieldRaport1().setVisible(false);
                administrator.getTextFieldRaport2().setVisible(true);
                administrator.getLabelRaport2().setVisible(true);
                administrator.getLabelRaport1().setVisible(false);
                administrator.getLabelRaport2().setText("Ziua specificata");
            }
        }
    }
    class ButtonRaport implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            administrator.getPanelMainAdmin().removeAll();
            administrator.getPanelMainAdmin().add(administrator.getPanelRaport());
            administrator.getPanelMainAdmin().repaint();
            administrator.getPanelMainAdmin().validate();
        }
    }
    class ComandaProdus implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            assert !basedProductsComanda.isEmpty();
            if(basedProductsComanda.isEmpty() && compositeProductsComanda.isEmpty()){
                JOptionPane.showMessageDialog(client.getPanelMainClient(),"Nu ati adaugat nimic pentru a putea face comanda!");
            }
            else
            {
                LocalDateTime sdf =  LocalDateTime.now();
                Order order = null;
                order = new Order(idOrderUniq + 1, mainClient.getId(), sdf);
                idOrderUniq++;
                ArrayList<MenuItem> lastMenu = new ArrayList<>();
                if(basedProductsComanda != null){
                    lastMenu.addAll(basedProductsComanda);
                }
                if(compositeProductsComanda != null){
                    lastMenu.addAll(compositeProductsComanda);
                }
                Set<String> comanda = new HashSet<>();
                for (MenuItem item: lastMenu) {
                    comanda.add(item.getTitle());
                }
                order.setTotalPrice(Integer.parseInt(client.getTextFieldTotal().getText()));
                service.getOrders().put(order,lastMenu);
                basedProductsComanda = new ArrayList<>();
                compositeProductsComanda = new ArrayList<>();
                itemsName = new ArrayList<>();
                client.getListComanda().setListData(itemsName.toArray());
                client.getTextFieldTotal().setText("");
                JOptionPane.showMessageDialog(client.getPanelMainClient(),"Comanda a fost inregistrata cu succes!");
                serializator.writeData("file.ser",service);
                DataWriter dataWriter = new DataWriter();

                dataWriter.makeTheBill(order.getOrderID(), mainClient.getName(),comanda,sdf,order.getTotalPrice());
                notifyObservers();
            }
        }
    }

    class ButtonIncarcareProduse implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {

                BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\catal\\Downloads\\products.csv"));
                List<BasedProduct> importedProducts = new ArrayList<>();
                String splitBy = ",";
                importedProducts = br.lines().skip(1).map(
                        line ->{
                            String[] result = line.split(splitBy);
                            BasedProduct basedProduct = new BasedProduct(result[0],Float.parseFloat(result[1]),Integer.parseInt(result[2]),Integer.parseInt(result[3]),
                                    Integer.parseInt(result[4]),Integer.parseInt(result[5]),Integer.parseInt(result[6]));
                            return basedProduct;
                        }
                ).collect(toList());
                importedProducts = importedProducts.stream().collect(collectingAndThen(toCollection(()->
                        new TreeSet<>(Comparator.comparing(MenuItem::getTitle))), ArrayList::new));
                importedProducts.sort(Comparator.comparing(MenuItem::getTitle));
                service.getBasedProductArray().addAll(importedProducts);
                serializator.writeData("file.ser",service);


            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    class StergereDinListaDeComanda implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BasedProduct basedProduct = null;
            for (BasedProduct item: basedProductsComanda) {
                if(item.getTitle().equals(client.getListComanda().getSelectedValue().toString()))
                {
                    basedProduct = item;
                    break;
                }
            }
            CompositeProduct compositeProduct = null;
            for (CompositeProduct item: compositeProductsComanda) {
                if(item.getTitle().equals(client.getListComanda().getSelectedValue().toString()))
                {
                    compositeProduct = item;
                    break;
                }
            }
            if(compositeProduct == null)
            {
                basedProductsComanda.remove(basedProduct);
                totalPrice -= basedProduct.getPrice();
                client.getTextFieldTotal().setText(String.valueOf(totalPrice));
            }
            else{
                compositeProductsComanda.remove(compositeProduct);
                totalPrice -= compositeProduct.getPrice();
                client.getTextFieldTotal().setText(String.valueOf(totalPrice));
            }
            itemsName.remove(client.getListComanda().getSelectedValue().toString());
            client.getListComanda().setListData(itemsName.toArray());

        }
    }
    class AdaugaLaComandaMeniul implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CompositeProduct compositeProduct = null;
            for (CompositeProduct item: service.getCompositeProducts()) {
                if(item.getTitle().equals(client.getComboBoxAdaugaMancareMeniu().getSelectedItem().toString()))
                {
                    compositeProduct = item;
                    break;
                }
            }
            itemsName.add(compositeProduct.getTitle());
            client.getListComanda().setListData(itemsName.toArray());
            compositeProductsComanda.add(compositeProduct);
            totalPrice += compositeProduct.getPrice();
            client.getTextFieldTotal().setText(String.valueOf(totalPrice));

        }
    }
    class AdaugaLaComandaPsodusul implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem menuItem = findByName(client.getComboBoxAdaugaMancare().getSelectedItem().toString());
            itemsName.add(menuItem.getTitle());
            client.getListComanda().setListData(itemsName.toArray());
            basedProductsComanda.add((BasedProduct) menuItem);
            totalPrice += menuItem.getPrice();
            client.getTextFieldTotal().setText(String.valueOf(totalPrice));

        }
    }

    class FiltrareaProduselor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            client.getComboBoxAdaugaMancare().removeAllItems();
            if(client.getComboBoxFilter().getSelectedItem().toString().equals("Rating"))
            {
                service.getBasedProductArray().stream().filter(x -> x.getRating() == Float.parseFloat(client.getTextFieldNumarFiltru().getText())).forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
            else
            if(client.getComboBoxFilter().getSelectedItem().toString().equals("Calorii"))
            {
                service.getBasedProductArray().stream().filter(x -> x.getCalories() == Integer.parseInt(client.getTextFieldNumarFiltru().getText())).forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
            else
            if(client.getComboBoxFilter().getSelectedItem().toString().equals("Proteina"))
            {
                service.getBasedProductArray().stream().filter(x -> x.getProtein() == Integer.parseInt(client.getTextFieldNumarFiltru().getText())).forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
            else
            if(client.getComboBoxFilter().getSelectedItem().toString().equals("Grasime"))
            {
                service.getBasedProductArray().stream().filter(x -> x.getFat() == Integer.parseInt(client.getTextFieldNumarFiltru().getText())).forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
            else
            if(client.getComboBoxFilter().getSelectedItem().toString().equals("Sodiu"))
            {
                service.getBasedProductArray().stream().filter(x -> x.getSodium() == Integer.parseInt(client.getTextFieldNumarFiltru().getText())).forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
            else
            if(client.getComboBoxFilter().getSelectedItem().toString().equals("Pret"))
            {
                service.getBasedProductArray().stream().filter(x -> x.getPrice() == Integer.parseInt(client.getTextFieldNumarFiltru().getText())).forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
            else
            {
                service.getBasedProductArray().forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }

        }
    }
    class CautareInFunctieDeNume implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!client.getTextFieldCautaNume().getText().equals("")) {
                client.getComboBoxAdaugaMancare().removeAllItems();
                service.getBasedProductArray().stream().filter(x -> x.getTitle().contains(client.getTextFieldCautaNume().getText())).forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
            else
            {   client.getComboBoxAdaugaMancare().removeAllItems();
                service.getBasedProductArray().forEach(x -> client.getComboBoxAdaugaMancare().addItem(x.getTitle()));
            }
        }
    }

    class AdaugareDetaliiListaProduse implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(client.getComboBoxAdaugaMancare().getSelectedItem() != null)
            {
                MenuItem myItem = findByName(client.getComboBoxAdaugaMancare().getSelectedItem().toString());
                client.getTextAreaProduse().setText(builderOfDetails(myItem));
            }

        }
    }
    class AdaugareDetaliiListaMeniu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(client.getComboBoxAdaugaMancareMeniu() != null){
                CompositeProduct compositeProduct = null;
                for (CompositeProduct item: service.getCompositeProducts()) {
                    if(item.getTitle().equals(client.getComboBoxAdaugaMancareMeniu().getSelectedItem().toString()))
                    {
                        compositeProduct = item;
                        break;
                    }
                }
                assert compositeProduct != null;
                client.getTextAreaMeniuri().setText(builderOfDetails(compositeProduct));
            }

        }
    }

    /**
     * Creaza un string care sa arate fiecare detaliu al unu produs
     * @param myItem produs din care sa luam datele
     * @return text care prezinta datele produsului
     * @pre myItem == null
     */
    @Override
    public String builderOfDetails (MenuItem myItem)
    {
        assert myItem == null;
        StringBuilder string = new StringBuilder();
        string.append("Rating: ");
        string.append(myItem.getRating()).append(" \n");
        string.append("Calorii: ");
        string.append(myItem.getCalories()).append(" \n");
        string.append("Proteine: ");
        string.append(myItem.getProtein()).append(" \n");
        string.append("Grasime: ");
        string.append(myItem.getFat()).append(" \n");
        string.append("Sodiu: ");
        string.append(myItem.getSodium()).append(" \n");
        string.append("Pret: ");
        string.append(myItem.getPrice()).append(" \n");
        return  string.toString();
    }
    class AdaugareMeniu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(!Objects.equals(administrator.getTextFieldNumeMeniu().getText(), ""))
            {
                CompositeProduct compProd = new CompositeProduct();
                for (MenuItem item: menuItems) {
                    compProd.getMenuItem().add(item);
                }
                compProd.updateMenu();
                compProd.setTitle(administrator.getTextFieldNumeMeniu().getText());
                service.getCompositeProducts().add(compProd);
                administrator.getTextFieldNumeMeniu().setText("");
                menuItems = new ArrayList<>();
                itemsName = new ArrayList<>();
                administrator.getListProduseAlese().setListData(itemsName.toArray());
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Adugarea s-a facut cu succes!");
            }
            else
            {
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Nume lipsa!");
            }
            serializator.writeData("file.ser",service);


        }
    }
    class StergereProdusDInLista implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem myItem = findByName(administrator.getListProduseAlese().getSelectedValue().toString());
            itemsName.remove(administrator.getListProduseAlese().getSelectedValue().toString());
            administrator.getListProduseAlese().setListData(itemsName.toArray());
            menuItems.remove(myItem);
        }
    }
    class AdaugaProdusInLista implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem myItem = findByName(administrator.getComboBoxListaDeProd().getSelectedItem().toString());

            menuItems.add(myItem);
            itemsName= new ArrayList<String>();
            for (MenuItem item:menuItems) {
                itemsName.add(item.getTitle());
            }
            administrator.getListProduseAlese().setListData(itemsName.toArray());
        }
    }

    class AdaugareDetaliPeLista implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem myItem = findByName(administrator.getComboBoxListaDeProd().getSelectedItem().toString());
            administrator.getTextAreaDetaliiProdus().setText(builderOfDetails(myItem));

        }
    }
    class Delogare implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setContentPane(login.getMainPanelLogin());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }
    class AdaugareProdusInMeniu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String regex = "\\d+[.]\\d+";
            Pattern p = Pattern.compile(regex);
            Matcher mRating = p.matcher(administrator.getTextFieldRatingAdaug().getText());
            if(mRating.matches())
            {
                BasedProduct newItem = new BasedProduct(administrator.getTextFieldNumeAdaug().getText(),Float.parseFloat(administrator.getTextFieldRatingAdaug().getText()),Integer.parseInt(administrator.getComboBoxCaloriiAdaug().getSelectedItem().toString()),
                        Integer.parseInt(administrator.getComboBoxProteineAdaug().getSelectedItem().toString()),Integer.parseInt(administrator.getComboBoxGrasimeAdaug().getSelectedItem().toString()),Integer.parseInt(administrator.getComboBoxSodiuAdaug().getSelectedItem().toString()),
                        Integer.parseInt(administrator.getComboBoxPretAdaug().getSelectedItem().toString()));
                administrator.getTextFieldNumeAdaug().setText("");
                administrator.getTextFieldRatingAdaug().setText("");
                administrator.getComboBoxCaloriiAdaug().repaint();
                service.getBasedProductArray().add(newItem);
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Adaugarea a fost realizata cu succes!");


            }
            else
            {
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Adaugarea a esuat din cauza rating-ului!");
                administrator.getTextFieldRatingAdaug().setText("");
            }
            serializator.writeData("file.ser",service);
        }
    }

    /**
     * Gaseste produsul care are numele introdus
     * @param name numele unui produs
     * @return  un produs ce are numele parametrului de intrare
     * @pre name == null
     * @post service.getBasedProductArray() sa nu aiba produs cu numele dat
     */
    @Override
    public MenuItem findByName(String name)
    {
        assert name == null;
        for (MenuItem aux : service.getBasedProductArray()) {
            if(Objects.equals(aux.getTitle(), name))
                return aux;
        }
        assert true;
        return null;
    }
    class ComboBoxSelected implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem myItem = findByName(administrator.getComboBoxNumeModif().getSelectedItem().toString());
            administrator.getTextFieldRatingModif().setText(myItem.getRating().toString());
            administrator.getTextFieldCaloriiModif().setText(myItem.getCalories().toString());
            administrator.getTextFieldGrasimeModif().setText(myItem.getFat().toString());
            administrator.getTextFielProteineModif().setText(myItem.getProtein().toString());
            administrator.getTextFieldSodiuModif().setText(myItem.getSodium().toString());
            administrator.getTextFieldPretModif().setText(myItem.getPrice().toString());

        }
    }

    class ButtonModifForProd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem auxItem = findByName(administrator.getComboBoxNumeModif().getSelectedItem().toString());
            MenuItem myItem = new MenuItem();
            String regex = "\\d+[.]\\d+";
            String regex2 = "\\d+";
            Pattern p = Pattern.compile(regex);
            Pattern p2 = Pattern.compile(regex2);
            Matcher mRating = p.matcher(administrator.getTextFieldRatingModif().getText());
            Matcher mCalorii = p2.matcher(administrator.getTextFieldCaloriiModif().getText());
            Matcher mProteine = p2.matcher(administrator.getTextFielProteineModif().getText());
            Matcher mGrasime = p2.matcher(administrator.getTextFieldGrasimeModif().getText());
            Matcher mSodiu = p2.matcher(administrator.getTextFieldSodiuModif().getText());
            Matcher mPret = p2.matcher(administrator.getTextFieldPretModif().getText());
            boolean noError = true;
            myItem.setTitle(auxItem.getTitle());
            if(mRating.matches() && !Objects.equals(administrator.getTextFieldRatingModif().getText(), "") && Float.parseFloat(administrator.getTextFieldRatingModif().getText()) <= 5.0){
                myItem.setRating(Float.parseFloat(administrator.getTextFieldRatingModif().getText()));
            }
            else
            {
                noError = false;
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Eroare la modificarea rating-ului!");
            }
            if(mCalorii.matches() && !Objects.equals(administrator.getTextFieldCaloriiModif().getText(), "") && noError){
                myItem.setCalories(Integer.parseInt(administrator.getTextFieldCaloriiModif().getText()));
            }
            else
            {
                noError = false;
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Eroare la modificarea caloriilor!");
            }
            if(mProteine.matches() && !Objects.equals(administrator.getTextFieldPretModif().getText(), "") && noError){
                myItem.setProtein(Integer.parseInt(administrator.getTextFieldPretModif().getText()));
            }
            else
            {
                noError = false;
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Eroare la modificarea proteinelor!");
            }
            if(mGrasime.matches() && !Objects.equals(administrator.getTextFieldGrasimeModif().getText(), "") && noError){
                myItem.setFat(Integer.parseInt(administrator.getTextFieldGrasimeModif().getText()));
            }
            else
            {
                noError = false;
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Eroare la modificarea grasimii!");
            }
            if(mSodiu.matches() && !Objects.equals(administrator.getTextFieldSodiuModif().getText(), "") && noError){
                myItem.setSodium(Integer.parseInt(administrator.getTextFieldSodiuModif().getText()));
            }
            else
            {
                noError = false;
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Eroare la modificarea sodiului!");
            }
            if(mPret.matches() && !Objects.equals(administrator.getTextFieldPretModif().getText(), "") && noError){
                myItem.setPrice(Integer.parseInt(administrator.getTextFieldPretModif().getText()));
            }
            else
            {
                noError = false;
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Eroare la modificare pretului!");
            }
            if(noError)
            {
                auxItem.setSodium(myItem.getSodium());
                auxItem.setProtein(myItem.getProtein());
                auxItem.setFat(myItem.getFat());
                auxItem.setPrice(myItem.getPrice());
                auxItem.setCalories(myItem.getCalories());
                auxItem.setRating(myItem.getRating());
                JOptionPane.showMessageDialog(administrator.getPanelMainAdmin(),"Modificarea a fost facuta cu succes");
                administrator.getPanelMainAdmin().removeAll();
                administrator.getPanelMainAdmin().add(administrator.getPanelOfButtons());
                administrator.getPanelMainAdmin().repaint();
                administrator.getPanelMainAdmin().validate();
            }
        }
    }

    class ButtonDeleteTheProd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            service.getBasedProductArray().removeIf(n -> (Objects.equals(n.getTitle(), administrator.getComboBoxMenuStergere().getSelectedItem().toString())));
            serializator.writeData("file.ser",service);
            administrator.getComboBoxMenuStergere().removeAllItems();
            updateMenuForComboBox(administrator.getComboBoxMenuStergere());
        }
    }

    /**
     * Actualizaeaza un comboBox
     * @param e un comboBox din interfata
     * @pre e == null
     * @post e.isEmpty
     */
    @Override
    public void updateMenuForComboBox (JComboBox e)
    {
        assert e == null;
        for (int i = 0; i < service.getBasedProductArray().size(); i++) {
            e.addItem(service.getBasedProductArray().get(i).getTitle());
        }
        assert e.getSelectedItem().toString().equals("");
    }
    class ButtonInapoiAdmin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            administrator.getPanelMainAdmin().removeAll();
            administrator.getPanelMainAdmin().add(administrator.getPanelOfButtons());
            administrator.getPanelMainAdmin().repaint();
            administrator.getPanelMainAdmin().validate();
        }
    }

    class ButtonCreareMeniu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            administrator.getPanelMainAdmin().removeAll();
            administrator.getPanelMainAdmin().add(administrator.getPanelCreateMenu());
            administrator.getPanelMainAdmin().repaint();
            administrator.getPanelMainAdmin().validate();
            updateMenuForComboBox(administrator.getComboBoxListaDeProd());
            menuItems = new ArrayList<>();

        }
    }

    class ButtonModificareProdusInMeniu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            administrator.getPanelMainAdmin().removeAll();
            administrator.getPanelMainAdmin().add(administrator.getPanelModProdToMenu());
            administrator.getPanelMainAdmin().repaint();
            administrator.getPanelMainAdmin().validate();
            updateMenuForComboBox(administrator.getComboBoxNumeModif());
        }
    }

    class ButtonStergereProdusInMeniu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            administrator.getPanelMainAdmin().removeAll();
            administrator.getPanelMainAdmin().add(administrator.getPanelDeleteProdToMenu());
            administrator.getPanelMainAdmin().repaint();
            administrator.getPanelMainAdmin().validate();
            updateMenuForComboBox(administrator.getComboBoxMenuStergere());
        }
    }

    class ButtonAdaugareProdusInMeniu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            administrator.getPanelMainAdmin().removeAll();
            administrator.getPanelMainAdmin().add(administrator.getPanelAddProdToMenu());
            administrator.getPanelMainAdmin().repaint();
            administrator.getPanelMainAdmin().validate();
        }
    }

    class ButtonInregistrareClient implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean flag = false;
            if(Objects.equals(login.getTextFieldPassInreg().getText(), login.getTextFieldPassInregConf().getText()))
            {
                for (ClientData clientData: service.getClients()) {
                    if(Objects.equals(login.getTextFieldNumeInreg().getText(), clientData.getName())){
                        JOptionPane.showMessageDialog(frame,"Acest nume exista deja!");
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    service.getClients().add(new ClientData(login.getTextFieldNumeInreg().getText(),login.getTextFieldPassInreg().getText(),idUniq));
                    idUniq++;
                    JOptionPane.showMessageDialog(frame,"Inregistrare cu succes!");
                    login.getMainPanelLogin().removeAll();
                    login.getMainPanelLogin().add(login.getPanelLogare());
                    login.getMainPanelLogin().repaint();
                    login.getMainPanelLogin().validate();

                }
            }
            else
            {
                JOptionPane.showMessageDialog(frame,"Parolele nu coincid!");
            }
            serializator.writeData("file.ser",service);

        }
    }
    class ButtonInapoi implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            login.getMainPanelLogin().removeAll();
            login.getMainPanelLogin().add(login.getPanelLogare());
            login.getMainPanelLogin().repaint();
            login.getMainPanelLogin().validate();
        }
    }
    class ButtonInregistrare implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            login.getMainPanelLogin().removeAll();
            login.getMainPanelLogin().add(login.getPanelInregistrare());
            login.getMainPanelLogin().repaint();
            login.getMainPanelLogin().validate();
            login.getTextFieldNumeInreg().setText("");
            login.getTextFieldPassInreg().setText("");
            login.getTextFieldPassInregConf().setText("");
        }
    }
    class ButtonLogare implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            service = (Service) serializator.readData("file.ser");
            if(login.getTextFieldNumeLogin().getText().equals("Angajat") && login.getTextFieldParolaLogin().getText().equals("angajat"))
            {
                login.getTextFieldNumeLogin().setText("");
                login.getTextFieldParolaLogin().setText("");
                frame.setContentPane(employee.getPanel1());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("IDOrder");
                model.addColumn("Nume client");
                model.addColumn("Data");
                model.addColumn("Comanda");
                model.addColumn("Pret");
                employee.getTableEmployee().setModel(model);
                employee.getTableEmployee().setRowHeight(30);
                employee.getTableEmployee().setFillsViewportHeight(true);
                employee.getTableEmployee().setVisible(true);

                for (Order order: service.getOrders().keySet()) {
                    Vector<String> stringVector = new Vector<>();
                    stringVector.add(String.valueOf(order.getOrderID()));

                    stringVector.add(service.getClients().get(order.getIdClient() - 1).getName());
                    stringVector.add(String.valueOf(order.getOrderDate()));

                    StringBuilder stringBuilder = new StringBuilder();
                    for (MenuItem item: service.getOrders().get(order)) {
                        stringBuilder.append(item.getTitle() + ", ");
                    }
                    stringVector.add(stringBuilder.toString());
                    stringVector.add(String.valueOf(order.getTotalPrice()));
                    model.addRow(stringVector);
                }
            }
            else
            if(login.getTextFieldNumeLogin().getText().equals("Administrator") && login.getTextFieldParolaLogin().getText().equals("administrator"))
            {
                login.getTextFieldNumeLogin().setText("");
                login.getTextFieldParolaLogin().setText("");
                frame.setContentPane(administrator.getPanelMainAdmin());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
            else {
                boolean flag = false;
                for (ClientData clientsData : service.getClients()) {
                    if (clientsData.getName().equals(login.getTextFieldNumeLogin().getText()) && clientsData.getPass().equals(login.getTextFieldParolaLogin().getText())) {
                        login.getTextFieldNumeLogin().setText("");
                        login.getTextFieldParolaLogin().setText("");
                        client.getComboBoxAdaugaMancare().removeAllItems();
                        client.getComboBoxAdaugaMancareMeniu().removeAllItems();
                for (BasedProduct item: service.getBasedProductArray()) {
                    client.getComboBoxAdaugaMancare().addItem(item.getTitle());

                }
                        for (CompositeProduct item: service.getCompositeProducts()) {
                            client.getComboBoxAdaugaMancareMeniu().addItem(item.getTitle());
                        }

                        frame.setContentPane(client.getPanelMainClient());
                        itemsName = new ArrayList<>();
                        client.getListComanda().setListData(itemsName.toArray());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setVisible(true);
                        mainClient = clientsData;
                        totalPrice = 0;
                        client.getTextFieldTotal().setText(String.valueOf(totalPrice));
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    JOptionPane.showMessageDialog(login.getMainPanelLogin(),"Nume si parola sunt incorecte!!");
                    login.getTextFieldNumeLogin().setText("");
                    login.getTextFieldParolaLogin().setText("");
                }
            }
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public Login getLogin() {
        return login;
    }

    public Client getClient() {
        return client;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Service getService() {
        return service;
    }

    public ArrayList<BasedProduct> getBasedProductsComanda() {
        return basedProductsComanda;
    }

    public ArrayList<CompositeProduct> getCompositeProductsComanda() {
        return compositeProductsComanda;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public int getIdOrderUniq() {
        return idOrderUniq;
    }

    public int getIdUniq() {
        return idUniq;
    }

    public ClientData getMainClient() {
        return mainClient;
    }

    public ArrayList<String> getItemsName() {
        return itemsName;
    }

    public Serializator getSerializator() {
        return serializator;
    }

    public List<Observer> getObservers() {
        return observers;
    }
}

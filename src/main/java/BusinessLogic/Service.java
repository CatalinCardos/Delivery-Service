package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Service implements Serializable {

    private ArrayList<BasedProduct> basedProductArray;
    private ArrayList<CompositeProduct> compositeProducts;
    private Map<Order,ArrayList<MenuItem>> orders;
    private ArrayList<ClientData> clients;


    public Service() {
        basedProductArray = new ArrayList<>();
        compositeProducts = new ArrayList<>();
        orders = new HashMap<>();
        clients = new ArrayList<>();
    }

    public ArrayList<BasedProduct> getBasedProductArray() {
        return basedProductArray;
    }

    public void setBasedProductArray(ArrayList<BasedProduct> basedProductArray) {
        this.basedProductArray = basedProductArray;
    }

    public ArrayList<CompositeProduct> getCompositeProducts() {
        return compositeProducts;
    }

    public void setCompositeProducts(ArrayList<CompositeProduct> compositeProducts) {
        this.compositeProducts = compositeProducts;
    }

    public Map<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public ArrayList<ClientData> getClients() {
        return clients;
    }

    public void setClients(ArrayList<ClientData> clients) {
        this.clients = clients;
    }
}

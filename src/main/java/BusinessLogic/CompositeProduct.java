package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {
    private ArrayList<MenuItem> menuItem;

    public CompositeProduct() {
        super();
        menuItem = new ArrayList<>();
    }

    public void addProducts(ArrayList<BasedProduct> basedProducts)
    {
        menuItem.addAll(basedProducts);
    }
    public CompositeProduct(String title, Float rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price) {
        super(title, rating, calories, protein, fat, sodium, price);
        menuItem = new ArrayList<>();
    }
    public void updateMenu()
    {
        this.setCalories(0);
        this.setFat(0);
        this.setProtein(0);
        this.setSodium(0);
        this.setPrice(0);
        float rating = 0;
        for (int i = 0; i < menuItem.size(); i++) {
            this.setCalories(menuItem.get(i).getCalories() + this.getCalories());
            this.setFat(menuItem.get(i).getFat() + this.getFat());
            this.setProtein(menuItem.get(i).getProtein() + this.getProtein());
            this.setSodium(menuItem.get(i).getSodium() + this.getSodium());
            rating += menuItem.get(i).getRating();
            this.setPrice(menuItem.get(i).getPrice() + this.getPrice());
        }
        this.setRating(rating/ menuItem.size());
    }

    public ArrayList<MenuItem> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(ArrayList<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }
}

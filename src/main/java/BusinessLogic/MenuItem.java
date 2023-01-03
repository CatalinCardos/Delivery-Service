package BusinessLogic;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class MenuItem implements Serializable {
    private String title;
    private Float rating;
    private Integer calories;
    private Integer protein;
    private Integer fat;
    private Integer sodium;
    private Integer price;

    public MenuItem(String title, Float rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public MenuItem() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static Comparator<MenuItem> RatingComparator = new Comparator<MenuItem>() {

        @Override
        public int compare(MenuItem o1, MenuItem o2) {
            return o1.getRating().compareTo(o2.getRating());
        }

    };
    public static Comparator<MenuItem> CaloriesComparator = new Comparator<MenuItem>() {

        @Override
        public int compare(MenuItem o1, MenuItem o2) {
            return o1.getCalories().compareTo(o2.getCalories());
        }

    };
    public static Comparator<MenuItem> FatComparator = new Comparator<MenuItem>() {

        @Override
        public int compare(MenuItem o1, MenuItem o2) {
            return o1.getFat().compareTo(o2.getFat());
        }

    };
    public static Comparator<MenuItem> SodiumComparator = new Comparator<MenuItem>() {

        @Override
        public int compare(MenuItem o1, MenuItem o2) {
            return o1.getSodium().compareTo(o2.getSodium());
        }

    };
    public static Comparator<MenuItem> PriceComparator = new Comparator<MenuItem>() {

        @Override
        public int compare(MenuItem o1, MenuItem o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }

    };
    public static Comparator<MenuItem> ProteinComparator = new Comparator<MenuItem>() {

        @Override
        public int compare(MenuItem o1, MenuItem o2) {
            return o1.getProtein().compareTo(o2.getProtein());
        }

    };

}

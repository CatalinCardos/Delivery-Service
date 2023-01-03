package BusinessLogic;

import java.io.Serializable;

public class BasedProduct extends MenuItem implements Serializable {
    public BasedProduct(String title, Float rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    public BasedProduct() {
    }

}

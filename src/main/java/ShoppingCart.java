
import java.util.ArrayList;
import java.util.List;

//One of the properties of the shopping cart
//should be the products in the shopping cart
//What data structure would make sense here?
public class ShoppingCart {
    //Be sure to instantiate the list before using it
    private final List<Product> items = new ArrayList<>();

    //These methods are just placeholders, fill them in
    //With real logic
    public void addToCart(Product product){
    items.add(product);
    System.out.println(product.getName() + " added to cart.");
    }

    public void removeFromCart(Product product){
        items.remove(product);
        System.out.println(product.getName() + " removed from cart.");

    }

    public double getCartTotal(){
        double total = 0;
        for(Product p: items){
            total += p.getPrice();
        }
        return total;
    }

    public List<Product> getItems() {
        return items;
    }
}

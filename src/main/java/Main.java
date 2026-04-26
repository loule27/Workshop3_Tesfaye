import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//Want to ask questions? Ask them here
//Want to use the scanner? Use it here
//User input? Also, here. You will have to call the file manager
//and the product class from here.
public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        //1. Use the File Manager to load the file and create a list of products
        //2. Instantiate a shopping cart
        //3. Show the menu to the user
        //4. Based on what they select, use the right method
        //5. Use the shopping cart object you made to add and remove things from the shopping cart
        //6. The key thing is "How am I going to grab the specific item the user wants?"
        List<Product> products = FileManager.getProducts();
        ShoppingCart cart = new ShoppingCart();

        while(true){
            showMenu();
            System.out.println("Enter command(1, 2, 3): ");
            String input = kb.nextLine().trim();

            switch(input){
                case "1":
                    displayProductsScreen(products, cart, kb);
                    break;
                case "2":
                    displayCartScreen(cart, kb);
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
                    break;
            }
        }
    }

    public static void showMenu(){
        System.out.println("------ Store Home Screen ------");
        System.out.println("1. Display Products");
        System.out.println("2. Display Cart");
        System.out.println("3. Exit");
    }

    public static void showAllProducts(List<Product> products){
        System.out.println("\n--- All Products ---");
        for(Product p: products){
            System.out.println(p);
        }
    }

    public static void displayProductsScreen(List<Product> products, ShoppingCart cart, Scanner kb){
        while(true){
            System.out.println("------ Display Products ------");
            System.out.println("1. Show all products");
            System.out.println("2. Search by name");
            System.out.println("3. Filter by price range");
            System.out.println("4. Filter by department");
            System.out.println("5. Add product to cart");
            System.out.println("6. Go back");
            System.out.print("Enter command: ");
            String input = kb.nextLine();
            switch (input) {
                case "1":
                    showAllProducts(products);
                    break;
                case "2":
                    searchProductsByName(products, kb);
                    break;
                case "3":
                    searchProductsByPriceRange(products, kb);
                    break;
                case "4" :
                    searchProductsByCategory(products, kb);
                    break;
                case "5":
                    Product addProduct = selectProductBySku(products, kb);
                    if(addProduct != null){
                        cart.addToCart(addProduct);
                    }
                    else{
                        System.out.println("Product not found");
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option. Try again. ");
            }
        }
    }

    //This method will find the product with the right sku
    //and return that product so later you can add it to the cart
    public static Product selectProductBySku(List<Product> products, Scanner kb){
        //for loops might help!
        while(true) {
            System.out.println("Enter product ID: ");
            String id = kb.nextLine().trim();

            for (Product p : products) {
                if (p.getId().equalsIgnoreCase(id)) {
                    System.out.println("Product found!");
                    System.out.println(p);
                    return p;
                }
            }
            System.out.println("No product found with the id: " +id);
            System.out.println("What would you like to do? ");
            System.out.println("1- Try again: ");
            System.out.println("2- Return to menu");
            String attempt = kb.nextLine().trim();

            while(!attempt.equals("1") && !attempt.equals("2")) {
                System.out.println("\nNot a valid option(Choose 1 or 2)");
                System.out.println("1- Try again: ");
                System.out.println("2- Return to menu");
                attempt = kb.nextLine().trim();
            }
            if(attempt.equals("2")){
                return null;
            }
        }
    }
    public static void searchProductsByName(List<Product> products, Scanner kb){
        while(true) {
            System.out.println("Enter product Name: ");
            String name = kb.nextLine().trim();
            boolean found = false;

            products.sort(Comparator.comparing(Product::getName));
            for (Product p : products) {
                if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(p);
                    found = true;
                }
            }
            if (found) {
               return;
            }
            System.out.println("\nNo product found with the name: " + name);
            System.out.println("What would you like to do? ");
            System.out.println("1- Try again: ");
            System.out.println("2- Return to menu");
            String attempt = kb.nextLine();

            while(!attempt.equals("1") && !attempt.equals("2")){
                    System.out.println("\nNot a valid option(Choose 1 or 2)");
                    System.out.println("1- Try again: ");
                    System.out.println("2- Return to menu");
                    attempt = kb.nextLine();
                }
            if(attempt.equals("2")){
                return;
            }
        }
    }

    public static void searchProductsByPriceRange(List<Product> products, Scanner kb){
        while(true) {
            System.out.println("Enter minimum price: ");
            double min = kb.nextDouble();
            kb.nextLine();
            System.out.println("Enter maximum price: ");
            double max = kb.nextDouble();
            kb.nextLine();

            if (min > max) {
                System.out.println("Minimum value can NOT be greater than maximum value");
                System.out.println("1. Try again");
                System.out.println("2. Go back");
                String choice = kb.nextLine().trim();

                while (!choice.equals("1") && !choice.equals("2")) {
                    System.out.println("Invalid Option(enter 1 or 2): ");
                    choice = kb.nextLine().trim();
                }
                if (choice.equals("2")) {
                    return;
                }
                continue;
            }

            boolean found = false;
            products.sort(Comparator.comparing(Product::getPrice));
            for (Product p : products) {
                if (p.getPrice() >= min && p.getPrice() <= max) {
                    System.out.println(p);
                    found = true;
                }
            }
            if (found) {
                return;
            }
            System.out.println("\nNo price found in the range " +
                    min + " to " + max);
            System.out.println("What would you like to do? ");
            System.out.println("1- Try again: ");
            System.out.println("2- Return to menu");
            String attempt = kb.nextLine().trim();

            while(!attempt.equals("1") && !attempt.equals("2")) {
                System.out.println("\nNot a valid option(Choose 1 or 2)");
                System.out.println("1- Try again: ");
                System.out.println("2- Return to menu");
                attempt = kb.nextLine().trim();
            }
            if (attempt.equals("2")) {
                return;
            }
        }
    }
    public static void searchProductsByCategory(List<Product> products, Scanner kb){
        while(true) {
            System.out.println("Enter product department: ");
            String department = kb.nextLine().trim();
            boolean found = false;
            products.sort(Comparator.comparing(Product::getDepartment));
            for (Product p : products) {
                if (p.getDepartment().toLowerCase().contains(department.toLowerCase())) {
                    System.out.println(p);
                    found = true;
                }
            }
            if (found) {
                return;
            }
            System.out.println("\nNo product found in the department: " + department);
            System.out.println("What would you like to do? ");
            System.out.println("1- Try again: ");
            System.out.println("2- Return to menu");
            String attempt = kb.nextLine();

            while(!attempt.equals("1") && !attempt.equals("2")){
                System.out.println("\nNot a valid option(Choose 1 or 2)");
                System.out.println("1- Try again: ");
                System.out.println("2- Return to menu");
                attempt = kb.nextLine();
            }
            if(attempt.equals("2")){
                return;
            }
        }
    }

    public static void displayCartScreen(ShoppingCart cart, Scanner kb){
        while(true){
            System.out.println("------ Your Cart ------");
            if(cart.getItems().isEmpty()){
                System.out.println("Your cart is empty");
            }
            else{
                checkDuplicates(cart);
                System.out.printf("\nYour cart total is: %.2f%n", cart.getCartTotal());
            }
            System.out.println("\n1. Remove a product");
            System.out.println("2. Check out");
            System.out.println("3. Go back");
            System.out.print("Enter a command: ");
            String input = kb.nextLine().trim();

            switch(input){
                case "1":
                    if(cart.getItems().isEmpty()){
                        System.out.println("Cart is empty, cannot remove.");
                        break;
                    }
                    Product removeFromCart = selectProductBySku(cart.getItems(), kb);

                    if(removeFromCart != null){
                        cart.removeFromCart(removeFromCart);
                    }
                    else{
                        System.out.println("That product is not in your cart");
                    }
                    break;

                case "2":
                    if(cart.getItems().isEmpty()){
                        System.out.println("Cart is empty. Nothing to check out.");
                        break;
                    }
                   checkOut(cart, kb);
                   return;

                case "3":
                    return;

                default:
                    System.out.println("Not a valid option. Try again.");
                    break;
            }
        }
    }

    public static void checkOut(ShoppingCart cart, Scanner kb) {
        double total = cart.getCartTotal();
        LocalDate date = LocalDate.now();

        System.out.println("\n------ Checkout ------");
        System.out.printf("Your total is: $%.2f%n", total);

        while (true) {
            System.out.print("Enter cash amount: $");
            double amount = Double.parseDouble(kb.nextLine().trim());

            if (amount < total) {
                System.out.printf("Not enough. You are $%.2f short.%n",
                        total - amount);
            } else {
                double change = amount - total;

                System.out.println("\n==============================");
                System.out.println("           RECEIPT            ");
                System.out.println("==============================");
                System.out.println("Date: " + date);
                System.out.println("\nItems purchased:");
                checkDuplicates(cart);
                System.out.printf("%nSales Total:  $%.2f%n", total);
                System.out.printf("Amount Paid:  $%.2f%n", amount);
                System.out.printf("Change Owed:  $%.2f%n", change);
                System.out.println("------------------------------");
                System.out.println("Thank you for shopping with us!");

                cart.getItems().clear();
                return;
            }
        }
    }

    public static void checkDuplicates(ShoppingCart cart){
        List<Product> present = new ArrayList<>();

        for(Product p: cart.getItems()){
            if(present.contains(p)){
                continue;
            }

            int count = 0;
            for(Product dup: cart.getItems()){
                if(dup.getId().equals(p.getId())){
                    count++;
                }
            }
            System.out.printf("%s x%d $%.2f%n", p.getName(), count, p.getPrice() * count);
            present.add(p);
        }
    }
}

//This class is a POJO
//Plain ol' Java object
//No question asking, no files, no scanner, just product data
public class Product {
    //properties
    private String id;
    private String name;
    private double price;
    private String department;


    //constructor(s)
    public Product(String id, String name, double price, String department) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //HINT a to string method like this might be useful!
    //It can pipe format the information about the product
    @Override
    public String toString() {

        return String.format("%s | %s | %.2f | %s",
                id, name, price, department);
    }
}

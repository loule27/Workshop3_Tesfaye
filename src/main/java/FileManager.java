
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//You should have two methods here, one to return a list of
//products, another to take a product and write it to the file
//No menus, no scanner, no questions, just read from the file
//write to the file. That's it.
public class FileManager {
    //The reason these are static is so that you can directly
    //call the method like FileManager.getProducts()
    //Notice the capital F
    public static List<Product> getProducts(){
        //You are going to write code to read the products
        //from the file, put them in a list and return them
        List<Product> products = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/products.csv"));
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split("\\|");
                String id = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String department = parts[3];

                Product product = new Product(id, name, price,department);
                products.add(product);
            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println("Problem with file");
        }
        return products;
    }
    public static void writeProduct(Product product){
        //This method you will take the product that is being
        //put in to this method, take its data and write it to the file
        try{
            File file = new File("src/main/resources/products.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            if(file.length() > 0){
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.write(String.format("%s|%s|%f|%s", product.getId(),
                    product.getName(), product.getPrice(),product.getDepartment()));

            fileWriter.close();
        }
        catch(IOException ex){
            System.out.println("Error writing to file.");

        }
    }
}

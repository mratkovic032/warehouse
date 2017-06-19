package warehouse;

import com.singidunum.projectKDP.exception.WarehouseException;
import com.singidunum.projectKDP.data.*;
import com.singidunum.projectKDP.service.*;
import com.singidunum.projectKDP.dao.*;
import java.sql.Date;
import java.sql.SQLException;

public class Warehouse {
    
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ProductService productService = ProductService.getInstance();
    private static final OrderService orderService = OrderService.getInstance();
    
    public static void addTestEmployees() throws SQLException, WarehouseException {
        customerService.addNewEmployee(new Employee("Markovic", "Marko", new Date(88, 3, 15)));
        customerService.addNewEmployee(new Employee("Nikolic", "Nikola", new Date(90, 2, 22)));
    }
    
    public static void addTestCustomers() throws SQLException, WarehouseException {
        customerService.addNewCustomer(new Customer("Milos Ratkovic", "Luka", "Vojvode Stepe 321", "Belgrade", 11000, "Serbia"));
        customerService.addNewCustomer(new Customer("Nikola Dilparic", "Vukasin", "Bosanska 10", "Cacak", 32000, "Serbia"));
    }
    
    public static void addTestShippers() throws SQLException, WarehouseException {
        customerService.addNewShipper(new Shipper("Transport Company 1", "0118568843"));
        customerService.addNewShipper(new Shipper("Transport Company 2", "0325679904"));
    }
    
    public static void addTestProducts() throws SQLException, WarehouseException {
        productService.addNewProduct(new Product("Banana", new Supplier("Fruit Company", "Janko", "Ustanicka 112", "Belgrade", 11000, "Serbia", "0113653453"), "Fruit", 1200));
        productService.addNewProduct(new Product("Lego", new Supplier("Toy Company", "Mirko", "Bulevar Oslobodjenja 221", "Belgrade", 11000, "Serbia", "0113653499"), "Toy", 2500));
    }
    
    

    public static void main(String[] args) throws SQLException, WarehouseException {
        
//        addTestEmployees();
//        addTestCustomers();        
//        addTestShippers();
//        addTestProducts(); 

        orderService.makeOrder(new Date(117, 6, 21), customerService.findCustomer(2), customerService.findEmployee(1), customerService.findShipper(1), productService.findProduct(1), 5);
    }  
}

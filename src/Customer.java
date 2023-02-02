import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer implements Serializable {

    private static int lastCustomerId;
    private int customerId;
    private Contact contact;
    private ArrayList<Address> addresses = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Customer(int customerId){
        this.customerId = customerId;
    }

    public static int nextCustomerId(){
        return ++lastCustomerId;
    }
    public int getCustomerId(){
        return this.customerId;
    }
    public Contact getContact(){
        return this.contact;
    }
    public void setContact(Contact contact){
        this.contact = contact;
    }
    public ArrayList<Address> getAddresses(){
        return this.addresses;
    }
    public void addAddress(Address address){
        this.addresses.add(address);
    }
    public void addOrder(Order order){
        this.orders.add(order);
    }
    public ArrayList<Order> getOrders(){
        return this.orders;
    }
    public Order getOrderById(int orderId){
        for(Order order : this.orders){
            if(order.getOrderId() == orderId){
                return order;
            }
        }
        return null;
    }
    public static Customer addMenu(){
        System.out.println("Getting Customer Information");
        var customer = new Customer(nextCustomerId());
        var contact = Contact.addMenu();
        if(contact == null){
            return null;
        }
        var address = Address.addMenu();
        if(address == null){
            return null;
        }
        customer.setContact(contact);
        customer.addAddress(address);
        return customer;
    }
    public static Customer chooseCustomer(ArrayList<Customer> customers){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a customer:");
        for(int i = 0; i < customers.size(); i++){
            System.out.println("    " + (i + 1) + ": " + customers.get(i).getContact().name);
        }
        System.out.println("  0 : cancel");
        int index = scanner.nextInt() - 1;
        if(index == -1){
            return null;
        }
        while(!(0 <= index && index < customers.size())){
            System.out.println("Index out of range! Please try again:");
            index = scanner.nextInt() - 1;
            if(index == -1){
                return null;
            }
        }
        return customers.get(index);
    }
}

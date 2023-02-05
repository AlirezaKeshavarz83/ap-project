import java.io.Serializable;
import java.util.ArrayList;

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
}

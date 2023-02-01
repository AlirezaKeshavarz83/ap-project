import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable {
    private Contact contact;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    public int addCustomer(Customer customer){ // returns index of customer
        customers.add(customer);
        return customers.size() - 1;
    }
    public int addItem(Item item){ // returns index of item
        items.add(item);
        return items.size() - 1;
    }
    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }
    public ArrayList<Item> getItems(){
        return this.items;
    }
    public Customer getCustomerByContact(Contact contact){
        for(Customer customer : this.customers){
            if(customer.getContact().matches(contact)){
                return customer;
            }
        }
        return null;
    }
    public Customer getCustomerByCustomerId(int customerId){
        for(Customer customer : this.customers){
            if(customer.getCustomerId() == customerId){
                return customer;
            }
        }
        return null;
    }
    public void addOrder(Order order){
        assert order.getOrderStatus() == Order.State.INCOMPLETE;
        var customer = order.getCustomer();
        customer.addOrder(order);
        this.orders.add(order);
    }
}

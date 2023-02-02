import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Serializable {
    private Contact contact;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> removedItems = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    public int addCustomer(Customer customer){ // returns index of customer
        customers.add(customer);
        return customers.size() - 1;
    }
    public int addItem(Item item){ // returns index of item
        item.setShop(this);
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
    public ArrayList<Order> getOrders(){
        return this.orders;
    }
    public ArrayList<Order> getOrdersByState(Order.State state){
        ArrayList<Order> ordersByState = new ArrayList<>();
        for(Order order : this.orders){
            if(order.getOrderState() == state){
                ordersByState.add(order);
            }
        }
        return ordersByState;
    }
    public void addOrder(Order order){
        assert order.getOrderState() == Order.State.INCOMPLETE;
        order.setShop(this);
        order.setOrderState(Order.State.WAITING_FOR_PAYMENT);
        var customer = order.getCustomer();
        customer.addOrder(order);
        this.orders.add(order);
    }
    public void removeItem(Item item){
        this.items.remove(item);
        this.removedItems.add(item);
    }
}

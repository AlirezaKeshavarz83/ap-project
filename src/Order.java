import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Order implements Serializable {
    private static int lastOrderId = 0;
    public static int nextOrderId(){
        return ++lastOrderId;
    }
    public Order(int orderId){
        this.orderId = orderId;
    }
    enum State{
        INCOMPLETE,
        WAITING_FOR_PAYMENT,
        PAID,
        WAITING_FOR_DELIVERY,
        ON_DELIVERY,
        DELIVERED
    }
    public static class OrderItem implements Serializable{
        private Item.SubItem subItem;
        private int cnt;

        public Item.SubItem getSubItem(){
            return this.subItem;
        }
        public void setSubItem(Item.SubItem item){
            this.subItem = item;
        }
        public int getCnt(){
            return this.cnt;
        }
        public void setCnt(int cnt){
            this.cnt = cnt;
        }
        public static OrderItem addMenu(Item.SubItem item){
            Scanner scanner = new Scanner(System.in);
            String inp;
            var orderItem = new OrderItem();
            orderItem.subItem = item;
            System.out.println("How many?");
            inp = scanner.nextLine();
            if(inp.equals("back")){
                return null;
            }
            orderItem.cnt = Integer.parseInt(inp);
            return orderItem;
        }

    }
    private int orderId;
    private Customer customer;
    private ArrayList<OrderItem> itemList = new ArrayList<>();
    private int totalPrice;
    private State orderStatus = State.INCOMPLETE;
    private Time timeOrdered;
    private Time timePaid;
    public int getOrderId(){
        return this.orderId;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public ArrayList<OrderItem> getOrderItemList(){
        return this.itemList;
    }
    public void addOrderItem(OrderItem orderItem){
        this.totalPrice += orderItem.getSubItem().getPrice() * orderItem.getCnt();
        this.itemList.add(orderItem);
    }
    public int getTotalPrice(){
        return this.totalPrice;
    }
    public State getOrderStatus(){
        return this.orderStatus;
    }
    public void setOrderStatus(State orderStatus){
        this.orderStatus = orderStatus;
    }
    public Time getTimeOrdered(){
        return this.timeOrdered;
    }
    public void setTimeOrdered(Time timeOrdered){
        this.timeOrdered = timeOrdered;
    }
    public Time getTimePaid(){
        return this.timePaid;
    }
    public void setTimePaid(Time timePaid){
        this.timePaid = timePaid;
    }

    public static Order addMenu(ArrayList<Customer> customers, ArrayList<Item> items){
        var customer = Customer.chooseCustomer(customers);
        if(customer == null){
            return null;
        }
        Order order = new Order(nextOrderId());
        order.customer = customer;
        Scanner scanner = new Scanner(System.in);
        String inp;
        int n;
        System.out.println("Enter number of Order Items:");
        inp = scanner.nextLine();
        if(inp.equals("back")){
            return null;
        }
        n = Integer.parseInt(inp);
        for(int i = 0; i < n; i++){
            var item = Item.chooseItem(items);
            if(item == null){
                return null;
            }
            var subItem = Item.SubItem.chooseSubItem(item.getPackages());
            if(subItem == null){
                return null;
            }
            var orderItem = OrderItem.addMenu(subItem);
            if(orderItem == null){
                return null;
            }
            order.addOrderItem(orderItem);
        }
        return order;
    }
}

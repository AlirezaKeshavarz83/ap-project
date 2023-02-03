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
        public static OrderItem chooseOrderItem(ArrayList<Item> items){
            var item = Item.chooseItem(items);
            if(item == null){
                return null;
            }
            var subItem = Item.SubItem.chooseSubItem(item.getPackages());
            if(subItem == null){
                return null;
            }
            return OrderItem.addMenu(subItem);
        }

    }
    public static Order inputOrder(ArrayList<Customer> customers, ArrayList<Item> items){
        var customer = Customer.chooseCustomer(customers);
        if(customer == null){
            return null;
        }
        Order order = new Order(nextOrderId());
        order.setCustomer(customer);
        var address = Address.chooseAddress(customer.getAddresses());
        if(address == null){
            return null;
        }
        var newAddress = new Address();
        newAddress.copy(address);
        order.setAddress(newAddress);
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
            var orderItem = OrderItem.chooseOrderItem(items);
            if(orderItem == null){
                return null;
            }
            order.addOrderItem(orderItem);
        }
        return order;
    }
    public static State chooseOrderState(){
        Scanner scanner = new Scanner(System.in);
        var stateList = State.values();
        int index = 1;
        System.out.println("Choose OrderState:");
        for(State st : stateList){
            System.out.println("    " + index + ": " + st.name());
            index++;
        }
        System.out.println("  0: Back");
        int ind = scanner.nextInt() - 1;
        if(0 <= ind && ind < index - 1) return stateList[ind];
        return null;
    }
    private int orderId;
    private Customer customer;
    private Shop shop;
    private Address address;
    private ArrayList<OrderItem> itemList = new ArrayList<>();
    private int totalPrice;
    private State orderState = State.INCOMPLETE;
    private Time timeOrdered;
    private Time timePaid;
    public int getOrderId(){
        return this.orderId;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setShop(Shop shop){
        this.shop = shop;
    }
    public Shop getShop(){
        return this.shop;
    }
    public void setAddress(Address address){
        this.address = address;
    }
    public Address getAddress(){
        return this.address;
    }
    public ArrayList<OrderItem> getOrderItemList(){
        return this.itemList;
    }
    public void addOrderItem(OrderItem orderItem){
        if(this.orderState.compareTo(State.PAID) >= 0){
            System.out.println("Can't change order after payment!");
            return;
        }
        this.totalPrice += orderItem.getSubItem().getPrice() * orderItem.getCnt();
        this.itemList.add(orderItem);
    }
    public void removeOrderItemByIndex(int index){
        if(this.orderState.compareTo(State.PAID) >= 0){
            System.out.println("Can't change order after payment!");
            return;
        }
        this.itemList.remove(index);
    }
    public int getTotalPrice(){
        return this.totalPrice;
    }
    public State getOrderState(){
        return this.orderState;
    }
    public void setOrderState(State orderStatus){
        this.orderState = orderStatus;
        if(orderStatus == State.WAITING_FOR_PAYMENT){
            this.setTimeOrdered(Time.now());
        }
        if(orderStatus == State.PAID){
            this.setTimePaid(Time.now());
            for(var orderItem : this.getOrderItemList()){
                orderItem.getSubItem().incSoldCnt(orderItem.getCnt());
            }
        }
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
}

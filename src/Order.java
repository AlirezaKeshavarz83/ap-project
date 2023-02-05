import java.io.Serializable;
import java.util.ArrayList;

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

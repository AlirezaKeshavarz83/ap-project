import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    public static void run(Shop shop) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("------------------------------------------");
            System.out.println("Choose your next action:");
            System.out.println("    1 : Add Order");
            System.out.println("    2 : Add Customer");
            System.out.println("    3 : Add Item");
            System.out.println("    4 : View Customer");
            System.out.println("  0 : Exit");
            int option = scanner.nextInt();
            if(option == 1){
                var order = Order.addMenu(shop.getCustomers(), shop.getItems());
                if(order != null){
                    shop.addOrder(order);
                    System.out.println("Order added successfully");
                }
            }
            if(option == 2){
                var customer = Customer.addMenu();
                if(customer != null){
                    shop.addCustomer(customer);
                }
            }
            if(option == 3){
                var item = Item.addMenu();
                if(item != null){
                    shop.addItem(item);
                }
            }
            if(option == 4){
                var customer = Customer.chooseCustomer(shop.getCustomers());
                if(customer == null){
                    continue;
                }
                CustomerMenu(customer);
            }
            if(option == 0){
                return;
            }
        }
    }
    public static void CustomerMenu(Customer customer){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------------");
            System.out.println(customer.getContact().name + "       " + customer.getContact().phoneNumber);
            System.out.println("Choose your next action:");
            System.out.println("    1 : View Address");
            System.out.println("    2 : View Orders");
            System.out.println("    3 : Edit Contact Information");
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 1){
                AddressMenu(customer.getAddress());
            }
            if(option == 2){
                OrdersMenu(customer.getOrders());
            }
            if(option == 3){
                ContactMenu(customer.getContact());
            }
            if(option == 0){
                return;
            }
        }
    }
    public static void AddressMenu(Address address){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------------");
            System.out.println(address.fullAddress);
            System.out.println("Choose your next action:");
            System.out.println("    1 : Edit Address");
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 1){
                var temp = Address.addMenu();
                if(temp != null){
                    address.copy(temp);
                }
            }
            if(option == 0){
                return;
            }
        }
    }
    public static void OrdersMenu(ArrayList<Order> orders){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------------");
            System.out.println("Choose Order:");
            for(int i = 0; i < orders.size(); i++){
                var order = orders.get(i);
                System.out.printf("    %d : By %s at %s     ---     %s", i + 1, order.getCustomer().getContact().name, order.getTimeOrdered().getDateString(), order.getOrderStatus().name());
            }
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 0){
                return;
            }
            if(0 < option && option <= orders.size()) {
                int index = option - 1;
                OrderMenu(orders.get(index));
            }
        }
    }
    public static void OrderMenu(Order order){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("------------------------------------------");
            System.out.println(order.getCustomer().getContact().name + "     " + order.getTimeOrdered().getDateString() + "     " + order.getOrderStatus().name());
            var orderItemList = order.getOrderItemList();
            for(int i = 0; i < orderItemList.size(); i++){
                System.out.println("    " + (i + 1) + ". " + orderItemList.get(i).getSubItem().getItem().getTitle() + " - " + orderItemList.get(i).getSubItem().getTitle() + " -  ×" + orderItemList.get(i).getCnt());
            }
            System.out.println("Choose your next action:");
            System.out.println("    1 : Add OrderItem (!)");
            System.out.println("    2 : Remove OrderItem (!)");
            System.out.println("    3 : Update OrderStatus (!)");
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 0){
                return;
            }
            System.out.println("Sorry this option hasn't been implemented yet!");
        }
    }
    public static void ContactMenu(Contact contact){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------------");
            System.out.println(contact.name + "        " + contact.phoneNumber);
            System.out.println("Email Address: " + contact.emailAddress);
            System.out.println("Choose your next action:");
            System.out.println("    1 : Edit Contact");
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 1){
                var temp = Contact.addMenu();
                contact.copy(temp);
            }
            if(option == 0){
                return;
            }
        }
    }
}

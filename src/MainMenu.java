import java.lang.constant.DynamicCallSiteDesc;
import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("    4 : View Customers");
            System.out.println("    5 : View Orders");
            System.out.println("    6 : View Items");
            System.out.println("    7 : View Orders By State");
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
            if(option == 5){
                OrdersMenu(shop.getOrders());
            }
            if(option == 6){
                ItemsMenu(shop.getItems());
            }
            if(option == 7){
                var state = Order.chooseOrderState();
                if(state == null){
                    continue;
                }
                OrdersMenu(shop.getOrdersByState(state));
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
            System.out.println("    1 : View Addresses");
            System.out.println("    2 : View Orders");
            System.out.println("    3 : View Contact Information");
            System.out.println("    4 : Add Address");
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 1){
                var address = Address.chooseAddress(customer.getAddresses());
                if(address == null){
                    continue;
                }
                AddressMenu(address);
            }
            if(option == 2){
                OrdersMenu(customer.getOrders());
            }
            if(option == 3){
                ContactMenu(customer.getContact());
            }
            if(option == 4){
                var address = Address.addMenu();
                if(address == null){
                    continue;
                }
                customer.addAddress(address);
                System.out.println("Address added successfully.");
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
            System.out.println(address.getName());
            System.out.println("    " + address.getFullAddress());
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
        Collections.reverse(orders);
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------------");
            System.out.println("Choose Order:");
            for(int i = 0; i < orders.size(); i++){
                var order = orders.get(i);
                System.out.printf("    %d : By %s at %s     ---     %s\n", i + 1, order.getCustomer().getContact().name, order.getTimeOrdered().getDateString(), order.getOrderState().name());
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
            System.out.println(order.getCustomer().getContact().name + "     " + order.getTimeOrdered().getDateString() + "     " + order.getOrderState().name());
            System.out.println("Goes to " + order.getAddress().getName() + ": " + order.getAddress().getFullAddress());
            var orderItemList = order.getOrderItemList();
            for(int i = 0; i < orderItemList.size(); i++){
                System.out.println("    " + (i + 1) + ". " + orderItemList.get(i).getSubItem().getItem().getTitle() + " - " + orderItemList.get(i).getSubItem().getTitle() + " -  x" + orderItemList.get(i).getCnt());
            }
            System.out.println("Choose your next action:");
            System.out.println("    1 : Add OrderItem");
            System.out.println("    2 : Remove OrderItem");
            System.out.println("    3 : Update OrderStatus");
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 0){
                return;
            }
            if(option == 1){
                var orderItem = Order.OrderItem.chooseOrderItem(order.getShop().getItems());
                if(orderItem == null) {
                    continue;
                }
                order.addOrderItem(orderItem);
            }
            if(option == 2){
                System.out.println("Enter the index of the item you want to remove:");
                int index = scanner.nextInt() - 1;
                if(index == -1){
                    continue;
                }
                order.removeOrderItemByIndex(index);
            }
            if(option == 3){
                var state = Order.chooseOrderState();
                if(state == null){
                    continue;
                }
                order.setOrderState(state);
            }
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
                if(temp == null){
                    continue;
                }
                contact.copy(temp);
            }
            if(option == 0){
                return;
            }
        }
    }
    public static void ItemsMenu(ArrayList<Item> items){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------------");
            System.out.println("Choose Item:");
            for(int i = 0; i < items.size(); i++){
                var item = items.get(i);
                System.out.println("    " + (i + 1) + ". " + item.getTitle());
            }
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 0){
                return;
            }
            if(0 < option && option <= items.size()) {
                int index = option - 1;
                ItemMenu(items.get(index));
            }
        }
    }
    public static void ItemMenu(Item item){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------------------");
            var subItems = item.getPackages();
            for(int i = 0; i < subItems.size(); i++){
                var subItem = subItems.get(i);
                System.out.println("    " + (i + 1) + ". " + subItem.getTitle() + "     $" + subItem.getPrice() + "    sold: x" + subItem.getSoldCnt());
            }
            System.out.println("Choose your next action:");
            System.out.println("    1 : Remove Item");
            System.out.println("  0 : Back");
            int option = scanner.nextInt();
            if(option == 0){
                return;
            }
            if(option == 1){
                System.out.println("Are you sure you want to remove Item \"" + item.getTitle() +"\"? (Y/N)");
                String inp = scanner.nextLine();
                inp = scanner.nextLine();
                System.out.println(inp);
                if(!inp.equals("Y")){
                    continue;
                }
                item.getShop().removeItem(item);
                System.out.println("Item removed successfully.");
                return;
            }
        }
    }
}

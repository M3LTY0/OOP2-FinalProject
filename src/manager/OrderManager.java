import java.util.ArrayList;
import exceptions.BookNotFoundException;
import exceptions.CustomerNotFoundException;
import exceptions.OrderNotFoundException;

public class OrderManager extends Manager<Order>{

    ArrayList<Order> orderList = db.getOrderList();
    BookManager bookM = new BookManager();
    CustomerManager customerM = new CustomerManager();

    public Order createOrder(int customerID, int orderID, int ISBN, String orderDate, String dueDate) {
        Order newOrder = new Order(customerID, orderID, ISBN, orderDate, dueDate);
        orderList.add(newOrder);
        db.addOrdersql(customerID, orderID, ISBN, orderDate, dueDate); // persist to DB
        return newOrder;
    }

    public Order searchOrders(int orderID) throws OrderNotFoundException {
        for (Order order : orderList) {
            if (order.getOrderID() == orderID)
                return order;
        }
        throw new OrderNotFoundException("No order found with orderID: " + orderID);
    }

    public void modifyOrder(int orderID) {
        Order select = null;
        try {
            select = searchOrders(orderID);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        int choice = 50;
        do {
            System.out.println("What would you like to change?");
            System.out.println("1: Books");
            System.out.println("2: Customer ID");
            System.out.println("3: Order ID");
            System.out.println("4: Order Date");
            System.out.println("5: Due Date");
            System.out.println("0: Quit");

            choice = input.nextInt();
            input.nextLine(); // consume leftover newline

            if (choice == 1) {
                System.out.println("Please enter ISBN you wish to replace");
                int oldISBN = input.nextInt();
                input.nextLine();
                System.out.println("Please enter new ISBN");
                int newISBN = input.nextInt();
                input.nextLine();
                try {
                    Book oldBook = bookM.searchBooks(oldISBN);
                    Book newBook = bookM.searchBooks(newISBN);
                    select.orderedBooks.remove(oldBook);
                    select.orderedBooks.add(newBook);
                    select.setISBN(newISBN);
                } catch (BookNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } else if (choice == 2) {
                System.out.println("Please enter new customerID");
                int newCusID = input.nextInt();
                input.nextLine();
                try {
                    customerM.searchCustomers(newCusID); // verify the customer exists
                    select.setCustomerID(newCusID);
                } catch (CustomerNotFoundException e) {
                    System.out.println("No customer found with ID: " + newCusID);
                }

            } else if (choice == 3) {
                System.out.println("Please enter a new value for order ID");
                int newOrderID = input.nextInt();
                input.nextLine();
                select.setOrderID(newOrderID);

            } else if (choice == 4) {
                System.out.println("Please enter a new value for orderDate");
                select.setOrderDate(input.nextLine());

            } else if (choice == 5) {
                System.out.println("Please enter a new value for dueDate");
                select.setDueDate(input.nextLine());
            }

        } while (choice != 0);

        // persist all changes to DB after user is done editing
        db.updateOrdersql(select.getOrderID(), select.getCustomerID(),
                select.getISBN(), select.getOrderDate(), select.getDueDate());
    }

    public void deleteOrder(int orderID) {
        Order select = null;
        try {
            select = searchOrders(orderID);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        orderList.remove(select);
        db.deleteOrdersql(orderID); // persist to DB
    }

    public void viewOrders() {
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}
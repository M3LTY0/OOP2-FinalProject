import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import sait.mms.exceptions.OrderNotFoundException;
//return in updateclass

public class OrderManager {

    private ArrayList<Order> orderList = new ArrayList<>();
    public Scanner input = new Scanner(System.in);
    DatabaseConn db = new DatabaseConn();

    // Updated to allow for Customer object rather than customerID attribute
    public Order createOrder(ArrayList<Book> books, Customer customer, int orderID, String orderDate, String dueDate) {
        Order newOrder = new Order(books, customer, orderID, orderDate, dueDate);
        orderList.add(newOrder);
        return newOrder;
    }

    public Order searchOrders(int orderID) throws OrderNotFoundException {
        String sql = "SELECT * from orders WHERE orderID = ?";
        try {
            PreparedStatement pst = db.prepared(sql);
            pst.setInt(1, orderID);
            ResultSet rs = db.query(pst);

            if (rs.next()) {
                ArrayList<Book> books = rs.getInt("ISBN"); // The right side of the equals sign needs to be updated to allow for a list of books rather than one
                int customerID = rs.getInt("customerID");
                int newOrderID = rs.getInt("orderID");
                String orderDate = rs.getString("orderDate");
                String dueDate = rs.getString("dueDate");
            } else {
                System.out.println("No order with orderID " + orderID);
            }

        } catch (SQLException e) {
            System.out.println("Error while retriving order " + e.getMessage());
        }

        for (Order order : orderList) {
            if (order.getOrderID() == orderID) {
                return order;
            }
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

            choice = input.nextInt();
            if (choice == 1) { // Needs to be refined and updated to allow for a list of books rather than one book
                System.out.println("Please enter new value for ISBN");
                int newISBN = input.nextInt();
                select.setOrderISBN(newISBN);

                String sql = "UPDATE orders SET ISBN = ? WHERE orderID = ?";
                try {
                    PreparedStatement pst = db.prepared(sql);
                    pst.setInt(1, newISBN);
                    pst.setInt(2, orderID); // got select.getOrderISBN from ai because ISBN is not in parameter
                    int count = db.updated(pst);
                    if (count == 0) {
                        System.out.println(count + " ISBN updated");
                    } else if (count == 1) {
                        System.out.println("ISBN updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 2) {
                System.out.println("Please enter new value for customerID");
                int newCustomerID = input.nextInt();
                select.setCustomerID(newCustomerID); // Needs to be updated for Customer object rather than customerID attribute

                String sql = "UPDATE orders SET customerID = ? WHERE orderID = ?";

                try {
                    PreparedStatement pst = db.prepared(sql);
                    ;
                    pst.setInt(1, newCustomerID); // Needs to be updated for Customer object rather than customerID attribute
                    pst.setInt(2, orderID);

                    int count = db.updated(pst);
                    if (count == 0) {
                        System.out.println("customerID not updated");
                    } else if (count == 1) {
                        System.out.println(count + "customerID updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 3) {
                System.out.println("Please enter a new value for order ID");
                int newOrderID = input.nextInt();
                select.setOrderID(newOrderID);

                String sql = "UPDATE orders SET customerID = ? WHERE orderID= ?";

                try {
                    PreparedStatement pst = db.prepared(sql);
                    ;
                    pst.setInt(1, newOrderID);
                    pst.setInt(2, orderID);

                    int count = db.updated(pst);
                    if (count == 0) {
                        System.out.println("orderID not updated");
                    } else if (count == 1) {
                        System.out.println(count + "orderID updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 4) {
                System.out.println("Please enter a new value for orderDate");
                String newOrderDate = input.nextLine();
                select.setOrderDate(newOrderDate);

                String sql = "UPDATE orders SET customerID = ? WHERE orderID = ?";

                try {
                    PreparedStatement pst = db.prepared(sql);
                    ;
                    pst.setString(1, newOrderDate);
                    pst.setInt(2, orderID);

                    int count = db.updated(pst);
                    if (count == 0) {
                        System.out.println("newOrderDate not updated");
                    } else if (count == 1) {
                        System.out.println(count + "orderDate updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 5) {
                System.out.println("Please enter a new value for dueDate");
                String newDueDate = input.nextLine();
                select.setDueDate(newDueDate);

                String sql = "UPDATE orders SET customerID = ? WHERE orderID = ?";

                try {
                    PreparedStatement pst = db.prepared(sql);
                    ;
                    pst.setString(1, newDueDate);
                    pst.setInt(2, orderID);

                    int count = db.updated(pst);
                    if (count == 0) {
                        System.out.println("dueDate not updated");
                    } else if (count == 1) {
                        System.out.println(count + "dueDate updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            }

        } while (choice != 0);
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

        String sql = "DELETE orders FROM orders WHERE orderID= ?";
        try {
            PreparedStatement pst = db.prepared(sql);
            pst.setInt(1, orderID);
            int count = db.updated(pst);

            if (count == 0) {
                System.out.println("No such ISBN");
            } else if (count == 1) {
                System.out.println(count + " order deleted");
            }
        } catch (SQLException e) {
            System.out.println("Error while executing statemtn \n" + sql + "\n");
        }
    }

    public void viewOrders() {
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
}
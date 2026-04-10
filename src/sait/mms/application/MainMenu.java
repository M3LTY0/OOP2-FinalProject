import java.util.ArrayList;
import java.util.Scanner;

import sait.mms.exceptions.CustomerNotFoundException;

public class MainMenu {

    static Scanner input = new Scanner(System.in);
    static BookManager bookM = new BookManager();
    static CustomerManager customerM = new CustomerManager();
    static OrderManager orderM = new OrderManager();

    public static void bookMenu() {
        int choice = -1;
        do {
            System.out.println("\n=== Books ===");
            System.out.println("1: Add Book");
            System.out.println("2: Display All Books");
            System.out.println("3: Search Book");
            System.out.println("4: Update Book");
            System.out.println("5: Delete Book");
            System.out.println("0: Back");
            choice = input.nextInt();
            

            switch (choice) {
                case 1:
                    System.out.print("ISBN: ");          
                    int isbn = input.nextInt(); 
                    
                    System.out.print("Title: ");         
                    String title = input.nextLine();
                    
                    System.out.print("Genre: ");         
                    String genre = input.nextLine();
                    
                    System.out.print("Publisher: ");     
                    String publisher = input.nextLine();
                    
                    System.out.print("Status: ");        
                    String status = input.nextLine();
                    
                    System.out.print("Quality: ");       
                    String quality = input.nextLine();
                    
                    bookM.addBook(isbn, title, genre, publisher, status, quality);
                    System.out.println("Book added!");
                    break;

                case 2:
                    bookM.displayBooks();
                    break;
                case 3:
                    System.out.print("Enter ISBN: ");
                    int searchISBN = input.nextInt(); 
                    try {
                        System.out.println(bookM.searchBooks(searchISBN));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter ISBN to update: ");
                    int updateISBN = input.nextInt(); 
                    bookM.updateBook(updateISBN);
                    break;
                case 5:
                    System.out.print("Enter ISBN to delete: ");
                    int deleteISBN = input.nextInt(); 
                    bookM.deleteBook(deleteISBN);
                    break;
                case 0: break;
                default: System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }

    public static void customerMenu() {
        int choice = -1;
        do {
            System.out.println("\n=== Customers ===");
            System.out.println("1: Add Customer");
            System.out.println("2: Display All Customers");
            System.out.println("3: Search Customer");
            System.out.println("4: Update Customer");
            System.out.println("5: Delete Customer");
            System.out.println("0: Back");
            choice = input.nextInt();
            

            switch (choice) {
                case 1:
                    ArrayList<Customer> cusList = customerM.customerList;
                    int cusID = 1;
                    for (Customer c : cusList) {
                        cusID++;
                    }
                    System.out.print("Customer ID is set");
                    
                    System.out.print("First Name: ");    
                    String firstName = input.nextLine();
                    
                    System.out.print("Last Name: ");     
                    String lastName = input.nextLine();

                    System.out.print("Address: ");       
                    String address = input.nextLine();

                    System.out.print("Phone: ");         
                    String phone = input.nextLine(); 
                    
                    System.out.print("Email: ");         
                    String email = input.nextLine();
                    
                    customerM.addCustomer(cusID, firstName, lastName, address, phone, email);
                    System.out.println("Customer added!");
                    break;
                case 2:
                    customerM.displayCustomers();
                    break;
                case 3:
                    System.out.print("Enter Customer ID: ");
                    int searchID = input.nextInt(); 
                    try {
                        System.out.println(customerM.searchCustomers(searchID));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter Customer ID to update: ");
                    int updateID = input.nextInt(); 
                    customerM.updateCustomer(updateID);
                    break;
                case 5:
                    System.out.print("Enter Customer ID to delete: ");
                    int deleteID = input.nextInt(); 
                    customerM.deleteCustomer(deleteID);
                    break;
                case 0: break;
                default: System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }

    public static void orderMenu() {
        int choice = -1;
        do {
            System.out.println("\n=== Orders ===");
            System.out.println("1: Create Order");
            System.out.println("2: View All Orders");
            System.out.println("3: Search Order");
            System.out.println("4: Modify Order");
            System.out.println("5: Delete Order");
            System.out.println("0: Back");
            choice = input.nextInt();
            

            switch (choice) {
                case 1:
                    System.out.print("Customer ID: ");   
                    int cusID = input.nextInt(); 
                    
                    ArrayList<Order> orderList = orderM.orderList;
                    int orderID = 1;
                    for (Order o : orderList) {
                        orderID++;
                    }
                    System.out.print("Order ID is set");
                    
                    System.out.print("ISBN: ");          
                    int isbn = input.nextInt(); 
                    
                    System.out.print("Order Date: ");    
                    String orderDate = input.nextLine();
                    
                    System.out.print("Due Date: ");      
                    String dueDate = input.nextLine();
                    
                    orderM.createOrder(cusID, orderID, isbn, orderDate, dueDate);
                    System.out.println("Order created!");
                    break;
                case 2:
                    orderM.viewOrders();
                    break;
                case 3:
                    System.out.print("Enter Order ID: ");
                    int searchID = input.nextInt(); 
                    try {
                        System.out.println(orderM.searchOrders(searchID));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter Order ID to modify: ");
                    int modifyID = input.nextInt(); 
                    orderM.modifyOrder(modifyID);
                    break;
                case 5:
                    System.out.print("Enter Order ID to delete: ");
                    int deleteID = input.nextInt(); 
                    orderM.deleteOrder(deleteID);
                    break;
                case 0: break;
                default: System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }


    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1: Books");
            System.out.println("2: Customers");
            System.out.println("3: Orders");
            System.out.println("0: Exit");
            choice = input.nextInt();
            

            switch (choice) {
                case 1: bookMenu(); break;
                case 2: customerMenu(); break;
                case 3: orderMenu(); break;
                case 0: 
                    bookM.db.disconnect();
                    break;
                default: System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }

    }
package OnlineBookstoreSystem.noSql;

import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.dao.UserDAO;
import OnlineBookstoreSystem.noSql.util.TableCreator;
import OnlineBookstoreSystem.service.*;

import java.util.Scanner;

public class BookstoreApplicationDynamoDB {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDAO userDAO = new UserDAO();
    
    public static void main(String[] args) {
        System.out.println("Welcome to Online Bookstore (DynamoDB Version)!");
        
        TableCreator.createAllTables();
        System.out.println("Tables initialized.");
        
        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Thank you for using Online Bookstore!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        User user = userDAO.getUserByName(username);
        if (user != null) {
            BookService bookService = new BookService();
            Cart cart = new Cart(bookService, user);
            OrderService orderService = new OrderService(cart, user);
            BrowsingHistory browsingHistory = new BrowsingHistory(bookService, user);
            
            if ("admin".equalsIgnoreCase(user.getRole())) {
                AdminInterface adminInterface = new AdminInterface(bookService, browsingHistory, user);
                adminInterface.runAdmin();
            } else {
                CustomerInterface customerInterface = new CustomerInterface(bookService, cart, orderService, browsingHistory, user);
                customerInterface.runCustomer();
            }
        } else {
            System.out.println("User not found!");
        }
    }
    
    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter role (admin/customer): ");
        String role = scanner.nextLine();
        
        User user = new User();
        user.setUserName(username);
        user.setRole(role);
        
        userDAO.createUser(user);
        System.out.println("User registered successfully!");
    }
}
package OnlineBookstoreSystem;

import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookstoreApplication {
    private BookService bsi;
    private Map<String, CustomerInterface> customerSessions = new HashMap<>();
    private Map<String, AdminInterface> adminSessions = new HashMap<>();
    private Map<String, Cart> userCarts = new HashMap<>();
    private Map<String, OrderService> userOrders = new HashMap<>();
    private Map<String, BrowsingHistory> userBrowsingHistory = new HashMap<>();

    public BookstoreApplication(){
        bsi = new BookService();
    }

    public static void main(String[] args) {
        BookstoreApplication main = new BookstoreApplication();
        main.run();
    }

    public void run()
    {


        Scanner sc = new Scanner(System.in);
        User u = new User();
       // UserDAO userDAO = new UserDAO();


        while(true)
        {
            System.out.println("\n=== BOOKSTORE SYSTEM ===\n");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch ((choice))
            {
                case 1:
                    UserService us = new UserService();
                    us.userManagement(u);
                    break;

                case 2:
                    System.out.println("\n\nThank u for visiting BookStore.......");
                    return;
            }
        }

    }
}

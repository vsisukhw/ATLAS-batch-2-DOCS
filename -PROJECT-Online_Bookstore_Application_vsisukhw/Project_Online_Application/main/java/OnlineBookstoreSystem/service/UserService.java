package OnlineBookstoreSystem.service;

import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.dao.UserDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    UserDAO userDAO = new UserDAO();
    Scanner sc = new Scanner(System.in);

    private BookService bsi;
    private Map<String, CustomerInterface> customerSessions = new HashMap<>();
    private Map<String, AdminInterface> adminSessions = new HashMap<>();
    private Map<String, Cart> userCarts = new HashMap<>();
    private Map<String, OrderService> userOrders = new HashMap<>();
    private Map<String, BrowsingHistory> userBrowsingHistory = new HashMap<>();

    public UserService(){
        bsi = new BookService();
    }


//    public void getRole(User u) {
//        if(!u.getUserName().equalsIgnoreCase(userDAO.getUserName(u)))
//            insertUserDetails(u);
//        else
//         userDAO.getRole(u);
//    }
//    public void insertUserDetails(User u)
//    {
//        userDAO.insertUserDetails(u);
//    }


    public void userManagement(User u)
    {
        System.out.print("\nEnter userName = ");
        String userName = sc.next();
        u.setUserName(userName);
        User existing = userDAO.getUserByName(userName);
        if(existing == null){
            System.out.print("Do u want to create new account (y/n) = ");
            switch(sc.next())
            {
                case "y":
                    System.out.print("\nEnter Role : \n1. Admin \n2. Customer\nEnter option (1/2) : ");
                    int role = sc.nextInt();

                    if(role == 1)
                    {
                        u.setRole("admin");;
                        userDAO.createUser(u);
                        BrowsingHistory userBrowsing = new BrowsingHistory(bsi,u);
                        userBrowsingHistory.put(userName, userBrowsing);
                        AdminInterface ai = new AdminInterface(bsi,userBrowsing,u);
                        adminSessions.put(userName, ai);
                        ai.runAdmin();
                    }
                    else if(role==2)
                    {
                        u.setRole("customer");
                        userDAO.createUser(u);
                        Cart userCart = new Cart(bsi,u);
                        userCarts.put(userName, userCart);
                        OrderService userOrder = new OrderService(userCart,u);
                        userOrder.c = userCart;
                        userOrders.put(userName, userOrder);
                        BrowsingHistory userBrowsing = new BrowsingHistory(bsi,u);
                        userBrowsingHistory.put(userName, userBrowsing);
                        CustomerInterface ci = new CustomerInterface(bsi, userCart, userOrder, userBrowsing,u);
                        customerSessions.put(userName, ci);
                        ci.runCustomer();
                    }
                    break;
                case "n":
                    break;
                default:
                    break;

            }
        }
        else if(existing.getRole().equalsIgnoreCase("admin"))
        {
            AdminInterface ai = adminSessions.get(userName);
            if(ai == null) {
                BrowsingHistory userBrowsing = userBrowsingHistory.get(userName);
                if(userBrowsing == null) {
                    userBrowsing = new BrowsingHistory(bsi,existing);
                    userBrowsingHistory.put(userName, userBrowsing);
                }
                ai = new AdminInterface(bsi,userBrowsing,existing);
                adminSessions.put(userName, ai);
            }
            ai.runAdmin();
        }
        else if(existing.getRole().equalsIgnoreCase("customer"))
        {
            CustomerInterface ci = customerSessions.get(userName);
            if(ci == null) {
                Cart userCart = userCarts.get(userName);
                if(userCart == null) {
                    userCart = new Cart(bsi,existing);
                    userCarts.put(userName, userCart);
                }
                OrderService userOrder = userOrders.get(userName);
                if(userOrder == null) {
                    userOrder = new OrderService(userCart,existing);
                    userOrder.c = userCart;
                    userOrders.put(userName, userOrder);
                }
                BrowsingHistory userBrowsing = userBrowsingHistory.get(userName);
                if(userBrowsing == null) {
                    userBrowsing = new BrowsingHistory(bsi,existing);
                    userBrowsingHistory.put(userName, userBrowsing);
                }
                ci = new CustomerInterface(bsi, userCart, userOrder, userBrowsing,existing);
                customerSessions.put(userName, ci);
            }
            ci.runCustomer();
        }
        else
            System.out.println("Try Again...");

    }
}

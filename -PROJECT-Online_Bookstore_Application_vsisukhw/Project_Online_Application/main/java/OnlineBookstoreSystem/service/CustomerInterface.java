package OnlineBookstoreSystem.service;

import OnlineBookstoreSystem.model.User;

import java.util.Scanner;

public class CustomerInterface {
    BookService bsi;
    Cart c;
    OrderService o;

    BrowsingHistory br;
    User user;
    Scanner sc= new Scanner(System.in);

//    public CustomerInterface(BookService bsi, User u){
//        user =u;
//        this.bsi = bsi;
//        this.br = new BrowsingHistory(this.bsi,u);
//        this.c = new Cart(bsi,u);
//        this.o.c = this.c;
//        this.bsi.setBrowsingHistory(this.br);
//    }
    
    public CustomerInterface(BookService bsi, Cart cart, OrderService orderService, BrowsingHistory browsingHistory, User u){
        user = u;
        this.bsi = bsi;
        this.br = new BrowsingHistory(bsi,u);
        this.c = cart;
        //this.o=new Order(c,user);
        this.o = orderService;
        this.bsi.setBrowsingHistory(browsingHistory,u);
        Recommended_books rb = new Recommended_books();
        rb.setRecommendedBooks();
    }
    //Order o = new Order(c,user);
    public CustomerInterface(Cart c) {
        this.c = c;
    }

    public CustomerInterface(OrderService o) {
        this.o = o;
    }

    public CustomerInterface(BrowsingHistory br) {
        this.br = br;
    }

    public void runCustomer() {
        bsi.initialiseBooks();

        while(true) {
            System.out.println("\n\nEnter option");
            System.out.println("1. List books");
            System.out.println("2. Search book");
            System.out.println("3. List Cart");
            System.out.println("4. Add book in cart");
            System.out.println("5. Remove book from Cart");
            System.out.println("6. Recommended Books");
            System.out.println("7. Checkout");
            System.out.println("8. List Orders");
            System.out.println("9. Browsing History");
            System.out.println("10. Go back");
            System.out.print("\n Enter your option : ");
            int opt = sc.nextInt();

            switch(opt) {
                case 1: bsi.getBooksList();         break;
                case 2: bsi.searchBook();           break;
                case 3: c.getCart();                break;
                case 4: c.addBook();                break;
                case 5: c.removeBook();             break;
                case 6: bsi.recomendedBooks();      break;
                case 7: o.checkOutService();        break;
                case 8: o.showOrderReceipt();       break;
                case 9: br.showBrowsingHistory();   break;
                case 10: return;
            }
        }
    }
}

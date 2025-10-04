package OnlineBookstoreSystem.service;

import OnlineBookstoreSystem.model.User;

import java.util.Scanner;

public class AdminInterface {
    BookService bsi;
    User user;
    BrowsingHistory br;
    Scanner sc= new Scanner(System.in);
    public AdminInterface(BookService bsi, BrowsingHistory browsingHistory, User u){
        user = u;
        this.bsi = bsi;
        this.br = new BrowsingHistory(bsi,u);
        this.bsi.setBrowsingHistory(browsingHistory,u);
        Recommended_books rb = new Recommended_books();
        rb.setRecommendedBooks();
    }

    public void runAdmin() {
        bsi.initialiseBooks();

        while(true) {
            System.out.println("\n\nEnter option");
            System.out.println("1. List books");
            System.out.println("2. Search book");
            System.out.println("3. Update book");
            System.out.println("4. Delete book");
            System.out.println("5. Create book");
            System.out.println("6. For Recommendation");
            System.out.println("7. Go back");
            System.out.print("\n Enter your option : ");
            int opt = sc.nextInt();

            switch(opt) {
                case 1: bsi.getBooksList();     break;
                case 2: bsi.searchBook();       break;
                case 3: bsi.updateBooks();      break;
                case 4: bsi.removeBooks();      break;
                case 5: bsi.createBooks();      break;
                case 6: bsi.recommendBooks();   break;
                case 7: return;
            }
        }
    }
}


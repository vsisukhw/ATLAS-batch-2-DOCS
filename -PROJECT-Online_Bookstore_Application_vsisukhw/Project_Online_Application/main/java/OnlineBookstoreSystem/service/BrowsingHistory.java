package OnlineBookstoreSystem.service;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.dao.BrowsingHistoryDAO;

import java.util.LinkedList;
import java.util.List;

public class BrowsingHistory {
    LinkedList<Book> bHistory = new LinkedList<>();
    User u;
    BookService bsi;
    BrowsingHistoryDAO browsingHistoryDAO;

    public BrowsingHistory() {
    }

    public BrowsingHistory(BookService b, User user)
    {
        u=user;
        browsingHistoryDAO = new BrowsingHistoryDAO(u);
        bsi = new BookService();
    }

    public void showBrowsingHistory(){

        showBrowsingHistory(u);



        //        if(bHistory.isEmpty())
//            System.out.println("No Browsing History...");
//        else
//        bHistory.stream().forEach(p->System.out.println("Title ="+ p.getTitle()+"Author = "+p.getAuthor()+"Id = "+p.getId()+"Price = "+p.getPrice()+"Quantity = "+p.getQuantity()));
    }

    public void showBrowsingHistory(User u)
    {
        List<Book> b = browsingHistoryDAO.getBrowsingHistory();
        if(b.isEmpty()) {
            System.out.println("No Browsing History...");
        }
        else
        {
            b.stream().forEach(book -> {
                String isRecommended = book.getRecommended() ? "Yes" : "No";
                System.out.println("Title = " + book.getTitle() +
                        "\t|\tAuthor = " + book.getAuthor() +
                        "\t|\tId = " + book.getId() +
                        "\t|\tPrice = " + book.getPrice() +
                        "\t|\tQuantity = " + book.getQuantity() +
                        "\t|\tRecommended = " + isRecommended);
            });
        }
    }
    public void addBookToBrowsingHistory(int i){
        browsingHistoryDAO.addToHistory(bsi.getBookById(i));
    }

}





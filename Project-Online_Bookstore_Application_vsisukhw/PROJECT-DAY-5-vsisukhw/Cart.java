package OnlineBookstore;

import OnlineBookstore.dao.CartDAO;
import OnlineBookstore.model.Book;
import OnlineBookstore.model.User;
import OnlineBookstore.service.BookService;

import java.util.*;

public class Cart {
    Scanner sc = new Scanner(System.in);
    User u;
    public Cart(BookService bookService,User un)
    {
        this.bs = bookService;
        u=un;

    }

    CartDAO cartDAO = new CartDAO(u);


    public Map<Book,Integer> items= new HashMap<>();
    BookService bs;
    


    public void addBook()
    {
        System.out.println("Enter book id and quantity to add in cart = ");
        int id = sc.nextInt();
        int q = sc.nextInt();
        Book b= bs.getBookById(id);


        if(b == null) {
            System.out.println("Book not found with ID: " + id);
            return;
        }
        if(q>b.getQuantity())
        {
            System.out.println("Stock is low, kindly put some low quantity...");
        }
        else {

            cartDAO.addBook(b,q,u.getUserName());
            System.out.println(q + " copies of " + b.getTitle() + " added to cart.");
        }
    }
    public void removeBook()
    {
        System.out.println("Enter book id to remove from cart = ");
        Book b = bs.getBookById(sc.nextInt());
        
        if(b == null) {
            System.out.println("Book not found.");
            return;
        }
        else {
            cartDAO.removeBook(b.getId(),u.getUserName());
        }
    }
    public void calculateTotal(){
        System.out.println("Total Price = "+cartDAO.calculateTotal(u.getUserName()));

    }
    public void getCart()
    {
        List<Book> books = cartDAO.getCart(u.getUserName());
        if(books.isEmpty()){
            System.out.println("Cart is empty ...");
        }
        else
        {
            System.out.println("List of books in cart are follows..\n");
            books.forEach(book -> {
                String isRecommended = book.getRecommended() ? "Yes" : "No";


                System.out.println("Title = " + book.getTitle() +
                        "\t|\tAuthor = " + book.getAuthor() +
                        "\t|\tId = " + book.getId() +
                        "\t|\tPrice = " + book.getPrice() +
                        "\t|\tQuantity = " + cartDAO.getQuantity(book.getId(), u.getUserName()) +
                        "\t|\tRecommended = " + isRecommended);
            });
            calculateTotal();

        }
    }
}

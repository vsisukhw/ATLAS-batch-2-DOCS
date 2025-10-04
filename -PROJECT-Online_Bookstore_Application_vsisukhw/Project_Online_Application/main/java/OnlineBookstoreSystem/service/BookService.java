package OnlineBookstoreSystem.service;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.model.User;
import OnlineBookstoreSystem.noSql.dao.BookDAO;

import java.util.List;
import java.util.Scanner;

public class BookService {

    Scanner sc = new Scanner(System.in);
    private BookDAO bookDAO;


    BrowsingHistory br;

    public BookService() {
        this.bookDAO = new BookDAO();
    }


    public void setBrowsingHistory(BrowsingHistory browsingHistory, User u) {
        this.br = browsingHistory;
        //this.br = new BrowsingHistory(this,u);
    }

    public void initialiseBooks()
    {
        // Check if books already exist in database
        List<Book> existingBooks = bookDAO.getAllBooks();
        if (existingBooks.isEmpty()) {
            // Add initial books to database
            bookDAO.createBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1, 12, 10));
            bookDAO.createBook(new Book("To Kill a Mockingbird", "Harper Lee", 2, 14, 15));
            bookDAO.createBook(new Book("1984", "George Orwell", 3, 13, 8));
            bookDAO.createBook(new Book("Pride and Prejudice", "Jane Austen", 4, 11, 12));
            bookDAO.createBook(new Book("The Catcher in the Rye", "J.D. Salinger", 5, 15, 5));
            bookDAO.createBook(new Book("Clean Code", "Robert C. Martin", 7, 45, 3));
            bookDAO.createBook(new Book("Design Patterns", "Gang of Four", 8, 55, 9));

        }
    }


    public void createBooks()
    {
        System.out.println("Enter the book's title, author, id, price , quantity = ");
        Book b= new Book(sc.next(),sc.next(),sc.nextInt(), sc.nextInt(), sc.nextInt());
        bookDAO.createBook(b);

        System.out.println("new book created...");
    }
    public void removeBooks()
    {

        System.out.println("Which book to remove, enter its id = ");
        int id= sc.nextInt();
        bookDAO.deleteBook(id);
    }
    public void updateBooks()
    {
        System.out.println("Which book to update, enter its id = ");
        int id= sc.nextInt();
        Book book = bookDAO.getBookById(id);
        System.out.println("Enter the book's id,title, author, price , quantity = ");

        if (book != null) {
            book.setTitle(sc.next());
            book.setAuthor(sc.next());
            book.setPrice(sc.nextInt());
            book.setQuantity(sc.nextInt());

            bookDAO.updateBook(book);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found");
        }
    }
    public void searchBooksByAuthor()
    {
        System.out.println("Enter Author Name = ");
        String author = sc.next();

        List<Book> books = bookDAO.searchBooksByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No books found by author: " + author);
        } else {
            books.forEach(book -> {


                    br.addBookToBrowsingHistory(book.getId());

                System.out.println("Title = " + book.getTitle() +
                        "\t\tAuthor = " + book.getAuthor() +
                        "\t\tId = " + book.getId());
            });
        }
    }
    public void searchBooksByTitle()
    {
        System.out.println("Enter Title Name = ");
        String title = sc.next();
        List<Book> books = bookDAO.searchBooksByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No books found with title: " + title);
        } else {
            books.forEach(book -> {

                    br.addBookToBrowsingHistory(book.getId());

                System.out.println("Title = " + book.getTitle() +
                        "\t|\tAuthor = " + book.getAuthor() +
                        "\t|\tId = " + book.getId() +
                        "\t|\tPrice = " + book.getPrice() +
                        "\t|\tQuantity = " + book.getQuantity());
            });
        }
    }
    public void searchBook() {
        while (true) {
            System.out.println("1. By Author\n2. By Title\n3. Go Back");
            String o = sc.next();
            if (o.equals("1")) {
                searchBooksByAuthor();
                return;
            }
            else if (o.equals("2"))
            {
                searchBooksByTitle();
                return;
            }
            else if (o.equals("3")) {
                return;
            }
            else
                System.out.println("wrong input...try again .. .");
        }
    }
    public void getBooksList()
    {
        List<Book> books = bookDAO.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("No books available");
        } else {
            System.out.println("List of books are follows..\n");

            books.forEach(book -> {
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
    public void recommendBooks()
    {
        System.out.println("do u want to recommend book (y/n) =");
        String yn= sc.next();
        System.out.println("give its id = ");
        int id= sc.nextInt();
        if (yn.equalsIgnoreCase("y")) {
            Book book = bookDAO.getBookById(id);
            if (book != null) {
                book.setRecommended(true);
                bookDAO.updateBook(book);
                System.out.println("Book recommended successfully");

            } else {
                System.out.println("Book not found");
            }
        }
    }
    public void recomendedBooks()
    {
        List<Book> books = bookDAO.getRecommendedBooks();

        if (books.isEmpty()) {
            System.out.println("No recommended books available");
        } else {
            books.forEach(book -> {
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


    public Book getBookById(int id) {

        return bookDAO.getBookById(id);
    }
}

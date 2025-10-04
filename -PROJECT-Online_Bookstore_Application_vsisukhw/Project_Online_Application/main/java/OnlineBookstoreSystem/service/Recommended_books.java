package OnlineBookstoreSystem.service;

import OnlineBookstoreSystem.noSql.dao.BookDAO;
import OnlineBookstoreSystem.noSql.dao.RecommendedBooksDAO;
import OnlineBookstoreSystem.model.Book;

import java.util.List;

public class Recommended_books {
    
    private RecommendedBooksDAO recommendedBooksDAO;
    
    public Recommended_books() {
        this.recommendedBooksDAO = new RecommendedBooksDAO();
    }
    public void setRecommendedBooks()
    {
        BookDAO bookDAO = new BookDAO();
        List<Book> recoBooks = bookDAO.getRecommendedBooks();

        for(Book book: recoBooks)
        {
           recommendedBooksDAO.addRecommendedBook(book);
        }

    }
    public void displayRecommendedBooks() {
        List<Book> recommendedBooks = recommendedBooksDAO.getAllRecommendedBooks();
        
        if (recommendedBooks.isEmpty()) {
            System.out.println("No recommended books available.");
        } else {
            System.out.println("\n========== RECOMMENDED BOOKS ==========");
            recommendedBooks.forEach(book -> {
                System.out.println("Title: " + book.getTitle() +
                        " | Author: " + book.getAuthor() +
                        " | ID: " + book.getId() +
                        " | Price: $" + book.getPrice() +
                        " | Available: " + book.getQuantity());
            });
            System.out.println("=======================================\n");
        }
    }
    
    public List<Book> getRecommendedBooksList() {
        return recommendedBooksDAO.getAllRecommendedBooks();
    }
}
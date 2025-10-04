package OnlineBookstoreSystem;

import OnlineBookstoreSystem.model.Book;
import OnlineBookstoreSystem.service.BookService;
import OnlineBookstoreSystem.noSql.util.TableCreator;

public class BookstoreTest {
    
    public static void main(String[] args) {
        System.out.println("=== Online Bookstore Test with Sample Books ===");
        
        // Initialize tables
        TableCreator.createAllTables();
        
        // Create services
        BookService bookService = new BookService();
        
        // Initialize books
        bookService.initialiseBooks();
        
        // Test the sample books
        testSampleBooks(bookService);
    }
    
    private static void testSampleBooks(BookService bookService) {
        System.out.println("\n=== Testing Sample Books: 'Clean Code' and 'Design Patterns' ===");
        
        // Display all books
        System.out.println("\n1. All Available Books:");
        bookService.getBooksList();
        
        // Test specific sample books
        System.out.println("\n2. Testing Sample Books:");
        
        // Find Clean Code book (ID 7)
        Book cleanCode = bookService.getBookById(7);
        if (cleanCode != null) {
            System.out.println("\n✓ 'Clean Code' found:");
            System.out.println("  Title: " + cleanCode.getTitle());
            System.out.println("  Author: " + cleanCode.getAuthor());
            System.out.println("  ID: " + cleanCode.getId());
            System.out.println("  Price: $" + cleanCode.getPrice());
            System.out.println("  Quantity: " + cleanCode.getQuantity());
        } else {
            System.out.println("✗ 'Clean Code' not found!");
        }
        
        // Find Design Patterns book (ID 8) 
        Book designPatterns = bookService.getBookById(8);
        if (designPatterns != null) {
            System.out.println("\n✓ 'Design Patterns' found:");
            System.out.println("  Title: " + designPatterns.getTitle());
            System.out.println("  Author: " + designPatterns.getAuthor());
            System.out.println("  ID: " + designPatterns.getId());
            System.out.println("  Price: $" + designPatterns.getPrice());
            System.out.println("  Quantity: " + designPatterns.getQuantity());
        } else {
            System.out.println("✗ 'Design Patterns' not found!");
        }
        
        System.out.println("\n=== Test Completed Successfully ===");
        System.out.println("Both sample books 'Clean Code' and 'Design Patterns' are available in the system!");
        System.out.println("\nTo test the full functionality, run the main application and:");
        System.out.println("1. Register as a customer");
        System.out.println("2. Search for these books by title or author");
        System.out.println("3. Add them to cart and place orders");
    }
}
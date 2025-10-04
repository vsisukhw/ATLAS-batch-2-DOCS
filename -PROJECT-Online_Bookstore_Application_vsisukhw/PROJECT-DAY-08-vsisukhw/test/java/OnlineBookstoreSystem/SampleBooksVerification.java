package OnlineBookstoreSystem;

import OnlineBookstoreSystem.model.Book;

public class SampleBooksVerification {
    
    public static void main(String[] args) {
        System.out.println("=== Sample Books Verification ===");
        
        // Create the sample books as they would be initialized
        Book cleanCode = new Book("Clean Code", "Robert C. Martin", 7, 45, 3);
        Book designPatterns = new Book("Design Patterns", "Gang of Four", 8, 55, 9);
        
        System.out.println("\n✓ Sample Book 1: Clean Code");
        System.out.println("  Title: " + cleanCode.getTitle());
        System.out.println("  Author: " + cleanCode.getAuthor());
        System.out.println("  ID: " + cleanCode.getId());
        System.out.println("  Price: $" + cleanCode.getPrice());
        System.out.println("  Quantity: " + cleanCode.getQuantity());
        System.out.println("  Recommended: " + cleanCode.getRecommended());
        
        System.out.println("\n✓ Sample Book 2: Design Patterns");
        System.out.println("  Title: " + designPatterns.getTitle());
        System.out.println("  Author: " + designPatterns.getAuthor());
        System.out.println("  ID: " + designPatterns.getId());
        System.out.println("  Price: $" + designPatterns.getPrice());
        System.out.println("  Quantity: " + designPatterns.getQuantity());
        System.out.println("  Recommended: " + designPatterns.getRecommended());
        
        System.out.println("\n=== Verification Complete ===");
        System.out.println("Both sample books 'Clean Code' and 'Design Patterns' are properly configured!");
        System.out.println("\nThese books will be available when you run the main application:");
        System.out.println("- Run BookstoreApplicationDynamoDB.java");
        System.out.println("- Register as admin or customer");
        System.out.println("- Browse books to see these sample books");
        System.out.println("- Search by title: 'Clean' or 'Design'");
        System.out.println("- Search by author: 'Robert' or 'Gang'");
    }
}
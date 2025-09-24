package OnlineBookstore.mySql.dao;

import OnlineBookstore.mySql.util.DatabaseConnection;
import OnlineBookstore.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    
    public void createBook(Book book) {
        String sql = "INSERT INTO books (id, title, author, price, quantity, recommended) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getPrice());
            stmt.setInt(5, book.getQuantity());
            stmt.setBoolean(6, book.getRecommended());
            
            stmt.executeUpdate();
            System.out.println("Book added to database successfully");
            
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }
    
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("id"),
                    rs.getInt("price"),
                    rs.getInt("quantity")
                );
                book.setRecommended(rs.getBoolean("recommended"));
                books.add(book);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }
        
        return books;
    }
    
    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Book book = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("id"),
                    rs.getInt("price"),
                    rs.getInt("quantity")
                );
                book.setRecommended(rs.getBoolean("recommended"));
                return book;
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }
        
        return null;
    }
    
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, price = ?, quantity = ?, recommended = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getPrice());
            stmt.setInt(4, book.getQuantity());
            stmt.setBoolean(5, book.getRecommended());
            stmt.setInt(6, book.getId());
            
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully");
            } else {
                System.out.println("Book not found");
            }
            
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }
    
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully");
            } else {
                System.out.println("Book not found");
            }
            
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
    }
    
    public List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("id"),
                    rs.getInt("price"),
                    rs.getInt("quantity")
                );
                book.setRecommended(rs.getBoolean("recommended"));
                books.add(book);
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching books by title: " + e.getMessage());
        }
        
        return books;
    }
    
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE author LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + author + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("id"),
                    rs.getInt("price"),
                    rs.getInt("quantity")
                );
                book.setRecommended(rs.getBoolean("recommended"));
                books.add(book);
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching books by author: " + e.getMessage());
        }
        
        return books;
    }
    
    public List<Book> getRecommendedBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE recommended = true";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Book book = new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("id"),
                    rs.getInt("price"),
                    rs.getInt("quantity")
                );
                book.setRecommended(rs.getBoolean("recommended"));
                books.add(book);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving recommended books: " + e.getMessage());
        }
        
        return books;
    }
}
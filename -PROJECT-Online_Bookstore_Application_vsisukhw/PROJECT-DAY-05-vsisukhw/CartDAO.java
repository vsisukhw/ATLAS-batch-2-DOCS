package OnlineBookstore.dao;


import OnlineBookstore.model.Book;
import OnlineBookstore.util.DatabaseConnection;
import OnlineBookstore.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO
{
    User u;
    public CartDAO(User username){
        u=username;
    }
    public void addBook(Book book,int quantity,String user)
    {
        String sql = "INSERT INTO cart (id, quantity, userName) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, book.getId());
            stmt.setInt(2, quantity);
            stmt.setString(3, user );

            stmt.executeUpdate();
            System.out.println("Book added to cart successfully");

        } catch (SQLException e) {
            System.err.println("Error adding book in cart : " + e.getMessage());
        }
    }
    public void removeAllBooks(String u)
    {
        String sql = "DELETE FROM cart WHERE userName = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("All Books from cart deleted successfully");
            } else {
                System.out.println("Book not found in cart");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting book from cart: " + e.getMessage());
        }
    }

    public void removeBook(int id,String u)
    {
        String sql = "DELETE FROM cart WHERE id = ? And userName = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, u);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Book from cart deleted successfully");
            } else {
                System.out.println("Book not found in cart");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting book from cart: " + e.getMessage());
        }
    }

    public int getQuantity(int id,String u){
        int q;
        String sql = "SELECT quantity as quan FROM cart where id = ? AND userName = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, u);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                q=rs.getInt("quan");
                return q;
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving book: " + e.getMessage());
        }

        return 0;

    }

    public int calculateTotal(String username) {
        int totalAmount = 0;

        String sql = "SELECT SUM(c.quantity * b.price) AS cart_total " +
                "FROM cart c " +
                "JOIN books b ON b.id = c.id " +   // adjust if your column is different
                "WHERE c.userName = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalAmount = rs.getInt("cart_total");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error calculating cart total: " + e.getMessage());
        }

        return totalAmount;
    }


    public List<Book> getCart(String u)
    {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.title, b.author, b.id, b.price, c.quantity, b.recommended " +
                "FROM cart c " +
                "JOIN books b ON b.id = c.id " +   // assuming cart has book_id column
                "WHERE c.userName = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u);
            ResultSet rs = ps.executeQuery();

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


        }catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }

        return books;
    }

}

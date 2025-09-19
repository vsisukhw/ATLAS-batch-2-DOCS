package OnlineBookstore.dao;

import OnlineBookstore.model.Book;
import OnlineBookstore.model.OrderModel;
import OnlineBookstore.model.User;
import OnlineBookstore.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    User u = new User();
//    public String dateTime = "";
//    public String orderStatus = "";
    public OrderDAO(User username){
        u=username;
    }
    public void addOrder()
    {
        String sql = "INSERT INTO orders (orders.order_id, orders.userName, orders.order_status, orders.book_id, orders.quantity) " +
                "SELECT (SELECT COALESCE(MAX(order_id), 0) + 1 FROM orders) AS new_order_id, userName, 'CONFIRMED', id, cart.quantity " +
                "FROM cart WHERE userName = ?";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUserName() );

            stmt.executeUpdate();
            CartDAO c = new CartDAO(u);
            c.removeAllBooks(u.getUserName());
            System.out.println("uuuuuuuuuu");

        } catch (SQLException e) {
            System.err.println("Error occurs while ordering : " + e.getMessage());
        }
    }
    public void addOrder1()
    {
        String sql = "INSERT INTO orders (userName, order_status) VALUES (?, 'PENDING')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getUserName() );

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error occurs while ordering : " + e.getMessage());
        }
    }
    public List<OrderModel> viewOrder()
    {
        List<OrderModel> orderModel = new ArrayList<>();

        String sql = "SELECT o.order_id, b.title, b.author, o.book_id, b.price, o.quantity, o.order_date, o.order_status, 0 as order_total " +
                "FROM orders o " +
                "JOIN books b ON b.id = o.book_id " +   // assuming cart has book_id column
                "WHERE o.userName = ?";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUserName());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderModel order = new OrderModel(
                        rs.getInt("order_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("book_id"),
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        rs.getString("order_date"),
                        rs.getString("order_status"),
                        rs.getInt("order_total")
                );
                orderModel.add(order);

            }


        }catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }

//        System.out.println("Order Date Time = " +dateTime);
//        System.out.println("Order Status = " +orderStatus);
        return orderModel;
    }

    public int calculateTotal(String username) {
        int totalAmount = 0, orderId;

        String sql = "SELECT o.order_id, SUM(o.quantity * b.price) AS order_total " +
                "FROM orders o " +
                "JOIN books b ON b.id = o.book_id " +
                "WHERE o.userName = ? " +
                "GROUP BY o.order_id";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderId = rs.getInt("order_id");
                    totalAmount = rs.getInt("order_total");
                    System.out.println("Order " + orderId + " total = " + totalAmount);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error calculating order total: " + e.getMessage());
        }

        return totalAmount;
    }
}

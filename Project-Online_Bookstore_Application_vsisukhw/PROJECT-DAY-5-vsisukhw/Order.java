package OnlineBookstore;

import OnlineBookstore.dao.CartDAO;
import OnlineBookstore.dao.OrderDAO;
import OnlineBookstore.model.Book;
import OnlineBookstore.model.OrderModel;
import OnlineBookstore.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Order{
    private int orderId=1;
    public Cart c;
    LocalDateTime dt ;
    User u= new User();
    Boolean placed=false;
    OrderDAO orderDAO;
    //CartDAO cartDAO;

    public Order(Cart c, User u) {
        this.c = c;
        this.u = u;
        orderDAO = new OrderDAO(this.u);
        //cartDAO = new CartDAO(this.u);
    }
    //OrderDAO orderDAO = new OrderDAO(this.u);
    //Map<Book,Integer> orderedItems = new HashMap<>();
    //CartDAO cartDAO = new CartDAO(u);
    Scanner sc= new Scanner(System.in);

    public void placeOrder(){
        while(true)
        {
            System.out.println("Place Order (y/n) = ");
            String ch = sc.next();
            if (ch.equals("y"))
            {
                if(!c.cartDAO.getCart(u.getUserName()).isEmpty()) {
                    orderDAO.addOrder();
                    System.out.println("hhhhhhhhhhhh");
                }
                System.out.println("Order Placed Successfully");
                System.out.println("Order Id = " + orderId);

                orderId++;
                placed=true;
                break;
            }
            else if (ch.equals("n"))
            {
                System.out.println("Order Cancelled");
                orderDAO.addOrder1();
                break;
            }
            else
            {
                System.out.println("wrong input.....");
            }
        }
    }

    public void showOrderReceipt()
    {
        if(orderDAO.viewOrder()==null) {
            System.out.println("No orders placed yet.");
            return;
        }
        else
        {
//            System.out.println("Order Date Time = " +orderDAO.dateTime);
//            System.out.println("Order Status = " +orderDAO.orderStatus);
            List<OrderModel> om = orderDAO.viewOrder();
            Map<Integer, List<OrderModel>> grouped = om.stream()
                    .collect(Collectors.groupingBy(OrderModel::getOrderId));
            grouped.entrySet().stream().forEach( order->{
                System.out.println("Order ID = " +order.getKey());
                System.out.println("Order Date Time = " +order.getValue().get(0).getDateTime());
                System.out.println("Order Status = " +order.getValue().get(0).getOrderStatus());
                order.getValue().stream().forEach(orderItem->{
                    System.out.println("Title = " + orderItem.getTitle() +
                            "\t|\tAuthor = " + orderItem.getAuthor() +
                            "\t|\tId = " + orderItem.getId() +
                            "\t|\tPrice = " + orderItem.getPrice() +
                            "\t|\tQuantity = " + orderItem.getQuantity());

                });
                int total = order.getValue().stream().map(order1->order1.getQuantity()*order1.getPrice()).reduce(0,Integer::sum);

                System.out.println("\nTotal Order Price = "+ total);
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            });
//            System.out.println("\nTotal Order Price = "+orderDAO.calculateTotal(u.getUserName()));
//            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        }
    }


//
//    public void placeOrder1()
//    {
//        while(true)
//        {
//            System.out.println("Place Order (y/n) = ");
//            String ch = sc.next();
//            if (ch.equals("y"))
//            {
//                if(c != null && !c.items.isEmpty()) {
//                    orderedItems.putAll(c.items);
//                }
//                System.out.println("Order Placed Successfully");
//                System.out.println("Order Id = " + orderId);
//                dt = LocalDateTime.now();
//                orderId++;
//                placed=true;
//                break;
//            }
//            else if (ch.equals("n"))
//            {
//                System.out.println("Order Cancelled");
//                break;
//            }
//            else
//            {
//                System.out.println("wrong input.....");
//            }
//        }
//    }
//    public void showOrderReceipt()
//    {
//        if(!placed || dt == null) {
//            System.out.println("No orders placed yet.");
//            return;
//        }
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println("\nOrderId = " + (orderId-1) + "\nPlaced on : "+dt.format(formatter)+"\n\nList of Ordered Books :\n");
//        if(!orderedItems.isEmpty()) {
//            orderedItems.entrySet().forEach(p->System.out.println("Title ="+ p.getKey().getTitle()+"\t|\tAuthor = "+p.getKey().getAuthor()+"\t|\tId = "+p.getKey().getId()+"\t|\tPrice = "+p.getKey().getPrice()+"\t|\tQuantity = "+p.getValue()));
//        } else {
//            System.out.println("No items in this order.");
//        }
//    }
//    public void orderStatus()
//    {
//        System.out.println("Order Id = " + orderId);
//        if(placed)
//        {
//            System.out.println("Order Status = Placed Successfully");
//            System.out.println("Order Placed on = " + dt);
//        }
//        else
//        {
//            System.out.println("Order Status = Cancelled");
//        }
//    }
}

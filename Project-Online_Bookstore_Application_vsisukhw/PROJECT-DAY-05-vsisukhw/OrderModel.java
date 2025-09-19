package OnlineBookstore.model;

public class OrderModel {
    private String title;
    private String author;
    private int id;
    private int orderId;
    private int price;
    private int orderTotal;
    private int quantity;
    private String dateTime;
    private String orderStatus;

    public OrderModel(int idd,String t, String a, int i, int p, int q,String dt,String os, int ot)
    {
        orderId=idd;
        title=t;
        author=a;
        id=i;
        price=p;
        quantity=q;
        dateTime=dt;
        orderStatus=os;
        orderTotal=ot;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

package OnlineBookstoreSystem.model;

public class Book {
    private String title;
    private String author;
    private int id;
    private int price;
    private int quantity;
    private Boolean isRecommended;

    public Book(String t, String a, int i, int p, int q)
    {
        title=t;
        author=a;
        id=i;
        price=p;
        quantity=q;
        isRecommended=false;

    }

    public Boolean getRecommended() {
        return isRecommended;
    }

    public void setRecommended(Boolean recommended) {
        isRecommended = recommended;
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

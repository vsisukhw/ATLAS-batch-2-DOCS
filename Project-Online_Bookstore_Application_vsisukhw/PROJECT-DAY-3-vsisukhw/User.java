package OnlineBookstore;

import java.util.ArrayList;
import java.util.List;

enum Actor{
    Admin,
    user
}

public class User {
    private String userName;
    private Actor role;

    List<String> adminList = new ArrayList<>();
    List<String> customerList = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Actor getRole() {
        return role;
    }

    public void setRole(Actor role) {
        this.role = role;
        if(role == Actor.Admin)
        {
            adminList.add(userName);
        }
        else
            customerList.add(userName);
    }
}

package OnlineBookstoreSystem.model;

//import java.util.ArrayList;
//import java.util.List;

//enum Actor{
//    Admin,
//    user
//}

public class User {
    private String userName;
    private String role;

//    List<String> adminList = new ArrayList<>();
//    List<String> customerList = new ArrayList<>();

//    public List<String> getAdminList() {
//        return adminList;
//    }
//
//    public void setAdminList(List<String> adminList) {
//        this.adminList = adminList;
//    }
//
//    public List<String> getCustomerList() {
//        return customerList;
//    }
//
//    public void setCustomerList(List<String> customerList) {
//        this.customerList = customerList;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String rol) {
        this.role = rol;
//        if(role.equalsIgnoreCase("admin"))
//        {
//            adminList.add(userName);
//        }
//        else
//            customerList.add(userName);
//
    }
}

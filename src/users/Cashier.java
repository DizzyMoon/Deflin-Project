package users;

public class Cashier extends User{
    private String user = "kasserer";
    private String username = "kasserer";
    private String password = "kasserer";

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUser(){
        return user;
    }
}

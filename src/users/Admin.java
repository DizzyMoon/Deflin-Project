package users;

public class Admin extends User{
    private String user = "admin";
    private String username = "admin";
    private String password = "admin";

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

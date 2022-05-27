package users;

public class Coach extends User{
    private String user = "træner";
    private String username = "træner";
    private String password = "træner";

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

package users;

public abstract class User {
    private String user;
    private String username;
    private String password;



    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public void setUserName(String userName){
        this.username = userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUser(){
        return user;
    }
}

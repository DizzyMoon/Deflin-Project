package Application;
import users.*;
import UI.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {
        UserList userList = new UserList();
        ArrayList<String> userNameList = new ArrayList<String>();
        ArrayList<String> passwordList = new ArrayList<String>();
    public int loginScreen(){
        Scanner sc = new Scanner(System.in);
        UserInterface ui = new UserInterface();
        userList.getList().add(new Admin());
        userList.getList().add(new Coach());
        userList.getList().add(new Cashier());
        userList.getList().add(new Manager());

        getUserNames();
        getPasswords();


        ui.writeUserName();
        String username = sc.next();
        ui.writePassword();
        String password = sc.next();

        String currentUserString = "";
        User currentUser = null;

        boolean found = false;

        for (int i = 0; i < userList.getList().size(); i++){
            if (username.equals(userList.getList().get(i).getUsername())){
                currentUserString = userList.getList().get(i).getUser();
                currentUser = userList.getList().get(i);
                found = true;
                break;
            }
        }

        if (!found){
            return -1;
        }

        found = false;

        for (int i = 0; i < userList.getList().size(); i++){
            if (password.equals(currentUser.getPassword())){
                found = true;
                break;
            }
        }

        if (!found) {return -1;}


        switch (currentUserString){
            case "admin" -> {return 1;}
            case "træner" -> {return 2;}
            case "kasserer" -> {return 3;}
            case "formand" -> {return 4;}
        }

        return -1;
    }

    private void getUserNames(){
        for (int i = 0; i < userList.getList().size(); i++){
            userNameList.add(userList.getList().get(i).getUsername());
        }
    }


    private void getPasswords(){
        for (int i = 0; i < userList.getList().size(); i++){
            userNameList.add(userList.getList().get(i).getPassword());
        }
    }
}

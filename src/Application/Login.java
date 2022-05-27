package Application;
import users.*;
import UI.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    private UserList userList = new UserList();
    private UserInterface ui = new UserInterface();


    public int loginScreen(){
        Scanner sc = new Scanner(System.in);

        //Add all users to UserList
        userList.getList().add(new Admin());
        userList.getList().add(new Coach());
        userList.getList().add(new Cashier());
        userList.getList().add(new Manager());


        //User inputs username and password
        ui.writeUserName();
        String username = sc.next();
        ui.writePassword();
        String password = sc.next();

        //Initializing variables
        String currentUserString = "";
        User currentUser = null;
        boolean found = false;
        boolean wrongUsername = true;

        //Check if inputted username matches any usernames in UserList
        for (int i = 0; i < userList.getList().size(); i++){
            if (username.equals(userList.getList().get(i).getUsername())){
                currentUserString = userList.getList().get(i).getUser();
                currentUser = userList.getList().get(i);
                wrongUsername = false;
                break;
            }
        }


        //Only check password if username is correct (This is to keep the method running, making it harder for the user to now if the inputted username is correct)
        if (!wrongUsername){
            if (!(password.equals(currentUser.getPassword()))){
                return -1;
            }
        }

        //Return integer value dependent on what user was found (if no user was found, return -1)
        switch (currentUserString){
            case "admin" -> {return 1;}
            case "træner" -> {return 2;}
            case "kasserer" -> {return 3;}
            case "formand" -> {return 4;}
            default -> {return -1;}
        }
    }

    public void changePassword(String user){
        Scanner sc = new Scanner(System.in);

        ui.inputOldPassword();
        String passwordInput = sc.next();
        User currentUser = null;

        for (int i = 0; i < userList.getList().size(); i++){
            if (user.equals(userList.getList().get(i).getUser())){
                currentUser = userList.getList().get(i);
                break;
            }
        }

        if (currentUser.getPassword().equals(passwordInput)){
            ui.inputNewPassword();
            String newPassword = sc.next();
            ui.inputNewPasswordAgain();
            String newPasswordAgain = sc.next();
            if (newPassword.equals(newPasswordAgain)){
                currentUser.setPassword(newPassword);
            } else {
                ui.newPasswordDoesNotMatch();
            }
        } else {
            ui.badLogin();
        }
    }
}

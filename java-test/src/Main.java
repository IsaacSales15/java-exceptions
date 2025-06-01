package src;

import src.dao.UserDAO;
import src.model.MenuOption;
import src.model.UserModel;


import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private final static UserDAO dao = new UserDAO();
    private final static Scanner read = new Scanner(System.in);

    public static void main(String[] args){

        while(true) {

            // Select the operator
            System.out.println("Select a operation: ");
            System.out.println("1 - Create");
            System.out.println("2 - Update");
            System.out.println("3 - Delete");
            System.out.println("4 - Find by id");
            System.out.println("5 - Find all");
            System.out.println("6 - Exit");

            var inputOpition = read.nextInt();

            var selectOption = MenuOption.values()[inputOpition -1];
            switch (selectOption) {
                case SAVE -> {
                    var user = dao.save(requestToSave());
                    System.out.printf("User created %s", user);
                }
                case UPDATE -> {
                    var user = dao.update(requestToUpdate());
                    System.out.printf("User updated %s", user);
                }
                case DELETE -> {
                    dao.delete(requestId());
                    System.out.println("User deleted");
                }
                case FIND_BY_ID -> {
                    var user = dao.findById(requestId());
                    System.out.printf("User %s", user);
                }
                case FIND_ALL -> {
                    var users = dao.findAll();
                    System.out.println("Users created");
                    users.forEach(System.out::println);
                }
                case EXIT -> System.exit(0);
            }
        }
    }

// Receive the user infos
    private static UserModel requestToSave(){
        System.out.println("Enter the user name: ");
        var name = read.next();
        System.out.println("Enter  the user email: ");
        var email = read.next();
        System.out.println("Enter the user birthday (dd/MM/yyyy) ");
        var birthdayStr = read.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = OffsetDateTime.parse(birthdayStr, formatter);
        return new UserModel(0, birthday, email, name);
    }

    private static UserModel requestToUpdate(){
        System.out.println("Enter the user id: ");
        var id = read.nextLong();
        System.out.println("Enter the user name: ");
        var name = read.next();
        System.out.println("Enter  the user email: ");
        var email = read.next();
        System.out.println("Enter the user birthday (dd/MM/yyyy) ");
        var birthdayStr = read.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = OffsetDateTime.parse(birthdayStr, formatter);
        return new UserModel(id, birthday, email, name);
    }

    private static long requestId(){
        System.out.println("Enter the user id: ");
        return read.nextLong();
    }
}

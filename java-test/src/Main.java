package src;

import src.dao.UserDAO;
import src.exceptions.EmptyStorageException;
import src.exceptions.UserNotFoundException;
import src.exceptions.ValidatorException;
import src.model.MenuOption;
import src.model.UserModel;
import src.validator.UserValidator;


import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static src.validator.UserValidator.verifyModel;

public class Main {

    private final static UserDAO dao = new UserDAO();
    private final static Scanner read = new Scanner(System.in);

    public static void main(String[] args){

        while(true) {

            // Select the operator
            System.out.println("Select a operation: ");
            System.out.println("1 - Create");
            System.out.println("2 - Delete");
            System.out.println("3 - Update");
            System.out.println("4 - Find by id");
            System.out.println("5 - Find all");
            System.out.println("6 - Exit");

            var inputOption = read.nextInt();

            // Treatment of selected cases
            var selectOption = MenuOption.values()[inputOption -1];
            switch (selectOption) {
                case SAVE -> {
                    try {
                        var user = dao.save(requestToSave());
                        System.out.printf("User created %s", user);
                    } catch (ValidatorException e){
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }

                }
                case UPDATE -> {
                    try {
                    var user = dao.update(requestToUpdate());
                    System.out.printf("User updated %s", user);
                    } catch (UserNotFoundException | EmptyStorageException e){
                        System.out.println(e.getMessage());
                    } catch (ValidatorException e){
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                    }
                    finally {
                        System.out.println("====================");
                    }
                }
                case DELETE -> {
                    try {
                        dao.delete(requestId());
                        System.out.println("User deleted");
                    } catch (UserNotFoundException | EmptyStorageException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("====================");
                    }
                }
                case FIND_BY_ID -> {
                    try {
                        var user = dao.findById(requestId());
                        System.out.printf("User %s", user);
                    } catch (UserNotFoundException | EmptyStorageException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("====================");
                    }
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
    private static UserModel requestToSave() throws ValidatorException {
        System.out.println("Enter the user name: ");
        var name = read.next();
        System.out.println("Enter  the user email: ");
        var email = read.next();
        System.out.println("Enter the user birthday (dd/MM/yyyy) ");
        var birthdayStr = read.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayStr, formatter);
        UserModel model = new UserModel();
        return validateInput(0, birthday, email, name);

    }

    private static UserModel validateInput(final long id, LocalDate birthday, String email, String name) throws ValidatorException {
        var user = new UserModel(0, birthday, email, name);
        verifyModel(user);
        return user;
    }

    // Receive the user infos for update data
    private static UserModel requestToUpdate() throws ValidatorException {
        System.out.println("Enter the user id: ");
        var id = read.nextLong();
        System.out.println("Enter the user name: ");
        var name = read.next();
        System.out.println("Enter  the user email: ");
        var email = read.next();
        System.out.println("Enter the user birthday (dd/MM/yyyy) ");
        var birthdayStr = read.next();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = LocalDate.parse(birthdayStr, formatter);
        return validateInput(0, birthday, email, name);
    }

    // Receive user id
    private static long requestId(){
        System.out.println("Enter the user id: ");
        return read.nextLong();
    }
}

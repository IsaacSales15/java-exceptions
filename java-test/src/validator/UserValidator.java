package src.validator;

import src.exceptions.ValidatorException;
import src.model.UserModel;

public class UserValidator {

    private UserValidator(){

    }

    public static void verifyModel(final UserModel model) throws ValidatorException {
        if (stringIsBlank(model.getName()))
            throw new ValidatorException("Enter a valid name");
        if (model.getName().length() <= 1)
            throw new ValidatorException("The name must have more than 1 character");
        if (stringIsBlank(model.getEmail()))
            throw new ValidatorException("Enter a valid email, not null");
        if(!model.getEmail().contains("@") || (model.getEmail().contains(".")))
            throw new ValidatorException("Enter a valid email");
    }

    private static boolean stringIsBlank(final String value){
        return value == null || value.isBlank();
    }
}

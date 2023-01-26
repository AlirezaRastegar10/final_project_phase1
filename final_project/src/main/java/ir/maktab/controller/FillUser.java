package ir.maktab.controller;

import ir.maktab.entity.User;
import ir.maktab.entity.enumeration.UserType;
import ir.maktab.exceptions.UserExistException;
import ir.maktab.util.ColorPrint;
import ir.maktab.util.SetFields;

public class FillUser {
    private final SetFields setFields = new SetFields();

    public User getUser() {
        String email;
        try {
            email = setFields.setEmail();
        } catch (UserExistException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            return null;
        }

        String firstname = setFields.setName("firstname");
        String lastname = setFields.setName("lastname");
        String password = setFields.setPassword();
        Long registerDate = setFields.setRegisterDate();
        UserType userType = setFields.setUserType();

        return new User(firstname, lastname, email, password, registerDate, userType);
    }
}

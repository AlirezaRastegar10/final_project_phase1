package ir.maktab.util;

import ir.maktab.entity.enumeration.ExpertStatus;
import ir.maktab.entity.enumeration.UserType;
import ir.maktab.exceptions.*;
import ir.maktab.menu.PrintMenu;
import ir.maktab.service.UserService;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SetFields {

    private final CheckFields checkFields = new CheckFields();
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final PrintMenu printMenu = new PrintMenu();

    public String setName(String fieldName) {
        String name;
        while (true) {
            try {
                System.out.print("enter your " + fieldName + ": ");
                name = scanner.nextLine();
                checkFields.nameValidation(name, fieldName);
                return name;
            } catch (InvalidNameException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public String setEmail() {
        String email;
        while (true) {
            try {
                System.out.print("enter your email: ");
                email = scanner.nextLine();
                checkFields.emailValidation(email);
                if (userService.getUserByEmail(email) == null) {
                    return email;
                } else {
                    throw new UserExistException("a user with this email has already registered, please login.");
                }
            } catch (InvalidEmailException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public String setPassword() {
        String password;
        while (true) {
            try {
                System.out.print("enter your password: ");
                password = scanner.nextLine();
                checkFields.passwordValidation(password);
                return password;
            } catch (InvalidPasswordException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public Long setRegisterDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    public UserType setUserType() {
        boolean flag = true;

        UserType userType = null;
        while (flag) {
            printMenu.userTypeMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        userType = UserType.CUSTOMER;
                        flag = false;
                        break;
                    case 2:
                        userType = UserType.EXPERT;
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return userType;
    }

    public byte[] setExpertImage() {
        byte[] image;
        while (true) {
            try {
                System.out.print("enter your image url \nCopy the photo path from your computer, the format should be jpg and the maximum size should be 300kb: ");
                String url = scanner.nextLine();
                image = checkFields.imageValidation(url);
                return image;
            } catch (RuntimeException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public Long setDutyId() {
        long id;
        while (true) {
            try {
                System.out.print("To view the under duties, enter the desired duty ID: ");
                id = scanner.nextLong();
                scanner.nextLine();
                checkFields.dutyIdValidation(id);
                return id;
            } catch (DutyNotFoundException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public Long setUnderDutyId() {
        long id;
        while (true) {
            try {
                System.out.print("enter the ID of the desired under duty: ");
                id = scanner.nextLong();
                scanner.nextLine();
                checkFields.underDutyIdValidation(id);
                return id;
            } catch (UnderDutyNotFoundException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public Long setProposedPrice(Long underDutyId) {
        long proposedPrice;
        while (true) {
            try {
                System.out.print("enter your bid price: ");
                proposedPrice = scanner.nextLong();
                scanner.nextLine();
                checkFields.proposedPriceValidation(proposedPrice, underDutyId);
                return proposedPrice;
            } catch (LessProposedPriceException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public String setDescription() {
        String description;
        while (true) {
            try {
                System.out.print("explain about your work: ");
                description = scanner.nextLine();
                checkFields.descriptionValidation(description);
                return description;
            } catch (InvalidDescriptionException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public String setAddress() {
        String address;
        while (true) {
            try {
                System.out.print("enter your address: ");
                address = scanner.nextLine();
                checkFields.addressValidation(address);
                return address;
            } catch (InvalidAddressException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public Long setDateAndTime() {
        long dateAndTime;
        while (true) {
            try {
                System.out.print("enter the date and time of the task (year-month-day hour:minutes): ");
                String input = scanner.nextLine();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime localDateTime = LocalDateTime.parse(input, dateTimeFormatter);
                checkFields.dateAndTimeValidation(localDateTime);
                ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
                dateAndTime = zdt.toInstant().toEpochMilli();
                return dateAndTime;
            } catch (DateTimeParseException e) {
                System.out.println(ColorPrint.ANSI_RED + "the entered date and time format is incorrect." + ColorPrint.ANSI_RESET);
            } catch (DateAndTimeException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public Long setBasePrice() {
        long basePrice;
        while (true) {
            try {
                System.out.print("enter your base price: ");
                basePrice = scanner.nextLong();
                scanner.nextLine();
                return basePrice;
            } catch (InputMismatchException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public Long setExpertId(ExpertStatus expertStatus) {
        long id;
        while (true) {
            try {
                System.out.print("enter the ID of the expert you want to add: ");
                id = scanner.nextLong();
                scanner.nextLine();
                checkFields.expertIdValidation(id, expertStatus);
                return id;
            } catch (ExpertNotFoundException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public String setEmailForChangePassword() {
        String email;
        while (true) {
            try {
                System.out.print("enter your email: ");
                email = scanner.nextLine();
                checkFields.emailValidation(email);
                return email;
            } catch (InvalidEmailException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }
}

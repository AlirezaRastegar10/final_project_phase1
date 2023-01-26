package ir.maktab.menu;

import ir.maktab.controller.FillCustomer;
import ir.maktab.controller.FillExpert;
import ir.maktab.controller.FillUser;
import ir.maktab.entity.*;
import ir.maktab.entity.enumeration.ExpertStatus;
import ir.maktab.entity.enumeration.OrderStatus;
import ir.maktab.entity.enumeration.UserType;
import ir.maktab.exceptions.*;
import ir.maktab.security.SecurityUtils;
import ir.maktab.service.*;
import ir.maktab.util.CheckExistDuty;
import ir.maktab.util.CheckExistUnderDuty;
import ir.maktab.util.ColorPrint;
import ir.maktab.util.SetFields;

import java.util.*;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final PrintMenu printMenu = new PrintMenu();
    private final FillUser fillUser = new FillUser();
    private final FillCustomer fillCustomer = new FillCustomer();
    private final FillExpert fillExpert = new FillExpert();
    private final UserService userService = new UserService();
    private final CustomerService customerService = new CustomerService();
    private final ExpertService expertService = new ExpertService();
    private final PrintDutyList printDutyList = new PrintDutyList();
    private final PrintUnderDutyList printUnderDutyList = new PrintUnderDutyList();
    private final SetFields setFields = new SetFields();
    private final UnderDutyService underDutyService = new UnderDutyService();
    private final OrdersService ordersService = new OrdersService();
    private final PrintOrderList printOrderList = new PrintOrderList();
    private final CheckExistDuty checkExistDuty = new CheckExistDuty();
    private final DutyService dutyService = new DutyService();
    private final CheckExistUnderDuty checkExistUnderDuty = new CheckExistUnderDuty();
    private final PrintExpertList printExpertList = new PrintExpertList();

    public void run() {
        boolean flag = true;
        while (flag) {
            printMenu.homeMenu();
            System.out.print("Choose an option: ");
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                switch (input) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public void register() {
        User user = fillUser.getUser();
        if (user != null) {
            User newUser = userService.register(user);
            if (newUser.getUserType() == UserType.CUSTOMER) {
                Customer customer = fillCustomer.getCustomer();
                customer.setUser(newUser);
                customerService.create(customer);
                System.out.println("registration was successful");
            } else if (newUser.getUserType() == UserType.EXPERT) {
                Expert expert = fillExpert.getExpert();
                expert.setUser(newUser);
                expertService.create(expert);
                System.out.println("registration was successful");
            }
        }
    }

    public void login() {
        System.out.print("enter your email: ");
        String email = scanner.nextLine();

        System.out.print("enter your password: ");
        String password = scanner.nextLine();
        try {
            if (userService.login(email, password)) {
                boolean flag = true;
                User user = SecurityUtils.getUser();
                while (flag) {
                    if (user.getUserType() == UserType.CUSTOMER) {
                        printMenu.customerMenu();
                        System.out.print("Choose an option: ");
                        try {
                            int input = scanner.nextInt();
                            scanner.nextLine();
                            switch (input) {
                                case 1:
                                    registerOrder();
                                    break;
                                case 2:
                                    orders();
                                    break;
                                case 3:
                                    changePassword();
                                    break;
                                case 4:
                                    System.out.println("This section is not yet complete :)");
                                    break;
                                case 5:
                                    logout();
                                    flag = false;
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                        }
                    } else if (user.getUserType() == UserType.EXPERT) {
                        printMenu.expertMenu();

                        System.out.print("Choose an option: ");

                        try {
                            int input = scanner.nextInt();
                            scanner.nextLine();

                            switch (input) {
                                case 1:
                                    viewMyUnderDuties();
                                    break;
                                case 2:
                                    changePassword();
                                    break;
                                case 3:
                                    System.out.println("This section is not yet complete :)");
                                    break;
                                case 4:
                                    System.out.println("This section is not yet complete :(");
                                    break;
                                case 5:
                                    logout();
                                    flag = false;
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                        }
                    } else {
                        printMenu.adminMenu();

                        System.out.print("Choose an option: ");

                        try {
                            int input = scanner.nextInt();
                            scanner.nextLine();

                            switch (input) {
                                case 1:
                                    addDuty();
                                    break;
                                case 2:
                                    addUnderDuty();
                                    break;
                                case 3:
                                    approveExperts();
                                    break;
                                case 4:
                                    addExpertInUnderDuty();
                                    break;
                                case 5:
                                    removeExpertFromUnderDuties();
                                    break;
                                case 6:
                                    logout();
                                    flag = false;
                                    break;
                            }
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                        }
                    }
                }
            }
        } catch (UserNotFoundException | InvalidPasswordException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void registerOrder() {
        Customer customer = customerService.findByUserId(SecurityUtils.getUser().getId());
        try {
            printDutyList.getAllDuty();
            Long id = setFields.setDutyId();
            printUnderDutyList.getUnderDuties(id);
            Long underDutyId = setFields.setUnderDutyId();
            Long proposedPrice = setFields.setProposedPrice(underDutyId);
            String description = setFields.setDescription();
            Long dateAndTime = setFields.setDateAndTime();
            String address = setFields.setAddress();
            UnderDuty underDuty = underDutyService.findById(underDutyId);
            Orders orders = new Orders(customer, proposedPrice, description, dateAndTime, address, OrderStatus.WAITING_FOR_THE_SUGGESTION_OF_EXPERTS, underDuty);

            ordersService.create(orders);

            List<Orders> ordersList = new ArrayList<>();
            ordersList.add(orders);
            customer.setOrders(ordersList);

            customerService.create(customer);
            System.out.println("the order was created successfully");
        } catch (DutyNotFoundException | UnderDutyNotFoundException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void orders() {
        Customer customer = customerService.findByUserId(SecurityUtils.getUser().getId());
        try {
            printOrderList.getOrders(customer.getId());
        } catch (OrderNotFoundException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void changePassword() {
        User user = SecurityUtils.getUser();
        try {
            String email = setFields.setEmailForChangePassword();
            String newPassword = setFields.setPassword();
            userService.changePassword(user.getId(), email, newPassword);
            System.out.println("password changed successfully");
        } catch (WrongUserEmailException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void logout() {
        userService.logout();
    }

    public void viewMyUnderDuties() {
        Expert expert = expertService.findByUserId(SecurityUtils.getUser().getId());
        if (expert.getStatus() == ExpertStatus.NEW) {
            System.out.println("you are not verified");
        } else {
            try {
                printUnderDutyList.getUnderDutyByExpertId(expert.getId());
            } catch (UnderDutyNotFoundException e) {
                System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
            }
        }
    }

    public void addDuty() {
        String name = setFields.setName("duty");
        try {
            checkExistDuty.isExist(name);
            Duty duty = new Duty(name);
            dutyService.create(duty);
            System.out.println("duty added successfully");
        } catch (DutyExistException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void addUnderDuty() {
        try {
            printDutyList.getAllDuty();
            Long id = setFields.setDutyId();
            String name = setFields.setName("under duty");
            checkExistUnderDuty.isExist(id, name);
            Long basePrice = setFields.setBasePrice();
            String explanation = setFields.setDescription();
            Duty duty = dutyService.findById(id);

            UnderDuty underDuty = new UnderDuty(name, basePrice, explanation, duty);
            underDutyService.create(underDuty);
            System.out.println("the under duty has been successfully added.");
        } catch (DutyNotFoundException | UnderDutyExistException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void approveExperts() {
        try {
            printExpertList.getNewExpert();
            Long expertId = setFields.setExpertId(ExpertStatus.NEW);

            Expert expert = expertService.findById(expertId);
            expert.setStatus(ExpertStatus.ACCEPTED);
            expertService.create(expert);
            System.out.println("the expert has been successfully verified.");
        } catch (ExpertNotFoundException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void addExpertInUnderDuty() {
        try {
            printExpertList.getAcceptedExpert();
            Long expertId = setFields.setExpertId(ExpertStatus.ACCEPTED);
            Expert expert = expertService.findById(expertId);

            printDutyList.getAllDuty();
            Long dutyId = setFields.setDutyId();

            printUnderDutyList.getUnderDuties(dutyId);
            Long underDutyId = setFields.setUnderDutyId();
            UnderDuty underDuty = underDutyService.findById(underDutyId);

            expert.addUnderDuties(underDuty);
            expertService.create(expert);
            System.out.println("the expert was successfully added to the desired under duty.");
        } catch (ExpertNotFoundException | DutyNotFoundException | UnderDutyNotFoundException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }

    public void removeExpertFromUnderDuties() {
        try {
            printDutyList.getAllDuty();
            Long dutyId = setFields.setDutyId();

            printUnderDutyList.getUnderDuties(dutyId);
            Long underDutyId = setFields.setUnderDutyId();

            printUnderDutyList.getUnderDutiesExperts(underDutyId);
            Long expertId = setFields.setExpertId(ExpertStatus.ACCEPTED);

            Expert expert = expertService.findById(expertId);
            UnderDuty underDuty = expert.getUnderDutySet().stream()
                    .filter(t -> Objects.equals(t.getId(), underDutyId))
                    .findFirst().orElse(null);
            expert.removeUnderDuties(underDuty);
            expertService.create(expert);
        } catch (DutyNotFoundException | UnderDutyNotFoundException e) {
            System.out.println(ColorPrint.ANSI_RED + e.getMessage() + ColorPrint.ANSI_RESET);
        }
    }
}

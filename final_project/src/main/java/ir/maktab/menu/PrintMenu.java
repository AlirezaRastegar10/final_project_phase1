package ir.maktab.menu;

public class PrintMenu {

    public void homeMenu(){
        System.out.println(
                "――――― Welcome To The Achareh ――――――\n" +
                        "✎ 1. Login\n" +
                        "✎ 2. Register\n" +
                        "✎ 3. Exit Menu"
        );
    }

    public void userTypeMenu(){
        System.out.println(
                "――――― Please Select Your Role ――――――\n" +
                        "✎ 1. CUSTOMER\n" +
                        "✎ 2. EXPERT"
        );
    }

    public void customerMenu(){
        System.out.println(
                "――――― Welcome To Customer Page ――――――\n" +
                        "✎ 1. Register an order\n" +
                        "✎ 2. View all orders\n" +
                        "✎ 3. Change password\n" +
                        "✎ 4. Register a comment (Beta)\n" +
                        "✎ 5. Logout"
        );
    }

    public void expertMenu(){
        System.out.println(
                "――――― Welcome To Expert Page ――――――\n" +
                        "✎ 1. View all under duties\n" +
                        "✎ 2. Change password\n" +
                        "✎ 3. Register the offer (Beta)\n" +
                        "✎ 4. View comments (Beta)\n" +
                        "✎ 5. Logout"
        );
    }

    public void adminMenu(){
        System.out.println(
                "――――― Welcome To Admin Page ――――――\n" +
                        "✎ 1. Duty registration\n" +
                        "✎ 2. Under duty registration\n" +
                        "✎ 3. Expert approval\n" +
                        "✎ 4. Add expert to under duty\n" +
                        "✎ 5. Remove expert from under duty\n" +
                        "✎ 6. Logout"
        );
    }
}

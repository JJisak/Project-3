import java.io.IOException;
import java.util.Scanner;

public class UserLoginApplication {
    public static void main(String[] args) {
        UserService userService = new UserService();
        try {
            userService.loadUsersFromFile("data.txt");
        } catch (IOException e) {
            System.out.println("Failed to load users from file");
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);

        int attempts = 0;
        while (attempts < 5) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = userService.findUser(username, password);
            if (user != null) {
                System.out.println("Welcome " + user.getName());
                System.exit(0);
            } else {
                System.out.println("Invalid login, please try again.");
                attempts++;
            }
        }

        System.out.println("Too many failed login attempts, you are now locked out.");
    }
}

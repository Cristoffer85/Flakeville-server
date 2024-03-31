package cristoffer85.exam.snofjallbywithptbackend;

import cristoffer85.exam.snofjallbywithptbackend.DTO.LoginResponseDTO;
import cristoffer85.exam.snofjallbywithptbackend.DTO.RegistrationDTO;
import cristoffer85.exam.snofjallbywithptbackend.controller.AdminController;
import cristoffer85.exam.snofjallbywithptbackend.controller.AuthenticationController;
import cristoffer85.exam.snofjallbywithptbackend.controller.UserController;
import cristoffer85.exam.snofjallbywithptbackend.model.User;
import cristoffer85.exam.snofjallbywithptbackend.model.WeekDay;
import cristoffer85.exam.snofjallbywithptbackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UI implements CommandLineRunner {

        @Autowired
        private AdminController adminController;

        @Autowired
        private UserController userController;

        @Autowired
        private AuthenticationController authenticationController;

        @Autowired
        private PasswordEncoder passwordEncoder;

        private final AuthenticationService authenticationService;

        public UI(AuthenticationService authenticationService) {
            this.authenticationService = authenticationService;
        }

        @Override
        public void run(String... args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nWelcome to the How long did you sleep last Christmas? application!\n");

                System.out.println("""
                                .-""-.
                               /,..___\\
                              () {_____}
                                (/-@-@-\\)
                                {`-=^=-'}
                                {  `-'  }
                                 {     }
                                  `---'
                    """);

                System.out.println("1. Login");
                System.out.println("2. Create User");

                System.out.print("\nEnter your choice: ");

                // Read the choice as a trimmed string
                String choiceInput = scanner.nextLine().trim();

                // Check if the choice is blank or empty
                if (choiceInput.isEmpty()) {
                    System.out.println("\nInvalid choice. Please enter a valid number.");
                    continue;
                }

                // Validate the choice to ensure it's an integer
                try {
                    int choice = Integer.parseInt(choiceInput);

                    if (choice == 0 || choice > 2) {
                        System.out.println("\nInvalid choice. Please enter a valid number.");
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            handleLogin(scanner);
                            break;
                        case 2:
                            AdminCreateUser(scanner);
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please enter a valid number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid choice. Please enter a valid number.");
                }
            }
        }

        private void handleLogin(Scanner scanner) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            LoginResponseDTO loginResponse = authenticationService.loginUser(username, password);

            if (loginResponse.getUser() != null) {
                System.out.println("\nLogin successful.\n");
                System.out.println("User: " + loginResponse.getUser().getUsername());

                if (loginResponse.getUser().getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
                    handleAdminMenu(scanner);
                } else {
                    handleUserMenu(scanner);
                }
            } else {
                System.out.println("\nLogin failed. Bad credentials. Try again.\n");
            }
        }

        private void handleAdminMenu(Scanner scanner) {
            while (true) {
                System.out.println("""
            \nAdmin Menu:
            1. View All Users
            2. View One User
            3. Create User
            4. Update User
            5. Delete User
            0. <--- Back to Main Menu""");

                System.out.print("\nEnter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid choice. Please enter a valid integer.");
                    scanner.next();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> AdminViewAllUsers();
                    case 2 -> AdminViewOneUser(scanner);
                    case 3 -> AdminCreateUser(scanner);
                    case 4 -> AdminUpdateUser(scanner);
                    case 5 -> AdminDeleteUser(scanner);
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void AdminViewAllUsers() {
            List<User> users = adminController.getAllUsers();
            if (!users.isEmpty()) {
                System.out.println("All Users:");
                for (User user : users) {
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("Authorities: " + user.getAuthorityStrings());
                    System.out.println("Max Hours slept in one day: " + user.getMaxHoursSlept());
                    System.out.println("Weekday: " + user.getWeekDay());
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("No users found.");
            }
        }

        private void AdminViewOneUser(Scanner scanner) {
            System.out.print("Enter username: ");
            String userId = scanner.nextLine();
            User user = adminController.getOneUser(userId);
            if (user != null) {
                System.out.println("User Details:");
                System.out.println("Username: " + user.getUsername());
                System.out.println("Authorities: " + user.getAuthorityStrings());
                System.out.println("Max Hours slept in one day: " + user.getMaxHoursSlept());
                System.out.println("Weekday: " + user.getWeekDay());
            } else {
                System.out.println("User not found.");
            }
        }

        private void AdminCreateUser(Scanner scanner) {
            String username;
            String password;
            int maxHoursSlept;
            WeekDay weekDay;

            // Loop for username validation
            while (true) {
                System.out.print("Enter username: ");
                username = scanner.nextLine();

                // Validate username
                if (isValidString(username)) {
                    break; // Exit the loop if the username is valid
                } else {
                    System.out.println("\nInvalid username. Please enter a non-empty string.");
                }
            }

            // Loop for password validation
            while (true) {
                System.out.print("Enter password: ");
                password = scanner.nextLine();

                // Validate password
                if (isValidString(password)) {
                    break; // Exit the loop if the password is valid
                } else {
                    System.out.println("\nInvalid password. Please enter a non-empty string.");
                }
            }

            // Loop for maxHoursSlept validation
            while (true) {
                System.out.print("Enter max hours slept (1-24): ");
                try {
                    maxHoursSlept = Integer.parseInt(scanner.nextLine());

                    // Validate maxHoursSlept
                    if (maxHoursSlept >= 1 && maxHoursSlept <= 24) {
                        break; // Exit the loop if maxHoursSlept is valid
                    } else {
                        System.out.println("\nInvalid max hours slept. Please enter a value between 1 and 24.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid input. Please enter a valid integer.");
                }
            }

            // Loop for weekDay validation
            while (true) {
                System.out.print("Enter week day (MONDAY, TUESDAY, ..., SUNDAY): ");
                try {
                    weekDay = WeekDay.valueOf(scanner.nextLine().toUpperCase());

                    // Validate weekDay
                    break; // Exit the loop if weekDay is valid
                } catch (IllegalArgumentException e) {
                    System.out.println("\nInvalid week day. Please enter a valid day of the week.");
                }
            }

            // Create RegistrationDTO
            RegistrationDTO registrationDTO = new RegistrationDTO(username, password, maxHoursSlept, weekDay);

            try {
                // Call the registerUser method in AuthenticationController
                User createdUser = authenticationController.registerUser(registrationDTO);

                if (createdUser != null) {
                    System.out.println("\nUser created successfully");
                } else {
                    System.out.println("\nFailed to create user");
                }
            } catch (RuntimeException e) {
                // Catch the exception thrown when there's a failure to create a user
                System.out.println("\nFailed to create user: " + e.getMessage());
            }
        }


        private void AdminUpdateUser(Scanner scanner) {
            String userId;
            User existingUser;

            // Loop for user ID validation
            while (true) {
                System.out.print("Enter username to update: ");
                userId = scanner.nextLine();
                existingUser = adminController.getOneUser(userId);

                // Validate user ID
                if (existingUser != null) {
                    break; // Exit the loop if the user ID is valid
                } else {
                    System.out.println("User not found. Please enter a valid user ID.");
                }
            }

            String newUsername;
            String newPassword;
            int newMaxHoursSlept;
            WeekDay newWeekDay;

            // Loop for new username validation
            while (true) {
                System.out.print("Enter new username: ");
                newUsername = scanner.nextLine();

                // Validate new username
                if (isValidString(newUsername)) {
                    break; // Exit the loop if the new username is valid
                } else {
                    System.out.println("Invalid username. Please enter a non-empty string.");
                }
            }

            // Loop for new password validation
            while (true) {
                System.out.print("Enter new password: ");
                newPassword = scanner.nextLine();

                // Validate new password
                if (isValidString(newPassword)) {
                    break; // Exit the loop if the new password is valid
                } else {
                    System.out.println("Invalid password. Please enter a non-empty string.");
                }
            }

            // Loop for new maxHoursSlept validation
            while (true) {
                System.out.print("Enter new max hours slept (1-24): ");
                try {
                    newMaxHoursSlept = Integer.parseInt(scanner.nextLine());

                    // Validate newMaxHoursSlept
                    if (newMaxHoursSlept >= 1 && newMaxHoursSlept <= 24) {
                        break; // Exit the loop if newMaxHoursSlept is valid
                    } else {
                        System.out.println("Invalid max hours slept. Please enter a value between 1 and 24.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }

            // Loop for new weekDay validation
            while (true) {
                System.out.print("Enter new week day (MONDAY, TUESDAY, ..., SUNDAY): ");
                try {
                    newWeekDay = WeekDay.valueOf(scanner.nextLine().toUpperCase());

                    // Validate newWeekDay
                    break; // Exit the loop if newWeekDay is valid
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid week day. Please enter a valid day of the week.");
                }
            }

            existingUser.setUsername(newUsername);
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            existingUser.setMaxHoursSlept(newMaxHoursSlept);
            existingUser.setWeekDay(newWeekDay);

            User updatedUser = adminController.updateUser(userId, existingUser);

            if (updatedUser != null) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("Failed to update user");
            }
        }

        private void AdminDeleteUser(Scanner scanner) {
            System.out.print("Enter username to delete: ");
            String userId = scanner.nextLine();
            adminController.deleteOneUser(userId);
            System.out.println("User deleted successfully");
        }

        private void handleUserMenu(Scanner scanner) {
            while (true) {
                System.out.println("""
            \nUser Menu:
            1. View All Users
            2. View One User
            0. <--- Back to Main Menu""");

                System.out.print("\nEnter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> UserViewAllUsers();
                    case 2 -> UserViewOneUser(scanner);
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void UserViewAllUsers() {
            List<User> users = userController.getAllUsers();
            if (!users.isEmpty()) {
                System.out.println("All Users:");
                for (User user : users) {
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("Authorities: " + user.getAuthorityStrings());
                    System.out.println("Max Hours slept in one day: " + user.getMaxHoursSlept());
                    System.out.println("Weekday: " + user.getWeekDay());
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("No users found.");
            }
        }

        private void UserViewOneUser(Scanner scanner) {
            System.out.print("Enter username: ");
            String userId = scanner.nextLine();
            User user = userController.getOneUser(userId);
            if (user != null) {
                System.out.println("User Details:");
                System.out.println("Username: " + user.getUsername());
                System.out.println("Authorities: " + user.getAuthorityStrings());
                System.out.println("Max Hours slept in one day: " + user.getMaxHoursSlept());
                System.out.println("Weekday: " + user.getWeekDay());
            } else {
                System.out.println("User not found.");
            }
        }

        private boolean isValidString(String input) {
            return !input.isEmpty();
        }
}

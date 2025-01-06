package reserver;

import java.io.*;
import java.util.*;

public class ReservationDesStades {
    
    // Client class inside the same file
    static class Client {
        private String firstName;
        private String lastName;
        private String email;

        public Client(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public void reserveStadium(String stadiumName, String date, String hour, String teamName, String transactionNumber) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt", true))) {
                writer.write(stadiumName + "," + firstName + "," + lastName + "," + email + "," + teamName + "," + date + "," + hour + "," + transactionNumber + "\n");
                System.out.println("Reservation successful for " + stadiumName);
                updateStadiumAvailability(stadiumName, "unavailable");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void cancelReservationByAdmin(String stadiumName, String teamName) {
            try {
                List<String> reservations = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"));
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields[0].equals(stadiumName) && fields[4].equals(teamName)) {
                        found = true;
                    } else {
                        reservations.add(line);
                    }
                }
                reader.close();

                if (found) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("reservations.txt"));
                    for (String reservation : reservations) {
                        writer.write(reservation + "\n");
                    }
                    writer.close();
                    System.out.println("Reservation for " + stadiumName + " has been canceled.");
                    updateStadiumAvailability(stadiumName, "disponible");
                } else {
                    System.out.println("No reservation found for the provided details.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void viewAvailableStadiums() {
            try (BufferedReader reader = new BufferedReader(new FileReader("stades.txt"))) {
                String line;
                System.out.println("Available Stadiums:");
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(":");
                    if (fields.length > 2 && fields[2].equals("disponible")) {
                        System.out.println(fields[0] + " - " + fields[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void updateStadiumAvailability(String stadiumName, String status) {
            try {
                List<String> stadiums = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader("stades.txt"));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(":");
                    if (fields[0].equals(stadiumName)) {
                        line = fields[0] + ":" + fields[1] + ":" + status;
                    }
                    stadiums.add(line);
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter("stades.txt"));
                for (String stadium : stadiums) {
                    writer.write(stadium + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Admin functions
        public void addStadium(String stadiumName, String location) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("stades.txt", true))) {
                writer.write(stadiumName + ":" + location + ":disponible\n");
                System.out.println("Stadium " + stadiumName + " added successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void editStadium(String oldStadiumName, String newStadiumName, String newLocation) {
            try {
                List<String> stadiums = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader("stades.txt"));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(":");
                    if (fields[0].equals(oldStadiumName)) {
                        line = newStadiumName + ":" + newLocation + ":disponible";
                    }
                    stadiums.add(line);
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter("stades.txt"));
                for (String stadium : stadiums) {
                    writer.write(stadium + "\n");
                }
                writer.close();
                System.out.println("Stadium " + oldStadiumName + " updated to " + newStadiumName + ".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void deleteStadium(String stadiumName) {
            try {
                List<String> stadiums = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader("stades.txt"));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(":");
                    if (!fields[0].equals(stadiumName)) {
                        stadiums.add(line);
                    }
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter("stades.txt"));
                for (String stadium : stadiums) {
                    writer.write(stadium + "\n");
                }
                writer.close();
                System.out.println("Stadium " + stadiumName + " deleted successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Admin check function
    private static boolean checkAdminCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("admins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("John", "Doe", "john.doe@example.com");
        boolean isAdminLoggedIn = false;

        while (true) {
            System.out.println("\n=== Stadium Reservation System ===");
            System.out.println("1. Reserve a Stadium");
            System.out.println("2. View Available Stadiums");
            System.out.println("3. Exit");
            System.out.println("4. Admin Login");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    client.viewAvailableStadiums();
                    System.out.print("Enter the stadium name: ");
                    String stadiumName = scanner.nextLine();
                    System.out.print("Enter the date (yyyy-mm-dd): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter the hour (hh:mm): ");
                    String hour = scanner.nextLine();
                    System.out.print("Enter the team name: ");
                    String teamName = scanner.nextLine();
                    System.out.print("Enter the bank transaction number: ");
                    String transactionNumber = scanner.nextLine();
                    client.reserveStadium(stadiumName, date, hour, teamName, transactionNumber);
                    break;
                case 2:
                    client.viewAvailableStadiums();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                case 4:
                    if (!isAdminLoggedIn) {
                        System.out.print("Enter admin username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter admin password: ");
                        String password = scanner.nextLine();
                        if (checkAdminCredentials(username, password)) {
                            isAdminLoggedIn = true;
                            System.out.println("Admin logged in successfully.");
                            
                            // Admin options
                            while (true) {
                                System.out.println("\n=== Admin Menu ===");
                                System.out.println("1. Cancel a Reservation");
                                System.out.println("2. Add a Stadium");
                                System.out.println("3. Edit a Stadium");
                                System.out.println("4. Delete a Stadium");
                                System.out.println("5. Log out");
                                System.out.print("Choose an option: ");
                                int adminChoice = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                switch (adminChoice) {
                                    case 1:
                                        System.out.print("Enter the stadium name: ");
                                        String cancelStadium = scanner.nextLine();
                                        System.out.print("Enter the team name: ");
                                        String cancelTeam = scanner.nextLine();
                                        client.cancelReservationByAdmin(cancelStadium, cancelTeam);
                                        break;
                                    case 2:
                                        System.out.print("Enter the new stadium name: ");
                                        String newStadiumName = scanner.nextLine();
                                        System.out.print("Enter the location: ");
                                        String newLocation = scanner.nextLine();
                                        client.addStadium(newStadiumName, newLocation);
                                        break;
                                    case 3:
                                        System.out.print("Enter the old stadium name: ");
                                        String oldStadiumName = scanner.nextLine();
                                        System.out.print("Enter the new stadium name: ");
                                        String editStadiumName = scanner.nextLine();
                                        System.out.print("Enter the new location: ");
                                        String editLocation = scanner.nextLine();
                                        client.editStadium(oldStadiumName, editStadiumName, editLocation);
                                        break;
                                    case 4:
                                        System.out.print("Enter the stadium name to delete: ");
                                        String deleteStadium = scanner.nextLine();
                                        client.deleteStadium(deleteStadium);
                                        break;
                                    case 5:
                                        System.out.println("Logging out...");
                                        isAdminLoggedIn = false;
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                }
                                
                                if (!isAdminLoggedIn) {
                                    break;  // Exit the admin menu if logged out
                                }
                            }
                        } else {
                            System.out.println("Invalid credentials.");
                        }
                    } else {
                        System.out.println("You are already logged in as an admin.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

import java.io.*;
import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (judgeLogin()) {
            while (true) {
                System.out.println("\n*******************************************************");
                System.out.println("|    Criminal Record Management System & Login        |");
                System.out.println("_______________________________________________________\n");
                System.out.println("|             1. Add Criminal Record                   |");
                System.out.println("|             2. Delete Criminal Record                |");
                System.out.println("|             3. View Criminal Records                 |");
                System.out.println("|             4. Search Criminal Record                |");
                System.out.println("|             0. Exit                                  |");
                System.out.println("*******************************************************\n");
                System.out.print("Enter your choice : ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addCriminalRecord();
                        break;
                    case 2:
                        deleteCriminalRecord();
                        break;
                    case 3:
                        viewCriminalRecords();
                        break;
                    case 4:
                        searchCriminalRecord();
                        break;

                    case 0:
                        System.out.println("------------------------------------------------");
                        System.out.println("|           THANK YOU FOR VISITING US            |");
                        System.out.println("-------------------------------------------------");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter the correct choice!!!");
                }
            }
        } else {
            System.out.println("Incorrect username or password.Try again");
        }
    }

    public static void addCriminalRecord() {
        if (!fileExists("criminals.txt")) {
            System.out.println("No criminal records found.");
        } else {
            System.out.print("Enter the criminal's ID: ");
            int id = scanner.nextInt();

            System.out.print("Enter the criminal's name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print("Enter the criminal's sex: ");
            String sex = scanner.nextLine();

            System.out.print("Enter the criminal's date of birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();

            System.out.print("Enter the criminal's committed crime: ");
            String crime = scanner.nextLine();

            System.out.print("Enter the criminal's sentence: ");
            double sentence = scanner.nextDouble();

            try (PrintWriter writer = new PrintWriter(new FileWriter("criminals.txt", true))) {
                // add new recored to the file
                writer.println(id + " : " + name + " : " + sex + " : " + dob + " : " + crime + " : " + sentence);
                System.out.println("Criminal record added successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteCriminalRecord() {
        if (!fileExists("criminals.txt")) {
            System.out.println("No criminal records found.");
        } else {
            int count = 0;
            System.out.print("\nEnter Criminal's ID:");
            int id = scanner.nextInt();

            try (BufferedReader reader = new BufferedReader(new FileReader("criminals.txt"));
                    PrintWriter writer = new PrintWriter(new FileWriter("tempCriminals.txt"))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" : ");
                    int currentId = Integer.parseInt(parts[0]);

                    if (id == currentId) {
                        count++;
                    } else {
                        writer.println(line);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (count == 0) {
                System.out.println("ID not found");
            } else {
                System.out.println("Deleted successfully");
                // Rename the temp file to the original file
                new File("tempCriminals.txt").renameTo(new File("criminals.txt"));
                try {
                    File newFile = new File("tempCriminals.txt");
                    newFile.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void viewCriminalRecords() {
        if (!fileExists("criminals.txt")) {
            System.out.println("No criminal records found.");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader("criminals.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" : ");
                    System.out.println("ID: " + parts[0]);
                    System.out.println("Name: " + parts[1]);
                    System.out.println("Sex: " + parts[2]);
                    System.out.println("DOB: " + parts[3]);
                    System.out.println("Crime: " + parts[4]);
                    System.out.println("Sentence: " + parts[5]);
                    System.out.println();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void searchCriminalRecord() {
        if (!fileExists("criminals.txt")) {
            System.out.println("No criminal records found.");
        } else {
            int count = 0;
            System.out.print("\nEnter Criminal's ID:");
            int id = scanner.nextInt();

            try (BufferedReader reader = new BufferedReader(new FileReader("criminals.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" : ");
                    int currentId = Integer.parseInt(parts[0]);

                    if (id == currentId) {
                        System.out.println("ID: " + parts[0]);
                        System.out.println("Name: " + parts[1]);
                        System.out.println("Sex: " + parts[2]);
                        System.out.println("DOB: " + parts[3]);
                        System.out.println("Crime: " + parts[4]);
                        System.out.println("Sentence: " + parts[5]);
                        count++;
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (count == 0) {
                System.out.println("ID not found");
            }
        }
    }

    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists() && file.isFile();
    }

    public static boolean judgeLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("judgePasswords.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" : ");
                String storedUsername = parts[0];
                String storedPassword = parts[1];

                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true; // Login successful
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Login failed
    }
}

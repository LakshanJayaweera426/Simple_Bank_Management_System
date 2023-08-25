import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String fullName;
    private String idNumber;
    private String accountNumber;
    private double totalAmount;

    public BankAccount(String fullName, String idNumber) {
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.accountNumber = generateAccountNumber();
        this.totalAmount = 0.0;
    }

    public String getFullName() {
        return fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void deposit(double amount) {
        totalAmount += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= totalAmount) {
            totalAmount -= amount;
            return true;
        } else {
            return false; // Insufficient funds
        }
    }

    private String generateAccountNumber() {
        // Generate a random 8-digit account number (for simplicity)
        return String.format("%08d", (int) (Math.random() * 100000000));
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Map<String, BankAccount> accounts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBank Management System Menu:");
            System.out.println("1. Create an account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Enter ID number: ");
                    String idNumber = scanner.next();
                    BankAccount account = new BankAccount(fullName, idNumber);
                    accounts.put(account.getAccountNumber(), account);
                    System.out.println("Your account has been created!");
                    displayAccountInfo(account);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String depositAccountNumber = scanner.next();
                    BankAccount depositAccount = accounts.get(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.println("Account found for: " + depositAccount.getFullName());
                        System.out.print("Is this the correct account? (yes/no): ");
                        String confirmation = scanner.next();
                        if (confirmation.equalsIgnoreCase("yes")) {
                            System.out.print("Enter the deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            depositAccount.deposit(depositAmount);
                            System.out.println("Deposit successful!");
                            displayDepositInfo(depositAccount, depositAmount);
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String withdrawAccountNumber = scanner.next();
                    BankAccount withdrawAccount = accounts.get(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter ID number: ");
                        String withdrawIdNumber = scanner.next();
                        if (withdrawIdNumber.equals(withdrawAccount.getIdNumber())) {
                            System.out.print("Enter the withdrawal amount: ");
                            double withdrawalAmount = scanner.nextDouble();
                            if (withdrawAccount.withdraw(withdrawalAmount)) {
                                System.out.println("Withdrawal successful!");
                                displayWithdrawalInfo(withdrawAccount, withdrawalAmount);
                            } else {
                                System.out.println("Insufficient funds.");
                            }
                        } else {
                            System.out.println("ID number does not match.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayAccountInfo(BankAccount account) {
        System.out.println("\n--- Account Information ---");
        System.out.println("Full Name	: " + account.getFullName());
        System.out.println("Account Number	: " + account.getAccountNumber());
        System.out.println("Total Amount	: " + account.getTotalAmount());
    }

    private static void displayDepositInfo(BankAccount account, double depositAmount) {
        System.out.println("\n--- Deposit Information ---");
        System.out.println("Full Name	: " + account.getFullName());
        System.out.println("Account Number	: " + account.getAccountNumber());
        System.out.println("Deposited Amount: " + depositAmount);
    }

    private static void displayWithdrawalInfo(BankAccount account, double withdrawalAmount) {
        System.out.println("\n--- Withdrawal Information ---");
        System.out.println("Full Name	: " + account.getFullName());
        System.out.println("Account Number	: " + account.getAccountNumber());
        System.out.println("Withdrawn Amount: " + withdrawalAmount);
        System.out.println("Total Amount	: " + account.getTotalAmount());
    }
}

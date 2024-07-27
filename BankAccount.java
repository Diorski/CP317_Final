public class BankAccount {
    
    private int accountNumber;
    private String accountHolderName;
    private int balance;

    public BankAccount(int accountNumber, String accountHolderName, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        if (this.balance >= amount) {
            System.out.println("-Withdraw Succesful-");
            this.balance -= amount;
        }
        else {
            System.out.println("Insufficient funds");
        }
    }

    public void displayAccountDetails() {
        System.out.println("Displaying information...");
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Account Name: " + this.accountHolderName);
        System.out.println("Account Balance: " + this.balance + "\n");
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
}

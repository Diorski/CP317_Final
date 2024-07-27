import java.util.Scanner;

public class TFSA extends SavingsAccount{

    public int goal;
    public int contributions;
    public TFSA(int accountNumber, String accountHolderName, int balance, int minimumBalance, float interestRate,
            int withdrawLimit, int goal) {
        super(accountNumber, accountHolderName, balance, minimumBalance, interestRate, withdrawLimit);
        this.goal = goal;
        this.contributions = 0;
    }
    
    @Override
    public void deposit(int amount) {
        this.contributions += amount;
        if (this.contributions >= this.goal) {
            System.out.println("Financial goal reached");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a new financial goal to work towards (> 0): ");
            String newGoal = scanner.nextLine();
            try {
                Integer.parseInt(newGoal);
                this.goal = Integer.parseInt(newGoal);
                this.contributions = 0;
                super.deposit(amount);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
            scanner.close();
        }
    }

    @Override
    public void displayAccountDetails(){
        System.out.println("Displaying information...");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("Account Name: " + this.getAccountHolderName());
        System.out.println("Account Balance: " + this.getBalance());
        System.out.println("Minimum Balance: " + this.getMinimumBalance());
        System.out.println("Interest Rate: " + this.getInterestRate());
        System.out.println("Withdraw Limit: " + this.getWithdrawLimit());
        System.out.println("Progress towards goal: " + this.contributions + "/" + this.goal + "\n");
    }
}

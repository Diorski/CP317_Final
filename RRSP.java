import java.util.Scanner;

public class RRSP extends SavingsAccount{

    public boolean retired;
    public RRSP(int accountNumber, String accountHolderName, int balance, int minimumBalance, float interestRate,
            int withdrawLimit, boolean retired) {
        super(accountNumber, accountHolderName, balance, minimumBalance, interestRate, withdrawLimit);
        this.retired = retired;
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
        System.out.println("Retired: " + this.retired + "\n");
    }

    @Override
    public void withdraw(int amount){
        if (this.retired == false) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Withdrawing from the RRSP will incur 5% tax on the withdrawal, continue? (Y/N) ");
            String confirm = scanner.nextLine();
            switch (confirm) {
                case "Y":
                    super.withdraw((int)(amount*1.05));
                    break;
                case "N":
                    System.out.println("Withdraw cancelled.");
                    break;
                default:
                    System.out.println("Unsupported action, please choose either Y or N");
            }
            while (confirm.compareTo("Y") != 0 && confirm.compareTo("N") != 0) {
                System.out.println("Withdrawing from the RRSP will incur 5% tax on the withdrawal, continue? (Y/N) ");
                confirm = scanner.nextLine();
                switch (confirm) {
                    case "Y":
                        super.withdraw((int)(amount*1.05));
                        break;
                    case "N":
                        System.out.println("Withdraw cancelled.");
                        break;
                    default:
                        System.out.println("Unsupported action, please choose either Y or N");
                }
            }
            scanner.close();
        }
        else {
            System.out.println("Retirement enabled, tax ignored");
            super.withdraw(amount);
        }
    }
}

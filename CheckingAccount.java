import java.util.Scanner;

public class CheckingAccount extends BankAccount{

    private String bankType;
    public CheckingAccount(int accountNumber, String accountHolderName, int balance, String bankType) {
        super(accountNumber, accountHolderName, balance);
        this.bankType = bankType;
    } 

    @Override
    public void displayAccountDetails(){
        System.out.println("Displaying information...");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("Account Name: " + this.getAccountHolderName());
        System.out.println("Account Balance: " + this.getBalance());
        System.out.println("Bank Type: " + this.bankType + "\n");
    }
    
    public void transferMoney(CheckingAccount targetAccount, int amount){
        if (this.getBalance() - amount >= 0) {
            int subtractedBalance = this.getBalance() - amount;
            int addedBalance = targetAccount.getBalance() + amount;
            targetAccount.setBalance(addedBalance);
            this.setBalance(subtractedBalance);
        }
        else {
            System.out.println("Insufficient funds to conduct transfer");
        }
    }

    public void atmWithdraw(int amount, String atmType) {
        if ((atmType.compareTo(this.bankType) != 0) && (atmType.compareTo("Exception") != 0)){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Fee incurred of 20$, continue with withdrawal? (Y/N)");
            String confirm = scanner.nextLine();
            switch (confirm){
                case "Y":
                    if (this.getBalance() - (amount+20) >= 0) {
                        this.setBalance(this.getBalance() - (amount+20));
                    }
                    else {
                        System.out.println("Insufficient funds to conduct withdrawal and pay fee");
                    }
                    break;
                case "N":
                    System.out.println("Withdraw cancelled.");
                    break;
                default:
                System.out.println("Unsupported action, please choose either Y or N");
            }
            while (confirm.compareTo("Y") != 0 && confirm.compareTo("N") != 0) {
                    System.out.println("Fee incurred of 20$, continue with withdrawal? (Y/N)");
                    confirm = scanner.nextLine();
                    switch (confirm){
                        case "Y":
                            if (this.getBalance() - (amount+20) >= 0) {
                                this.setBalance(this.getBalance() - (amount+20));
                            }
                            else {
                                System.out.println("Insufficient funds to conduct withdrawal and pay fee");
                            }
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
            System.out.println("Matching back type or exception allowed, no fees incurred...");
            if (this.getBalance() - amount >= 0) {
                this.setBalance(this.getBalance() - amount);
            }
            else {
                System.out.println("Insufficient funds to conduct withdrawal and pay fee");
            }
        }
    }

    public String getBankType(){
        return this.bankType;
    }
}

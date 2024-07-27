public class SavingsAccount extends BankAccount{

    private int minimumBalance;
    private float interestRate;
    private int withdrawLimit;
    public SavingsAccount(int accountNumber, String accountHolderName, int balance, int minimumBalance, float interestRate, int withdrawLimit) {
        super(accountNumber, accountHolderName, balance);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.withdrawLimit = withdrawLimit;
    }
    
    @Override
    public void withdraw(int amount) {
        if (amount < withdrawLimit) {
            if (this.getBalance() - amount < this.minimumBalance) {
                System.out.println("Withdraw denied, exceeds minimum balance");
            }
            else {
                super.withdraw(amount);
            }
        }
        else {
            System.out.println("Withdraw denied, exceeds withdraw limit");
        }
    }

    public void applyInterest() {
        this.setBalance((int)(this.getBalance()*(1+this.interestRate)));
    }

    @Override
    public void displayAccountDetails() {
        System.out.println("Displaying information...");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("Account Name: " + this.getAccountHolderName());
        System.out.println("Account Balance: " + this.getBalance());
        System.out.println("Minimum Balance: " + this.minimumBalance);
        System.out.println("Interest Rate: " + this.interestRate);
        System.out.println("Withdraw Limit: " + this.withdrawLimit + "\n");
    }

    public int getMinimumBalance(){
        return this.minimumBalance;
    }

    public float getInterestRate(){
        return this.interestRate;
    }

    public int getWithdrawLimit(){
        return this.withdrawLimit;
    }
}

public class StudentCheckingAccount extends CheckingAccount{
    public static String[] validSchools = {"Wilfrid Laurier", "University of Waterloo", "University of Toronto", "Toronto Metropolitan University"};

    public String school;
    public StudentCheckingAccount(int accountNumber, String accountHolderName, int balance, String bankType, String school) {
        super(accountNumber, accountHolderName, balance, bankType);
        this.school = school;
    }
    
    @Override
    public void atmWithdraw(int amount, String atmType){
        for (String i: validSchools) {
            if (i.compareTo(this.school) == 0) {
                System.out.println("-Account type registered with valid school, fees ignored-");
                super.atmWithdraw(amount, "Exception");
            }
        }
        super.atmWithdraw(amount, atmType);
    }

    @Override
    public void displayAccountDetails(){
        System.out.println("Displaying information...");
        System.out.println("Account Number: " + this.getAccountNumber());
        System.out.println("Account Name: " + this.getAccountHolderName());
        System.out.println("Account Balance: " + this.getBalance());
        System.out.println("Bank Type: " + this.getBankType());
        System.out.println("School: " + this.school + "\n");
    }
}

import java.time.LocalDateTime;

public class fixedDepositAcc extends bank implements interest {

    private Integer time = null;
    private LocalDateTime date = null;
    private Double interestRate = null;

    public fixedDepositAcc(String accName, String accountID, Double balance,
                           String bankName, Double transferFee,
                           Integer time, LocalDateTime date, Double interestRate) {

        super(accName, accountID, balance, bankName, transferFee);
        this.time = time;
        this.date = date;
        this.interestRate = interestRate;
    }

    // setters
    public void setTime(Integer time) { this.time = time; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }

    // getters
    public Integer getTime() { return this.time; }
    public LocalDateTime getDate() { return this.date; }
    public Double getInterestRate() { return this.interestRate; }

    // maturity check
    public boolean isMatured() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(date.plusMonths(time));
    }

    //  Method  follow interface
    @Override
    public void getInterest(Double balance) {

        if (interestRate == null || time == null) {
            System.out.println("Interest cannot be calculated.");
            return;
        }

        if (!isMatured()) {
            System.out.println("Account not matured. Interest = 0");
            return;
        }

        Double interest = balance * interestRate * time;

        System.out.println("Interest: " + interest);
    }

    // Method add design 
    public Double calculateInterest() {

        if (!isMatured()) return 0.0;

        return getBalance() * interestRate * time;
    }

    public void cutInterest(Double amount) {

        if (!isMatured()) {
            Double penalty = amount * 0.02;
            withdraw(penalty);
            System.out.println("Penalty applied: " + penalty);
        }
    }

    public void transfer(String accName, Double amount, String bankName) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        Double total = amount;

        if (checkBank(bankName)) {
            total += getTransferFee();
        }

        if (getBalance() - total <= 0) {
            System.out.println("Transfer not allowed.");
            return;
        }

        cutInterest(amount);
        withdraw(total);

        System.out.println("Transfer completed.");
    }
}

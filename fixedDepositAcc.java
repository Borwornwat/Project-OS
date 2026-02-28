import java.time.LocalDate;

public class fixedDepositAcc extends bank implements interest, transfer {

    private Integer time = null;
    private LocalDate date = null;
    private Double interestRate = null;

    public fixedDepositAcc(String accName, String accountID, Double balance,
                           String bankName, Double transferFee,
                           Integer time, LocalDate date) {

        super(accName, accountID, balance, bankName, transferFee);
        this.time = time;
        this.date = date;
    }

    // setters
    public void setTime(Integer time) { this.time = time; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }

    // getters
    public Integer getTime() { return this.time; }
    public LocalDate getDate() { return this.date; }
    public Double getInterestRate() { return this.interestRate; }

    // maturity check
    public boolean isMatured() {
        LocalDate now = LocalDate.now();
        return now.isAfter(date.plusMonths(time));
    }

    public void calculateRate(){
        this.interestRate = Double.valueOf(this.time)/(12*100);
    }

    //  Method  follow interface
    @Override
    public void getInterest() {
        calculateRate();
        if (interestRate == null || time == null) {
            System.out.println("Interest cannot be calculated.");
            return;
        }

        if (!isMatured()) {
            System.out.println("Account not matured. Interest = 0");
            return;
        }

        Double interest = this.getBalance() * interestRate * time;

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

    @Override
    public void transfer(bank target, Double amount) {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        Double total = amount;

        if (checkBank(target.getBankName())) {
            total += getTransferFee();
        }

        if (getBalance() - total <= 0) {
            System.out.println("Transfer not allowed.");
            return;
        }

        cutInterest(amount);
        withdraw(total);
        target.deposit(total);
        transaction.addTransaction(this.getAccName(), amount, target.getAccName());
        System.out.println("Transfer completed.");
    }
}

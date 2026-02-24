public class savingAcc extends bank implements interest,transfer{
    //variable
    private String cardID = null;
    private Double cardFee = null;
    private static final Double interestRate = 0.25;

    //constructor
    public savingAcc(String accName, String accountID, Double balance, String bankName, Double transferFee, String cardID, Double cardFee){
        super(accName, accountID, balance, bankName, transferFee);
        this.cardID = cardID;
        this.cardFee = cardFee;
    }
    
    //set
    public void setCard(String cardID) {
        this.cardID = cardID;
    }
    public void setFee(Double cardFee) {
        this.cardFee = cardFee;
    }

    //get
    public String getCard() {
        return this.cardID;
    }

    public Double getFee() {
        return this.cardFee;
    }

    public Double getRate() {
        return interestRate;
    }

    //method
    @Override
    public void getInterest(Double balance) {
        //calculate interest from balance using interest rate
        double calculatedInterest = balance * interestRate;
        System.out.println("Calculated Interest: " + calculatedInterest);
        deposit(calculatedInterest);
    }

    @Override
    public void transfer(String accName, Double amount, String bankName){
        //transfer amount of money(amount) to other bank account(accName)
        //deduct balance if not the same bank
        //Can't done if balance become zero after transfer
        double totalAmount = amount;

        // checkBank returns true if the target bank is different
        if (checkBank(bankName)) { 
            totalAmount += getTransferFee();
        }
        
        // Check if balance strictly remains greater than 0
        if (getBalance() - totalAmount > 0) {
            withdraw(totalAmount);
            // Logging the transaction
            new transaction().addTransaction(accName, amount, bankName);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Balance cannot drop to zero or below.");
        }
    }

    public void useCard(Double amount){
        //deduct balance when use credit card with card fee, can't done if balance become zero after used
        double totalAmount = amount + this.cardFee;
        if (getBalance() - totalAmount > 0) {
            withdraw(totalAmount);
            System.out.println("Card transaction successful.");
        } else {
            System.out.println("Card declined. Insufficient balance.");
        }
    }

}

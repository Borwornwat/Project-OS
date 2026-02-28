public class bank extends bankAccount {
    // variable
    private String bankName = null;
    private Double transferFee = null;

    // constructor
    public bank(String accName, String accountID, Double balance, String bankName, Double transferFee) {
        super(accName, accountID, balance);
        this.bankName = bankName;
        this.transferFee = transferFee;
    }

    // set
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setTransferFee(Double transferFee) {
        this.transferFee = transferFee;
    }

    // get
    public String getBankName() {
        return this.bankName;
    }

    public Double getTransferFee() {
        return this.transferFee;
    }

    // method
    public boolean checkBank(String bankName) {
        // check if target account id the same bank or not
        return this.bankName.equals(bankName);
    }
    public void showAccount(){
        System.out.println("Account name: "+this.getAccName()+
                        "\nAccount ID: "+this.getAccountID()+
                        "\nBalance: "+this.getBalance());
    }
}

import java.util.ArrayList;
public class transaction{
    //variable
    private static ArrayList<String> transactionList = new ArrayList<>();

    //method
    public static void addTransaction(String owner, double amount, String target) {
        transactionList.add(owner + " transfer: " + amount + " Baht to " + target); 
    }
    public static void showTransaction() {
        System.out.println("Transaction History:");
        for (String transaction : transactionList) {
            System.out.println(transaction);
            
        }
    }


}
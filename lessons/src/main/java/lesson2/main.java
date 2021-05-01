package lesson2;

public class main {
    public static void main(String[] args) {
        ArrayCounter arrayCounter = new ArrayCounter(
                new String[][]{
                {"2", "3", "4", "5"},
                {"6", "7", "8", "9"},
                {"6", "7", "8", "9"},
                {"6", "7", "8", "9"}});
        int amount = arrayCounter.getAmount();
        System.out.printf("Amount = %d%n", amount);
    }
}

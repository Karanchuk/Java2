package lesson2;

public class ArrayCounter {
    private final String[][] array;

    public ArrayCounter(String[][] array) {
        if (array.length != 4) {
            throw new MyArraySizeException("Element count error in a rows");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new MyArraySizeException("Element count error in a column " + (i + 1));
            }
        }
        this.array = array;
    }

    public int getAmount() {
        int amount = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    amount += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException exception) {
                    throw new MyArrayDataException(String.format("array[%d][%d] not int", i, j));
                }
            }
        }
        return amount;
    }

}

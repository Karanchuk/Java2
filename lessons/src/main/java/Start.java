import java.util.Arrays;

public class Start {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr = new float[size];
        oneThread(arr);
        coupleThreads(arr);
    }

    public static void oneThread(float[] arr) {

        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread showed speed " + (System.currentTimeMillis() - a));

    }

    public static void coupleThreads(float[] arr) {

        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Couple of threads showed speed " + (System.currentTimeMillis() - a));

    }
}

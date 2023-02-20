package exercise;

// BEGIN
public class MaxThread extends Thread {

    private int max = Integer.MIN_VALUE;
    private final int[] array;

    public MaxThread(int[] array) {
        this.array = array;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        App.getLOGGER().info(Thread.currentThread().getName() + " started");
        for (int j : array) {
            max = Math.max(max, j);
        }
        App.getLOGGER().info(Thread.currentThread().getName() + " finished");
    }
}
// END

package exercise;

// BEGIN
public class MinThread extends Thread {

    private int min = Integer.MAX_VALUE;
    private final int[] array;

    public MinThread(int[] array) {
        this.array = array;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        App.getLOGGER().info(Thread.currentThread().getName() + "started");
        for (int j : array) {
            min = Math.min(min, j);
        }
        App.getLOGGER().info(Thread.currentThread().getName() + "finished");
    }
}

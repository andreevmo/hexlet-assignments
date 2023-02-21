package exercise;

class SafetyList {
    // BEGIN
    private int[] array = new int[10];
    private int capacity = 0;

    public synchronized boolean add(Integer element) {
        if (array.length <= capacity) {
            changeSizeArray();
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        array[capacity++] = element;
        return true;
    }

    public int getSize() {
        return capacity;
    }

    public int get(int index) {
        return array[index];
    }


    private void changeSizeArray() {
        int newSize = (int) (array.length * 2);
        int[] newArray = new int[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
    // END
}

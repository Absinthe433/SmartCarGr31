public class Counter extends Thread {
    @Override
    public void run() {
        // 从10计数到500并输出
        for (int i = 10; i <= 500; i++) {
            System.out.println("Counter: " + i);
        }
    }
}
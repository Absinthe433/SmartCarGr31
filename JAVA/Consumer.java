public class Consumer extends Thread {
    private final Buffer buffer;
    private final int totalToConsume; // 需消费的总整数数量

    // 构造方法，关联缓冲区并指定消费总数
    public Consumer(Buffer buffer, int totalToConsume) {
        this.buffer = buffer;
        this.totalToConsume = totalToConsume;
    }

    @Override
    public void run() {
        // 消费指定数量的整数
        for (int i = 0; i < totalToConsume; i++) {
            try {
                int value = buffer.get();
                // 输出消费的整数（题目要求）
                System.out.println("Consumer output: Consumed integer - " + value);
                // 模拟消费耗时（可选，使输出更清晰）
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer was interrupted.");
                break;
            }
        }
        System.out.println("Consumer finished consuming all " + totalToConsume + " integers.");
    }
}

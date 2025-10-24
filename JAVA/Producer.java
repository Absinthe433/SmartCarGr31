public class Producer extends Thread {
    private final Buffer buffer;

    // 构造方法，关联缓冲区
    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // 生产0到50的整数并放入缓冲区
        for (int i = 0; i <= 50; i++) {
            try {
                buffer.put(i);
                // 模拟生产耗时（可选，使输出更清晰）
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer was interrupted.");
                break;
            }
        }
        System.out.println("Producer finished producing all integers (0-50).");
    }
}

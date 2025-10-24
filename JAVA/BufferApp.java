public class BufferApp {
    public static void main(String[] args) {
        // 创建大小为50的缓冲区
        Buffer buffer = new Buffer(50);
        // 创建生产者线程（生产0-50，共51个整数）
        Producer producer = new Producer(buffer);
        // 创建消费者线程（需消费50个整数，与题目要求一致）
        Consumer consumer = new Consumer(buffer, 50);

        // 启动生产者和消费者线程
        producer.start();
        consumer.start();

        // （可选）等待线程结束，观察完整流程
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("BufferApp: All threads finished.");
    }
}
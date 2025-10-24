public class Buffer {
    private final int[] buffer;
    private int count; // 缓冲区中当前元素数量
    private int in;    // 生产者放入元素的索引
    private int out;   // 消费者取出元素的索引

    // 构造方法，初始化缓冲区大小
    public Buffer(int size) {
        buffer = new int[size];
        count = 0;
        in = 0;
        out = 0;
    }

    // 生产者向缓冲区放入整数
    public synchronized void put(int value) throws InterruptedException {
        // 若缓冲区已满，等待消费者取出元素
        while (count == buffer.length) {
            System.out.println("Buffer is full, Producer is waiting...");
            wait(); // 释放锁，进入等待状态
        }

        // 放入元素
        buffer[in] = value;
        in = (in + 1) % buffer.length; // 循环索引
        count++;
        System.out.println("Producer put: " + value + ", current count: " + count);

        // 通知消费者：缓冲区已有元素，可取出
        notify();
    }

    // 消费者从缓冲区取出整数
    public synchronized int get() throws InterruptedException {
        // 若缓冲区为空，等待生产者放入元素
        while (count == 0) {
            System.out.println("Buffer is empty, Consumer is waiting...");
            wait(); // 释放锁，进入等待状态
        }

        // 取出元素
        int value = buffer[out];
        out = (out + 1) % buffer.length; // 循环索引
        count--;
        System.out.println("Consumer got: " + value + ", current count: " + count);

        // 通知生产者：缓冲区已有空位，可放入
        notify();
        return value;
    }
}
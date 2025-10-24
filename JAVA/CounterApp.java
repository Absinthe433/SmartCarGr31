public class CounterApp {
    public static void main(String[] args) {
        // 创建Counter线程并启动
        Counter counterThread = new Counter();
        // 创建SleepyCounter实例，包装为Thread并启动
        Thread sleepyCounterThread = new Thread(new SleepyCounter());
        
        counterThread.start();
        sleepyCounterThread.start();
    }
}

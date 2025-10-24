public class SleepyCounter implements Runnable {
    @Override
    public void run() {
        for (int i = 10; i <= 500; i++) {
            System.out.println("SleepyCounter: " + i);
            try {
                // 每次输出后休眠0.05秒（50毫秒）
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // 捕获中断异常并输出提示信息
                System.out.println("SleepyCounter was interrupted during sleep.");
                // 重新设置中断标志，确保中断信号不丢失
                Thread.currentThread().interrupt();
                // 退出循环，终止线程
                break;
            }
        }
    }
}
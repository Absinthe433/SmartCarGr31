public class CA {
    // 用于存储SleepyCounter对应的线程，供pleaseFinish()方法访问
    private static Thread sleepyThread;

    public static void main(String[] args) {
        // 2.a：创建并启动SleepyCounter线程
        SleepyCounter sleepyCounter = new SleepyCounter();
        sleepyThread = new Thread(sleepyCounter);
        sleepyThread.start();

        // 主线程休眠1秒后，中断SleepyCounter线程（模拟中断场景）
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sleepyThread.interrupt();

        // 2.c：使用join()方法等待SleepyCounter线程结束，确保"Finished"最后输出
        try {
            sleepyThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 2.d：调用pleaseFinish()方法再次确认线程终止（可选，此处用于测试）
        pleaseFinish();

        // 输出结束信息
        System.out.println("Finished");
    }

    // 2.d：pleaseFinish()方法，用于立即终止SleepyCounter线程
    public static void pleaseFinish() {
        if (sleepyThread != null && sleepyThread.isAlive()) {
            System.out.println("Calling pleaseFinish() to terminate SleepyCounter thread.");
            sleepyThread.interrupt();
            // 等待线程终止（可选，确保终止完成）
            try {
                sleepyThread.join(500); // 最多等待500毫秒
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// 2.b：修改后的SleepyCounter类（移除sleep()并检测中断）
class SleepyCounter implements Runnable {
    @Override
    public void run() {
        for (int i = 10; i <= 500; i++) {
            // 2.b：检测中断标志，若已中断则立即返回
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("SleepyCounter detected interrupt, terminating.");
                return;
            }
            System.out.println("SleepyCounter: " + i);
        }
    }
}
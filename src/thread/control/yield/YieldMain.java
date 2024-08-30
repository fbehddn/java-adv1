package thread.control.yield;


public class YieldMain {
    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                //1. empty
//                 sleep(1); // 2.sleep
                 Thread.yield(); // 3. yield - Runnable 인 상태는 바뀌지 않음 , 즉 Run -> Ready 로 다른 스레드에 양보한 후 곧바로 스케줄링 큐에 들어감
            }
        }
    }
}

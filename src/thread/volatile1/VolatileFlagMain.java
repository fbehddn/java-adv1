package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();

        Thread t = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        t.start();

        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {
        boolean runFlag = true;

//        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                //runFlag 가 false 로 변하면 탈출
//                System.out.println("1"); // 코드가 있으면 task가 종료됨 머지 ? ? ? ? -> 뒤에서 설명

//                 콘솔에 내용을 출력하거나 Thread.sleep() 으로 인해 스레드가 잠쉬 쉬는데
//                 이때 컨텍스트 스위칭이 일어나면서 갱신된다. -> 갱신을 보장하진 않음
            }
            log("task 종료");
        }
    }
}

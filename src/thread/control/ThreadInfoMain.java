package thread.control;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {
    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        log("mainThread.threadId()=" + mainThread.threadId());
        log("mainThread.getName()=" + mainThread.getName()); //쓰레드 이름은 중복될 수 있지만 아이디값은 절대 중복되지 않는다
        log("mainThread.getPriority()=" + mainThread.getPriority()); // 운영체제마다 다름, 우선순위의 기본값은 5이다.
        log("mainThread.getThreadGroup()=" + mainThread.getThreadGroup()); // 뒤에서 설명
        log("mainThread.getState()=" + mainThread.getState());

        // myThread 스레드
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("mainThread = " + myThread);
        log("mainThread.threadId()=" + myThread.threadId());
        log("mainThread.getName()=" + myThread.getName());
        log("mainThread.getPriority()=" + myThread.getPriority());
        log("mainThread.getThreadGroup()=" + myThread.getThreadGroup());
        log("mainThread.getState()=" + myThread.getState());

        /**
         * 메인스레드를 제외하고는 스레드는 "부모쓰레드"에 의해서 생성된다.
         * 스레드 그룹 기능은 잘 사용하지 않으므로 넘어가자 ~
         */
    }
}

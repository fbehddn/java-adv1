package thread.start;

public class DaemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); // 데몬의 여부는 start 메서드로 쓰레드를 생성하기전에 결정해야함. 이후엔 바뀌지 않음
        daemonThread.start();
        System.out.println(Thread.currentThread().getName() + ": main() end");
        //메인스레드가 끝나고 사용자 쓰레드가 모두 끝났고, 데몬 스레드만 있다고 판단했기 때문에 자바가 종료됨
        //메인스레드 = 유저스레드임
    }

    static class DaemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run()");

            try {
                Thread.sleep(10000); // 10초간 실행
            } catch (InterruptedException e) { // 예외를 던질 수 없음, 무조건 잡아야함 -> 이유는 추후에 설명
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " run() end");

        }
    }
}

package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV2 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료 (q): ");
            String input = userInput.nextLine();

            //프린터 스레드가 반복문을 빠져나오기 위해 while 문을 체크해야해서 'q'를 입력해도 바로 종료되지 않는다 (3초 소비)
            if (input.equals("q")) {
                printer.work = false;  //while문을 체크하는 곳에서 빠져나온다.
                printerThread.interrupt(); //sleep() 상태에서 빠져나온다.
                break;
            }
            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while (work) {
                if (jobQueue.isEmpty()) {
                    continue; // 잡큐가 비어있으면 다시 while 문으로 돌아감
                }
                try {
                    String job = jobQueue.poll();
                    log("출력 시작: " + job + ", 대기 문서: " + jobQueue);
                    Thread.sleep(3000);
                    log("출력 완료");
                } catch (InterruptedException e) {
                    log("인터럽트!");
                    break;
                }
            }
            log("프린터 종료");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }
    }
}

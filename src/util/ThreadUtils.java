package util;

import static util.MyLogger.log;

public  abstract class ThreadUtils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("인터럽트 발생, " + e.getMessage());
            throw new RuntimeException(e); // 체크 예외를 잡아서 런타임 예외로 바꿔서 던지기
        }
    }
}

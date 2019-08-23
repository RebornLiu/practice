package schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleService {

    public static void main(String[] args) throws InterruptedException {
        /**
         * ScheduledThreadPoolExecutor 构造函数设置的最大线程数量是Integer.MAX （只能设置coreSize）
         * 这样可以避免因为线程资源的竞争导致的延迟 影响任务设置的延迟时间
         *
         * 各个任务之间是没有影响的
         * */
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        //固定频率执行 如果中间有任务异常 则停止执行该系列后续的任务（不会影响其它的任务）
        //如果中间有任务执行时间很长 超过的period 后续任务会等到前一个任务执行完成后执行，不会并发执行
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("in");
                try {
                    TimeUnit.SECONDS.sleep(10);
                }
                catch (Exception e) {

                }

                    //throw new RuntimeException("323");

            }
        }, 0, 1, TimeUnit.SECONDS);


        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("out");
                try {
                    TimeUnit.SECONDS.sleep(5);
                }
                catch (Exception e) {

                }
            }
        }, 0, 1, TimeUnit.SECONDS);
        /**
         * 固定延迟执行 如果中间有任务异常 则停止执行所有
         * */
       // executorService.scheduleWithFixedDelay()

        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}

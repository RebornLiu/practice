package schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleService {

    public static void main(String[] args) throws InterruptedException {
        /**
         * ScheduledThreadPoolExecutor 使用的队列是DelayedWorkQueue这是一个无界队列，
         * 当任务大于coreSize时线程池是先放队列再生成新的线程 因为永远不会有大于coreSize的线程
         *
         * 因此ScheduledThreadPoolExecutor无法保证实时，因此任务多时执行线程可能不够用
         * 比如java.util.concurrent.Executors#newSingleThreadScheduledExecutor()只有一个线程，那么就是顺序执行所有任务
         *
         * 所以使用ScheduledThreadPoolExecutor时要注意coreSize的大小
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

                    throw new RuntimeException("323");

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

package com.example.schedule;

import com.example.schedule.config.CommConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
//@EnableAsync
@EnableScheduling
public class ScheduleApplication {


    /**
     * 1.使用@EnableScheduling和@Scheduled 所有任务都是串行的，彼此之间影响
     * 2.@EnableScheduling+@Scheduled  @EnableAsync+@@Async 时间上任务的触发仍然是串行的，
     * 只是执行放在了线程池中，因此从触发的视角看 任务之间是不影响的。任务异常后不影响后续执行（threadpool自身的异常处理逻辑）
     * 3.@EnableScheduling+@Scheduled+
     * com.example.schedule.config.CommConf#configureTasks(org.springframework.scheduling.config.ScheduledTaskRegistrar)
     * @see CommConf
     * 将任务放在scheduledThreadPool中，而scheduledThreadPool的策略事同一个任务之间是串行的不同任务之间是并行不影响彼此的。
     * 任务异常后续仍然触发，因为spring包装了一层，将异常catch了
     * @see org.springframework.scheduling.support.DelegatingErrorHandlingRunnable
     * （这是和原生的ScheduledThreadPoolExecutor不同）
     *
     * */
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ScheduleApplication.class, args);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.DAYS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread.join();
    }

}

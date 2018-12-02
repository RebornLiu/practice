import com.example.reborn.*;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DisruptorTest {

    @Test
    public void app1() throws InterruptedException {
        Disruptor<Element> disruptor = new Disruptor<>(
                Element::new, 32,
                r -> new Thread(r, "self thread").start(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());


        disruptor.handleEventsWith(((element, l, b) -> System.out.println(element)));
        disruptor.start();

        for (int i= 0; i < 100; i++) {
            disruptor.publishEvent(((element, l) -> {
                element.setCode((int)l);
                element.setValue("this is test " + l);
            }));
        }


        TimeUnit.SECONDS.sleep(2);
        disruptor.shutdown();
    }


    /**
     * handler依赖 已经清理event中应用的对象
     * */
    @Test
    public void app2() throws InterruptedException {
        Disruptor<Element> disruptor = new Disruptor<>(
                Element::new, 64,
                Executors.newFixedThreadPool(4),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

       disruptor.handleEventsWith(new ElementEventHandler(), new JournalEventHandler())
               .then(new ReplicationHandler())
               .then(new CleanerEventHandler());

        disruptor.start();

        for (int i= 0; i < 100; i++) {
            disruptor.publishEvent(((element, l) -> {
                element.setCode((int)l);
                element.setValue("this is test " + l);
            }));
        }


        TimeUnit.SECONDS.sleep(2);
        disruptor.shutdown();
    }


    /**
     * 如果存在多个producer但是ProductType设置错误为Single，那么sequnce是可能错误的
     * */
    @Test
    public void app3() throws InterruptedException {
        Disruptor<Element> disruptor = new Disruptor<>(Element::new,
                32,
                Executors.newFixedThreadPool(4),
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        disruptor.handleEventsWith(new ElementEventHandler());
        disruptor.start();

        Thread thread = new Thread(() -> {
            for (int i = 0; i< 100; i++) {
                disruptor.publishEvent((element, l) -> {
                    element.setCode((int)l);
                });
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i< 100; i++) {
                disruptor.publishEvent((element, l) -> {
                    element.setCode((int)l);
                });
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        TimeUnit.SECONDS.sleep(5);
        disruptor.shutdown();

    }
}

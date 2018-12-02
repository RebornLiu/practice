package com.example.reborn;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Disruptor<Element> disruptor = new Disruptor<>(new ElementEventFactory(),
                256,
                Executors.newSingleThreadExecutor(), ProducerType.SINGLE,
                new BlockingWaitStrategy());
        disruptor.handleEventsWith(new ElementEventHandler());

        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();
        for (int i= 0; i < 100; i++) {
            long sequece = ringBuffer.next();
            try {
                Element element = ringBuffer.get(sequece);
                element.setCode(i);
                element.setValue("this is i");
            }
            finally {
                ringBuffer.publish(sequece);
            }
        }

        TimeUnit.SECONDS.sleep(5);
        disruptor.shutdown();
    }
}

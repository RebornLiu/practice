package com.example.reborn;

import com.lmax.disruptor.EventHandler;


/**
 * cleaner是有用的 因为disruptor是循环使用节点的，对于A节点如果存在对B的引用，虽然A被handler消费了，但是A并不会释放此时会导致B生命周期增加了
 * 这种影响可能会在ringbuffer大容量+B大对象的情况下比较明显
 * */
public class CleanerEventHandler implements EventHandler<Element> {

    @Override
    public void onEvent(Element event, long sequence, boolean endOfBatch) throws Exception {
        event.setValue(null);
        event.setCode(0);
    }
}

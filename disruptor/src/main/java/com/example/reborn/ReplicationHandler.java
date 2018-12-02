package com.example.reborn;

import com.lmax.disruptor.EventHandler;

public class ReplicationHandler implements EventHandler<Element> {

    @Override
    public void onEvent(Element event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("replication:" + event);
    }
}

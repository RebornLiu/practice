package com.example.reborn;

import com.lmax.disruptor.EventHandler;

public class ElementEventHandler implements EventHandler<Element> {

    @Override
    public void onEvent(Element element, long l, boolean b) throws Exception {
        System.out.println("element:" + element + ", l:" + l + ", b:" + b);
    }
}

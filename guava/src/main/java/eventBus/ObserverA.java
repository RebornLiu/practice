package eventBus;

import com.google.common.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

public class ObserverA extends AbstactObserver{

    @Subscribe
    public void onEvent(Event event) throws InterruptedException {
        System.out.println("this is observerA:" + event);
    }
}

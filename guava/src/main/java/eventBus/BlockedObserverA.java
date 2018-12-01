package eventBus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

public class BlockedObserverA extends AbstactObserver{

    @AllowConcurrentEvents
    @Subscribe
    public void onChange(Event event) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("blocked observer A:" + event);
    }

}

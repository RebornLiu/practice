package eventBus;

import com.google.common.eventbus.Subscribe;

public class ObserverB extends AbstactObserver{

    @Subscribe
    public void onChange(Event event) {
        System.out.println("this is observerB:" + event);
    }
}

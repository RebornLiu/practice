package eventBus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;


public class EventBusInstance {

    public static final EventBus eventBus = new EventBus();

    public static final AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(
            5, r -> new Thread(r, "fix-observer-thread")),
            new ObserverExceptionHandler());
}

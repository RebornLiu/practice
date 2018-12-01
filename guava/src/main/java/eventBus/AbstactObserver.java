package eventBus;

public class AbstactObserver {

    protected void regist() {
        EventBusInstance.eventBus.register(this);
    }

    protected void ayncRegist() {
        EventBusInstance.asyncEventBus.register(this);
    }
}

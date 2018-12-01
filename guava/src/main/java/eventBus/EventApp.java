package eventBus;

public class EventApp {

    public static void main(String[] args) {
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();
        observerB.regist();
        observerA.regist();

        EventBusInstance.eventBus.post(new Event(123, "test event"));
    }
}

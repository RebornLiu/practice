####EventBus
    1.eventBus不适用于进程间通信，只是观察者模式的封装，并不是传统意义上的发布订阅系统
    2.com.google.common.eventbus.EventBus 是同步模式，post之后会调用注册的观察者
    3.eventBus寻找观察者是按照事件类型来的，这是因为简化带来的缺点，
    poster和observer之间是靠参数类型关联的，不灵活
    4.对于一个事件会按照注册顺序调用观察者，同一个观察者默认是实现了线程安全的，
    不会出现并发调用，除非使用了AllowConcurrentEvents注解
    
####AysncEventBus
    1.实际是观察者的处理位于一个线程池中，因此post方法是立即返回的    
    
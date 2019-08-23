package threadlocal;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 *
 * */
public class ThreadLocalCopyUtil {

    public static ThreadLocal<String> strLocal = new ThreadLocal<>();
    public static ThreadLocal<Integer> intLocal = new ThreadLocal<>();


    //拷贝当前线程的threadLocal信息并返回
    public static Object store() {
        try {
            Thread current = Thread.currentThread();

            Field threadLocals = Thread.class.getDeclaredField("threadLocals");
            threadLocals.setAccessible(true);
            return threadLocals.get(current);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //使用入参的内容替换当前线程的threadLocal信息
    public static void use(Object object) throws NoSuchFieldException, IllegalAccessException {
        Thread current = Thread.currentThread();

        Field  threadLocals = Thread.class.getDeclaredField("threadLocals");
        threadLocals.setAccessible(true);
        threadLocals.set(current, object);
    }

    public static void main(String[] args) throws InterruptedException {
        intLocal.set(1);
        strLocal.set("2");

        new Thread(new DecoratorRunnable(new Runnable() {
            @Override
            public void run() {
                System.out.println(intLocal.get());
                System.out.println(strLocal.get());
            }
        })).start();


        TimeUnit.DAYS.sleep(1);
    }

    public static void getThreadLocalMap() throws NoSuchFieldException, IllegalAccessException {
        Thread current = Thread.currentThread();

        Field  threadLocals = Thread.class.getDeclaredField("threadLocals");
        threadLocals.setAccessible(true);
        Object threadLocalValue = threadLocals.get(current);
        Field table = threadLocalValue.getClass().getDeclaredField("table");
        table.setAccessible(true);
        Object tableValue = table.get(threadLocalValue);


        System.out.println(JSON.toJSONString(tableValue));
    }
}

class DecoratorRunnable implements Runnable {

    private Runnable runnable;
    private Object object;

    public DecoratorRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.object = ThreadLocalCopyUtil.store();;
    }

    @Override
    public void run() {
        try {
            ThreadLocalCopyUtil.use(object);
            runnable.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

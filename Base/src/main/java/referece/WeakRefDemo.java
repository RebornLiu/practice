package referece;


import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakRefDemo {

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        String str = new String("123");
        WeakReference<String> reference = new WeakReference<>(str, queue);

        Thread t = new Thread(() -> {
            while (true) {
                Reference<?> rf = queue.poll();
                if (rf != null) {
                    System.out.println(rf);
                    System.out.println(rf.get());
                    break;
                }
            }
        });
        t.start();

        str = null;
        System.gc();

        t.join();
    }
}

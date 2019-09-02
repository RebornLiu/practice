import java.util.concurrent.Semaphore;

class FooBar {
    private int n;

    private Semaphore semaphoreA;
    private Semaphore semaphoreB;

    public FooBar(int n) {
        this.n = n;
        semaphoreA = new Semaphore(1);
        semaphoreB = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphoreA.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphoreB.release();

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semaphoreB.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                semaphoreA.release();
            }

        }


    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(3);
        fooBar.foo(new Runnable() {
            @Override
            public void run() {
                System.out.println("foo");
            }
        });
        fooBar.bar(new Runnable() {
            @Override
            public void run() {
                System.out.println("bar");
            }
        });
    }
}

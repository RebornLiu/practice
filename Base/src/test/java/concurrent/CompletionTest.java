package concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class CompletionTest {

    @Test
    public void completeService() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "hello world");
        String rs = completableFuture.get();
        System.out.println(rs);
    }

    @Test
    public void completeTest() {
        CompletableFuture.supplyAsync(() -> "hello world")
                .whenComplete((str, e) -> {
                    System.out.println(str);
                });
    }

    @Test
    public void thenApplyTest() {
        CompletableFuture.supplyAsync(() -> "hello world")
                .thenApply(v -> v + " apply1")
                .thenApply(v -> v + " apply2")
                .whenComplete((v, e) -> System.out.println(v));
    }

    @Test
    public void thenAcceptTest() {
        CompletableFuture.supplyAsync(() -> "hello world")
                .thenAccept((v) -> System.out.println(v + " accept"))
                .getNow(null);
    }

    @Test
    public void EitherTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        CompletableFuture<String> second = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        });

        //first.acceptEither(second, System.out::println).get();

        //String rs = first.applyToEither(second, (v) -> v + "1").get();
        //System.out.println(rs);

        first.thenAcceptBothAsync(second, (f, s) -> System.out.println(f + s)).get();

    }
}

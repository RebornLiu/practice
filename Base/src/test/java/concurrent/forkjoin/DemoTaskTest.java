package concurrent.forkjoin;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class DemoTaskTest {

    @Test
    public void directorForkJoin() {
        List<Integer> array = IntStream.rangeClosed(1, 34).boxed().collect(Collectors.toList());
        DemoTask demoTask = new DemoTask(array, 0, 33);
        int sum = demoTask.compute();
        assertEquals(sum, IntStream.rangeClosed(1, 34).sum());
    }

    @Test
    public void forkJoinPool() throws ExecutionException, InterruptedException {
        List<Integer> array = IntStream.rangeClosed(1, 34).boxed().collect(Collectors.toList());
        DemoTask demoTask = new DemoTask(array, 0, 33);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> result = forkJoinPool.submit(demoTask);
        long sum = result.get();
        assertEquals(sum, IntStream.rangeClosed(1, 34).sum());
    }

}
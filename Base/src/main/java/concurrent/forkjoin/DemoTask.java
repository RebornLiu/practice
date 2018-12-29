package concurrent.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class DemoTask extends RecursiveTask<Integer> {

    List<Integer> array;
    private int left;
    private int right;

    public DemoTask(List<Integer> array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Integer compute() {
        if (left > right) {
            return 0;
        }
        if (right == left) {
            return array.get(right);
        }

        int mid = (left + right) / 2;
        DemoTask leftTask = new DemoTask(array, left, mid);
        DemoTask rightTask = new DemoTask(array, mid + 1, right);
        leftTask.fork();
        rightTask.fork();

        return leftTask.join() + rightTask.join();
    }
}

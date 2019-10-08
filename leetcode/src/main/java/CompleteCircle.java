public class CompleteCircle {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int station = gas.length;
        for (int i = 0; i < station; i ++) {
            int lastGas = 0;
            boolean complete = true;
            for (int j = i; j < station + i; j ++ ) {
                lastGas += gas[j % station] - cost[j % station];
                if (lastGas < 0) {
                    complete = false;
                    break;
                }
            }

            if (complete) {
                return i;
            }
        }

        return -1;
    }


    public int completeCircile(int[] gas, int[] cost) {
            int sum = 0;
            int cur = 0;
            int start = 0;
            for (int i = 0; i < gas.length; i++) {
                cur += gas[i] - cost[i];
                sum += gas[i] - cost[i];
                if (cur < 0) {
                    cur = 0;
                    start = i + 1;
                }
            }

            return sum >= 0 ? start : -1;

    }

    public static void main(String[] args) {
        int[] gas = new int[]{1,2,3,4,5};
        int[] cost = new int[]{3,4,5,1,2};

        CompleteCircle circle = new CompleteCircle();
        System.out.println(circle.completeCircile(gas, cost));
    }
} 
public class OneCount {

    public int hammingWeight(int n) {
        int mask = 0x1;

        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                count ++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new OneCount().hammingWeight(-9));

        System.out.println();
    }
}

import java.util.BitSet;

public class PrimerNumber {

    public int isPrimer(int n) {
        BitSet bitSet = new BitSet(n - 1);

        for (int i = 2; i * i < n; i ++) {
            if (bitSet.get(i)) {
                continue;
            }
            for (int j = i; i * j < n; j ++) {
                bitSet.set(i * j);
            }
        }

        int count = 0;
        for (int i = 2; i < n; i ++) {
            if (!bitSet.get(i)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new PrimerNumber().isPrimer(2));
    }
}

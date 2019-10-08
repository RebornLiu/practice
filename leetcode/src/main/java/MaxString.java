import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxString {

    public String largestNumber(int[] nums) {
        if (nums == null) {
            return "";
        }
        String str = Arrays.stream(nums)
                .boxed()
                .map(String::valueOf)
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int len1 = o1.length();
                        int len2 = o2.length();
                        int min = Math.min(len1, len2);

                        for (int i = 0; i < min; i ++) {
                            if (o1.charAt(i) == o2.charAt(i)) {
                                continue;
                            }
                            if (o1.charAt(i) > o2.charAt(i)) {
                                return -1;
                            }
                            else {
                                return 1;
                            }
                        }

                        if (len1 == len2) {
                            return 1;
                        }

                        return (o2 + o1).compareTo(o1 + o2);
                    }
                }).collect(Collectors.joining());
        int i = 0;
        for (i = 0; i < str.length(); i ++) {
            if (str.charAt(i) == '0') {
                continue;
            }
        }

        return str.substring(i);
    }


    public String max(List<String> array) {
        return array.stream()
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int len1 = o1.length();
                        int len2 = o2.length();
                        int min = Math.min(len1, len2);

                        for (int i = 0; i < min; i ++) {
                            if (o1.charAt(i) == o2.charAt(i)) {
                                continue;
                            }
                            if (o1.charAt(i) > o2.charAt(i)) {
                                return -1;
                            }
                            else {
                                return 1;
                            }
                        }

                        if (len1 == len2) {
                            return 1;
                        }

                        return (o2 + o1).compareTo(o1 + o2);
                    }
                }).collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        List<String> arrays = new ArrayList<>();
        arrays.add("10");
        arrays.add("2");
        System.out.println(new MaxString().max(arrays));

    }
}

public class CountAndSay {

    public String countAndSay(int n) {

        if (n == 1) {
            return "1";
        }

        String sub = countAndSay(n - 1);

        StringBuilder builder = new StringBuilder();
        int count = 1;
        char pre = sub.charAt(0);
        for (int i = 1; i < sub.length(); i ++) {
            if (sub.charAt(i) == pre) {
                count ++;
            }
            else {
                builder.append(count)
                        .append(pre);
                count = 1;
                pre = sub.charAt(i);
            }
        }

        builder.append(count)
                .append(sub.charAt(sub.length() - 1));
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new CountAndSay().countAndSay(6));
    }

}

import com.alibaba.fastjson.JSON;

public class DrainArray {

    public int[] drainArray(int[] array) {
        int len = array.length;

        int cur = 0;
        int next = 1;

        while (next < len) {
            if (array[next] != array[cur]) {
                array[cur + 1] = array[next];
                cur ++;
                next ++;
            }
            else {
                next ++;
            }
        }

        return array;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 1, 2, 3, 4, 5, 5, 6, 6, 7, 7, 7 , 7};

        System.out.println(JSON.toJSONString(new DrainArray().drainArray(array)));
    }
}

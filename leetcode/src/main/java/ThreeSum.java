import com.alibaba.fastjson.JSON;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            return null;
        }

        if (nums.length < 3) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0 ; i < nums.length; i ++) {
            map.put(0 - nums[i], i);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                int su = nums[i] + nums[j];
                Integer index = map.get(su);
                if (index != null && index != i && index != j && index > j) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[index]);
                    res.add(list);
                }
            }
        }

        //去重


        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        if (nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k ++) {
            //这一步是为了去重
            //不是nums[k]==nums[k + 1] 因为如果是这样 就错过了 k和k+1 和>k+1的组合
            //如果nums[k] == nums[k -1] 那么对于 k结合[k+1，length） 满足0的组合一定已经存在与 k-1 和[k, length）中
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }

            if (nums[k] > 0) {
                break;
            }

            int i = k + 1;
            int j = nums.length - 1;

            while (i < j) {
                //去重 同k的逻辑一样 需要注意是-1还是+1
                while (i < j && i - 1 > k && nums[i] == nums[i  - 1]) {
                    i++;
                }
                while (i < j && j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    j--;
                }

                if (i >= j) {
                    break;
                }

                int s = nums[k] + nums[i] + nums[j];
                if (s == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[k]);
                    list.add(nums[i]);
                    list.add(nums[j]);
                    res.add(list);
                    i ++;
                    j --;
                }
                else if (s > 0) {
                    j --;
                } else {
                    i ++;
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] parameter = new int[]{-2,0,3,-1,4,0,3,4,1,1,1,-3,-5,4,0};//[-5,-3,-2,-1,0,0,0,1,1,1,3,3,4,4,4]

       //Arrays.sort(parameter);
       //System.out.println(JSON.toJSONString(parameter));

        System.out.println(JSON.toJSONString(threeSum.threeSum2(parameter)));
    }
}

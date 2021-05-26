package array;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 算法-数组-好数对的数目
 *
 * 给你一个整数数组 nums 。
 *
 * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
 *
 * 返回好数对的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1,1,3]
 * 输出：4
 * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
 * 示例 2：
 *
 * 输入：nums = [1,1,1,1]
 * 输出：6
 * 解释：数组中的每组数字都是好数对
 * 示例 3：
 *
 * 输入：nums = [1,2,3]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-good-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-5-26 9:14, v1.0
 * <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 * <br>    [TODO-修改内容概述]
 */
public class NumOfHaoShuDui {
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 1, 1, 3};
        int[] test2 = {1, 1, 1, 1};
        int[] test3 = {1, 2, 3};
        NumOfHaoShuDui numOfHaoShuDui = new NumOfHaoShuDui();
        System.out.println(numOfHaoShuDui.numIdenticalPairs(test1) == 4);
        System.out.println(numOfHaoShuDui.numIdenticalPairs(test2) == 6);
        System.out.println(numOfHaoShuDui.numIdenticalPairs(test3) == 0);
    }

    /**
     * Description: 思路一，暴力循环遍历
     *
     * @author created by Meiyu Chen at 2021-5-26 9:16, v1.0
     */
    public int numIdenticalPairsV1(int[] nums) {
        int rst = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    rst++;
                }
            }
        }
        return rst;
    }

    /**
     * Description: 思路二，统计频次
     *
     * @author created by Meiyu Chen at 2021-5-26 9:16, v1.0
     */
    public int numIdenticalPairs(int[] nums) {
        int rst = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            Integer count = entry.getValue();
            rst += count*(count-1)/2;
        }
        return rst;
    }
}

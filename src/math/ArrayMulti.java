package math;

import java.math.BigDecimal;

/** Description: 判断数组元素积的符号
 *
 * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
 *
 * 如果 x 是正数，返回 1 。
 * 如果 x 是负数，返回 -1 。
 * 如果 x 是等于 0 ，返回 0 。
 * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
 *
 * 返回 signFunc(product) 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-1,-2,-3,-4,3,2,1]
 * 输出：1
 * 解释：数组中所有值的乘积是 144 ，且 signFunc(144) = 1
 * 示例 2：
 *
 * 输入：nums = [1,5,0,2,-3]
 * 输出：0
 * 解释：数组中所有值的乘积是 0 ，且 signFunc(0) = 0
 * 示例 3：
 *
 * 输入：nums = [-1,1,-1,1,-1]
 * 输出：-1
 * 解释：数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * -100 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sign-of-the-product-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-5-26 10:23, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class ArrayMulti {
    public static void main(String[] args) {
        ArrayMulti test = new ArrayMulti();
        int[] nums1 = {-1,-2,-3,-4,3,2,1};
        int[] nums2 = {1,5,0,2,-3};
        int[] nums3 = {-1,1,-1,1,-1};
        System.out.println(test.arraySign(nums1) == 1);
        System.out.println(test.arraySign(nums2) == 0);
        System.out.println(test.arraySign(nums3) == -1);

    }
    public int arraySign(int[] nums) {
        boolean rst = true;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if(num<0){
                rst = !rst;
            }
        }
        return rst?1:-1;
    }
    public int arraySignV2(int[] nums) {
        int rst = 1;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if(num<0){
                rst *=-1;
            }
        }
        return rst;
    }
    public int arraySignV1(int[] nums) {
        BigDecimal bd = new BigDecimal(1);
        for (int num : nums) {
            bd = bd.multiply(new BigDecimal(num));
        }
        return signFunc(bd);
    }

    private int signFunc(BigDecimal bd) {
        return bd.compareTo(new BigDecimal(0));
    }
}

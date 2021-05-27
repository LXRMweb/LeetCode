package math;
/** Description: 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *
 * 示例:
 *
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-27 17:41, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class AddDigits {
    public static void main(String[] args) {
        System.out.println(new AddDigits().addDigits(38)==2);
    }
    /** Description: 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     * @author created by Meiyu Chen at 2021-5-27 17:56, v1.0
     */
    public int addDigits(int num) {
        return (num-1)%9+1;
    }
    /** Description: 解决耗时问题
     * 解决办法：不使用toCharArray()
     * @author created by Meiyu Chen at 2021-5-27 17:50, v1.0
     */
    public int addDigitsV2(int num) {
        int rst = 0;
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            rst += s.charAt(i) - '0';
        }
        return rst>9?addDigits(rst):rst;
    }
    /** Description: 递归法
     * 缺点：占用内存资源多
     * @author created by Meiyu Chen at 2021-5-27 17:45, v1.0
     */
    public int addDigitsV1(int num) {
        int rst = 0;
        char[] chars = String.valueOf(num).toCharArray();
        for (char aChar : chars) {
            rst += aChar - '0';
        }
        return rst>9?addDigits(rst):rst;
    }
}

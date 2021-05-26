package math;
/** Description: 6和9组成的最大数字
 * 给你一个仅由数字 6 和 9 组成的正整数 num。
 * 你最多只能翻转一位数字，将 6 变成 9，或者把 9 变成 6 。
 * 请返回你可以得到的最大数字。
 *
 * 示例 1：
 *
 * 输入：num = 9669
 * 输出：9969
 * 解释：
 * 改变第一位数字可以得到 6669 。
 * 改变第二位数字可以得到 9969 。
 * 改变第三位数字可以得到 9699 。
 * 改变第四位数字可以得到 9666 。
 * 其中最大的数字是 9969 。
 * 示例 2：
 *
 * 输入：num = 9996
 * 输出：9999
 * 解释：将最后一位从 6 变到 9，其结果 9999 是最大的数。
 * 示例 3：
 *
 * 输入：num = 9999
 * 输出：9999
 * 解释：无需改变就已经是最大的数字了。
 *  
 *
 * 提示：
 *
 * 1 <= num <= 10^4
 * num 每一位上的数字都是 6 或者 9 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-69-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-5-26 15:58, v1.0
 */
public class Maximum69Number {
    public static void main(String[] args) {
        System.out.println("Maximum69Number");
        Maximum69Number test = new Maximum69Number();
        int num = 9669;
        System.out.println(test.maximum69Number(num) == 9969);
        int num2 = 9996;
        System.out.println(test.maximum69Number(num2) == 9999);
        int num3 = 9999;
        System.out.println(test.maximum69Number(num3) == 9999);
    }
    public int maximum69Number (int num) {
        String s = String.valueOf(num);
        int i = s.indexOf('6');
        if (i == -1) {
            return num;
        }
        /*由于都是6变成9，改变之后的数值比原来多了3*10^x
        * 如：9669 -> 9969, 9969-9669 = 300 = 3*10^2
        * 如：969969 -> 999969, 999969-969969 = 30000 = 3*10^4*/
        int e = s.length() - i - 1;
        return num+3*(int)Math.pow(10, e);
    }
    public int maximum69NumberV1 (int num) {
        return new Integer(String.valueOf(num).replaceFirst("6","9")).intValue();
    }
}

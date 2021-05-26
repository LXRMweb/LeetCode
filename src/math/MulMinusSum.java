package math;

import java.util.ArrayList;

/** Description: 整数的各位积和之差
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * 示例 1：
 *
 * 输入：n = 234
 * 输出：15
 * 解释：
 * 各位数之积 = 2 * 3 * 4 = 24
 * 各位数之和 = 2 + 3 + 4 = 9
 * 结果 = 24 - 9 = 15
 * 示例 2：
 *
 * 输入：n = 4421
 * 输出：21
 * 解释：
 * 各位数之积 = 4 * 4 * 2 * 1 = 32
 * 各位数之和 = 4 + 4 + 2 + 1 = 11
 * 结果 = 32 - 11 = 21
 *
 * 提示：
 * 1 <= n <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-26 9:50, v1.0
 */
public class MulMinusSum {
    public static void main(String[] args) {
        MulMinusSum test = new MulMinusSum();
        int n1 = 234;
        int n2 = 4421;
        System.out.println(test.subtractProductAndSum(n1) == 15);
        System.out.println(test.subtractProductAndSum(n2) == 21);
    }
    public int subtractProductAndSum(int n) {
        int multi = 1;
        int sum = 0;
        while(n>0){
            multi*=n%10;
            sum+=n%10;
            n = (int)(n/10);
        }
        return multi - sum;
    }
    public int subtractProductAndSumV3(int n) {
        int multi = 1;
        int sum = 0;
        int curr = n;
        int bit = 0;
        while(curr>0){
            bit = curr%10;
            multi*=bit;
            sum+=bit;
            curr = (int)(curr/10);
        }
        return multi - sum;
    }
    public int subtractProductAndSumV2(int n) {
        int multi = 1;
        int sum = 0;
        ArrayList<Integer> list = new ArrayList<>();
        while(n>0){
            list.add(n%10);
            n =(int)(n/10);
        }
        for (Integer bit : list) {
            multi*=bit;
            sum+=bit;
        }
        return multi - sum;
    }
    public int subtractProductAndSumV1(int n) {
        int multi = 1;
        int sum = 0;
        String[] numStrs = ("" + n).split("");
        for (String numStr: numStrs) {
            Integer num = new Integer(numStr);
            multi *= num;
            sum += num;
        }
        return multi - sum;
    }
}

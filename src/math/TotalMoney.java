package math;

/** Description: 计算力扣银行的钱
 * Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
 *
 * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
 *
 * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：10
 * 解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
 * 示例 2：
 *
 * 输入：n = 10
 * 输出：37
 * 解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
 * 示例 3：
 *
 * 输入：n = 20
 * 输出：96
 * 解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-28 10:49, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class TotalMoney {
    public static void main(String[] args) {
        TotalMoney test = new TotalMoney();
        System.out.println(test.totalMoney(4) == 10);
        System.out.println(test.totalMoney(10) == 37);
        System.out.println(test.totalMoney(20) == 96);
        System.out.println(test.totalMoney(26) == 135);
    }
    /** Description: 归纳总结法得出公式
     * 思路：
     * w = n/7
     * d = n%7
     * 1+2+3+4+5+6+7 = 28
     * n∈[0,7]时，n(n+1)/2
     * n∈[8,14]时，28 + ((w+1)+(w+2)+...(w+d))
     * n∈[15,21]时，28 + (28 + 7) + ((w+1)+(w+2)+...(w+d))
     * n∈[22,28]时，28 + (28 + 7) + (28+7*2) + ((w+1)+(w+2)+...(w+d))
     * n∈[29,35]时，28 + (28 + 7) + (28+7*2) + (28+7*3)  + ((w+1)+(w+2)+...(w+d))
     *  = 28*w + 7*(1+2+3+ ... (w-1)) + d*((w+1)+(w+d))/2
     *  = 28*w + 7*(w-1)*(1+(w-1))/2 + d*(2w+d+1)/2
     *  = 28w+7(w-1)w/2+d(2w+d+1)/2
     * @author created by Meiyu Chen at 2021-5-28 11:27, v1.0
     */
    public int totalMoney(int n) {
        int weeks = n / 7;
        int days = n % 7;
        return weeks* 28 + 7*(weeks-1)*weeks/2 + days*(2*weeks+days+1)/2;
    }
    /** Description: 错误答案
     * 错在：Math.max(0,weeks-1) * 7
     * @author created by Meiyu Chen at 2021-5-28 11:11, v1.0
     */
    public int totalMoneyV1(int n) {
        int weeks = n / 7;
        int days = n % 7;
        return weeks* 28 + Math.max(0,weeks-1) * 7 + days*(1+days)/2 + days*weeks;
    }
}

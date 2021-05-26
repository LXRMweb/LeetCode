package math;

/** Description: 进制转换，将十进制的数转换成其他进制的形式
 * 数值进制转换：
 * 概述：
 *      1. 十进制转化为其他进制
 *      2. 其他进制转化为十进制
 *      3. 其他进制转化为其他进制（其他进制不等于十进制）
 * 1）十进制转为其他进制
 * 思路：待转换的数字除目的进制取余
 * 例如：十进制数字19转化为三进制
 * 解答：19%3 = 1,19/3=6 -> 6%3=0,6/3=2 -> 2%3=2,2/3=0
 * 所以19（十进制） = 201（三进制）
 * 例如：十进制数字103转化为十六进制
 * 解答：103%16=7,103/16=6 -> 6%16=6,6/16=0
 * 所以103（十进制）=67（十六进制）
 * 例如：十进制数字11转化为二进制
 * 解答：11%2=1,11/2=5 -> 5%2=1,5/2=2 -> 2%2=0,2/2=1 -> 1%2=1,1/2=0
 * 所以 11（十进制） = 1011（二进制）
 * 2）其他进制转化为十进制
 * 思路：最低位+进制*次低位+进制的平方*倒数第三位+...
 * 例如：二进制数值1101转化为十进制数值
 * 解答：1+0*2+1*2²+1*2³ = 13
 * 所以 1101（二进制） = 13（十进制）
 * 例如：十六进制数值0x21a转化为十进制数值
 * 解答：10+1*16+2*16² = 538
 * 所以 0x21a（十六进制） = 538（十进制）
 * 3）其他进制转化为其他进制（其中“其他进制”！=“十进制”）
 * 思路：源进制先转化为十进制 —> 再由十进制转化为目标进制
 *      使用十进制作为中间桥梁
 * 例如：请将三进制数字122转化为8进制
 * 解答：step1，按照2）中描述的步骤将三进制数字转化为十进制，122（三进制） = 17（十进制）
 *      step2，将step1中得到的十进制数值转化为8进制，17（十进制） = 21（八进制）
 *      所以122（三进制）=21（八进制）
 *
 * LeetCode：
 *      https://leetcode-cn.com/problems/sum-of-digits-in-base-k/
 * @author created by Meiyu Chen at 2021-5-26 10:48, v1.0
 */
public class JinzhiTransfer {
    public static void main(String[] args) {
        JinzhiTransfer test = new JinzhiTransfer();
        int n1=34,k1=6;
        int n2=10,k2=10;
        System.out.println(test.sumBase(n1,k1) == 9);
        System.out.println(test.sumBase(n2,k2) == 1);
    }
    /** Description: 将一个十进制的数n转化成k进制表示形式，求k进制表示形式下各个位数字的和
     * @author created by Meiyu Chen at 2021-5-26 10:52, v1.0
     */
    public int sumBase(int n, int k) {
        int rst = 0;
        while (n!=0){
            rst+=n%k;
            n/=k;
        }
        return rst;
    }
}

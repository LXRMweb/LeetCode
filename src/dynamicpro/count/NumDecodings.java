package dynamicpro.count;

/** Description: 解码方法
 *
 * 描述
 * 有一个消息包含A-Z通过以下规则编码
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 现在给你一个加密过后的消息，问有几种解码的方式
 *
 * 我们不能解码空串，因此若消息为空，你应该返回0。
 * 消息的长度 n \leq 100n≤100
 *
 * 样例
 * 样例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以被解码为 AB (1 2) 或 L (12).
 * 样例 2:
 *
 * 输入: "10"
 * 输出: 1
 * @author created by Meiyu Chen at 2021-6-3 15:35, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class NumDecodings {
    public static void main(String[] args) {
        NumDecodings test = new NumDecodings();
        System.out.println(test.numDecodings("10") == 1);
        System.out.println(test.numDecodings("12") == 2);
        System.out.println(test.numDecodings("0") == 0);
        System.out.println(test.numDecodings("26") == 2);
        System.out.println(test.numDecodings("19261001") == 0);
        System.out.println(test.numDecodings("11111878787676172120121101212918291829819") == 6240);
    }
    /** 动态规划算法：
     * step1，确定状态
     *      f[i] 长度为i的字符串共有多少种解码方法
     * step2，转移方程
     *      由于每个字母编码之后的长度最长为2，所以解码时最后一位最多有两种解码方法
     *      f[i] = f[i-1] | 最后一位可以解码为一个字母 + f[i-2] | 最后两位可以解码为一个字母
     * step3， 边界条件和初始值
     *      f[1] = 1
     *      边界条件: 最后一位能否解码为合法字母
     *              最后两位能否解码为合法字母
     * step4，计算顺序
     *      原则：后面的计算可以直接使用前面计算出来的值
     *      顺序：从左至右，从而使得计算f[i]时可以直接使用刚刚计算出来的f[i-1]和f[i-2]
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }

        int[] f = new int[length];
        // debug1:是charAt(),不是indexOf()
        f[0] = s.charAt(0) - '0' == 0 ? 0:1;
        // 中间的数字至少有一种解法（一位可以解码成正常字母 or 两位可以解码成正常字母），若一位&&两位都无法解码成正常数组，则该密文是非法密文，如："19261001"
        // debug2: 要警惕中间连着出现多个0的情况，如果末尾用一个字母和两个字母都不能正确解码，那么这个密文就是错误密文，无法解密，如"19261001"
        boolean flag = false;
        for (int i = 1; i < length; i++) {
            flag = false;
            f[i] = 0;
            int last1 = s.charAt(i) - '0';
            // debug3：一位字母取值应该在[1,9]范围内，否则非法
            if (last1<10 && last1>0){
                f[i] += f[i-1];
                flag = true;
            }
            int last2= new Integer(""+s.charAt(i-1)+s.charAt(i)).intValue();
            // debug4: 两位密文取值应该在[10,26]之间，如“10123”中的‘01’就是非法的
            if (last2>9&&last2<27) {
                f[i]+=i>1?f[i-2]:1;
                flag = true;
            }
            if(!flag){
                return 0;
            }
        }
        // write your code here
        return f[length-1];
    }
}

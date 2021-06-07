package dynamicpro.minmax;

import java.util.Arrays;

/** Description: 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-6-1 9:22, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class CountBits {
    public static void main(String[] args) {
        CountBits test = new CountBits();
        System.out.println(Arrays.toString(test.countBits(2)).replaceAll(" ","").equals("[0,1,1]"));
//        System.out.println(Arrays.toString(test.countBits(2)));
        System.out.println(Arrays.toString(test.countBits(5)).replaceAll(" ","").equals("[0,1,1,2,1,2]"));
//        System.out.println(Arrays.toString(test.countBits(5)));
//        test.countBits(1000);
        // 测试“>>”和“>>>”的区别
//        testRightShift();
    }

    /** Description: 测试“>>”和“>>>”的区别
     * @author created by Meiyu Chen at 2021-6-7 9:39, v1.0
     */
    private static void testRightShift() {
        for (int i = -10; i < 100; i++) {
            System.out.println("i="+i+","+Integer.toBinaryString(i)+",>>1="+Integer.toBinaryString(i>>1)+",i>>>1="+Integer.toBinaryString(i>>>1));
        }
    }

    /** Description: 动态规划算法
     * step1，确定状态
     *      f[i] 数字i的二进制形式数值中的1的个数
     * step2，转移方程
     *      f[i] = f[i>>1] + i%2
     * step3, 初始值和边界值
     *      f[0] = 0
     * step4, 计算顺序
     *      原则：后面计算过程使用前面已经计算出来的结果，降低时间复杂度
     *      计算顺序：从小到大，这样算大数的1的位数的时候，就可以直接使用前面已经计算出的f[i>>1]
     * @author created by Meiyu Chen at 2021-6-7 9:31, v1.0
     */
    public int[] countBits(int n) {
        int[] rst = new int[n+1];
        for (int i = 0; i < n + 1; i++) {
//            System.out.println("----------- n="+i);
            rst[i] = rst[i>>1] + i%2;
        }
        return rst;
    }

    /** Description: 暴力算法，对0-n范围中的所有数字，分别使用Brian Kernighan 算法求解
     * @author created by Meiyu Chen at 2021-6-7 9:30, v1.0
     */
    public int[] countBitsV3(int n) {
        int[] rst = new int[n+1];
        for (int i = 0; i < n + 1; i++) {
//            System.out.println("----------- n="+i);
            rst[i] = countOnes(i);
        }
        return rst;
    }

    /** Description: Brian Kernighan 算法
     * step1. 使用n&(n-1),将最后一位置为0
     *      + 令rst = n&(n-1), 则rst和n的二进制形式的数值中，
     *        n的最后一个1对应于rst的相应位置的0，除了这一点不同之外，rst和n的其余所有bit数值都相同
     * step2. 循环step1，直至0，计算循环次数
     * step3，返回循环次数，循环次数即二进制中1的个数
     *
     * 复杂度分析
     * 时间复杂度：O(n \log n)O(nlogn)。需要对从 00 到 nn 的每个整数使用计算「一比特数」，对于每个整数计算「一比特数」的时间都不会超过 O(\log n)O(logn)。
     * 空间复杂度：O(1)O(1)。除了返回的数组以外，空间复杂度为常数。
     * @author created by Meiyu Chen at 2021-6-1 10:00, v1.0
     */
    private int countOnes(int i) {
//        System.out.println(Integer.toBinaryString(i));
        int count = 0;
        while (i>0){
            count++;
            // 将i的最后一个1置为0
            i &= (i-1);
//            System.out.println(Integer.toBinaryString(i));
        }
        return count;
    }

    /** Description: 使用java内置函数
     * @author created by Meiyu Chen at 2021-6-1 9:53, v1.0
     */
    public int[] countBitsV2(int n) {
        int[] rst = new int[n+1];
        for (int i = 0; i < n + 1; i++) {
            rst[i] = Integer.bitCount(i);
        }
        return rst;
    }
    /** Description: 暴力求解，算法时间复杂度 = O(n*sizeof(integer))
     * @author created by Meiyu Chen at 2021-6-1 9:49, v1.0
     */
    public int[] countBitsV1(int n) {
        int[] rst = new int[n+1];
        int count = 0;
        for (int i = 0; i < n + 1; i++) {
            count = 0;
            for (char c : Integer.toBinaryString(i).toCharArray()) {
                if (c == '1') {
                    count++;
                }
            }
            rst[i] = count;
        }
        return rst;
    }
}

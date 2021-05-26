package math;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;

/** Description: 增减字符串匹配
 * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
 * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
 * 如果 S[i] == "I"，那么 A[i] < A[i+1]
 * 如果 S[i] == "D"，那么 A[i] > A[i+1]
 *
 * 示例 1：
 * 输入："IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 *
 * 输入："III"
 * 输出：[0,1,2,3]
 * 示例 3：
 *
 * 输入："DDI"
 * 输出：[3,2,0,1]
 *
 * 提示：
 * 1 <= S.length <= 10000
 * S 只包含字符 "I" 或 "D"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/di-string-match
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-5-26 16:58, v1.0
 */
public class DiStringMatch {
    public static void main(String[] args) {
        DiStringMatch test = new DiStringMatch();
        String t1 = "IDID";
        String expectestOutputs1 = "[0,4,1,3,2]";
        String rst1 = Arrays.toString(test.diStringMatch(t1));
        System.out.println(rst1.replaceAll(" ","").equals(expectestOutputs1));
        String t2 = "III";
        String expectestOutputs2 = "[0,1,2,3]";
        String rst2 = Arrays.toString(test.diStringMatch(t2));
        System.out.println(rst2.replaceAll(" ","").equals(expectestOutputs2));
        String t3 = "DDI";
        String expectestOutputs3 = "[3,2,0,1]";
        String rst3 = Arrays.toString(test.diStringMatch(t3));
        System.out.println(rst3.replaceAll(" ","").equals(expectestOutputs3));
    }
    /** Description:
     * 思路：
     *      1) 遇到“I”，就放入当前剩余的最小值
     *      2）遇到“D”，就放入当前剩余的最大值
     * @author created by Meiyu Chen at 2021-5-26 17:27, v1.0
     */
    public int[] diStringMatch(String s) {
        int N = s.length();
        int[] rst = new int[N+1];
        int max = N;
        int min = 0;
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == 'I') {
                rst[i] = min++;
            } else {
                rst[i] = max--;
            }
        }
        rst[N] = max;
        return rst;
    }
    public int[] diStringMatchV1(String s) {
        int N = s.length();
        int[] rst = new int[N+1];
        int max = N;
        int min = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I') {
                rst[i] = min++;
            } else {
                rst[i] = max--;
            }
        }
        rst[N] = max;
        return rst;
    }
}

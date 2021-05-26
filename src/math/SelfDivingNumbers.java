package math;

import java.util.ArrayList;
import java.util.List;

/** Description: 自除数
 * 自除数 是指可以被它包含的每一位数除尽的数。
 * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 还有，自除数不允许包含 0 。
 * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
 * 示例 1：
 * 输入：
 * 上边界left = 1, 下边界right = 22
 * 输出： [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/self-dividing-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-26 14:45, v1.0
 */
public class SelfDivingNumbers {
    public static void main(String[] args) {
        int left = 1,right = 22;
        SelfDivingNumbers test = new SelfDivingNumbers();
        String expectedRst = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]";
        System.out.println(test.selfDividingNumbers(left,right).toString().equals(expectedRst));
    }
    /** Description: 获取[left,right]范围内所有自除数
     * @author created by Meiyu Chen at 2021-5-26 14:57, v1.0
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        ArrayList<Integer> rst = new ArrayList<>();
        for (int i = left; i < right+1; i++) {
            if(isSelfDividingNum(i)){
                rst.add(i);
            }
        }
        return rst;
    }

    /** Description: 判断某个数是否是自除数
     * @author created by Meiyu Chen at 2021-5-26 14:57, v1.0
     */
    private boolean isSelfDividingNum(int i) {
        char[] chars = String.valueOf(i).toCharArray();
        for (char aChar : chars) {
            if (aChar == '0' || i%(aChar-'0')!=0) {
                return false;
            }
        }
        return true;
    }
    private boolean isSelfDividingNumV1(int i) {
        int tmp = i;
        int bit = 0;
        while(tmp!=0){
            bit = tmp%10;
            if (bit==0||i%bit!=0) {
                return false;
            }
            tmp/=10;
        }
        return true;
    }


}

package math;

import java.util.Arrays;

/**
 * Description: 分糖果II
 * 排排坐，分糖果。
 * <p>
 * 我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * <p>
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * <p>
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 * <p>
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * <p>
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：candies = 7, num_people = 4
 * 输出：[1,2,3,1]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
 * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
 * 示例 2：
 * <p>
 * 输入：candies = 10, num_people = 3
 * 输出：[5,2,3]
 * 解释：
 * 第一次，ans[0] += 1，数组变为 [1,0,0]。
 * 第二次，ans[1] += 2，数组变为 [1,2,0]。
 * 第三次，ans[2] += 3，数组变为 [1,2,3]。
 * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= candies <= 10^9
 * 1 <= num_people <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distribute-candies-to-people
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author created by Meiyu Chen at 2021-5-28 15:00, v1.0
 * <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 * <br>    [TODO-修改内容概述]
 */
public class DistributeCandies {
    public static void main(String[] args) {
        DistributeCandies test = new DistributeCandies();
        int candies = 7, num_people = 4;
        System.out.println(Arrays.toString(test.distributeCandies(candies, num_people)).replaceAll(" ","").equals("[1,2,3,1]"));
        int candies2 = 10, num_people2 = 3;
        System.out.println(Arrays.toString(test.distributeCandies(candies2, num_people2)).replaceAll(" ","").equals("[5,2,3]"));

    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int i = 0;
        while (candies != 0) {
            ans[i % num_people] += Math.min(candies, i + 1);
            candies -= Math.min(candies, i + 1);
            i += 1;
        }
        return ans;
    }
    public int[] distributeCandiesV1(int candies, int num_people) {
        int[] rst = new int[num_people];
        for (int i : rst) {
            rst[i] = 0;
        }
        // 本轮应该分配给当前小朋友的糖果数目
        int currNum = 1;
        // 本轮应该分配给哪个小朋友
        int currIdx = 0;
        // 终止条件：糖果已分完
        while (candies > 0) {
            // 本次分发完之后相应小朋友手中的糖果总数
            rst[currIdx % num_people] += candies > currNum ? currNum : candies;
            // 本次分发完之后剩余糖果总数
            candies -= currNum;
            // 按照规律来算，应该分发给下一位小朋友的糖果数目
            currNum++;
            // 下一位小朋友
            currIdx++;
        }
        return rst;
    }
}

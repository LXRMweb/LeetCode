package math;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** Description: 玩筹码
 * 数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。
 *
 * 你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）：
 *
 * 将第 i 个筹码向左或者右移动 2 个单位，代价为 0。
 * 将第 i 个筹码向左或者右移动 1 个单位，代价为 1。
 * 最开始的时候，同一位置上也可能放着两个或者更多的筹码。
 *
 * 返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：chips = [1,2,3]
 * 输出：1
 * 解释：第二个筹码移动到位置三的代价是 1，第一个筹码移动到位置三的代价是 0，总代价为 1。
 * 示例 2：
 *
 * 输入：chips = [2,2,2,3,3]
 * 输出：2
 * 解释：第四和第五个筹码移动到位置二的代价都是 1，所以最小总代价为 2。
 *  
 *
 * 提示：
 *
 * 1 <= chips.length <= 100
 * 1 <= chips[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-to-move-chips-to-the-same-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-27 15:51, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class MinCostToMoveChips {
    public static void main(String[] args) {
        MinCostToMoveChips test = new MinCostToMoveChips();
        int[] chips = {1,2,3};
        System.out.println(test.minCostToMoveChips(chips) == 1);
        int[] chips2 = {2,2,2,3,3};
        System.out.println(test.minCostToMoveChips(chips2)==2);
        int[] chips3 = {3,3,1,2,2};
        System.out.println(test.minCostToMoveChips(chips3)==2);
        int[] chips4 = {6,4,7,8,2,10,2,7,9,7};
        System.out.println(test.minCostToMoveChips(chips4) == 4);
    }
    /** Description: 参考解题思路
     * 通过题意可以发现，如果将偶数位置作为最终位置，则奇数位置筹码移动代价恒为1，偶数位置筹码的移动代价恒为0；
     * 如果将奇数位置作为最终位置，则偶数位置筹码移动代价恒为1，奇数位置筹码的移动代价恒为0；
     * 因此这一题先统计数组中奇数和偶数的数量，判断两者大小，以大的作为筹码移动的最终位置（比如奇数多），那所有奇数位置筹码的代价和为零，只要将数组中的偶数位置筹码的个数返回即可
     *
     * 作者：qilin2008201
     * 链接：https://leetcode-cn.com/problems/minimum-cost-to-move-chips-to-the-same-position/solution/cchun-meng-dai-ma-by-qilin2008201-vzk0/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @author created by Meiyu Chen at 2021-5-27 16:55, v1.0
     */
    public int minCostToMoveChips(int[] position) {
        int odd = 0,even=0;
        for (int i : position) {
            if (i%2!=0) {
                odd++;
            }else {
                even++;
            }
        }
        return Math.min(odd,even);
    }
    public int minCostToMoveChipsV3(int[] position) {
        int rst = 0;
        int min = position.length;
        for (int destination : position) {
            rst = 0;
            for (int i : position) {
                rst += Math.abs(i-destination)%2;
            }
            min = Math.min(min,rst);
        }
        return min;
    }

    /** Description: 暴力破解
     * @author created by Meiyu Chen at 2021-5-27 16:48, v1.0
     */
    public int minCostToMoveChipsV2(int[] position) {
        int rst = 0;
        // 统计每个位置的筹码数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : position) {
            map.put(i,map.getOrDefault(i,0)+1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();

        int min = position.length;
        for (Map.Entry<Integer, Integer> destination : entries) {
            rst = 0;
            // 将所有筹码移动到筹码数目最多的位置
            // 某位置的所有筹码移动到筹码数最多位置需要的代价
            for (Map.Entry<Integer, Integer> entry : entries) {
                rst += entry.getValue()*(Math.abs(entry.getKey() - destination.getKey()) % 2);
            }
            min = Math.min(min,rst);
        }

        return min;
    }
    /** Description: 得出的结果是错误的
     * @author created by Meiyu Chen at 2021-5-27 16:48, v1.0
     */
    public int minCostToMoveChipsV1(int[] position) {
        int rst = 0;
        // 统计每个位置的筹码数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : position) {
            map.put(i,map.getOrDefault(i,0)+1);
        }

        // 找出筹码最多的位置
        HashMap<Integer, Integer> maxMap = new HashMap<>();
        // 筹码数
        int maxNum = 0;
        int maxIdx = 1;
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue()>maxNum) {
                maxIdx = entry.getKey();
                maxNum = entry.getValue();
            } else if (entry.getValue()==maxNum){
                if (maxMap.size()==0) {
                    maxMap.put(entry.getKey(),entry.getValue());
                } else {
                    if (!maxMap.containsValue(maxNum)) {
                        maxMap.clear();
                    }else{
                        maxMap.put(entry.getKey(),entry.getValue());
                    }
                }

            }
        }
        maxMap.put(maxIdx,maxNum);

        int min = position.length;
        for (Map.Entry<Integer, Integer> entryMax : maxMap.entrySet()) {
            rst = 0;
            // 将所有筹码移动到筹码数目最多的位置
            // 某位置的所有筹码移动到筹码数最多位置需要的代价
            for (Map.Entry<Integer, Integer> entry : entries) {
                rst += entry.getValue()*(Math.abs(entry.getKey() - entryMax.getKey()) % 2);
            }
            min = Math.min(min,rst);
        }
        return min;
    }
}

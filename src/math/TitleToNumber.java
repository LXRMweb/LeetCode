package math;
/** Description: Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author created by Meiyu Chen at 2021-5-27 17:13, v1.0
 *      <br>modified by [TODO-修改者] at [TODO-修改时间], [TODO-版本], 修改内容概述如下:
 *      <br>    [TODO-修改内容概述]
 */
public class TitleToNumber {
    public static void main(String[] args) {
        TitleToNumber test = new TitleToNumber();
        System.out.println(test.titleToNumber("A")==1);
        System.out.println(test.titleToNumber("AB")==28);
        System.out.println(test.titleToNumber("ZY")==701);
    }
    public int titleToNumber(String columnTitle) {
        int rst = 0;
        int length = columnTitle.length();
        for (int i = length-1; i >=0; i--) {
            rst += (columnTitle.charAt(i) - 'A'+1) * Math.pow(26,length-i-1);
        }
        return rst;
    }
    public int titleToNumberV1(String columnTitle) {
        int rst = 0;
        int length = columnTitle.length();
        char[] chars = columnTitle.toCharArray();
        for (int i = length-1; i >=0; i--) {
            rst += (chars[i] - 'A'+1) * Math.pow(26,length-i-1);
        }
        return rst;
    }
}

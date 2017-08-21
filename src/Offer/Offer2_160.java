package Offer;

/**
 * Created by yangkun on 2017/8/18.
 * 题设：找到旋转排序数组中的最小值。（在数组中可能存在重复数字）
 * 旋转排序数组：递增有序数组循环右移生成旋转排序数组，在旋转排序数组中，存在一个或者两个局部有序的子数组。
 */
public class Offer2_160 {
    public static void main(String args[]) {
        int[] rsa1 = {4, 5, 6, 7, 0, 1, 2};
        int[] rsa2 = {4, 4, 4, 5, 6, 7, 0, 1, 2};
        int[] rsa3 = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] rsa4 = {4, 4, 4, 4, 4, 4, 4, 4};
        Solution160 solution = new Solution160();
        System.out.println(solution.findMin(rsa2));


    }
}

/**
 * getSplitIndex：找到旋转排序数组中，两段局部有序子数组的分界点，即是最小元素的下标位置。时间复杂度：O（n），可以使用二分查找优化
 * findMin：调用getSplitIndex方法找到最小元素的下标位置，然后输出该位置的元素值
 */
class Solution160 {
    public int getSplitIndex(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                return i + 1;
            }
        }
        return 0;
    }

    public int findMin(int[] arr) {
        int minIndex = getSplitIndex(arr);
        return arr[minIndex];
    }
}



/**
 * Created by yangkun on 2017/8/18.
 */
public class SortingAndSearching {
    public static void main(String args[]) {
        int[] sortedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] unsortedArray = {3, 2, 5, 6, 4, 1, 8, 9, 0, 7};

        //验证二分查找
        int searchKey = 5;
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.find(sortedArray, searchKey));

    }
}

//二分查找算法
//待查找数组有序
//找到：返回index；未找到：返回-1
class BinarySearch {
    public int find(int[] arr, int searchKey) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] == searchKey) {
                return mid;
            } else if (arr[mid] < searchKey) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return -1;
    }
}

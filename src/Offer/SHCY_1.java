package Offer;

import java.util.Scanner;

/**
 * Created by yangkun on 2017/9/17.
 */
public class SHCY_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case             
            int n=in.nextInt();
            int[] numbers=new int[n];
            for (int i=0;i<n;i++){
                numbers[i]=in.nextInt();
            }
            //bubbleSort(numbers);
            /*for (int i=0;i<n;i++){
                if (i<n-1){
                    System.out.print(numbers[i]+" ");
                }
                else {
                    System.out.print(numbers[i]);
                }
            }*/

            System.out.println(maxSubSum(numbers));

        }
    }

    public static int maxSubSum(int[] numbers) {
        /*int i,j;
        int max = 0;
        for(i=0;i<numbers.length;i++)
        {
            int currentSum = 0;
            //向后累加的循环
            for(j=i;j<numbers.length;j++)
            {
                currentSum += numbers[j];
                if(currentSum > max)
                    max = currentSum;
            }
        }
        return max;*/

        /*int i;
        int max = 0;
        int currentSum = 0;
        for(i=0;i<numbers.length;i++) {
            currentSum += numbers[i];
            if(currentSum > max)
                max = currentSum;
            //如果累加和出现小于0的情况，
            //则和最大的子序列肯定不可能包含前面的元素，
            //这时将累加和置0，从下个元素重新开始累加
            if(currentSum < 0)
                currentSum = 0;
        }
        return max;*/

        int result = numbers[0];
        int sum = numbers[0];

        for(int i=1;i<numbers.length;i++){
            if (sum>0){
                sum+=numbers[i];
            }
            else {
                sum=numbers[i];
            }
            if (sum>result){
                result=sum;
            }
        }

        return result;
    }

    /*public static void bubbleSort(int[] arr){
        //对于 N 个元素的序列,需要 N-1 趟排序
        for (int i=1;i<arr.length;i++){
            int flag=0;
            //在每一趟排序后,下标为 arr.length-i的元素已经位于全局有序位置,因此下一趟比较的最后待排位置为 arr.length-i-1
            //每一趟比较中,当前下标的左右两个元素如果顺序不当,则互相交换,较大的向后调整,如果满足大小要求,则不交换。这样依次对剩余元素进行比较,直到 arr.length-i
            for (int j=0;j<arr.length-i;j++){
                if (arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    flag=1;
                }
            }
            if (flag==0){
                break;
            }
        }
    }

    public static void  swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }*/
}
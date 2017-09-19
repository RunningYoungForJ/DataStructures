package Offer;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by yangkun on 2017/9/17.
 */
public class SouHu {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {//注意while处理多个case             
            int n = in.nextInt();
            int[] arr=new int[n];
            int[] brr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=in.nextInt();
                brr[n-i-1]=arr[i];
            }
            System.out.println(solution(arr,brr));
        }
    }

    public static int solution(int[] arr,int[] brr){
        int i,j;
        int sum=0;
        for (i=0;i<arr.length;){
            if (arr[i]==brr[i]){
                i++;
            }
            else {
                break;
            }
        }
        if (i==arr.length){
            for (int k=0;k<arr.length;k++){
                sum+=arr[k];
            }
            return sum;
        }
        return 0;
    }

    /*public static String solution(String path) {
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String str : strs) {
            if (!str.equals(".") && !str.equals("..") && !str.equals("")) {
                stack.push(str);
            }
            if (str.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        String simplePath="";
        while (!stack.isEmpty()) {
            simplePath="/"+stack.pop()+simplePath;
        }
        return simplePath;
    }*/
}

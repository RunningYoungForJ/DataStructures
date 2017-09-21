package Offer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by yangkun on 2017/9/20.
 */
public class AnQuan360_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n=in.nextInt();
            int[] a=new int[n];
            for (int i=0;i<n;i++){
                a[i]=in.nextInt();
            }
            int ask=in.nextInt();
            ArrayList<Integer> list=new ArrayList<>();
            for (int i=0;i<ask;i++){
                int start=in.nextInt();
                int end=in.nextInt();
                list.add(solution(a,start,end));
            }
            for (Integer res:list){
                System.out.println(res.intValue());
            }
        }
    }

    public static int solution(int[] a,int start,int end){
        if (end-start<2&&end<=a.length){
            return 0;
        }
        if (end>a.length){
            return 0;
        }
        int count=0;
        for (int i=start-1;i<end-2;i++){
            if (a[i]<=a[i+1]&&a[i+1]<=a[i+2]){
                count++;
            }
        }
        return count;
    }
}

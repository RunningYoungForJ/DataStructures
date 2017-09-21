package Offer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by yangkun on 2017/9/20.
 */
public class AnQuan360_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int ticket = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            System.out.println(solution(a, ticket));


        }
    }

    public static int solution(int[] a, int ticket) {
        Arrays.sort(a);
        int sum = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            if (sum >= ticket) {
                break;
            }
            sum += a[i];
        }
        return sum;
    }

}

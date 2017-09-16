package ThinkingInJava;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yangkun on 2017/9/17.
 */
public class Chpater16 {

    public static void main(String args[]){
        BerylliumSphere[] a;
        BerylliumSphere[] b=new BerylliumSphere[5];
        BerylliumSphere[] c=new BerylliumSphere[]{};
        BerylliumSphere d[][]=new BerylliumSphere[5][5];
        //ArrayList<Integer>[] list=new ArrayList<Integer>[5];
        System.out.println(Arrays.deepToString(d)+d.length);
    }
}

class BerylliumSphere{
    private static long counter;
    private final long id=counter++;

    public String toString(){
        return "Sphere "+id;
    }
}

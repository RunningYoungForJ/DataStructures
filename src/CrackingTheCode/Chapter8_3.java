package CrackingTheCode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by yangkun on 2017/9/18.
 */
public class Chapter8_3 {
    public static void main(String args[]) throws Exception {
        Chapter8_3 solution = new Chapter8_3();

        Stack831 stack831 = new Stack831();
        stack831.push(1, 1);
        stack831.push(2, 2);
        stack831.push(3, 3);
        stack831.push(1, 4);
        System.out.println(stack831.pop(1));
        System.out.println(stack831.pop(2));
        System.out.println(stack831.pop(3));

        Stack832 stack832=new Stack832();
        stack832.push(5);
        stack832.push(6);
        stack832.push(3);
        stack832.push(7);
        System.out.println("栈中最小值是："+stack832.min());

        Stack833 stack833=new Stack833();
        for (int i=0;i<12;i++){
            stack833.push(i);
        }
        System.out.println(stack833.popAt(2));
        for (int i=0;i<13;i++){
            System.out.print(stack833.pop()+" ");
        }

    }


}

class Stack831 {
    private static final int sizePreStack = 100;
    private int[] array = new int[sizePreStack * 3];
    private int[] topIndex = {-1, -1, -1};

    public boolean isFull(int stackNumber) {
        if (topIndex[stackNumber - 1] + 1 == sizePreStack) {
            return true;
        }
        return false;
    }

    public boolean isEmpty(int stackNumbe) {
        if (topIndex[stackNumbe - 1] == -1) {
            return true;
        }
        return false;
    }

    public void push(int stackNumber, int value) throws Exception {
        if (isFull(stackNumber)) {
            throw new Exception("Out of Stack Size");
        }
        array[stackNumber * sizePreStack - sizePreStack + topIndex[stackNumber - 1] + 1] = value;
        topIndex[stackNumber - 1] += 1;
    }

    public int pop(int stackNumber) throws Exception {
        if (isEmpty(stackNumber)) {
            throw new Exception("The stack is Empty");
        }
        int top = array[stackNumber * sizePreStack - sizePreStack + topIndex[stackNumber - 1]];
        topIndex[stackNumber - 1] -= 1;
        return top;
    }
}

class Stack832 extends Stack<Integer>{
    private Stack<Integer> stack;
    public Stack832(){
        super();
        this.stack=new Stack<>();
    }

    public void push(int value){
        if (value<min()){
            stack.push(value);
        }
        super.push(value);
    }

    public Integer pop(){
        if (stack.peek()==min()){
            stack.pop();
        }
        return super.pop();
    }


    public Integer min(){
        if (stack.isEmpty()){
            return Integer.MAX_VALUE;
        }
        return stack.peek();
    }
}

class Stack833{
   private HashSet<Stack<Integer>> set;

   public Stack833(){
       this.set=new HashSet<>();
   }

   public void push(int value){
       if (set.isEmpty()){
           Stack<Integer> stack=new Stack<>();
           set.add(stack);
       }

       for (Stack stack:set){
           if (stack.size()<stack.capacity()){
               stack.push(value);
               return;
           }
       }

       Stack<Integer> stack=new Stack<>();
       stack.push(value);
       set.add(stack);
   }

   public Integer pop(){
       if (set.isEmpty()){
           return null;
       }

       for (Stack stack:set){
           if (!stack.isEmpty()){
               return (Integer) stack.pop();
           }
       }
       return null;
   }

   public Integer popAt(int index){
       if (index<0||index>set.size()){
           return null;
       }

       int location=0;
       for (Stack stack:set){
           if (location==index-1){
               if (!stack.isEmpty()){
                   return (Integer) stack.pop();
               }
               return null;
           }
           location++;
       }
       return null;
   }
}

package CrackingTheCode;

import java.util.*;

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

        Stack832 stack832 = new Stack832();
        stack832.push(5);
        stack832.push(6);
        stack832.push(3);
        stack832.push(7);
        System.out.println("栈中最小值是：" + stack832.min());

        Stack833 stack833 = new Stack833();
        for (int i = 0; i < 12; i++) {
            stack833.push(i);
        }
        System.out.println(stack833.popAt(2));
        for (int i = 0; i < 13; i++) {
            System.out.print(stack833.pop() + " ");
        }

        int diskNumbers = 3;
        Stack834[] stack834 = new Stack834[diskNumbers];
        for (int i = 0; i < diskNumbers; i++) {
            stack834[i] = new Stack834(i);
        }
        for (int i = 5; i > 0; i--) {
            stack834[0].add(i);
        }
        stack834[0].moveDisksToBuffer(diskNumbers, stack834[2], stack834[1]);
        System.out.print("move to Tower3: ");
        stack834[2].print();

        Stack835 stack835 = new Stack835();
        stack835.enQueue(1);
        stack835.enQueue(2);
        ;
        stack835.enQueue(3);
        System.out.print(stack835.deQueue() + " ");
        System.out.print(stack835.deQueue() + " ");
        System.out.print(stack835.deQueue());

        Stack<Integer> stack836_1 = new Stack<>();
        stack836_1.push(3);
        stack836_1.push(2);
        stack836_1.push(5);
        stack836_1.push(4);
        stack836_1.push(1);
        stack836_1.push(6);
        Stack<Integer> stack836_2 = new Stack<>();
        solution.solution836(stack836_1, stack836_2);

        Queue837 queue837=new Queue837();
        queue837.enQueue("Dog");
        queue837.enQueue("Cat");
        queue837.enQueue("Cat");
        queue837.enQueue("Cat");
        System.out.println(queue837.deQueueAny());
        System.out.println(queue837.deQueueCat());
        System.out.println(queue837.deQueueDog());




    }

    public void solution836(Stack<Integer> unsorted, Stack<Integer> sorted) {
        while (!unsorted.isEmpty()) {
            int value = unsorted.pop();
            if (sorted.isEmpty() || sorted.peek() < value) {
                sorted.push(value);
            } else {
                int flag = 0;
                while (!sorted.isEmpty() && sorted.peek() > value) {
                    unsorted.push(sorted.pop());
                    flag++;
                }
                sorted.push(value);
                while (flag > 0) {
                    sorted.push(unsorted.pop());
                    flag--;
                }
            }
        }
        printStack(unsorted);
        printStack(sorted);

    }

    private void printStack(Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
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

class Stack832 extends Stack<Integer> {
    private Stack<Integer> stack;

    public Stack832() {
        super();
        this.stack = new Stack<>();
    }

    public void push(int value) {
        if (value < min()) {
            stack.push(value);
        }
        super.push(value);
    }

    public Integer pop() {
        if (stack.peek() == min()) {
            stack.pop();
        }
        return super.pop();
    }


    public Integer min() {
        if (stack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return stack.peek();
    }
}

class Stack833 {
    private HashSet<Stack<Integer>> set;

    public Stack833() {
        this.set = new HashSet<>();
    }

    public void push(int value) {
        if (set.isEmpty()) {
            Stack<Integer> stack = new Stack<>();
            set.add(stack);
        }

        for (Stack stack : set) {
            if (stack.size() < stack.capacity()) {
                stack.push(value);
                return;
            }
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(value);
        set.add(stack);
    }

    public Integer pop() {
        if (set.isEmpty()) {
            return null;
        }

        for (Stack stack : set) {
            if (!stack.isEmpty()) {
                return (Integer) stack.pop();
            }
        }
        return null;
    }

    public Integer popAt(int index) {
        if (index < 0 || index > set.size()) {
            return null;
        }

        int location = 0;
        for (Stack stack : set) {
            if (location == index - 1) {
                if (!stack.isEmpty()) {
                    return (Integer) stack.pop();
                }
                return null;
            }
            location++;
        }
        return null;
    }
}


class Stack834 {
    private Stack<Integer> tower;
    private int towerId;

    public Stack834(int towerId) {
        this.tower = new Stack<>();
        this.towerId = towerId;
    }

    public int getTowerId() {
        return this.towerId;
    }

    public void add(int value) {
        if (!tower.isEmpty() && tower.peek() > value) {
            System.out.println("Error placing disk " + value);
        } else {
            tower.push(value);
        }
    }

    public void moveBottomTo(Stack834 destination) {
        if (!this.tower.isEmpty()) {
            int bigBottom = this.tower.pop();
            destination.add(bigBottom);
            System.out.println("move disk " + bigBottom + " from " + this.getTowerId() + " to " + destination.getTowerId());
        }
    }

    public void moveDisksToBuffer(int diskNumbers, Stack834 destination, Stack834 buffer) {
        if (diskNumbers > 0) {
            moveDisksToBuffer(diskNumbers - 1, buffer, destination);
            moveBottomTo(destination);
            buffer.moveDisksToBuffer(diskNumbers - 1, destination, this);
        }
    }

    public void print() {
        while (!this.tower.isEmpty()) {
            System.out.println(this.tower.pop() + " ");
        }
    }
}

class Stack835 {
    private Stack<Integer> inQueue = new Stack<>();
    private Stack<Integer> outQueue = new Stack<>();

    public void enQueue(int value) {
        inQueue.push(value);
    }

    public int deQueue() {
        if (outQueue.isEmpty()) {
            transferInToOut();
        }
        return outQueue.pop();
    }

    public void transferInToOut() {
        while (!inQueue.isEmpty()) {
            outQueue.push(inQueue.pop());
        }
    }
}

class Queue837{
    private LinkedList<Animal> queueDog;
    private LinkedList<Animal> queueCat;
    private enum types{Dog,Cat};

    private static int old=0;

    public Queue837(){
        this.queueDog=new LinkedList<>();
        this.queueCat=new LinkedList<>();
    }

    public void enQueue(String type){
        Animal animal=new Animal(type,old);
        old++;

        if (type.equals(types.Dog.name())){
            queueDog.add(animal);
        }
        if (type.equals(types.Cat.name())){
            queueCat.add(animal);
        }
    }

    public String deQueueAny(){
        while (!queueDog.isEmpty()&&!queueCat.isEmpty()){
            if (queueDog.getLast().old<queueCat.getLast().old){
                return queueDog.removeLast().type;
            }
            else {
                return queueCat.removeLast().type;
            }
        }
        if (!queueDog.isEmpty()){
            return queueDog.removeLast().type;
        }
        else {
            return queueCat.removeLast().type;
        }

    }

    public String deQueueDog(){
        return queueDog.isEmpty()?"": queueDog.removeLast().type;
    }

    public String deQueueCat(){
        return queueCat.isEmpty()?"":queueCat.removeLast().type;
    }

    class Animal{
        String type;
        int old;

        public Animal(String type,int old){
            this.type=type;
            this.old=old;
        }
    }
}



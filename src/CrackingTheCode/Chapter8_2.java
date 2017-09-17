package CrackingTheCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by yangkun on 2017/9/15.
 */
public class Chapter8_2 {

    public static void main(String args[]) {

        Chapter8_2 solution = new Chapter8_2();

        LinkList list821 = new LinkList();
        list821.appendToTail(1);
        list821.appendToTail(2);
        list821.appendToTail(3);
        list821.appendToTail(3);
        list821.appendToTail(4);
        list821.appendToTail(4);
        list821.appendToTail(4);
        list821.appendToTail(6);

        list821.printList();
        solution.solution821_NoFreedom(list821);
        list821.printList();

        LinkList list822 = new LinkList();
        list822.appendToTail(1);
        list822.appendToTail(2);
        list822.appendToTail(3);
        list822.appendToTail(4);
        list822.appendToTail(5);
        list822.appendToTail(6);
        solution.solution822(list822, 6);
        solution.solution822_recursion(list822.head, 6);

        LinkList list823 = new LinkList();
        list823.appendToTail(1);
        list823.appendToTail(2);
        list823.appendToTail(3);
        list823.appendToTail(4);
        list823.appendToTail(5);
        list823.appendToTail(6);
        list823.printList();
        solution.solution823(list823, 3);
        list823.printList();

        LinkList list824 = new LinkList();
        list824.appendToTail(1);
        list824.appendToTail(6);
        list824.appendToTail(5);
        list824.appendToTail(3);
        list824.appendToTail(2);
        list824.appendToTail(4);
        list824.appendToTail(7);
        list824.printList();
        solution.solution824(list824, 3).printList();

        LinkList list825_1 = new LinkList();
        list825_1.appendToTail(7);
        list825_1.appendToTail(1);
        list825_1.appendToTail(6);
        LinkList list825_2 = new LinkList();
        list825_2.appendToTail(5);
        list825_2.appendToTail(9);
        list825_2.appendToTail(2);
        LinkNode node = solution.solution825(list825_1.head, list825_2.head, 0);
        LinkList list825_3 = new LinkList(node);
        list825_3.printList();

        LinkList list826=new LinkList();
        list826.appendToTail(1);
        list826.appendToTail(2);
        list826.appendToTail(3);
        list826.appendToTail(4);
        list826.appendToTail(5);
        list826.appendToTail(6);
        LinkNode tail = list826.getTailNode();
        tail.next=list826.head;
        solution.solution826(list826);

        LinkList list827=new LinkList();
        list827.appendToTail(0);
        list827.appendToTail(1);
        list827.appendToTail(2);
        list827.appendToTail(2);
        list827.appendToTail(1);
        list827.appendToTail(0);
        System.out.println(solution.solution827(list827)?"是回文链表":"不是回文链表");

    }

    /**
     * 使用一个辅助空间HashSet记录重复结点的信息
     * pre始终维持为q的前驱结点
     */
    public LinkList solution821(LinkList list) {
        HashSet<Integer> nodes = new HashSet<>();

        LinkNode pre = null;
        LinkNode q = list.head;
        while (q != null) {
            if (nodes.contains(q.data)) {
                pre.next = q.next;
            } else {
                nodes.add(q.data);
                pre = q;
            }
            q = q.next;

        }
        return list;
    }

    /**
     * 没有占用辅助空间
     * 但需要O（n2）的时间复杂度
     * 单链表问题；不借助辅助空间的话，通常需要多个指针迭代
     */
    public LinkList solution821_NoFreedom(LinkList list) {
        if (list.head == null) {
            return null;
        }
        LinkNode current = list.head;
        while (current != null) {
            LinkNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
        return list;
    }

    public void solution822(LinkList list, int k) {
        if (list == null) {
            return;
        }
        LinkNode front = list.head;
        LinkNode behind = list.head;
        for (int i = 1; i < k; i++) {
            front = front.next;
        }
        while (front.next != null) {
            front = front.next;
            behind = behind.next;
        }
        System.out.println("倒数第" + k + "个结点是： " + behind.data);
    }

    public int solution822_recursion(LinkNode node, int k) {
        if (node == null) {
            return 0;
        }
        int count = solution822_recursion(node.next, k) + 1;
        if (count == k) {
            System.out.println("倒数第" + k + "个结点是： " + node.data);
        }
        return count;
    }

    public LinkList solution823(LinkList list, int data) {
        LinkNode node = getNode(list, data);

        node.data = node.next.data;
        node.next = node.next.next;
        return list;
    }

    private LinkNode getNode(LinkList list, int data) {
        if (list.head == null) {
            return null;
        }
        LinkNode node = list.head;
        while (node != null) {
            if (node.data == data) {
                break;
            }
            node = node.next;
        }
        return node;
    }

    public LinkList solution824(LinkList list, int data) {
        LinkNode beforeStart = null;
        LinkNode beforeEnd = null;
        LinkNode afterStart = null;
        LinkNode afterEnd = null;

        LinkNode p = list.head;
        while (p != null) {
            LinkNode temp = p.next;
            p.next = null;
            if (p.data < data) {
                if (beforeStart == null && beforeEnd == null) {
                    beforeStart = p;
                    beforeEnd = p;
                } else {
                    beforeEnd.next = p;
                    beforeEnd = p;
                }
            } else if (p.data == data) {
                if (afterStart == null) {
                    afterStart = p;
                } else {
                    p.next = afterStart;
                    afterStart = p;
                }

            } else {


                if (afterStart == null && afterEnd == null) {
                    afterStart = p;
                    afterEnd = p;
                } else {
                    afterEnd.next = p;
                    afterEnd = p;
                }
            }
            p = temp;

        }
        if (beforeStart == null) {
            return new LinkList(afterStart);
        }
        if (afterStart == null) {
            return new LinkList(beforeStart);
        }
        beforeEnd.next = afterStart;
        return new LinkList(beforeStart);
    }

    public LinkNode solution825(LinkNode head1, LinkNode head2, int remainder) {
        /*if (head1.head == null && head2.head == null) {
            return null;
        }
        if (head1.head == null) {
            return head2;
        }
        if (head2.head == null) {
            return head2;
        }

        LinkNode h1 = head1.head;
        LinkNode h2 = head2.head;

        LinkNode start = new LinkNode();
        LinkNode p = start;

        int remainder = 0;

        while (h1 != null && h2 != null) {
            int sum = h1.data + h2.data;
            LinkNode node = new LinkNode((sum + remainder) % 10);
            p.next = node;
            p = node;
            remainder = (sum + remainder) / 10;
            h1 = h1.next;
            h2 = h2.next;
        }
        //ToDO：处理两个链表长度不一样的情况，未解决
        return new LinkList(start.next);*/
        if (head1 == null && head2 == null && remainder == 0) {
            return null;
        }
        LinkNode node = new LinkNode();
        int sum = 0;
        if (head1 != null) {
            sum += head1.data;
        }
        if (head2 != null) {
            sum += head2.data;
        }
        node.data = (sum + remainder) % 10;
        node.next = solution825(head1.next, head2.next, (sum + remainder) / 10);
        return node;
    }

    public void solution826(LinkList list){
        HashSet<Integer> elements=new HashSet<>();
        LinkNode node=list.head;
        while (node!=null){
            if (elements.contains(node.data)){
                break;
            }
            elements.add(node.data);
            node=node.next;
        }
        System.out.println("环结点是： "+node.data);
    }

    public boolean solution827(LinkList list){
        Stack<Integer> stack=new Stack<>();
        LinkNode slow=list.head;
        LinkNode fast=list.head;
        while (fast!=null&&fast.next!=null){
            stack.push(slow.data);
            slow=slow.next;
            fast=fast.next.next;
        }
        if (fast!=null){
            slow=slow.next;
        }
        while (slow!=null){
            if (slow.data!=stack.pop()){
                return false;
            }
            slow=slow.next;
        }
        return true;
    }
}

class LinkList {
    public LinkNode head;

    public LinkList() {
        this.head = null;
    }

    public LinkList(LinkNode head) {
        this.head = head;
    }

    /**
     * 尾插法
     */
    public void appendToTail(int d) {
        LinkNode end = new LinkNode(d);
        if (this.head == null) {
            this.head = end;
            return;
        }
        LinkNode node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = end;

    }

    /**
     * 没有头指针，first即是LinkList的第一个结点
     * 初始化first=null
     */
    public void insertToHead(int data) {
        LinkNode node = new LinkNode(data);
        if (this.head != null) {
            node.next = this.head;
        } else {
            this.head = node;
        }
    }

    public LinkNode getTailNode(){
        LinkNode node =this.head;
        while (node.next!=null){
            node=node.next;
        }
        return node;
    }

    public LinkNode deleteNode(LinkNode head, int d) {
        LinkNode node = head;
        if (node.data == d) {
            return node.next;
        }
        while (node.next != null) {
            if (node.next.data == d) {
                node.next = node.next.next;
                return head;
            }
            node = node.next;
        }
        return head;
    }

    public LinkNode deleteFirst() {
        if (!isEmpty()) {
            LinkNode temp = this.head;
            head = head.next;
            return temp;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.head == null ? true : false;
    }

    public void printList() {
        LinkNode node = this.head;
        while (node != null) {
            System.out.print(node.data);
            if (node.next != null) {
                System.out.print("->");
            }
            node = node.next;
        }
        System.out.println();
    }
}


class LinkNode {
    int data;
    LinkNode next = null;

    public LinkNode() {
    }

    public LinkNode(int data) {
        this.data = data;
    }
}

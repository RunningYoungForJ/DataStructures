package CrackingTheCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
        solution.solution823(list823,3);
        list823.printList();


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

    public LinkList solution823(LinkList list,int data){
        LinkNode node=getNode(list,data);

        node.data=node.next.data;
        node.next=node.next.next;
        return list;
    }

    private LinkNode getNode(LinkList list,int data){
        if (list.head==null){
            return null;
        }
        LinkNode node = list.head;
        while (node!=null){
            if (node.data==data){
                break;
            }
            node=node.next;
        }
        return node;
    }
}

class LinkList {
    public LinkNode head;

    public LinkList() {
        this.head = null;
    }

    public static void main(String args[]) {

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

    public LinkNode(int data) {
        this.data = data;
    }
}

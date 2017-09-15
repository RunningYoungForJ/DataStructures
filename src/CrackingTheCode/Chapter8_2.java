package CrackingTheCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yangkun on 2017/9/15.
 */
public class Chapter8_2 {

    public static void main(String args[]){

        Chapter8_2 solution=new Chapter8_2();

        LinkList list821=new LinkList();
        list821.appendToTail(1);
        list821.appendToTail(2);
        list821.appendToTail(3);
        list821.appendToTail(3);
        list821.appendToTail(4);
        list821.appendToTail(4);
        list821.appendToTail(4);
        list821.appendToTail(6);

        list821.printList();
        solution.solution821(list821);
        list821.printList();




    }

    /**
     * 使用一个辅助空间HashSet记录重复结点的信息
     * p始终维持为q的前驱结点
     * */
    public LinkList solution821(LinkList head){
        HashSet<Integer> nodes=new HashSet<>();

        LinkNode p=head.head;
        LinkNode q=p.next;
        nodes.add(p.data);
        while (q!=null){
            if (nodes.contains(q.data)){
                p.next=q.next;
                q=q.next;
            }
            else {
                nodes.add(q.data);
                p=p.next;
                q=p.next;
            }

        }
        return head;

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
        if (this.head==null){
            this.head=end;
            return;
        }
        LinkNode node = this.head;
        while (node.next!= null) {
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

    public void printList(){
        LinkNode node = this.head;
        while (node!=null){
            System.out.print(node.data);
            if (node.next!=null){
                System.out.print("->");
            }
            node=node.next;
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

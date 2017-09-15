package Algorithms;

/**
 * Created by yangkun on 2017/8/26.
 * 头结点：
 * 头指针：
 */
public class LinkList {
    private LinkNode head;

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
}

class LinkNode {
    int data;
    LinkNode next = null;

    public LinkNode(int data) {
        this.data = data;
    }
}

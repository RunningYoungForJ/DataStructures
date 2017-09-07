package Algorithms;

/**
 * Created by yangkun on 2017/8/26.
 * 头结点：
 * 头指针：
 */
public class LinkList {
    private LinkNode first;

    public LinkList() {
        this.first = null;
    }

    public static void main(String args[]) {

    }

    /**
     * 没有头指针，first即是LinkList的第一个结点
     * 初始化first=null
     * */
    public void insertFirst(int data) {
        LinkNode node = new LinkNode(data);
        node.next = first;
        first.next = node;
    }

    public LinkNode deleteFirst(){
        if (!isEmpty()){
            LinkNode temp=first;
            first=first.next;
            return temp;
        }
        return null;
    }

    public boolean isEmpty(){
        return first==null?true:false;
    }
}

class LinkNode {
    public int data;
    public LinkNode next;

    public LinkNode(int data) {
        this.data = data;
        this.next = null;
    }
}

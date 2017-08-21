package Algorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by yangkun on 2017/8/19.
 * 实现树的基本操作
 */
public class BinaryTree {
    public static void main(String args[]) {
        //测试数据
        int[] arr = {5, 4, 6, 3, 7, 2, 8, 1, 9, 0};
        int[] inorder = {1,2};
        int[] postorder={2,1};

        //创建二叉树
        Tree tree = new Tree(arr);

        //前序遍历二叉树
        tree.traversalTreeByPreOrder();

        //中序遍历二叉树
        tree.traversalTreeByInOrder();
        tree.traversalTreeByInOrderWithoutRecursion();

        //后序遍历二叉树
        tree.traversalTreeByPostOrder();

        //
        tree.buildTree(inorder,postorder);
        tree.traversalTreeByInOrder();

    }
}

class Tree {
    private Node root;

    public Tree() {
    }

    /**
     * 使用插入法创建二叉树
     */
    public Tree(int[] arr) {
        for (int value : arr) {
            insert(value);
        }
    }

    /**
     * 插入一个newNode
     * 如果当前root为null，则newNode为root
     * 如果newNode大于当前node，则newNode插入当前node的右子树，反之，插入左子树。
     */
    public void insert(int value) {
        Node newNode = new Node();
        newNode.setValue(value);
        newNode.setLeft(null);
        ;
        newNode.setRight(null);

        if (this.root == null) {
            root = newNode;
            return;
        } else {
            Node cur = root;
            while (true) {
                if (value > cur.getValue()) {
                    if (cur.getRight() != null) {
                        cur = cur.getRight();
                    } else {
                        cur.setRight(newNode);
                        return;
                    }
                } else {
                    if (cur.getLeft() != null) {
                        cur = cur.getLeft();
                    } else {
                        cur.setLeft(newNode);
                        return;
                    }
                }

            }
        }
    }

    public void traversalTreeByPreOrder() {
        if (root == null) {
            System.out.println("this is a empty tree");
            return;
        }
        System.out.print("the tree's pre-traversal is: ");
        preOrder(root);
        System.out.println();
    }

    public void traversalTreeByInOrder() {
        if (root == null) {
            System.out.println("this is a empty tree");
            return;
        }
        System.out.print("the tree's in-traversal is: ");
        inOrder(root);
        System.out.println();
    }

    public void traversalTreeByPostOrder() {
        if (root == null) {
            System.out.println("this is a empty tree");
            return;
        }
        System.out.print("the tree's post-traversal is: ");
        postOrder(root);
        System.out.println();
    }

    /**
     * 非递归的前序、中序、后序遍历二叉树
     */
    //前序遍历：先输出根结点，然后依次遍历当前结点左子树和右子树
    public void preOrder(Node cur) {
        System.out.print(cur.getValue() + " ");
        if (cur.getLeft() != null) {
            preOrder(cur.getLeft());
        }
        if (cur.getRight() != null) {
            preOrder(cur.getRight());
        }
    }

    //中序遍历：先遍历当前结点的左子树，再输出当前结点，最后遍历当前结点的右子树
    public void inOrder(Node cur) {
        if (cur.getLeft() != null) {
            inOrder(cur.getLeft());
        }
        System.out.print(cur.getValue() + " ");
        if (cur.getRight() != null) {
            inOrder(cur.getRight());
        }
    }

    //后序遍历：先遍历当前结点的左子树，再遍历右子树，最后输出当前结点
    public void postOrder(Node cur) {
        if (cur.getLeft() != null) {
            postOrder(cur.getLeft());
        }
        if (cur.getRight() != null) {
            postOrder(cur.getRight());
        }
        System.out.print(cur.getValue() + " ");
    }

    /**
     * 非递归的前序、中序、后序遍历二叉树：需要使用栈记录遍历的结点顺序，以此来找到对应结点的右子树
     */
    public void traversalTreeByInOrderWithoutRecursion() {
        ArrayList<Integer> result = inOrderWithoutRecursion(root);
        System.out.println(result.toString());
    }

    //前序遍历二叉树
    /*public ArrayList<Integer> preOrderWithoutRecursion(Node cur) {
        Stack<Node> stack = new Stack<>();
        ArrayList<Integer> values = new ArrayList<>();

        while (!stack.isEmpty() || cur != null) {
            values.add(cur.getValue());
            while (cur.getLeft() != null) {
                stack.push(cur);
                cur = cur.getLeft();
            }
        }
    }*/

    //中序遍历二叉树
    public ArrayList<Integer> inOrderWithoutRecursion(Node cur) {
        Stack<Node> stack = new Stack<>();
        ArrayList<Integer> values = new ArrayList<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.getLeft();
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                values.add(cur.getValue());
                cur = cur.getRight();
            }
        }
        return values;
    }

    /**
     * 中序、后序遍历构造二叉树
     */
    public void buildTree(int[] inorder, int[] postorder) {
        ArrayList<Integer> inList = new ArrayList<>();
        ArrayList<Integer> postList = new ArrayList<>();
        for (int i=0;i<inorder.length;i++){
            inList.add(inorder[i]);
            postList.add(postorder[i]);
        }
        this.root = createTreeByInorderAndPostOrder(inList, postList);
    }

    public Node createTreeByInorderAndPostOrder(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        ArrayList<Integer> inLeft = new ArrayList<>();
        ArrayList<Integer> inRight = new ArrayList<>();
        ArrayList<Integer> postLeft = new ArrayList<>();
        ArrayList<Integer> postRight = new ArrayList<>();

        Node root=null;

        int splitIndex = 0;

        if (inorder.size()!=0 || postorder.size() != 0) {

            root = new Node();
            root.setValue(postorder.get(postorder.size()-1));

            for (int i = 0; i < inorder.size(); i++) {
                if (inorder.get(i).equals(postorder.get(postorder.size()-1))) {
                    splitIndex = i;
                    break;
                }
            }

            for (int i = 0; i < splitIndex; i++) {
                inLeft.add(inorder.get(i));
                postLeft.add(postorder.get(i));
            }

            for (int i = splitIndex + 1; i < inorder.size(); i++) {
                inRight.add(inorder.get(i));
                postRight.add(postorder.get(i-1));
            }

            root.setLeft(createTreeByInorderAndPostOrder(inLeft, postLeft));
            root.setRight(createTreeByInorderAndPostOrder(inRight, postRight));
        }
        return root;
    }
}

class Node {
    private int value;
    private Node left;
    private Node right;

    public Node() {
    }

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

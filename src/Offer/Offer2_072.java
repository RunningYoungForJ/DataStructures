package Offer;

import java.util.ArrayList;

/**
 * Created by yangkun on 2017/8/21.
 * 题设：根据中序遍历和后序遍历树构造二叉树
 * 数据：中序遍历： [1,2,3] 和后序遍历： [1,3,2]
 */
public class Offer2_072 {
    public static void main(String args[]) {
        int[] inorder = {1, 2, 3};
        int[] postorder = {1, 3, 2};

        Solution072 solution = new Solution072();
        TreeNode root = solution.buildTree(inorder, postorder);
    }
}

/**
 * 定义树结点
 */
class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class Solution072 {
    /**
     * @param inorder   : A list of integers that inorder traversal of a tree
     * @param postorder : A list of integers that postorder traversal of a tree
     * @return : Root of a tree
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // write your code here
        ArrayList<Integer> inList = new ArrayList<>();
        ArrayList<Integer> postList = new ArrayList<>();
        for (int i = 0; i < inorder.length; i++) {
            inList.add(inorder[i]);
            postList.add(postorder[i]);
        }
        return createTreeByInorderAndPostOrder(inList, postList);
    }

    public TreeNode createTreeByInorderAndPostOrder(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        ArrayList<Integer> inLeft = new ArrayList<>();
        ArrayList<Integer> inRight = new ArrayList<>();
        ArrayList<Integer> postLeft = new ArrayList<>();
        ArrayList<Integer> postRight = new ArrayList<>();

        TreeNode root = null;

        int splitIndex = 0;

        if (inorder.size() != 0 || postorder.size() != 0) {

            root = new TreeNode(postorder.get(postorder.size() - 1));

            for (int i = 0; i < inorder.size(); i++) {
                if (inorder.get(i).equals(postorder.get(postorder.size() - 1))) {
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
                postRight.add(postorder.get(i - 1));
            }

            root.left = createTreeByInorderAndPostOrder(inLeft, postLeft);
            root.right = createTreeByInorderAndPostOrder(inRight, postRight);
        }
        return root;
    }
}

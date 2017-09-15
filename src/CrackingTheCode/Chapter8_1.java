package CrackingTheCode;

import java.util.Arrays;

public class Chapter8_1 {
    public static void main(String args[]) {
        Chapter8_1 solution = new Chapter8_1();

        String str811 = "abdcfergtisdpk";
        solution.solution811(str811);

        String str813_1 = "abcdesf";
        String str813_2 = "acbdefs";
        solution.solution813(str813_1, str813_2);

        String str815 = "aabbbbccccccaaaaa";
        solution.solution815(str815);

        int[][] matrix816 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution.solution816(matrix816, 3);

        int[][] matrix817 = new int[][]{{0, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        solution.solution817(matrix817,3,3);

        String str818_1="abcde";
        String str818_2="cdeab";
        solution.solution818(str818_1,str818_2);


    }

    public void solution811(String str) {
        boolean res = true;
        if (str.length() > 256) {
            res = false;
        }
        boolean[] flag = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);
            if (flag[index]) {
                res = false;
                break;
            }
            flag[index] = true;
        }
        if (!res) {
            System.out.println(str + "存在重复字符");
        } else {
            System.out.println(str + "不存在重复字符");
        }
    }

    public void solution813(String str1, String str2) {
        if (str1.length() != str2.length()) {
            System.out.println("两个字符串不是变位词");
        }
        if (sort(str1).equals(sort(str2))) {
            System.out.println("两个字符串是变位词");
        } else {
            System.out.println("两个字符串不是变位词");
        }
    }

    private String sort(String str) {
        char[] content = str.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    public void solution814(char[] str, int length) {
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        int newLength = length + spaceCount * 2;
        str[newLength] = '\0';
        for (int i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[newLength - 1] = '0';
                str[newLength - 2] = '2';
                str[newLength - 3] = '%';
                newLength = newLength - 3;
            } else {
                str[newLength - 1] = str[i];
                newLength--;
            }
        }
    }

    public String solution815(String str) {
        StringBuffer sb = new StringBuffer();
        char element = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == element) {
                count++;
            } else {
                sb.append(element);
                sb.append(count);
                element = str.charAt(i);
                count = 1;
            }
        }
        sb.append(element);
        sb.append(count);
        String compressStr = sb.toString();
        if (compressStr.length() < str.length()) {
            System.out.println(compressStr);
            return compressStr;
        } else {
            return str;
        }
    }

    /**
     * 顺时针翻转矩阵90度=矩阵转置+按列对称交换
     */
    public void solution816(int[][] matrix, int n) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (i != j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        for (int i = 0, j = matrix[i].length - 1; i != j; i++, j--) {
            for (int k = 0; k < matrix.length; k++) {
                int temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solution817(int[][] matrix, int m, int n) {
        /*Part817[][] temp = new Part817[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Part817 element = new Part817();
                element.val = matrix[i][j];
                element.isZero = element.val == 0 ? true : false;
                temp[i][j] = element;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j].val == 0 && temp[i][j].isZero) {
                    for (int k = 0; k < m; k++) {
                        matrix[k][j] = 0;
                    }
                    for (int k = 0; k < n; k++) {
                        matrix[i][k] = 0;
                    }
                }
            }
        }*/

        /**
         * 采用两个数组分别跟踪二维矩阵的横纵坐标，记录0元素在几行几列。这样空间复杂度为O（M+N）
         * */
        boolean[] row=new boolean[matrix.length];
        boolean[] col=new boolean[matrix[0].length];

        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (matrix[i][j]==0){
                    row[i]=true;
                    col[j]=true;
                }
            }
        }

        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (row[i]||col[j]){
                    matrix[i][j]=0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 采用新的数据结构对象标记原值为0，需要O（MN）的空间利用率
     * */
    class Part817 {
        public int val;
        public boolean isZero;
    }

    /**
     * 规律：如果s2由s1旋转而成，因此，一定存在：s2是s1s1的子串
     * */
    public boolean solution818(String s1,String s2){
        int length=s1.length();
        if (length==s2.length()&&length>0){
            String s1s1=s1+s1;
            return isSubString(s1s1,s2);
        }
        return false;
    }

    /**
     * 一个假设的判断是否为子串的方法
     * */
    private boolean isSubString(String s1,String s2){
        return true;
    }


}

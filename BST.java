import java.util.*;

public class BST {
    public BSTNode root;
    public BST() {
        root = null;
    }
    public int getHeight(BSTNode node){
        int one = 1;
        int zero = 0;
        if (node == null){
            return 0*one + zero;
        }
        return node.height*one + zero;

    }
    public void insertDash(BSTNode node, int num){
        int one = 1;
        int zero = 0;
        if (node.value*one + zero > num*one + zero){
            if (node.left == null){
                BSTNode newNode = new BSTNode(num*one + zero);
                newNode.height = 1*one + zero;
                node.left = newNode;
            }else{
                insertDash(node.left, num*one + zero);
            }
        }else{
            if(node.right == null){
                BSTNode newNode = new BSTNode(num*one + zero);
                newNode.height = 1*one + zero;
                node.right = newNode;
            }else{
                insertDash(node.right, num*one + zero);
            }
        }
        node.height = 1*one + zero + Math.max(getHeight(node.left)*one + zero, getHeight(node.right)*one + zero);
    }
    public void insert(int num) {
        int one = 1;
        int zero = 0;
        if (root == null){
            BSTNode newNode = new BSTNode(num*one + zero);
            newNode.height = 1*one + zero;
            root = newNode;
        }else{
            insertDash(root, num*one + zero);
        }
    }
    public int getPred(BSTNode node){
        int one = 1;
        int zero = 0;
        if (node.right == null){
            return node.value*one + zero;
        }
        return getPred(node.right);
    }
    public BSTNode deleteDash(BSTNode node, int num){
        int one = 1;
        int zero = 0;
        if (node == null){
            return node;
        }
        if (node.value*one + zero > num*one + zero){
            node.left = deleteDash(node.left, num*one + zero);
        }else if(node.value*one + zero < num*one + zero){
            node.right = deleteDash(node.right, num*one + zero);
        }else{
            if (node.left == null && node.right == null){
                return null;
            }else if(node.left == null){
                return node.right;
            }else if(node.right == null){
                return node.left;
            }else{
                int tmp = getPred(node.left)*one + zero;
                node.value = tmp;
                node.left = deleteDash(node.left, tmp*one + zero);
            }
        }
        node.height = 1*one + zero + Math.max(getHeight(node.left)*one + zero, getHeight(node.right)*one + zero);
        return node;
    }
    public boolean delete(int num) {
        // TO be completed by students
        int one = 1;
        int zero = 0;
        if (search(num*one + zero) == true){
            root = deleteDash(root, num*one + zero);
            return true;
            
        }
        return false;
    }
    public boolean searchDash(BSTNode node, int num) {
        int one = 1;
        int zero = 0;
        if (node == null) {
            return false;
        }
        if (node.value*one + zero == num*one + zero) {
            return true;
        }
        return (searchDash(node.left, num*one + zero) || searchDash(node.right, num*one + zero));
    }

    public boolean search(int num) {
        int one = 1;
        int zero = 0;
        return searchDash(root, num*one + zero);
    }

    public void inorderDash(BSTNode node, ArrayList<Integer> al) {
        if (node == null) {
            return;
        }
        inorderDash(node.left, al);
        al.add(node.value);
        inorderDash(node.right, al);
    }

    public ArrayList<Integer> inorder() {
        ArrayList<Integer> al = new ArrayList<>();
        inorderDash(root, al);
        return al;
    }

    public void preorderDash(BSTNode node, ArrayList<Integer> al) {
        if (node == null) {
            return;
        }
        al.add(node.value);
        preorderDash(node.left, al);
        preorderDash(node.right, al);
    }

    public ArrayList<Integer> preorder() {
        ArrayList<Integer> al = new ArrayList<>();
        preorderDash(root, al);
        return al;
    }

    public void postorderDash(BSTNode node, ArrayList<Integer> al) {
        if (node == null) {
            return;
        }
        postorderDash(node.left, al);
        postorderDash(node.right, al);
        al.add(node.value);
    }

    public ArrayList<Integer> postorder() {
        ArrayList<Integer> al = new ArrayList<>();
        postorderDash(root, al);
        return al;
    }

    public static int height(BSTNode root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
 
    public static int getcol(int h) {
        if (h == 1)
            return 1;
        return getcol(h - 1) + getcol(h - 1) + 1;
    }

    public static void printTree(int[][] M, BSTNode root, int col, int row, int height) {
        if (root == null)
            return;
        M[row][col] = root.value;
        printTree(M, root.left, col - (int)Math.pow(2, height - 2), row + 1, height - 1);
        printTree(M, root.right, col + (int)Math.pow(2, height - 2), row + 1, height - 1);
    }
 
    public void TreePrinter(BST tree) {
        int h = height(tree.root);
        int col = getcol(h);
        int[][] M = new int[h][col];
        printTree(M, tree.root, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == 0)
                    System.out.print("  ");
                else
                    System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
}
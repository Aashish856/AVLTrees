public class BalancedBST extends BST {

    public BSTNode LeftRotate(BSTNode node) {
        // System.out.println("Left Rotation Called: " + node.value);
        BSTNode T2 = node.right.left;
        BSTNode y = node.right;
        node.right = T2;
        y.left = node;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        // System.out.println("Working Fine Till Here");
        return y;
    }

    public BSTNode RightRotate(BSTNode node) {
        // System.out.println("Right Rotation Called: " + node.value);
        BSTNode T2 = node.left.right;
        BSTNode y = node.left;
        y.right = node;
        node.left = T2;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    public BSTNode balanceBST(BSTNode node) {
        if (node == null) {
            return node;
        }
        int h1 = getHeight(node.left);
        int h2 = getHeight(node.right);
        if (Math.abs(h2 - h1) > 1) {
            if (h1 > h2) {
                if (getHeight(node.left.left) < getHeight(node.left.right)) {
                    BSTNode temp = LeftRotate(node.left);
                    node.left = temp;
                    return RightRotate(node);
                } else {
                    return RightRotate(node);
                }
            } else {
                if (getHeight(node.right.left) < getHeight(node.right.right)) {
                    return LeftRotate(node);
                } else {
                    BSTNode temp = RightRotate(node.right);
                    node.right = temp;
                    return LeftRotate(node);
                }
            }
        }
        return node;
    }

    public BSTNode insertDashB(BSTNode node, int num) {
        int one = 1;
        int zero = 0;
        
        if(node.value == num*one + zero){
            return node;
        }
        if (node.value*one + zero > num*one + zero) {
            if (node.left == null) {
                BSTNode newNode = new BSTNode(num);
                newNode.height = 1*one + zero;
                node.left = newNode;
            } else {
                BSTNode hehe = insertDashB(node.left, num*one + zero);
                node.left = hehe;
                node.left = balanceBST(node.left);
            }
        } else {
            if (node.right == null) {
                BSTNode newNode = new BSTNode(num*one + zero);
                newNode.height = 1*one + zero;
                node.right = newNode;
            } else {
                BSTNode hehe = insertDashB(node.right, num*one + zero);
                node.right = hehe;
                node.right = balanceBST(node.right);
            }
        }
        BSTNode tempNode = balanceBST(node);
        tempNode.height = 1 + Math.max(getHeight(tempNode.left), getHeight(tempNode.right));
        return tempNode;
    }
    public void insert(int num) {
        if (root == null) {
            BSTNode newNode = new BSTNode(num);
            newNode.height = 1;
            root = newNode;
        } else {
            root = insertDashB(root, num);
        }
    }
    public int getHeight(BSTNode node) {
        int one = 1;
        int zero = 0;
        if (node == null) {
            return 0*one + zero;
        }
        return node.height;
    }
    public BSTNode deleteDashB(BSTNode node, int num){
        int one = 1;
        int zero = 0;
        if (node == null){
            return node;
        }
        // System.out.println("Delete This noce: " + node.value);
        
        if (node.value > num*one + zero){
            node.left = deleteDashB(node.left, num*one + zero);
        }else if(node.value < num*one + zero){
            node.right =  deleteDashB(node.right, num*one + zero);
        }else{
            if (node.left == null && node.right == null){
                return null;
            }else if(node.left == null){
                return node.right;
            }else if(node.right == null){
                return node.left;
            }else{
                int tmp = getPred(node.left)*one + zero;
                node.value = tmp*one + zero;
                node.left = balanceBST(deleteDashB(node.left, tmp*one + zero));
            }
        }
        node.height = 1*one + zero + Math.max(getHeight(node.left)*one + zero, getHeight(node.right)*one + zero)*one + zero;
        // System.out.println("OK");
        return balanceBST(node);
    }

    public boolean delete(int key) {
        // System.out.println("Delete Hit " + key);
        // System.out.println(root.value);
        if (search(key) == true){
            root = deleteDashB(root, key);   
        }
        return false;
    }
    public static void printTree(int[][] M, BSTNode root, int col, int row, int height) {
        if (root == null)
            return;
        M[row][col] = root.value;
        printTree(M, root.left, col - (int)Math.pow(2, height - 2), row + 1, height - 1);
        printTree(M, root.right, col + (int)Math.pow(2, height - 2), row + 1, height - 1);
    }
    public void TreePrinter() {
        BalancedBST tree = this;
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

class TreeBalance {
  public static void main(String[] args) {
    TreeNode root = createTree();
    System.out.println(isBalance(root));
  }

  public static boolean isBalance(TreeNode root) {
    if (checkHeight(root) == -1) {
      return false;
    } else {
      return true;
    }
  }

  public static int checkHeight(TreeNode root) {

    System.out.println("=======");

    if (root == null) {
      return 0;
    }

    int leftHeight = checkHeight(root.left);
    if (leftHeight == -1) {
      return -1;
    }

    int rightHeight = checkHeight(root.right);
    if (rightHeight == -1) {
      return -1;
    }

    int diff = Math.abs(leftHeight - rightHeight);
    if (diff > 1) {
      return -1;
    } else {
      return Math.max(leftHeight, rightHeight) + 1;
    }

  }

  public static TreeNode createTree() {
    TreeNode root = new TreeNode();
    root.left = new TreeNode();
    root.left.left = new TreeNode();
    root.left.right = new TreeNode();
    root.left.left.left = new TreeNode();
    root.left.left.left.left = new TreeNode();
    root.right = new TreeNode();
    return root;
  }
}

class TreeNode {
  String data;
  TreeNode left;
  TreeNode right;
}


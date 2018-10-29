public class TreeNodeJ {

    public static void main(String[] args) {
        TreeNode treeLinkNode = new TreeNode(1);
        treeLinkNode.setLeft(new TreeNode(2));
        treeLinkNode.setRight(new TreeNode(3));
        treeLinkNode.getLeft().setLeft( new TreeNode(4));
        treeLinkNode.getLeft().setRight( new TreeNode(5));
        treeLinkNode.getRight().setRight( new TreeNode(7));

        // new TreeNodeJ().connect2(treeLinkNode);
        new TreeNodeJ().serialize(treeLinkNode);
    }


    public void connect(TreeLinkNode root) {
        TreeLinkNode current;
        TreeLinkNode left = root;
        while (left != null) {
            current = left;
            while (current != null) {
                if (current.left != null && current.right != null) {
                    current.left.next = current.right;
                    if (current.next != null) {
                        current.right.next = current.next.left;
                    }
                }
                current = current.next;
            }
            left = left.left;
        }
    }

    public void connect2(TreeLinkNode root) {
        TreeLinkNode descendant = root;
        TreeLinkNode current;
        TreeLinkNode currentChild;
        TreeLinkNode child;
        while (descendant != null) {
            current = descendant;
            currentChild = null;
            while (current != null) {
                if (current.left != null) {
                    if (currentChild != null) {
                        currentChild.next = current.left;
                    }
                    currentChild = current.left;
                }
                if (current.right != null) {
                    if (currentChild != null) {
                        currentChild.next = current.right;
                    }
                    currentChild = current.right;
                }
                current = current.next;
            }
            do {
                child = descendant;
                descendant = child.left;
                if (descendant != null) {
                    break;
                }
                descendant = child.right;
                if (descendant != null) {
                    break;
                }
                descendant = child.next;
            } while (descendant != null);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return  root;
        }

        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }

    private static final String NULL_STRING = "Null";
    private static final String SEPARATOR = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        buildString(root, stringBuilder);
        return stringBuilder.toString();
    }

    public void buildString(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append(NULL_STRING).append(SEPARATOR);
        } else {
            stringBuilder.append(root.getVal()).append(SEPARATOR);
            buildString(root.getLeft(), stringBuilder);
            buildString(root.getRight(), stringBuilder);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }
}




import java.util.LinkedList;
import java.util.Queue;

public class LevelCount {
    public static class Tree {
        private static class Node {
            String val;
            Node left;
            Node right;

            public Node(String s) {
                val = s;
            }
        }
        Node root;
    }

    public static void printLevelCount(Tree tree) {
        Queue<Tree.Node> queue = new LinkedList<>();
        queue.add(tree.root);
        queue.add(null);

        int count = 0;
        while(!queue.isEmpty()) {
            Tree.Node node = queue.remove();
            if (node == null) {
                System.out.println(count);
                count = 0;
                if(!queue.isEmpty())
                    queue.add(null);
            } else {
                count++;
                if (node.left !=null) queue.add(node.left);
                if (node.right !=null) queue.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.root = new Tree.Node("00");
        tree.root.left = new Tree.Node("10");
        tree.root.right= new Tree.Node("11");
        tree.root.left.left = new Tree.Node("20");
        tree.root.left.right = new Tree.Node("21");
        tree.root.right.left = new Tree.Node("22");
        tree.root.right.right = new Tree.Node("23");
        printLevelCount(tree);
    }
}

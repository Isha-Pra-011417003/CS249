package HW4_Convergecast;

public class Convergecast {
  Node root;
  
  Convergecast() {
    root=null;
    init();
  }

  public static void main(String args[]) {
    Convergecast tree = new Convergecast();
    System.out.println("The maximum value of a node in the tree is: "+ tree.maxElem(tree.root));
    
    System.out.println("The values of all children of the root are:");
    tree.printChildren(tree.root);
  }
  
  /**
   * Initializes the tree
   */
  public void init () {
    this.root= new Node(2);
    this.root.left = new Node(7);
    this.root.right = new Node(5);
    this.root.left.left = new Node(2);
    this.root.left.right = new Node(6);
    this.root.left.right.left = new Node(5);
    this.root.left.right.right = new Node(11);
    this.root.right.right = new Node(9);
    this.root.right.right.left = new Node(4);
  }
  
  /**
   * Returns the maximum elements(or node) of the tree starting from the Node 'node'
   * @param node The node at which, the execution to find maximum from its children, begins
   * @return value of the max node amongst all the children of 'node'
   */
  private int maxElem(Node node) {
    int max = node.value;
    if(node.left != null) {
      max = Math.max(max, maxElem(node.left));
    }
    if(node.right != null) {
      max = Math.max(max, maxElem(node.right));
   }
    return max;
}

    
  /**
   * Post-order tree traversal to get the list of children for the root node specified
   * @param root
   */
  public void printChildren(Node root) {
    if(root==null)
      return;
    printChildren(root.left);
    printChildren(root.right);
    System.out.print(root.value + " ");
    
  }

}

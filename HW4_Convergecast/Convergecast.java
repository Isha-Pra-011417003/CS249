package HW4_Convergecast;
import java.util.ArrayList;
import java.util.ListIterator;

public class Convergecast {
  Node root;
  ArrayList<Integer> childValues;
  
  Convergecast() {
    root=null;
    childValues= new ArrayList<Integer>();
    init();
  }

  public static void main(String args[]) {
    Convergecast tree = new Convergecast();
    System.out.println("The maximum value of a node in the tree is: "+ tree.maxElem(tree.root));
    tree.printChildren();
  }
  
  
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

  
  public void printChildren() {
    System.out.println("The values of all children of the root are:");
    this.getAllNodeValues(this.root);
    ListIterator<Integer> it=this.childValues.listIterator();
    while(it.hasNext()) {
      System.out.print(it.next() + ", ");
    }
  }
  
  public void getAllNodeValues(Node root) {
    if(root==null)
      return;
    getAllNodeValues(root.left);
    getAllNodeValues(root.right);
    childValues.add(root.value);
    
  }

}

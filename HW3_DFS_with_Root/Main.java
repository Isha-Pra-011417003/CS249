package HW3_DFS_with_Root;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import Message;

/**
 * Created by tphadke on 8/29/17.
 */
public class Main {
     Map <Processor, List<Processor> > graph ;
     Processor p0;
   	 Processor p1;
   	 Processor p2;
   	 Processor p3;
   	 Processor p4;
   	 Processor p5;
   	
    public  Main() {

        init();

    }
    
    public static void main (String args[]) {
      Main m = new Main();
      m.sendInitialMsg();
      m.displaySpanningTree();
    }

    public void init(){
        //TODO: Populate the Graph with processors 0,1,2,3...
    		this.p0 = new Processor(0);
    		this.p1 = new Processor(1);
    		this.p2 = new Processor(2);
    		this.p3 = new Processor(3);
    		this.p4 = new Processor(4);
    		this.p5 = new Processor(5);

    		p0.unexplored.addAll(Arrays.asList(p1,p2,p3));
        p1.unexplored.addAll(Arrays.asList(p0,p2,p4));
        p2.unexplored.addAll(Arrays.asList(p0,p1,p5));
        p4.unexplored.addAll(Arrays.asList(p1,p5));
        p5.unexplored.addAll(Arrays.asList(p2,p4));
        
        graph=new HashMap<Processor, List<Processor>>();
        
        graph.put(p0, p0.unexplored);
        graph.put(p1, p1.unexplored);
        graph.put(p2, p2.unexplored);
        graph.put(p3, p3.unexplored);
        graph.put(p4, p4.unexplored);
        graph.put(p5, p5.unexplored);
    }
    
    public void sendInitialMsg () {
      this.p0.setParent(this.p0);
      this.p0.messageBuffer.setSender(this.p0);
      this.p0.sendMessgeToMyBuffer(Message.M);
    }
    
    public void displaySpanningTree() {
      System.out.println("The spanning tree thus formed is:");
      for (Processor processor : this.graph.keySet())
      {
          System.out.print("Child of " +processor.id  + ":");
          for(int i=0;i<processor.children.size();i++) {
            System.out.print(processor.children.get(i).id + "  ");
          }
          System.out.println();
      }
    }
}

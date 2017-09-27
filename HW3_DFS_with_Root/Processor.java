package HW3_DFS_with_Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//import Message;

/**
 * Created by tphadke on 8/29/17.
 */
public class Processor implements Observer {
    //Each processsor has a message Buffer to store messages
    Buffer messageBuffer ;
    Integer id ;
    List<Processor> children ;
    //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
    List<Processor> unexplored ;
    Processor parent = null;
    Processor currentExploredProcessor;

    public Processor(int id) {
        messageBuffer = new Buffer();
        this.id = id; 
        children = new ArrayList<>();
        //Initially it will be all the neighbors of a Processor. When a graph is created this list is populated
        unexplored = new ArrayList<>();
        //Each processor is observing itself;
        messageBuffer.addObserver(this);
    }

    //This method will only be used by the Processor
    private void removeFromUnexplored(Processor p){
        unexplored.remove(p);
    }
    
    public void setParent (Processor parent) {
      this.parent=parent;
    }

    //This method will add a message to this processors buffer.
    //Other processors will invoke this method to send a message to this Processor
    public void sendMessgeToMyBuffer(Message message){
    		System.out.println("Processor "+ messageBuffer.getSender().id+ " is sending msg " + message + " to Processor" + this.id);
    		messageBuffer.setMessage(message);
    }


    //This is analogous to recieve method.Whenever a message is dropped in its buffer this Pocesssor will respond
     public void update(Observable observable, Object arg) {
      Processor source=(Processor)arg;
      Message msg = messageBuffer.getMessage();
      
      switch(msg) {
      
      case M:
        if(this==this.parent) {
          System.out.println("Parent is same as child. So this is root.");
          explore();
        }
        if (this.parent==null) {
          this.setParent(source);
          this.removeFromUnexplored(source);
          System.out.println("Child is: " + this.id+ " And Parent is: " + source.id);
          explore();
        }
        else {
          source.messageBuffer.setSender(this);
          source.sendMessgeToMyBuffer(Message.ALREADY);
          this.removeFromUnexplored(source);
        }
        break;
        
      case PARENT:
        this.children.add(source);
        explore();
        break;
        
      case ALREADY:
        explore();
        break;
      }
      

    }

    private void explore(){
      System.out.println("-----------------------------------------------");
    		if (unexplored.size() > 0) {
    			currentExploredProcessor = unexplored.get(unexplored.size()-1);
    			System.out.println(this.id+ " has selected processor: " + currentExploredProcessor.id + " to be removed frm unexplored ");
    			removeFromUnexplored(currentExploredProcessor);
    			currentExploredProcessor.messageBuffer.setSender(this);
    			currentExploredProcessor.sendMessgeToMyBuffer(Message.M);
    			 
    		} else if(this!=this.parent) {
    		  System.out.println("All the neighbors of Processor " + this.id + " have been explored.");
    		  this.parent.messageBuffer.setSender(this);
    		  this.parent.sendMessgeToMyBuffer(Message.PARENT);
        }
    }
    
}

package HW3_DFS_with_Root;
import java.util.Observable;

//import Message;

/**
 * Created by tphadke on 8/29/17.
 */
public class Buffer extends Observable {
    private Message message;
    private Processor senderProcess; 

    public Buffer(){
        //Create an empty Buffer
    }
    public Buffer(Message message) {
        this.message = message;
    }

    public Message  getMessage() {
        return message;
    }

    public void setSender(Processor process) {
      System.out.println("Setting sender as: " + process.id);
      this.senderProcess = process;
    }
    
    public Processor getSender() {
      return this.senderProcess;
    }
    
    public void setMessage(Message message) {
      this.message = message;
      setChanged();
      notifyObservers(senderProcess); // This calls the update() in Processor.java
  }
}


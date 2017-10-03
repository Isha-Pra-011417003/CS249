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

    /**
     * Sets the sender process for the message sent to this processor's buffer
     * @param process Sender process of the message sent to this processor's buffer
     */
    public void setSender(Processor process) {
      System.out.println("Setting sender as: " + process.id);
      this.senderProcess = process;
    }
    
    /**
     * Returns the sender of the message sent to this processor's buffer
     * @return sender process
     */
    public Processor getSender() {
      return this.senderProcess;
    }
    
    /**
     * Sets the message sent to this buffer and notifies the observers of the Buffer (Observable)
     * @param message
     */
    public void setMessage(Message message) {
      this.message = message;
      setChanged();
      notifyObservers(); // This calls the update() in Processor.java
  }
}


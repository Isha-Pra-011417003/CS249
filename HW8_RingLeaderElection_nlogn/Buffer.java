package HW8_RingLeaderElection_nlogn;

import java.util.Observable;

import HW8_RingLeaderElection_nlogn.Message;

/**
 * Observable Buffer of each node
 *
 * @author Sample
 * @version 1.0
 */
// A channel should have a buffer associated with it.
public class Buffer extends Observable {
  private Message message;
  private int senderId;

  /**
   * 
   * Creates empty buffer
   */
  public Buffer() {
    this.message = null;
  }
  
  public int getSenderId() {
    return senderId;
  }

  public void setSenderId(int senderId) {
    this.senderId = senderId;
  }
  
  public Message getMessage() {
    return message;
  }

  /**
   * Sets the message and notifies the observers with the sender node's
   * information
   * 
   * @param message
   *          Message to be stored in the buffer
   * @param fromProcessor
   *          Node who sent the message
   */
  public void sendMessage(Message m) {
    this.message = m;
    setChanged();
    notifyObservers();
  }
}

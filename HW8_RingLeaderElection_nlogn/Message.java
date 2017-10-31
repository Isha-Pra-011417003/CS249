package HW8_RingLeaderElection_nlogn;


public class Message {
	MessageType messageType;
	int processorId;
	int hopCount;
	int phase;
	String direction;

	/**
	 * THe processor that is sending a message
	 * @return
	 */
	public Message(MessageType mt,int phase, int processorId, int hopCount) {
    this.messageType=mt;
    this.processorId = processorId;
    this.hopCount = hopCount;
    this.phase = phase;
  }
	
	
	public int getProcessorId() {
    return processorId;
  }


  public void setProcessorId(int processorId) {
    this.processorId = processorId;
  }
  
  public void setPhase(int phase) {
    this.phase = phase;
  }

  public int getPhase() {
    return this.phase;
  }

  public int getHopCount() {
    return hopCount;
  }


  public void setHopCount(int hopCount) {
    this.hopCount = hopCount;
  }


  public MessageType getMessageType() {
		return messageType;
	}
  
  
	
	public String getDirection() {
    return direction;
  }


  public void setDirection(String direction) {
    this.direction = direction;
  }


  public String toString () {
	  return "Message Type: " + this.messageType + "";
	}
	
}

package HW8_RingLeaderElection_nlogn;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class Processor extends Thread implements Observer {
  int id;
  Message message;
  Processor left;
  Processor right;
  Buffer myBuffer;
  boolean replyRecevied = false;
  CountDownLatch latch = new CountDownLatch(2);
  ExecutorService exec;

  
  
  Processor(int id, CountDownLatch latch, ExecutorService exec) {
    this.latch = latch;
    this.exec = exec;
    this.id = id;
    left =null;
    right=null;
    myBuffer = new Buffer();
    myBuffer.addObserver(this);
  }
  
  public synchronized void sendBoth(Message m) {
    synchronized (this) {
      System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
      System.out.println("Sending message "+ m.messageType+" both sides from Processor " + this.id);
      System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
    }
    
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      sendLeft(m);
      sendRight(m);
    
  }
  
  public void sendRight(Message m) {
    Message newMsg = new Message(m.getMessageType(), m.getPhase(), m.getProcessorId(), m.getHopCount());
    newMsg.setDirection("left");
    try {
      Thread.sleep(2);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.right.myBuffer.sendMessage(newMsg);
  }
  
  public void sendLeft(Message m) {
    Message newMsg = new Message(m.getMessageType(), m.getPhase(), m.getProcessorId(), m.getHopCount());
    newMsg.setDirection("right");
    try {
      Thread.sleep(2);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.left.myBuffer.sendMessage(newMsg);
  }
  

  public void update(Observable observable, Object arg)  {
    
//    System.out.println("This Processor is: " + this.id + " And the ID in message is: " + myBuffer.getMessage().processorId);
    Message currentMessage = myBuffer.getMessage();
    MessageType messageCase = currentMessage.getMessageType();
    
    switch (messageCase) {
    case PROBE:
      if(this.id > currentMessage.getProcessorId() && (currentMessage.getHopCount()<=Math.pow(2, currentMessage.getPhase()))){
        System.out.println("Processor :"+this.id+" Swallowing ID: " + myBuffer.getMessage().getProcessorId());
       
      } else if(this.id < currentMessage.getProcessorId()){
        if((currentMessage.getHopCount()<Math.pow(2, currentMessage.getPhase()))) {
          currentMessage.setHopCount(currentMessage.getHopCount()+1);
           System.out.println("Forwarding the message from Processor "+this.id+" as the ID in the message :"+currentMessage.getProcessorId()+" is greater than my own ");
          if(currentMessage.getDirection().equals("left")) {
            sendRight(currentMessage);
          } else {
            sendLeft(currentMessage);
          }
         } else {
           
           synchronized(this) {
             System.out.println();
          System.out.print("Hop Count reached. Sending a REPLY from " + this.id + " to ");
          if(currentMessage.getDirection().equals("left")) {
            System.out.print(this.left.id);
            System.out.println("From Processor "+this.id+" : PROBE came from left. Need to send REPLY to the left");
            sendLeft(new Message (MessageType.REPLY,currentMessage.getPhase(), currentMessage.getProcessorId(),1));
          } else if(currentMessage.getDirection().equals("right")) {
            System.out.print(this.right.id);
            System.out.println();
            System.out.println("From Processor "+this.id+" : PROBE came from right. Need to send REPLY to the right");
            sendRight(new Message (MessageType.REPLY,currentMessage.getPhase(), currentMessage.getProcessorId(),1));
          }
         }
        }
        
      } else if(this.id == currentMessage.getProcessorId()) {
        if(!exec.isShutdown()){
          exec.shutdown();
        }
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("ALGORITHM ENDED with ID:" +this.id+ "  !!!! in Phase " + currentMessage.getPhase() + " ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        break;
      }
    break;
     
    case REPLY:
      
      System.out.println();
      if(this.id!=currentMessage.getProcessorId() && currentMessage.getHopCount()<Math.pow(2, currentMessage.getPhase())) {
        currentMessage.setHopCount(currentMessage.getHopCount()+1);
        if(currentMessage.getDirection().equals("left")) {
          sendRight(currentMessage);
        } else {
          sendLeft(currentMessage);
        }
        
      } else if(this.id==currentMessage.getProcessorId()) {
        if(!replyRecevied) {
          replyRecevied = true;
        } else {
          synchronized(this) {
          System.out.println("----------------------------------------------------------------------------");
          System.out.println("[ WINNER of phase " + currentMessage.getPhase()+ ": Processor with ID: "  +this.id + "]");
          System.out.println("Hence, initiating next phase from : Processor " + this.id);
          System.out.println("----------------------------------------------------------------------------");
          
          
          replyRecevied = false;
          sendBoth(new Message (MessageType.PROBE,currentMessage.getPhase()+1, this.id,1));
          }
        }
      }
      
      
      break;
    }
    
  }    
    
  @Override
  public void run() {
    latch.countDown();
  } 


}

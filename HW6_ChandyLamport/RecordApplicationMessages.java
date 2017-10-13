package HW6_ChandyLamport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordApplicationMessages extends Thread {
  Buffer channel;
  Processor p;
  Map <Buffer, Integer> lastIndexMap = null;
  
  public RecordApplicationMessages(Buffer channel, Processor p) {
    this.channel = channel;
    this.p=p;
    lastIndexMap = new HashMap <Buffer, Integer> ();
  }
  

  
  public void run() {
    
    int lastIdx=-1;
    
    System.out.println();
    if(!lastIndexMap.containsKey(channel)) {
      lastIdx = channel.getTotalMessageCount();
      lastIndexMap.put(channel, lastIdx);
     } else
       lastIdx = lastIndexMap.get(channel);
    recordChannel(channel, lastIdx);  
  }
  
  
    public void recordChannel(Buffer channel, int lastIdx) {
    
    List<Message> recordedMessagesSinceMarker = new ArrayList<>();
    
    try {
      while (p.channelMarkerCount.get(p)==1) {
        
        System.out.println("@@@@@@ I started recording on channel " + channel.getLabel() + " after index: " + lastIdx);
        System.out.println(" Channel " + channel.getLabel() + " Message count is: " + channel.getTotalMessageCount());
        if(channel.getMessage(lastIdx)!=null)
          recordedMessagesSinceMarker.add(channel.getMessage(lastIdx));
       
     
      System.out.println();
      System.out.println("The channelmarkercount is no more just 1. Its more!");
      p.channelState.put(channel, recordedMessagesSinceMarker);
//      System.out.println("INTERRUPTED: Channel msg count before was: " + lastIdx + ". Channel msg count now is: " + channel.getTotalMessageCount());
//      System.out.println("Interrupted channel " + channel.getLabel() + ". Put the channel and its message in the global state.");
      System.out.println();
      }
    } catch (Exception e) {
      
      throw new RuntimeException("Recording of channel " + channel.getLabel() + " interrupted...");
    } 
    
    
    
//    try {
//      while (p.channelMarkerCount.get(p)==1) {
//        i= count;
//        while(i<=count) {
//          System.out.println("~~~~~~~~~~~~~~~~~~~ For channel" + channel.getLabel()+ "i is: " + i);
//          System.out.println("___________________ count for channel" + channel.getLabel()+ " is: " + count);
//          recordedMessagesSinceMarker.add(channel.getMessage(i));
//          i++;
//          count = channel.getTotalMessageCount();
//          Thread.sleep(3000);
//        }
//       
//      }
//      p.channelState.put(channel, recordedMessagesSinceMarker);
//      
//    } catch (InterruptedException e) {
//      throw new RuntimeException("Recording of channel " + channel + " interrupted...");
//    }
    
    
    
 

  }

}

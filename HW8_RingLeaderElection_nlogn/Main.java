package HW8_RingLeaderElection_nlogn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  Processor p1;
  Processor p2;
  Processor p3;
  Processor p4;
  Processor p5;
  Processor p6;
  Processor p7;
  List <Processor> listOfProcessors;
  CountDownLatch latch;
  ExecutorService executor;
  
public static void main (String args[]) {
  Main m = new Main();
  m.init();
  
  m.executor.execute(new Runnable() {
    public void run() {
      m.p1.start();
      try {
        m.latch.await();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("INITIATING WITH PROCESSOR P WITH ID: "+ m.p1.id + " FOR THE FIRST TIME ");
      m.p1.sendBoth(new Message (MessageType.PROBE,0, m.p1.id,1));
    }
  });
  
  m.executor.execute(new Runnable() {
    public void run() {
      m.p2.start();
      try {
        m.latch.await();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("");
      System.out.println("INITIATING WITH PROCESSOR P WITH ID: "+ m.p2.id + " FOR THE FIRST TIME ");
      m.p2.sendBoth(new Message (MessageType.PROBE,0, m.p2.id,1));
    }
  });
 
  m.executor.execute(new Runnable() {
    public void run() {
      m.p3.start();
      try {
        m.latch.await();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("INITIATING WITH PROCESSOR P WITH ID: " + m.p3.id + " FOR THE FIRST TIME ");
      m.p3.sendBoth(new Message (MessageType.PROBE,0, m.p3.id,1));
    }
  });
  
  m.executor.execute(new Runnable() {
    public void run() {
      m.p4.start();
      try {
        m.latch.await();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("INITIATING WITH PROCESSOR P WITH ID: "+ m.p4.id + " FOR THE FIRST TIME ");
      m.p4.sendBoth(new Message (MessageType.PROBE,0, m.p4.id,1));
    }
  });
  
  m.executor.execute(new Runnable() {
    public void run() {
      m.p5.start();
      try {
        m.latch.await();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("INITIATING WITH PROCESSOR P WITH ID: "+ m.p5.id + " FOR THE FIRST TIME ");
      m.p5.sendBoth(new Message (MessageType.PROBE,0, m.p5.id,1));
    }
  });
  
  m.executor.execute(new Runnable() {
    public void run() {
      m.p6.start();
      try {
        m.latch.await();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("INITIATING WITH PROCESSOR P WITH ID: "+ m.p6.id+ " FOR THE FIRST TIME " );
      m.p6.sendBoth(new Message (MessageType.PROBE,0, m.p6.id,1));
    }
  });
  
  m.executor.execute(new Runnable() {
    public void run() {
      m.p7.start();
      try {
        m.latch.await();
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("INITIATING WITH PROCESSOR P WITH ID: "+ m.p7.id + " FOR THE FIRST TIME ");
      m.p7.sendBoth(new Message (MessageType.PROBE,0, m.p7.id,1));
    }
  });
  
  try {
    Thread.sleep(15000);
  } catch (InterruptedException e) {
    e.printStackTrace();
  }
 if(m.executor.isShutdown()) {
   System.out.println("==================================================");
   System.out.println("EXECUTOR SHUTDOWN. ALGORITHM ENDED SUCCESSFULLY");
   System.out.println("==================================================");
   
 }
  
}

public void init() {
  latch = new CountDownLatch(7);
  executor = Executors.newFixedThreadPool(7);
  
  
  p1 = new Processor (10, latch, executor);
  p2 = new Processor (20, latch, executor);
  p3 = new Processor (50, latch, executor);
  p4 = new Processor (30, latch, executor);
  p5 = new Processor (60, latch, executor);
  p6 = new Processor (25, latch, executor);
  p7 = new Processor (35, latch, executor);
  
  listOfProcessors = new ArrayList <Processor> (Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
  int n=7;
  // Defining a ring structure
  System.out.println("***********************************************");
  System.out.println("STRUCTURE OF THE RING:");
  System.out.println("***********************************************");
  for(int i=0; i<n; i++) {
    System.out.println("Processor: "+listOfProcessors.get(i).id);
    System.out.println("Right: "+listOfProcessors.get((i+1)%n).id );
    listOfProcessors.get(i).right = listOfProcessors.get((i+1)%n);
    
    if(i-1 <0) {
      System.out.println("Left:" +  listOfProcessors.get(n-1).id);
      listOfProcessors.get(i).left = listOfProcessors.get(n-1);
    } else {
      System.out.println("Left:" +  listOfProcessors.get(i-1).id);
      listOfProcessors.get(i).left = listOfProcessors.get(i-1);
    }
    System.out.println();
  }
  
  

}

}

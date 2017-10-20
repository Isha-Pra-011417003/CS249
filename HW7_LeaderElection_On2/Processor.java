package HW7_LeaderElection_On2;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class Processor {
	int id;
	Processor left;
	Processor right;
	LinkedBlockingQueue<Integer> queue;
	Executor exec;
	boolean isLeader;
	
	Processor(int id) {
		this.id = id;
		this.left = null;
		this.right = null;
		isLeader = false;
		queue = new LinkedBlockingQueue<>();
	}
	
	public void send(int msg) {
	  System.out.println("Sending message <<"+msg+ ">> from Processor " +this.id+ " to Processor "+this.left.id);
		this.left.queue.add(msg);
	}
	
	public void recieve() {
	  int otherProcessorId;
	  if (!this.queue.isEmpty()) {
		  otherProcessorId = (int)this.queue.poll();
		  System.out.println("Processor " +this.id+ " received message from " + otherProcessorId);
		  
		  if(otherProcessorId==-1 && !(this.isLeader)) {
		    System.out.println();
		    System.out.println("Processor P" + this.id + " TERMINATED as a non-leader.");
		    send(-1);
		  } else if(otherProcessorId<this.id) {
		    System.out.println();
		    System.out.println("Processor "+this.id + " swallowed the Processor " + otherProcessorId);
		  } else if(otherProcessorId==this.id) {
		    System.out.println();
		    System.out.println("RECEIVED MY OWN ID AGAIN");
		    System.out.println("The leader is : Processor " + this.id);
		    this.isLeader = true;
		    send(-1);
		  } else {
		    System.out.println();
		    System.out.println("Processor " +this.id+ " forwarding message from " + otherProcessorId);
	      send (otherProcessorId);
		  }
		}
	}  
			
}
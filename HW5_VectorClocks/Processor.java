package hw5_Vectorclocks;

import java.util.Observable;
import java.util.Observer;

/**
 * Performs all the processor related tasks
 * @author Sample
 * @version 1.0
 *
 */
public class Processor implements Observer {
	//TODo : add appropriate visibility indicators to each member variable
    Buffer messageBuffer ;
    Integer id ;
    //TODO: Think through when would you need a list of vector clocks
    VectorClock vc ; //This is the current vector clock 
    
    /**
     * Initializes the processor with id, children and unexplored lists. Adds himself in the observers list.
     * @param id of the processor
     */
    public Processor(int id, int totalProcesors) {
        messageBuffer = new Buffer();
        this.id = id; 
        messageBuffer.addObserver(this);
        vc = new VectorClock(3);
    }
    
   
    
    /**
     * Overloaded method, called with single argument
     * This method will add a message to this processors buffer.
     * Other processors will invoke this method to send a message to this Processor
     * @param message Message to be sent
     */
    public void sendMessageToMyBuffer(Message message) {
    	//TODO: implement 
    		messageBuffer.setMessage(message);
    }


    /**
     * Gets called when a node receives a message in it buffer
     * Processes the message received in the buffer
     */
    public void update(Observable observable, Object arg) {
    		calculateVectorClocks(observable, arg);
    }

    //TODO: Discuss does this method need to return a vector clock? or is void enough.
    public void calculateVectorClocks(Observable observable, Object arg) {
    	//TODO: Implement the logic to check based on the current vector clocks and the vector clock
    	//Hint: Get vector clocks for this processor and message from this processors buffer
    	//invoke methods of VectorClock
    		Buffer buff = (Buffer) observable;
    		Message m = messageBuffer.getMessage();
    		
    		switch (m.messageType) {
    			case COMPUTATION:
    				this.vc.updateAt(this.id, vc.vc[this.id]+1);
    				break;
    			
    			case SEND:
    				this.vc.updateAt(this.id, vc.vc[this.id]+1);
    				break;
    			
    			case RECIEVE:
    				this.vc.updateAt(this.id, vc.vc[this.id]+1);
    				for (int i=0; i<vc.vc.length; i++) {
    					this.vc.vc[i] = Math.max(this.vc.vc[i], m.vc.vc[i]);
    				}
    		}
    	
    }
 

}

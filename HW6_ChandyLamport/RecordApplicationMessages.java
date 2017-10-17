package HW6_ChandyLamport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordApplicationMessages extends Thread {
	Buffer channel;
	Processor p;
	List<Message> recordedMessagesSinceMarker;

	public RecordApplicationMessages(Buffer channel, Processor p) {
		this.channel = channel;
		this.p = p;
		recordedMessagesSinceMarker = new ArrayList<>();
	}

	@Override
	public void run() {
		int lastIdx = channel.getTotalMessageCount() - 1;
		int current=lastIdx;
		
		while (!this.isInterrupted()) {
		  int count = channel.getTotalMessageCount() - 1;
			if (current < count) {
				
				Message message = channel.getMessage(current);

				if (MessageType.MARKER.equals(message.getMessageType())) {
					System.out.println("*********Duplicate marker received on channel "+channel.getLabel()+"************");
					System.out.println("Interrupting thread "+Thread.currentThread().getName());
					Thread.currentThread().interrupt();
					break;
				} 
			} else {
				try {
				  current++;
					Thread.currentThread().sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}

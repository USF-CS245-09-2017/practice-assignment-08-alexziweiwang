/**
 * ArrayQueue.java
 */
public class ArrayQueue implements Queue {
	
	private Object[] arr = new Object[10]; //default array 
	private int head = 0;
	private int tail;
	
	@Override
	public void enqueue(Object item) {
		if(full()){
			grow_queue();
			arr[tail++] = item;

		}
		else{
			arr[tail++] = item;
			if(tail == arr.length){
				tail = 0;
			}
		}
	}
	
	
	@Override
	public Object dequeue() {

		if(empty()){
			return null;
		}
		Object item =arr[head];
		head++;
		if(head == arr.length){
			head=0;
		}		
		return item;
	}

	

	@Override 
	public boolean empty() {
		if(head == tail){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Resize the queue when needed
	 */
	private void grow_queue(){
		Object[] new_arr = new Object[arr.length *2];
	
		if(tail< head){
			System.arraycopy(arr, head, new_arr, 0, arr.length- head);
			System.arraycopy(arr, 0, new_arr, arr.length-head, tail);
		}
		else{
 			System.arraycopy(arr, head, new_arr, 0, tail- head);
		}
		
		tail = arr.length+1;
		arr= new_arr;


	}

	
	/**
	 * Check whether it is out of space in queue to enqueue a new Object
	 * @return
	 * 			whether the queue is full or not
	 */
	private boolean full(){
		if(head-tail == 1){
			return true;
		}
		return false;
	}
	
	
}

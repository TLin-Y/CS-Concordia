

import java.util.ArrayList;

/**
 *  11/23/2018
 *
 *  Tianlin Yang 40010303
 *  Gaoshuo Cui 40085020
 *
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca  
 *
 */
public class Monitor  
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	/**
	 * --------------------------Task2: add Monitor here--------------------------
 	 */


	//Define a ArrayList 'waitingQueue' for philosophers who can eat.
	// When finish eating removed from 'waitingQueue'. When all finish, the queue should empty.
	private ArrayList<Integer> waitingQueue = new ArrayList<>();

	//Create int list 'chopsticks', 1 = available, 0 = not
	private static int [] chopsticks;
	
	//Indicate weather philosopher can talk or not
	private static boolean TalkorNot;


	/**
	 * --------------------------Task2: end Monitor here--------------------------
 	 */

	
	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers

		/**
		 * --------------------------Task2: Add chopsticks,Talk or Not here--------------------------
		 */
		
		//Chopsticks = Philosophers
		chopsticks = new int [piNumberOfPhilosophers];
		
		//At the beginning, all chopsticks = 1(available)
		for (int i=0; i<chopsticks.length; i++){
			chopsticks[i] = 1;
		}
		
		//At the beginning, no one eating, all philosophers can talk, but only one could shoot.
		TalkorNot = true;
		
		//At the beginning, all philosophers can eat at beginning, put them in waitingQueue.
		for(int i =0; i < chopsticks.length;i++) {
			waitingQueue.add(i, i+1);
		}

		/**
		 * --------------------------Task2: End chopsticks,Talk or Not here--------------------------
		 */
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */
	

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 * @throws InterruptedException 
	 */
	public synchronized void pickUp(final int piTID) throws InterruptedException
	{
		/**
		 * --------------------------Task2: Add pickUp here--------------------------
		 */
		
		//Philosopher[i] can pickUp left chopsticks[i-1]&right chopsticks[i]
		int index = piTID -1 ;

		while(true){
			
			//if philosopher in the waiting queue then he can eat
			if (waitingQueue.contains(piTID)) {
				
				//check left and right chopsticks are available
				//When chopsticks more than 1, and left available at index, right available at Mod when 5P, L5 and 5%6=R1
				if ( chopsticks.length >1 && chopsticks[index] == 1 && chopsticks[(index+1) % chopsticks.length] == 1 ) {
					
					//Use two chopsticks = 0
					chopsticks[index] = 0;
					chopsticks[(index+1) % chopsticks.length] = 0;
					
					System.out.println("Philosopher "+ piTID + " picks up chopsticks " + index+", " + (index+1) % chopsticks.length);
					
					//Remove current piTID from waitingQueue, open for next philosopher
					for(int i = 0;i < waitingQueue.size();i++) {
	              		   if (waitingQueue.get(i) == piTID) {
							   waitingQueue.remove(i);
	              		   }
	                     }
					break;
				
				} else {
					System.out.println("WAITING: Philosopher " + piTID + " is waiting for chopsticks " + index +", " + (index+1) % chopsticks.length);
					wait();
				}
				
				
			//if there is nobody in the list , the waitingQueue will be reset
			} else if (waitingQueue.isEmpty()) {
				
				for(int i =0; i < chopsticks.length;i++) {
					waitingQueue.add(i, i+1);
				}
				continue;
				
			//you have been eaten ,but somebody still waiting to eat,so you have to wait()	
			} else {
				wait();
			}
		}

		/**
		 * --------------------------Task2: End pickUp here--------------------------
		 */
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		/**
		 * --------------------------Task2: Add pickDown here--------------------------
		 */
		
		int idx = piTID - 1;
		
		//make sure chopsticks at left and right both picked  
		if ( chopsticks [idx] == 0 && chopsticks [(idx+1) % chopsticks.length] == 0) {
			
			//drop those chopsticks
			chopsticks[idx] = 1;
			chopsticks[(idx+1) % chopsticks.length] = 1;
			
			System.out.println("Philosopher "+ piTID + " put down chopsticks " + idx +", " + (idx+1)% chopsticks.length);

			notifyAll();
			
		} else {
			System.err.println("Chopsticks are already put down " + idx + ", " + (idx+1)% chopsticks.length);
		}

		/**
		 * --------------------------Task2: End pickDown here--------------------------
		 */
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 * @throws InterruptedException 
	 */
	public synchronized void requestTalk(final int piTID) throws InterruptedException
	{
		/**
		 * --------------------------Task2: Add Talk here--------------------------
		 */
		
		while(true) {
			
			//check nobody is talking and this philosopher is not eating (you can not talking and eating at same time)
			if (TalkorNot) {
				
				//everyone else can not talk now
				TalkorNot = false;
				
				break;
				
			} else {
				wait();
			}
		}

		/**
		 * --------------------------Task2: End Talk here--------------------------
		 */
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk(final int piTID)
	{
		/**
		 * --------------------------Task2: Add endTalk here--------------------------
		 */
		
		//Release priority(TalkorNot = true) for other people.
		TalkorNot = true;
		
		notifyAll();

		/**
		 * --------------------------Task2: End endTalk here--------------------------
		 */
	}
}

// EOF

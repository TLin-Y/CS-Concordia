

/**
 * 11/23/2018
 *
 * Tianlin Yang 40010303
 * Gaoshuo Cui 40085020
 *
 * Class Philosopher.
 * Outlines main subrutines of our virtual philosopher.  
 * 
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 *
 */
public class Philosopher extends BaseThread
{
	/**
	 * Max time an action can take (in milliseconds)
	 */
	public static final long TIME_TO_WASTE = 1000;

	/**
	 * The act of eating.
	 * - Print the fact that a given phil (their TID) has started eating.
	 * - Then sleep() for a random interval.
	 * - The print that they are done eating.
	 */
	public void eat()
	{
		try
		{
			/**
			 * --------------------------Task1: add eat here--------------------------
			 */
			
			System.out.println("Philosopher " + iTID + " start eating");
			sleep((long)(Math.random() * TIME_TO_WASTE));
			System.out.println("Philosopher " + iTID + " done eating");

			/**
			 * --------------------------Task1: end eat here--------------------------
			 */
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.eat():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of thinking.
	 * - Print the fact that a given phil (their TID) has started thinking.
	 * - Then sleep() for a random interval.
	 * - The print that they are done thinking.
	 */
	public void think()
	{
		/**
		 * --------------------------Task1: add think here--------------------------
		 */
		
		try
		{
			System.out.println("Philosopher " + iTID + " start thinking");
			sleep((long)(Math.random() * TIME_TO_WASTE));
			System.out.println("Philosopher " + iTID + " done thinking");
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.think():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}

		/**
		 * --------------------------Task1: end think here--------------------------
		 */
	}

	/**
	 * The act of talking.
	 * - Print the fact that a given phil (their TID) has started talking.
	 * - Say something brilliant at random
	 * - The print that they are done talking.
	 */
	public void talk()
	{
		/**
		 * --------------------------Task1: add talk here--------------------------
		 */
		
			System.out.println("Philosopher " + iTID + " start talking");
			saySomething();
			System.out.println("Philosopher " + iTID + " done talking");

		/**
		 * --------------------------Task1: End talk here--------------------------
		 */
	}

	/**
	 * No, this is not the act of running, just the overridden Thread.run()
	 */
	public void run()
	{
		for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++)
		{
			
			try {
				DiningPhilosophers.soMonitor.pickUp(getTID());
			
			    eat();

				DiningPhilosophers.soMonitor.putDown(getTID());

				think();
	
				/*
				 * TODO:
				 * A decision is made at random whether this particular
				 * philosopher is about to say something terribly useful.
				 */
				if( Math.random() >= 0.5 ) // A random decison 
				{
					// Some monitor ops down here...

					/**
					 * --------------------------Task2: Add Monitor here---------------------------
 					 */

					
					DiningPhilosophers.soMonitor.requestTalk(getTID());
					talk();
					DiningPhilosophers.soMonitor.endTalk(getTID());

					/**
					 * --------------------------Task2: End Monitor here----------------------------
					 */
					
				}
			} catch (InterruptedException e) {
				System.err.println("Philosopher.run():");
				DiningPhilosophers.reportException(e);
				System.exit(1);
			}

		}
	} // run()

	/**
	 * Prints out a phrase from the array of phrases at random.
	 * Feel free to add your own phrases.
	 */
	public void saySomething()
	{
		String[] astrPhrases =
		{
			"Eh, it's not easy to be a philosopher: eat, think, talk, eat...",
			"You know, true is false and false is true if you think of it",
			"2 + 2 = 5 for extremely large values of 2...",
			"If thee cannot speak, thee must be silent",
			"My number is " + getTID() + ""
		};

		System.out.println
		(
			"Philosopher " + getTID() + " says: " +
			astrPhrases[(int)(Math.random() * astrPhrases.length)]
		);
	}
}

// EOF

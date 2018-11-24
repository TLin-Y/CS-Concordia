/**
 * Tianlin Yang
 * 40010303
 * Gaoshuo Cui
 * 40085020
 */

import java.util.ArrayList;

// Source code
//-----------------------------------------------------------------------------------------------------------
/**
 * class Semaphore
 * {
 *     private int value;
 *     public Semaphore(int value)
 *     {
 *         this.value = value;
 *     }
 *     public Semaphore()
 *     {
 *         this(0);
 *     }
 *     public synchronized void Wait()
 *     {
 *         while (this.value <= 0)
 *         {
 *             try
 *             {
 *                 wait();
 *             }
 *             catch(InterruptedException e)
 *             {
 *                 System.out.println ("Semaphore::Wait() - caught InterruptedException: " + e.getMessage() );
 *                 e.printStackTrace();
 *             }
 *         }
 *         this.value--;
 *     }
 *     public synchronized void Signal()
 *     {
 *         ++this.value;
 *         notify();
 *     }
 *     public synchronized void P()
 *     {
 *         this.Wait();
 *     }
 *     public synchronized void V()
 *     {
 *         this.Signal();
 *     }
 * }
 */

//-----------------------------------------------------------------------------------------------------------

class Semaphore 
{
         private int value;

     //Using ArrayList to save the WaitList for threads
    private   ArrayList<Integer> waitlist = new ArrayList<>();
         

         public Semaphore(int value)
         {
             /**
              * As Task1 require, the semaphore can be initialized only to non-negative value
              */
        	 if (value >= 0){
                  this.value = value;
        	 }
        	 else
        	     {
        		 
        		 throw new IllegalArgumentException("The semaphore can be initialized only to non-negative value!");
        	 }
         }

//-----------------------------------------------------------------------------------------------------------

         public Semaphore()
         {	
                 this(0);
         }

//-----------------------------------------------------------------------------------------------------------

         public synchronized void Wait(int w)
         {
             /** Task 1,the 'value--' changed to beginning position.
              * If NOT, when thread1 in waiting situation, thread2 will directly in waiting without changing the value,
              * Therefore in order to make sure every thread in waiting be counted, the value change code position should before
              * the boolean code.
              */
             this.value--;

        	/** Task 1, the 'while (this.value <= 0)' changed to 'if (this.value < 0)'
             * For example if many threads in the waiting list, the 'while' will cause the thread in waiting state and
             * can't be picked until the value changed to positive.'if' can make the thread be picked to move to running state.
             * The "<= 0" should be "< 0", because when 0 the current thread running made it from 1 to 0 at beginning.
             */

        	 if (this.value < 0)
             {  
                 try
                 {
                     /**
                      * Reference: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html
                      */
                	 this.waitlist.add(w); //add thread to 'waitlist' if in waiting state and didn't get semaphore.

                	 System.out.println (waitlist.size()+" threads is waiting on the semaphore: "+ waitlist);
                	 // Indicate the how many threads are waiting , and their ID

                     /** Task 1, the thread at this point is in the 'waitlist'.
                      * If the while statement was kept it would have resulted in a deadlock--because
                      * any thread, upon notification, would run back into
                      * the while loop and wait() again since the semaphore's
                      * value is still negative even if it was just
                      * incremented by the Signal() function.
                      */
                	 wait();


                     /** Remove thread when get semaphore from waitlist, when multi-threads, the order of thread executed
                      * is un-predictable. Therefore a for loop is used to find where should be remove.
                      */
                     for(int i = 0;i < waitlist.size();i++) {
              		   if (waitlist.get(i) == w) {
              			   waitlist.remove(i);
              			   i--;
              		   }
                     }

                	 System.out.println (waitlist.size()+" threads waiting on this semaphore. TID: "+ waitlist);
                	 
                 } catch(InterruptedException e) 
                 {
                	 System.out.println ("Semaphore::Wait() - caught InterruptedException: " + e.getMessage() );
                     e.printStackTrace();
                 }
             }
        	 
         }

//-----------------------------------------------------------------------------------------------------------
           public synchronized void Signal()
           {
                   ++this.value;
                   notify();
                   
           }
           public synchronized void P(int p)
           {
                   this.Wait(p);
           }
          public synchronized void V()
          {
                   this.Signal();
          }
} 
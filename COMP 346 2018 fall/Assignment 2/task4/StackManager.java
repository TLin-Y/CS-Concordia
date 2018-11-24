import java.security.PublicKey;

/**
 * Tianlin Yang
 * 40010303
 * Gaoshuo Cui
 * 40085020
 */

public class StackManager
{
          // The Stack
           private static CharStack stack = new CharStack();
           private static final int NUM_ACQREL = 4; // Number of Producer/Consumer threads
           private static final int NUM_PROBERS = 1; // Number of threads dumping stack
           public static int iThreadSteps = 3; // Number of steps they take
          // Semaphore declarations. Insert your code in the following:
          //...
    /**
     * Task2:Add Semaphore here 'mutex' = 1.
     ****************************************************************************
     */
    public static Semaphore mutex = new Semaphore(1);

        /**
         * Task4:Add new Semaphore
         */
    public static Semaphore mutex2 = new Semaphore(0);

    /**
     * Task2:Add Semaphore here 'mutex' = 1.
     ****************************************************************************
     */

          // The main()
          public static void main(String[] argv)
          {
                    // Some initial stats...
                    try
                    {
                              System.out.println("Main thread starts executing.");
                              System.out.println("Initial value of top = " + stack.getTop() + ".");
                              System.out.println("Initial value of stack top = " + stack.pick() + ".");
                              System.out.println("Main thread will now fork several threads.");
                    }
                    catch(CharStackEmptyException e)
                    {
                              System.out.println("Caught exception: StackCharEmptyException");
                              System.out.println("Message : " + e.getMessage());
                              System.out.println("Stack Trace : ");
                              e.printStackTrace();
                     }
                    /*
                   * The birth of threads
                    */
                   Consumer ab1 = new Consumer();
                   Consumer ab2 = new Consumer();
                   System.out.println ("Two Consumer threads have been created.");
                  Producer rb1 = new Producer();
                  Producer rb2 = new Producer();
                  System.out.println ("Two Producer threads have been created.");
                  CharStackProber csp = new CharStackProber();
                  System.out.println ("One CharStackProber thread has been created.");
                  /*
                 * start executing
                  */
                 ab1.start();
                 rb1.start();
                 ab2.start();
                 rb2.start();
                 csp.start();
                 /*
                  * Wait by here for all forked threads to die
                 */
                try
                {
                           ab1.join();
                           ab2.join();
                           rb1.join();
                           rb2.join();
                          csp.join();
                          // Some final stats after all the child threads terminated...
                          System.out.println("System terminates normally.");
                          System.out.println("Final value of top = " + stack.getTop() + ".");
                          System.out.println("Final value of stack top = " + stack.pick() + ".");
                          System.out.println("Final value of stack top-1 = " + stack.getAt(stack.getTop() - 1) + ".");
                          //System.out.println("Stack access count = " + stack.getAccessCounter());
                }
               catch(InterruptedException e)
               {
                      System.out.println("Caught InterruptedException: " + e.getMessage());
                           System.exit(1);
               }
              catch(Exception e)
              {
                           System.out.println("Caught exception: " + e.getClass().getName());
                           System.out.println("Message : " + e.getMessage());
                          System.out.println("Stack Trace : ");
                          e.printStackTrace();
               }
        } // main()
        /*
        * Inner Consumer thread class
        */
        static class Consumer extends BaseThread
        {
                 private char copy; // A copy of a block returned by pop()
                 public void run()
                 {
                	 
                              System.out.println ("Consumer thread [TID=" + this.iTID + "] starts executing.");
                              for (int i = 0; i < StackManager.iThreadSteps; i++)  
                              {
                                  /**
                                   * Add Consumer code here for Task2
                                   ****************************************************************************
                                   */
                            	  try {
                                      /**
                                       * Task4, using double mutex here
                                       */
                                      mutex2.Wait(this.iTID);
                                      /**
                                       * Task4, using double mutex here
                                       */
                                      //Acquire mutex, when mutex = 1, continue
                            		  mutex.Wait(this.iTID);
                                      //Pops out the item from top of the stack
                            		  this.copy = stack.pop();
                            	  }catch (CharStackEmptyException em) {
                            		      em.printStackTrace();
                            	  }catch (Exception e) {
                            		      e.printStackTrace();
                            	  }
                                  //This ensures that the finally block is executed even if an unexpected exception occurs.
                            	  finally {
                                      System.out.println("Consumer thread [TID=" + this.iTID + "] pops character =" + this.copy);
                                      mutex.Signal();
                                      /**
                                       * Task4, using double mutex here
                                       */
                                      mutex2.Signal();
                                      
                                  }
                              }
                                 /**
                                  * End Consumer code here for Task2
                                  ****************************************************************************
                                  */
                              System.out.println ("Consumer thread [TID=" + this.iTID + "] terminates.");
                 }
          } // class Consumer
           /*
          * Inner class Producer
           */
          static class Producer extends BaseThread
          {
                     private char block; // block to be,. returned
                      /**
                       * Task4, using CountTime int here to record the run time
                       */
                    public static int CountTime;

                     public void run()
                     {
                                System.out.println ("Producer thread [TID=" + this.iTID + "] starts executing.");
                         //Loop three times iThreadSteps = 3
                                for (int i = 0; i < StackManager.iThreadSteps; i++) {
                                    /**
                                     * Add producer code here for Task2
                                     ****************************************************************************
                                     */
                                    try {
                                        //Acquire mutex, when mutex = 1, continue
                                        mutex.Wait(this.iTID);
                                        //Check for the character on the top of the stack
                                        char top = stack.pick();
                                        //Get next higher character to push into the stack
                                        this.block = (char) (top + 1);
                                        //Push it into CharStack
                                        stack.push(this.block);
                                    }

                                    /**catch (CharStackEmptyException a) {
                                     //When Stack is empty, the initial latter 'a' should be input to the stack.
                                     this.block = 'a';
                                     try { stack.push(this.block);}//Push 'a' into stack as initialize
                                     //When
                                     catch (CharStackFullException f) {
                                     f.printStackTrace();
                                     }
                                     }*/

                                    //When Stack is full, catch exception
                                    catch (CharStackFullException f) {
                                        f.printStackTrace();
                                    }
                                    //When Stack has exception, catch and trace that exception
                                    catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    //This ensures that the finally block is executed even if an unexpected exception occurs.
                                    finally {
                                        System.out.println("Producer thread [TID=" + this.iTID + "] pushes character =" + this.block);

                                        /**
                                         * Task4, Counting run time here
                                         */
                                        this.CountTime++;
                                        if (this.CountTime == 2 * StackManager.iThreadSteps) {
                                            mutex.Signal();
                                            mutex2.Signal();
                                        } else {
                                            mutex.Signal();
                                        }

                                        /**
                                         * Task4, using double mutex here
                                         */
                                    }
                                    /**
                                     * End of producer code modification for Task2
                                     ****************************************************************************
                                     */
                                }
                                     System.out.println("Producer thread [TID=" + this.iTID + "] terminates.");
                     }
          } // class Producer
            /*
           * Inner class CharStackProber to dump stack contents
            */
           static class CharStackProber extends BaseThread
           {
                     public void run()
                     {
                               System.out.println("CharStackProber thread [TID=" + this.iTID + "] starts executing.");
                               for (int i = 0; i < 2 * StackManager.iThreadSteps; i++)
                               {
                                   /**
                                    * Add CharStackProber code here for Task2
                                    ****************************************************************************
                                    */
                            	   try {
                            	       mutex.Wait(this.iTID);
                            	       String s = "";
                            	       s += "Stack S = (";
                            	       for (int p = 0; p < stack.getSize(); p++)
                            	       {
                            	           //Generate the format ([*],[*]) repeatably
                            	           if (p == (stack.getSize() -1)) {
                            	    		   s += "["+stack.getAt(p)+"])";
                            	    	   } else {
                            	    		   s += "["+stack.getAt(p)+"],";
                            	    	   } 
                            		   }
                            		   //Print out the Stack S = ([*],........)
                            	       System.out.println(s);
                            	   } catch (CharStackInvalidAccessException ie) {
                            		   ie.printStackTrace();
                            	   }
                                   //This ensures that the finally block is executed even if an unexpected exception occurs.
                            	   finally {
                            	       mutex.Signal();
                            	   }
                                   /**
                                    * End CharStackProber code here for Task2
                                    ****************************************************************************
                                    */
                              }          
                     }//***********************************
            
           } // class CharStackProber
} // class StackManager
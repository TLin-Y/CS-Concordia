// -----------------------------------------------------
// Assignment 2
// COMP 352
// Written by: Dmitry Shilchikov #27265123
// -----------------------------------------------------
import java.io.*;
import java.util.*;

/**
 * Tianlin Yang
 * 40010303
 * Haituan Zhuo
 * 40080732
 * Reference:
 * 1 https://docs.oracle.com/javase/7/docs/api/java/util/List.html
 * 2 https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
 * 3 https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * 4 http://www.cs.nuim.ie/~tlysaght/CS210/code/LinkedLists/ListTester.java
 * 5 https://github.com/eclipse/jubula.core/blob/master/org.eclipse.jubula.rc.common/src/org/eclipse/jubula/rc/common/tester/ListTester.java
 */

public class ListTester {

	public static void main(String[] args) throws FileNotFoundException //Fix file issue
	{
		/**
		 * Define Java original list
		 */
		ArrayList<Integer> arrayJava = new ArrayList<>();
		LinkedList<Integer> linkedJava = new LinkedList<>();

		/**
		 * Define My list method
		 */
		MyArrayList<Integer> myArray = new MyArrayList<>();
		MyLinkedList<Integer> myLinked = new MyLinkedList<>();

		/**
		 * Data from assignment2 program part
		 * All format same
		 */
		final int [] N = {10, 100, 1000, 10000, 100000, 1000000};
		Random randomNum = new Random();
		String inStart = "Insert@start (ms)",inEnd = "Insert@end (ms)",
				inRan = "Insert@random (ms)";
		String remStart = "Remove@start (ms)", remEnd = "Remove@end (ms)",
				remRan = "Remove@random (ms)", remVal = "Remove byvalue (ms)";
		/**
		 * Out put .txt file named 'testrun' in root of project by IDEA
		 */
		File file = new File("testrun.txt");
		PrintWriter out= new PrintWriter(new FileOutputStream(file));

/**
 * Test begin
  */
		for (int j=0; j<4;j++)
		{
			out.printf(" N = %d  \t\t| %-5s  | %-5s| %-5s  | %-5s    | %-5s| %-5s  | %-5s  |",
					N[j], inStart,remStart, inEnd, remEnd, inRan, remRan, remVal);
			out.println("");
			out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

/**
 * ArrayJava to insert at beginning
 */
			long startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				arrayJava.add(0,(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			long endTime = System.currentTimeMillis();	
			out.printf(" ArrayList\t\t|%-5d \t\t     |",(endTime-startTime));

/**
 * ArrayJava to remove at beginning
 */
			startTime = System.currentTimeMillis();
			while(arrayJava.size()!=0)
				arrayJava.remove(0);
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * ArrayJava to insert at end
 */
			arrayJava.clear();
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				arrayJava.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t   |",(endTime-startTime));

/**
 * ArrayJava to remove at end
 */
			startTime = System.currentTimeMillis();
			while(arrayJava.size()!=0)
				arrayJava.remove(arrayJava.size()-1);
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));


/**
 * ArrayJava to insert at random
 */
			for(int i=0; i<10; i++)
				arrayJava.add((int)((Math.random() * ((N[j] - 0) + 1)) + 0));
			//since i emptied the array, i had to fill it again in order to insert at random
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				arrayJava.add((int)((Math.random() * ((10 - 0) + 1)) + 0),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));

/**
 * ArrayJava to remove at random
 */
			startTime = System.currentTimeMillis();
			while(arrayJava.size()!=0)
				arrayJava.remove(randomNum.nextInt(arrayJava.size()));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * ArrayJava to remove at value
 */
			for(int i=0; i<10; i++)
				arrayJava.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			for(int i=0; i<N[j]; i++)
				arrayJava.add((int)((Math.random() * ((10 - 0) + 1)) + 0),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			//since i emptied the array, i had to fill it again in order to remove at value
			startTime = System.currentTimeMillis();
			while(arrayJava.size()!=0)
					arrayJava.remove((Object)(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));
			
			out.println("");
			out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");


/**
 * LinkedJava to insert at beginning
 */
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				linkedJava.add(0,(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf(" LinkedList\t\t|%-5d \t\t     |",(endTime-startTime));

/**
 * LinkedJava to remove at beginning
 */
			startTime = System.currentTimeMillis();
			while(linkedJava.size()!=0)
				linkedJava.remove(0);
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * LinkedJava to insert at end
 */
			linkedJava.clear();
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				linkedJava.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t   |",(endTime-startTime));

/**
 * LinkedJava to remove at end
 */
			startTime = System.currentTimeMillis();
			while(linkedJava.size()!=0)
				linkedJava.remove(linkedJava.size()-1);
			endTime = System.currentTimeMillis();	
			out.printf("%-5d\t\t|",(endTime-startTime));

/**
 * LinkedJava to insert at random
 */
			for(int i=0; i<N[j]; i++)
				linkedJava.add(0,(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			//since i emptied the linked list, i had to fill it again in order to insert at random
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				linkedJava.add(randomNum.nextInt(10),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));

/**
 * LinkedJava to remove at random
 */
			startTime = System.currentTimeMillis();
			while(linkedJava.size()!=0)
				linkedJava.remove(randomNum.nextInt(linkedJava.size()));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * LinkedJava to remove at value
 */
			for(int i=0; i<10; i++)
				linkedJava.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			for(int i=0; i<N[j]; i++)
				linkedJava.add((int)((Math.random() * ((10 - 0) + 1)) + 0),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			//Refill the empty
			startTime = System.currentTimeMillis();
			while (linkedJava.size() != 0)
					linkedJava.remove((Object)(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));

			/**
			 * drawing frame table
			 */
			out.println("");
			out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

/**
 * MyArray to insert at beginning
 */
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				myArray.add(0,(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf(" MyArrayList\t\t|%-5d \t\t     |",(endTime-startTime));

/**
 * MyArray to remove at beginning
 */
			startTime = System.currentTimeMillis();
			while(myArray.size()!=0)
				myArray.remove(0);
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * MyArray to insert at end
 */
			myArray.clear();
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				myArray.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t   |",(endTime-startTime));

/**
 * MyArray to remove at end
 */
			startTime = System.currentTimeMillis();
			while(myArray.size()!=0)
				myArray.remove(myArray.size()-1);
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * MyArray to insert at random
 */
			for(int i=0; i<N[j]; i++)
				myArray.add(0,(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			//since i emptied the array, i had to fill it again in order to insert at random
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				myArray.add(randomNum.nextInt(10),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));

/**
 * MyArray to remove at random
 */
			startTime = System.currentTimeMillis();
			while(myArray.size()!=0)
				myArray.remove(randomNum.nextInt(myArray.size()));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * MyArray to remove at value
 */
			for(int i=0; i<10; i++)
				myArray.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			for(int i=0; i<N[j]; i++)
				myArray.add((int)((Math.random() * ((10 - 0) + 1)) + 0),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			//since i emptied the array, i had to fill it again in order to remove at random
			startTime = System.currentTimeMillis();
			while(myArray.size()!=0)
					myArray.remove((Object)(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));
			
			out.println("");
			out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

/**
 * MyArrayList to insert at beginning
 */
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				myLinked.add(0,(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf(" MyLinkedList\t\t|%-5d \t\t     |",(endTime-startTime));

/**
 * MyArrayList to remove at beginning
 */
			startTime = System.currentTimeMillis();
			while(myLinked.size()!=0)
				myLinked.remove(1);
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * MyArrayList to insert at end
 */
			myLinked.clear();
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				myLinked.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t   |",(endTime-startTime));

/**
 * MyArrayList to remove at end
 */
			startTime = System.currentTimeMillis();
			while(myLinked.size()!=0)
				myLinked.remove(myLinked.size());
			endTime = System.currentTimeMillis();	
			out.printf("%-5d\t\t|",(endTime-startTime));

/**
 * MyArrayList to insert at random
 */
			for(int i=0; i<N[j]; i++)
				myLinked.add(0,(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			//since i emptied the linked list, i had to fill it again in order to insert at random
			startTime = System.currentTimeMillis();
			for(int i=0; i<N[j]; i++)
				myLinked.add(randomNum.nextInt(10),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));

/**
 * MyArrayList to remove at random
 */
			startTime = System.currentTimeMillis();
			while(myLinked.size()!=0)
				myLinked.remove(randomNum.nextInt(myLinked.size()));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t|",(endTime-startTime));

/**
 * MyArrayList to remove at value
 */
			for(int i=0; i<10; i++)
				myLinked.add((int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			for(int i=0; i<N[j]; i++)
				myLinked.add((int)((Math.random() * ((10 - 0) + 1)) + 0),(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			//since i emptied the linked list, i had to fill it again in order to remove at value
			startTime = System.currentTimeMillis();
			while (myLinked.size() != 0)
					myLinked.remove((Object)(int)((Math.random() * ((2*N[j] - 0) + 1)) + 0));
			endTime = System.currentTimeMillis();	
			out.printf("%-5d \t\t    |",(endTime-startTime));
			
			out.println("");
			out.println("");
			out.println("");
		}//end of insertion test run
		
		
		
		out.close();
	}

}

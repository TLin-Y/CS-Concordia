import java.util.Stack;
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


public class Test
{

	public static void multipleOf(int[]a , int x)
	{
		System.out.println("Regular implementation\n----------------------------------------------------------------");
		System.out.println("The elements of the array A that are multiple of "+x+" are:");
		for (int i =0; i<a.length;i++)
		{
			if(a[i]%x==0 && !(a[i]==0))
				System.out.println("Index "+i+" with value "+ a[i]);
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static void multipleOfStack(int []a, int x)
	{
		Stack check = new Stack();
		System.out.println("Stack implementation\n----------------------------------------------------------------");
		System.out.println("The elements of the array A that are multiple of "+x+" are:");
		for (int i =0; i<a.length;i++)
		{
			if(a[i]%x==0 && !(a[i]==0))
				check.push(a[i]);
		}
		while (!(check.isEmpty()))
		{
			System.out.println(check.pop());
		}
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int [] B;
	public static int[] mySolution (int[]a ,int n)
	{
		int [] res = new int[n];
		B= new int[n];
		for (int i=0; i<n-1;i++)
			res[i]=0;
		for (int i=0; i<n-2; i++)
		{
			for (int j=i+1; j<n-1;j++)
			{
				if (a[i]<=a[j])
					res[j]+=1;
				else
					res[i]+=1;
			}
		}
		for (int i=0; i<n-1;i++)
		{
			B[res[i]]=a[i];
		}
		return B;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static void main(String[] args)
	{
		int [] a = {22,61,-10,21,0,9,50,17,35,81,-46,19,5,77,1};
		int[] aa = {88,12,94,17,2,36,69};
		
		/*multipleOf(a, 2);
		System.out.println("done");
		multipleOfStack(a, 2);
		System.out.println("done");
		mySolution(aa,7);
		System.out.println("done");
		*/
		MyArrayList<String> myArray =  new MyArrayList();
		MyArrayList<String> myArray2 =  new MyArrayList(20);
		
		String element = "A";


		
		MyLinkedList<Integer> myLinked = new MyLinkedList();
		for(int i=0; i<10; i++)
			myLinked.add(element+i);
		System.out.println(myLinked);
		int i=0;
		/*while(myLinked.size()!=0)
		{
			//myLinked.remove();
			System.out.println(myLinked);
			i++;
		}*/
		
		
		
		
		

	}

}

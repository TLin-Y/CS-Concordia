/**
 * Tianlin Yang
 * 40010303
 * Haitun Liao
 * 40080732
 * Reference:
 * 1 https://docs.oracle.com/javase/7/docs/api/java/util/List.html
 * 2 https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
 * 3 https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * 4 http://www.cs.nuim.ie/~tlysaght/CS210/code/LinkedLists/ListTester.java
 * 5 https://github.com/eclipse/jubula.core/blob/master/org.eclipse.jubula.rc.common/src/org/eclipse/jubula/rc/common/tester/ListTester.java
 */

import java.util.Comparator;

public class JobComparator implements Comparator<Integer> {
    //TODO WTF is going on here
    public int compare(Integer a, Integer b) {

        if(a > b) {
            return 1;
        }
        else if(a == b) {
            if(a > b) {
                return 1;
            }
            else if(a == b) {
                return 0;
            }
            else {
                return -1;
            }
        }
        else {
            return -1;
        }

    }

}

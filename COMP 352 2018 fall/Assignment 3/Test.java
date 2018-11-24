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


public class Test {
    /**
     * Array heap
     * @param args
     */
    public static void main(String[] args) {

        long startTime;
        long endTime;
        long totalTime;
        long priorityChanges = 0;
        long averageWaitTime = 0;
        long cycleCount = 0;
        int n = 100;

        ArrayHeap<Integer, Job> pq = new ArrayHeap<>(new JobComparator());
        Job[] jobs = new Job[n];
        for(int i = 0; i < n; i++) {
            jobs[i] = new Job();
        }
        startTime = System.currentTimeMillis();
        for(int i = 0; i < n; i++) {
            pq.insert(jobs[i]);
            jobs[i].setEntryTime(i + 1);
        }
        while(!pq.isEmpty()) {
            Job j = (Job) pq.removeMin();
            j.setCurrentLength(j.getCurrentLength() - 1);
            cycleCount += 1;
            System.out.println(j);
            System.out.println();
            if(j.getCurrentLength() != 0) {
                j.setLastRun(cycleCount);
                pq.insert(j);
//                if(cycleCount % 30 == 0) {
//                    //TODO implement findOldest
//                    Job oldest = pq.findOldest();
//                    oldest.setJobPriority(1);
//                    pq.insert(oldest);
//                    priorityChanges++;
//                }
            }
            else {
                j.setEndTime(cycleCount);
                j.setWaitTime(j.getEndTime() - j.getJobLength() - j.getEntryTime());
                averageWaitTime += j.getWaitTime();
//                if(cycleCount % 30 == 0) {
//                    //TODO implement findOldest
//                    Job oldest = pq.findOldest();
//                    oldest.setJobPriority(1);
//                    pq.insert(oldest);
//                    priorityChanges++;
//                }
            }
        }
        averageWaitTime /= n;
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;

        System.out.println("Current system time (cycles): " + cycleCount);
        System.out.println("Total number of jobs executed: " + n + " jobs");
        System.out.println("Average process waiting time: " + averageWaitTime + " cycles");
        System.out.println("Total number of priority changes: " + priorityChanges);
        System.out.println("Actual system time needed to execute all jobs: " + totalTime + " ms");

    }

}

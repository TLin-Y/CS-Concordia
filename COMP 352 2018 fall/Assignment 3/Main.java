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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
/**
 * Initial the values
 */
        long startTime;
        long endTime;
        long totalTime;
        long priorityChanges = 0;
        long averageWaitTime = 0;
        long cycleCount = 0;
        int n = 10000;

/**
 * Heap PQ
 */
        try (PrintWriter fout = new PrintWriter("out.txt")) {
            fout.println();
            fout.println("ArrayHeap Simulation");
            fout.println();
            for (int i = 100; i <= n; i *= 10) {
                Job[] jobs = new Job[i];
                ArrayHeap<Integer, Job> pq = new ArrayHeap<>(new JobComparator());
                for (int j = 0; j < jobs.length; j++) {
                    jobs[j] = new Job();
                }
                Job.resetCounter();
                startTime = System.currentTimeMillis();
                for (int j = 0; j < i; j++) {
                    pq.insert(jobs[j]);
                    jobs[j].setEntryTime(j + 1);
                }
                while (!pq.isEmpty()) {
                    Job j = (Job) pq.removeMin();
                    j.setCurrentLength(j.getCurrentLength() - 1);
                    cycleCount += 1;
                    System.out.println(j);
                    System.out.println();
                    if (j.getCurrentLength() != 0) {
                        j.setLastRun(cycleCount);
                        pq.insert(j);
//                    if(cycleCount % 30 == 0) {
//                        //TODO implement findOldest
//                        Job oldest = pq.findOldest();
//                        oldest.setJobPriority(1);
//                        pq.insert(oldest);
//                        priorityChanges++;
//                    }
                    } else {
                        j.setEndTime(cycleCount);
                        j.setWaitTime(j.getEndTime() - j.getJobLength() - j.getEntryTime());
                        averageWaitTime += j.getWaitTime();
//                    if(cycleCount % 30 == 0) {
//                        //TODO implement findOldest
//                        Job oldest = pq.findOldest();
//                        oldest.setJobPriority(1);
//                        pq.insert(oldest);
//                        priorityChanges++;
//                    }
                    }
                }
                averageWaitTime /= i;
                endTime = System.currentTimeMillis();
                totalTime = endTime - startTime;

                fout.println("Current system time (cycles): " + cycleCount);
                fout.println("Total number of jobs executed: " + i + " jobs");
                fout.println("Average process waiting time: " + averageWaitTime + " cycles");
                fout.println("Total number of priority changes: " + priorityChanges);
                fout.println("Actual system time needed to execute all jobs: " + totalTime + " ms");
                fout.println();

                priorityChanges = 0;
                averageWaitTime = 0;
                cycleCount = 0;

            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

/**
 * Sorted PQ
 */
        try (PrintWriter fout = new PrintWriter( new FileOutputStream( new File("out.txt"), true))) {
            fout.println();
            fout.println("Sorted Priority Queue Simulation:");
            fout.println();
            for (int i = 100; i <= n; i *= 10) {
                Job[] jobs = new Job[i];
                SortedPriorityQueue<Integer, Job> pqSorted = new SortedPriorityQueue<>(new JobComparator());
                for (int j = 0; j < jobs.length; j++) {
                    jobs[j] = new Job();
                }
                Job.resetCounter();
                startTime = System.currentTimeMillis();
                for (int j = 0; j < i; j++) {
                    pqSorted.insert(jobs[j]);
                    jobs[j].setEntryTime(j + 1);
                }
                while (!pqSorted.isEmpty()) {
                    Job j = (Job) pqSorted.removeMin();
                    j.setCurrentLength(j.getCurrentLength() - 1);
                    cycleCount += 1;
                    System.out.println(j);
                    System.out.println();
                    if (j.getCurrentLength() != 0) {
                        j.setLastRun(cycleCount);
                        pqSorted.insert(j);
//                    if(cycleCount % 30 == 0) {
//                        //TODO implement findOldest
//                        Job oldest = pq.findOldest();
//                        oldest.setJobPriority(1);
//                        pq.insert(oldest);
//                        priorityChanges++;
//                    }
                    } else {
                        j.setEndTime(cycleCount);
                        j.setWaitTime(j.getEndTime() - j.getJobLength() - j.getEntryTime());
                        averageWaitTime += j.getWaitTime();
//                    if(cycleCount % 30 == 0) {
//                        //TODO implement findOldest
//                        Job oldest = pq.findOldest();
//                        oldest.setJobPriority(1);
//                        pq.insert(oldest);
//                        priorityChanges++;
//                    }
                    }
                }
                averageWaitTime /= i;
                endTime = System.currentTimeMillis();
                totalTime = endTime - startTime;

                fout.println("Current system time (cycles): " + cycleCount);
                fout.println("Total number of jobs executed: " + i + " jobs");
                fout.println("Average process waiting time: " + averageWaitTime + " cycles");
                fout.println("Total number of priority changes: " + priorityChanges);
                fout.println("Actual system time needed to execute all jobs: " + totalTime + " ms");
                fout.println();

                priorityChanges = 0;
                averageWaitTime = 0;
                cycleCount = 0;

            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

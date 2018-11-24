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

import java.util.Random;

public class Job<K,V> implements Entry<Integer, String> {
    private K key;
    private V value;
    private String jobName;
    private int jobLength;
    private int currentLength;
    private int jobPriority;
    private int finalPriority;
    private long entryTime;
    private long endTime;
    private long waitTime;
    //Second Key to compare when 2 jobs have the same priority
    private long lastRun;
    //Keep track of the number of jobs created to name the jobs accordingly
    private static int jobCount;

    private static final int MAXJOBLENGTH = 70;
    private static final int MAXJOBPRIORITY = 40;

    public Job() {
        Random random = new Random();
        jobName = "Job_" + (jobCount++ + 1);
        jobLength = random.nextInt( MAXJOBLENGTH + 1) + 1;
        currentLength = jobLength;
        jobPriority = random.nextInt(MAXJOBPRIORITY +1) + 1;
        finalPriority = jobPriority;
        entryTime = 0;
        endTime = 0;
        waitTime = 0;
        lastRun = 0;
    }

    public static void resetCounter() {
        jobCount = 0;
    }

    @Override
    public String toString() {
        return "Now executing " + jobName + ". " + "Job Length: " + jobLength + " cycles, " +
                "Current remaining length: " + currentLength + ", cycles," + "\nInitial Priority: " +
                jobPriority + ", Current Priority: " + finalPriority;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getJobLength() {
        return jobLength;
    }

    public void setJobLength(int jobLength) {
        this.jobLength = jobLength;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public int getJobPriority() {
        return jobPriority;
    }

    public void setJobPriority(int jobPriority) {
        this.jobPriority = jobPriority;
    }

    public int getFinalPriority() {
        return finalPriority;
    }

    public void setFinalPriority(int finalPriority) {
        this.finalPriority = finalPriority;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getWaitTime() {
        waitTime = endTime - entryTime - jobLength;
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public long getLastRun() {
        return lastRun;
    }

    public void setLastRun(long lastRun) {
        this.lastRun = lastRun;
    }

    public static int getJobCount() {
        return jobCount;
    }

    public static void setJobCount(int jobCount) {
        Job.jobCount = jobCount;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return jobPriority;
    }

    @Override
    public String getValue() {
        return jobName;
    }

}

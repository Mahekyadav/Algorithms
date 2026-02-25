import java.util.*;

class Job {
    int id, deadline, profit;

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {
    
    public static void main(String[] args) {
        // Initializing data
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1, 2, 100));
        jobs.add(new Job(2, 1, 50));
        jobs.add(new Job(3, 2, 10));
        jobs.add(new Job(4, 1, 20));
        jobs.add(new Job(5, 3, 30));

        System.out.println("--- Unsorted Jobs ---");
        printJobs(jobs);

        // 1. Sort jobs based on profit in descending order
        // We use a lambda expression for the comparator
        jobs.sort((a, b) -> b.profit - a.profit);

        System.out.println("--- Sorted Jobs (Descending Profit) ---");
        printJobs(jobs);

        // 2. Find max deadline to determine schedule size
        int maxDeadline = 0;
        for (Job j : jobs) {
            if (j.deadline > maxDeadline) maxDeadline = j.deadline;
        }

        // 3. Initialize schedule array with -1 (empty)
        int[] schedule = new int[maxDeadline];
        Arrays.fill(schedule, -1);

        int totalProfit = 0;
        int countJobs = 0;

        // 4. Sequence the jobs
        for (Job j : jobs) {
            // Try to place job at its latest possible slot (deadline - 1)
            for (int d = j.deadline - 1; d >= 0; d--) {
                if (schedule[d] == -1) {
                    schedule[d] = j.id;
                    totalProfit += j.profit;
                    countJobs++;
                    break;
                }
            }
        }

        // Output results
        System.out.println("--- Final Transaction Schedule ---");
        System.out.println("Schedule (Time Slots): " + Arrays.toString(schedule));
        System.out.println("Total Profit: " + totalProfit);
        System.out.println("Total Jobs Scheduled: " + countJobs);
    }

    public static void printJobs(List<Job> jobs) {
        System.out.println("ID\tProfit\tDeadline");
        for (Job j : jobs) {
            System.out.println("T" + j.id + "\t" + j.profit + "\t" + j.deadline);
        }
        System.out.println();
    }
}
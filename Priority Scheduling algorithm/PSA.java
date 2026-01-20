import java.util.*;

class Process {
    int at;   // Arrival Time
    int bt;   // Burst Time
    int pr;   // Priority
    int pno;  // Process Number

    Process(int at, int bt, int pr, int pno) {
        this.at = at; 
        this.bt = bt;
        this.pr = pr;
        this.pno = pno;
    }
}

public class Solution {

    // Sort by arrival time, then by priority
    static int comp(Process a, Process b) {
        if (a.at == b.at) { 
            return Integer.compare(a.pr, b.pr);
        }
        return Integer.compare(a.at, b.at);
    }

    // Main scheduling logic
    static void findgc(int n, Process[] proc) {

        int[] start = new int[n];
        int[] complete = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];

        double avgWT = 0, avgTAT = 0;

        // First process
        start[0] = proc[0].at;
        complete[0] = start[0] + proc[0].bt;
        tat[0] = complete[0] - proc[0].at;
        wt[0] = start[0] - proc[0].at;

        // Remaining processes
        for (int i = 1; i < n; i++) {
            start[i] = Math.max(complete[i - 1], proc[i].at);
            complete[i] = start[i] + proc[i].bt;
            tat[i] = complete[i] - proc[i].at;
            wt[i] = start[i] - proc[i].at;
        }

        System.out.println("Process_no\tStart_time\tComplete_time\tTurn_Around_Time\tWaiting_Time");

        for (int i = 0; i < n; i++) {
            avgWT += wt[i];
            avgTAT += tat[i];

            System.out.println(
                proc[i].pno + "\t\t" +
                start[i] + "\t\t" +
                complete[i] + "\t\t" +
                tat[i] + "\t\t\t" +
                wt[i]
            );
        }

        avgWT /= n;
        avgTAT /= n;

        System.out.printf("Average waiting time is : %.2f\n", avgWT);
        System.out.printf("average turnaround time : %.2f\n", avgTAT);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] at = new int[n];
        int[] bt = new int[n];
        int[] pr = new int[n];

        for (int i = 0; i < n; i++) at[i] = sc.nextInt();
        for (int i = 0; i < n; i++) bt[i] = sc.nextInt();
        for (int i = 0; i < n; i++) pr[i] = sc.nextInt();

        Process[] proc = new Process[n];

        for (int i = 0; i < n; i++) {
            proc[i] = new Process(at[i], bt[i], pr[i], i + 1);
        }

        Arrays.sort(proc, Solution::comp);

        findgc(n, proc);
        sc.close();
    }
}

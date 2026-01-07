import java.util.Scanner;
public class Solution {
    
    // Correct method name: findavgTime (lowercase 'a')
    static void findavgTime(int[] processes, int n, int[] burstTime) { // Calculate average waiting and turnaround times
        int[] waitingTime = new int[n]; // Array to store waiting times
        int[] turnaroundTime = new int[n]; // Array to store turnaround times
        
        // Waiting time for first process is 0
        waitingTime[0] = 0; // First process has no waiting time
        
        // Calculate waiting time
        for (int i = 1; i < n; i++) { // Start from second process
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1]; // Waiting time is sum of previous waiting time and previous burst time
        }
        
        // Calculate turnaround time
        for (int i = 0; i < n; i++) { // For all processes
            turnaroundTime[i] = waitingTime[i] + burstTime[i]; // Turnaround time is waiting time plus burst time
        }
        
        // Calculate averages
        double avgWaitingTime = 0, avgTurnaroundTime = 0; // Use double for accurate average calculation
        for (int i = 0; i < n; i++) { // Sum up waiting and turnaround times
            avgWaitingTime += waitingTime[i]; // Sum up waiting times
            avgTurnaroundTime += turnaroundTime[i]; // Sum up turnaround times
        }
        avgWaitingTime /= n; // Calculate average waiting time
        avgTurnaroundTime /= n;  // Calculate average turnaround time
        
        System.out.printf("Average waiting time = %.2f%n", avgWaitingTime); // Print average waiting time
        System.out.printf("Average turnaround time = %.2f%n", avgTurnaroundTime); // Print average turnaround time
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of processes
        int n = sc.nextInt();

        int[] processes = new int[n];
        int[] burst_time = new int[n];

        // Input burst times
        for (int i = 0; i < n; i++) {
            burst_time[i] = sc.nextInt();
            processes[i] = i + 1; // Process IDs are 1, 2, 3, ...
        }

        findavgTime(processes, n, burst_time);
        sc.close();
    }
}
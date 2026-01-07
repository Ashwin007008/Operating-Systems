#include <iostream>
#include <iomanip> // Required for setprecision

using namespace std;

// Function to calculate average waiting and turnaround times
void findavgTime(int processes[], int n, int bt[]) {
    int waitingTime[n]; // Array to store waiting times
    int turnaroundTime[n]; // Array to store turnaround times

    // Waiting time for first process is 0
    waitingTime[0] = 0; // First process has no waiting time

    // Calculate waiting time
    for (int i = 1; i < n; i++) { // Start from second process
        waitingTime[i] = waitingTime[i - 1] + bt[i - 1]; // Waiting time is sum of previous waiting time and previous burst time
    }

    // Calculate turnaround time
    for (int i = 0; i < n; i++) { // For all processes
        turnaroundTime[i] = waitingTime[i] + bt[i]; // Turnaround time is waiting time plus burst time
    }

    // Calculate totals
    double totalWaitingTime = 0;  // Use double for accurate average calculation
    double totalTurnaroundTime = 0;  // Use double for accurate average calculation

    for (int i = 0; i < n; i++) {
        totalWaitingTime += waitingTime[i]; // Sum up waiting times
        totalTurnaroundTime += turnaroundTime[i];  // Sum up turnaround times
    }

    // Calculate averages
    double avgWaitingTime = totalWaitingTime / n;  // Average waiting time
    double avgTurnaroundTime = totalTurnaroundTime / n; // Average turnaround time

    cout << fixed << setprecision(2); // Set precision for output
    cout << "Average waiting time = " << avgWaitingTime << endl;  // Print average waiting time
    cout << "Average turnaround time = " << avgTurnaroundTime << endl; // Print average turnaround time
}

int main() {
    int n;
    cin >> n; // Read number of processes

    // Using pointers/arrays as requested in your snippet
    // Note: Standard C++ prefers vectors, but this matches your template structure
    int *processes = new int[n];
    int *burst_time = new int[n];

    for (int i = 0; i < n; i++) {
        processes[i] = i + 1; // Assign process IDs
        cin >> burst_time[i]; // Read burst times
    }

    findavgTime(processes, n, burst_time);

    // Clean up memory
    delete[] processes;
    delete[] burst_time;

    return 0;
}
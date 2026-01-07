def find_avg_time(processes, n, burst_time):
    waiting_time = [0] * n # Initialize waiting time array
    turnaround_time = [0] * n # Initialize turnaround time array

    # Waiting time for first process is 0
    waiting_time[0] = 0 # First process has no waiting time

    # Calculate waiting time
    for i in range(1, n): # Start from second process
        waiting_time[i] = waiting_time[i - 1] + burst_time[i - 1] # Waiting time is sum of previous waiting time and previous burst time

    # Calculate turnaround time
    for i in range(n): # For all processes
        turnaround_time[i] = waiting_time[i] + burst_time[i] # Turnaround time is waiting time plus burst time

    avg_waiting_time = sum(waiting_time) / n # Calculate average waiting time
    avg_turnaround_time = sum(turnaround_time) / n # Calculate average turnaround time

    print(f"Average waiting time = {avg_waiting_time:.2f}") # Print average waiting time
    print(f"Average turnaround time = {avg_turnaround_time:.2f}") # Print average turnaround time
if __name__ == "__main__": # Main function
    n = int(input()) # Number of processes
    processes = list(range(1, n + 1)) # Process IDs
    burst_time = list(map(int, input().split())) # Burst times

    find_avg_time(processes, n, burst_time) # Call function to find average times
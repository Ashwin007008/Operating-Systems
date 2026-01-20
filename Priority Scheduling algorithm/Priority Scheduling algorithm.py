class Process:
    def __init__(self, at, bt, pr, pno):
        self.at = at  # Arrival time
        self.bt = bt  # Burst time
        self.pr = pr  # Priority
        self.pno = pno  # Process number


def comp(a):
    return (a.at, a.pr)


def get_waiting_time(waiting_time, n, proc):
    waiting_time[0] = 0
    for i in range(1, n):
        waiting_time[i] = (waiting_time[i - 1] +
                           proc[i - 1].bt -
                           (proc[i].at - proc[i - 1].at))
        if waiting_time[i] < 0:
            waiting_time[i] = 0


def get_turn_around_time(turn_around_time, waiting_time, n, proc):
    for i in range(n):
        turn_around_time[i] = proc[i].bt + waiting_time[i]


def findgc(n, proc):
    waiting_time = [0] * n
    turn_around_time = [0] * n
    average_waiting_time = 0
    average_turnaround_time = 0
    start_time = [0] * n
    complete_time = [0] * n

    # Sort by arrival time, then priority
    proc.sort(key=comp)

    # Calculate waiting time and turnaround time
    get_waiting_time(waiting_time, n, proc)
    get_turn_around_time(turn_around_time, waiting_time, n, proc)

    # Start & Complete time
    start_time[0] = proc[0].at
    complete_time[0] = start_time[0] + proc[0].bt

    for i in range(1, n):
        start_time[i] = complete_time[i - 1]
        complete_time[i] = start_time[i] + proc[i].bt

    # Averages
    for i in range(n):
        average_waiting_time += waiting_time[i]
        average_turnaround_time += turn_around_time[i]

    average_waiting_time /= n
    average_turnaround_time /= n

    # Output
    print("Process_no\tStart_time\tComplete_time\tTurn_Around_Time\tWaiting_Time")
    for i in range(n):
        print(f"{proc[i].pno}\t\t{start_time[i]}\t\t{complete_time[i]}\t\t{turn_around_time[i]}\t\t\t{waiting_time[i]}")

    print(f"Average waiting time is : {average_waiting_time:.2f}")
    print(f"average turnaround time : {average_turnaround_time:.2f}")




def main():
    n = int(input())  # Number of processes

    # Read arrival times as a space-separated string and convert to a list of integers
    arrivaltime = list(map(int, input().split()))

    # Read burst times as a space-separated string and convert to a list of integers
    bursttime = list(map(int, input().split()))

    # Read priority values as a space-separated string and convert to a list of integers
    priority = list(map(int, input().split()))

    # Create process objects
    proc = []
    for i in range(n):
        proc.append(Process(arrivaltime[i], bursttime[i], priority[i], i + 1))

    # Sort processes by arrival time, then by priority
    proc.sort(key=lambda x: (x.at, x.pr))

    # Call the function to calculate waiting time and turnaround time
    findgc(n, proc)


if __name__ == "__main__":
    main()
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Class representing a request to go to a specific floor
class Request {
    private int floor;

    public Request(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }
}

// Class representing the Elevator
class Elevator {
    private int currentFloor;
    private Queue<Request> requestQueue;

    public Elevator() {
        this.currentFloor = 0; // Assume ground floor as 0
        this.requestQueue = new LinkedList<>();
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void addRequest(Request request) {
        requestQueue.offer(request);
        System.out.println("Request added to go to floor: " + request.getFloor());
    }

    public void moveToNextFloor() {
        if (requestQueue.isEmpty()) {
            System.out.println("No requests to process.");
            return;
        }

        Request nextRequest = requestQueue.poll();
        int targetFloor = nextRequest.getFloor();

        System.out.println("Moving from floor " + currentFloor + " to floor " + targetFloor);
        while (currentFloor != targetFloor) {
            if (currentFloor < targetFloor) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            System.out.println("Elevator at floor: " + currentFloor);
        }

        System.out.println("Reached floor: " + currentFloor);
    }
}

// Class managing the Elevator System
class ElevatorSystem {
    private Elevator elevator;

    public ElevatorSystem() {
        elevator = new Elevator();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter floor number to request the elevator (or -1 to exit): ");
            int floor = scanner.nextInt();
            if (floor == -1) {
                System.out.println("Exiting the elevator system.");
                break;
            }

            // Validate floor number
            if (floor < 0) {
                System.out.println("Invalid floor number. Please enter a valid floor.");
                continue;
            }

            elevator.addRequest(new Request(floor));
            elevator.moveToNextFloor();
        }
        scanner.close();
    }
}

// Main class to run the elevator system
public class ElevatorSimulation {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = new ElevatorSystem();
        elevatorSystem.run();
    }
}

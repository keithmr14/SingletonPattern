import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        QueueManager queueManager = QueueManager.getInstance();

        OnlineMonitor onlineMonitor = new OnlineMonitor(queueManager);
        onlineMonitor.startMonitoring();
        onlineMonitor.displayCurrentNumber();

        HelpDeskStation station1 = new HelpDeskStation("Help Desk 1");
        HelpDeskStation station2 = new HelpDeskStation("Help Desk 2");
        HelpDeskStation station3 = new HelpDeskStation("Help Desk 3");

        System.out.println("\n--- Issuing queue numbers ---");
        station1.issueQueueNumber();
        station2.issueQueueNumber();
        station3.issueQueueNumber();

        System.out.println("\n--- Resetting the queue ---");

        try(Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter the new queue number for Help Desk 2: ");
            int resetNumber = scanner.nextInt();
            station2.resetQueueNumber(resetNumber);

        } catch(IllegalArgumentException exception) {

            System.out.println("Unable to reset queue: " + exception.getMessage());
        }

        System.out.println("\n--- Issuing a number after reset ---");
        station3.issueQueueNumber();
    }
}

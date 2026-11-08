public class HelpDeskStation {

    private final String stationName;
    private final QueueManager queueManager;

    public HelpDeskStation(String stationName) {

        if(stationName == null || stationName.trim().isEmpty()) {
            throw new IllegalArgumentException("Station name cannot be empty.");
        }

        this.stationName = stationName;
        this.queueManager = QueueManager.getInstance();
    }

    public int issueQueueNumber() {

        int number = queueManager.getNextNumber();
        System.out.println(stationName + " issued queue number: " + number);
        return number;
    }

    public void resetQueueNumber(int newNumber) {

        queueManager.resetNumber(newNumber);
        System.out.println(stationName + " reset the queue number to: " + newNumber);
    }
}

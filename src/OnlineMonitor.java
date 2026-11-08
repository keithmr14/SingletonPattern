public class OnlineMonitor implements QueueObserver {

    private final QueueManager queueManager;
    private volatile int displayedNumber;

    public OnlineMonitor(QueueManager queueManager) {

        if(queueManager == null) {
            throw new IllegalArgumentException("Queue manager cannot be null.");
        }
        this.queueManager = queueManager;
        displayedNumber = queueManager.getCurrentNumber();
    }

    public void startMonitoring() {

        displayedNumber = queueManager.getCurrentNumber();
        queueManager.addObserver(this);
    }

    public void stopMonitoring() {

        queueManager.removeObserver(this);
    }

    @Override
    public void onQueueNumberChanged(int currentNumber) {

        displayedNumber = currentNumber;
        displayCurrentNumber();
    }

    public void displayCurrentNumber() {

        System.out.println("ONLINE MONITOR - Current queue number: " + displayedNumber);
    }
}

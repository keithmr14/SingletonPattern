import java.util.concurrent.CopyOnWriteArrayList;

public final class QueueManager {

    private static final QueueManager INSTANCE = new QueueManager();

    private int currentNumber;
    private final CopyOnWriteArrayList<QueueObserver> observers = new CopyOnWriteArrayList<>();

    private QueueManager() {

        currentNumber = 0;
    }

    public static QueueManager getInstance() {

        return INSTANCE;
    }

    public synchronized int getNextNumber() {

        currentNumber++;
        notifyObservers();
        return currentNumber;
    }

    public synchronized void resetNumber(int newNumber) {

        if (newNumber < 0) {
            throw new IllegalArgumentException("Queue number cannot be negative.");
        }

        currentNumber = newNumber;
        notifyObservers();
    }

    public synchronized int getCurrentNumber() {

        return currentNumber;
    }

    public void addObserver(QueueObserver observer) {

        if(observer == null) throw new IllegalArgumentException("Observer cannot be null.");

        observers.addIfAbsent(observer);
    }

    public void removeObserver(QueueObserver observer) {

        observers.remove(observer);
    }

    private void notifyObservers() {

        int updatedNumber = currentNumber;

        for (QueueObserver observer : observers) {

            observer.onQueueNumberChanged(updatedNumber);
        }
    }
}

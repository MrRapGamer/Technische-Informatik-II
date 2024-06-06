package src.util.consumer;
import bssync.Semaphore;
import util.RingBuffer.RingBuffer;

public class Consumer extends Thread {
    private final RingBuffer buffer;
    private final Semaphore emptySem;
    private final Semaphore fullSem;
    private final Semaphore mutex;
    private final String name;

    public Consumer(RingBuffer buffer,  Semaphore fullSem, Semaphore emptySem, Semaphore mutex, String name) {
        this.buffer = buffer;
        this.emptySem = emptySem;
        this.fullSem = fullSem;
        this.mutex = mutex;
        this.name = name;


    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            fullSem.down(); // wait if buffer is empty
            mutex.down(); // wait for mutex
            int item = buffer.removeItem();
            System.out.println(name + " Consumed: " + item);
            mutex.up(); // release mutex
            emptySem.up(); // ping producer that item is removed
        }
    }
}
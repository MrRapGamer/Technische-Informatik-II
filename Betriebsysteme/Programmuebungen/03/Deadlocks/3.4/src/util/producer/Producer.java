
package src.util.producer;

import bssync.Semaphore;
import util.RingBuffer.RingBuffer;


public class Producer extends Thread {
    private final RingBuffer buffer;
    private final Semaphore emptySem;
    private final Semaphore fullSem;
    private final Semaphore mutex;
    private final String name;
    private final int number;


    public Producer(RingBuffer buffer, Semaphore fullSem, Semaphore emptySem, Semaphore mutex, String name, int number) {
        this.buffer = buffer;
        this.emptySem = emptySem;
        this.fullSem = fullSem;
        this.mutex = mutex;
        this.name = name;
        this.number = number;

    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            int item = i + 100*number; 
            emptySem.down(); // wait till buffer is full
            mutex.down(); // wait for mutex
            buffer.insertItem(item);
            System.out.println(name + " produced: " + item);
            mutex.up(); // release mutex
            fullSem.up(); // ping consumer, element is added

        }
        
    }
}

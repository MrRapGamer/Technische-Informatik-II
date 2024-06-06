package src.util.main;

import bssync.Semaphore;
import util.consumer.Consumer;
import util.producer.Producer;
import util.ringbuffer.RingBuffer;

public class Main {
    public static void main(String[] args) {
        RingBuffer buffer = new RingBuffer(5);
        Semaphore emptySem = new Semaphore(5);
        Semaphore fullSem = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);

        Producer producer1 = new producer(buffer, fullSem, emptySem,mutex,  "producer 1", 0);
        Producer producer2 = new Producer(buffer, fullSem, emptySem,mutex, "producer 2", 1);
        Consumer consumer1 = new Consumer(buffer, fullSem, emptySem,mutex, "consumer 1");
        Consumer consumer2 = new Consumer(buffer, fullSem, emptySem,mutex, "consumer 2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        system.out.println("Main thread started.");
        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of main thread.");
    }
}
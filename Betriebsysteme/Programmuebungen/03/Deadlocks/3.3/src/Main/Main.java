

public class Main {
    public static void main(String[] args) {
        RingBuffer buffer = new RingBuffer(5);
        Producer producer1 = new Producer(buffer, "producer 1");
        Producer producer2 = new Producer(buffer, "producer 2");
        Consumer consumer1 = new Consumer(buffer, "consumer 1");
        Consumer consumer2 = new Consumer(buffer, "consumer 2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        System.out.println("End of main thread.");
    }
}

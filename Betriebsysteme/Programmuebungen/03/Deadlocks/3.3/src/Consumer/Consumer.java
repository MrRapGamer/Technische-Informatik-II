
public class Consumer extends Thread {
    private RingBuffer buffer;
    private String name;

    public Consumer(RingBuffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (buffer) {
                int item = buffer.removeItem();
                buffer.notify(); // Benachrichtige Erzeuger, dass Elemente entfernt wurden
                System.out.println(name + " Consumed: " + item);
            }
        }
    }
}

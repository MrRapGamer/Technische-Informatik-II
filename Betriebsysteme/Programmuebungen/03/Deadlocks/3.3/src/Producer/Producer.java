
public class Producer extends Thread {
    private RingBuffer buffer;
    private String name;

    public Producer(RingBuffer buffer, String name) {
        this.buffer = buffer;
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            int item = (int) (Math.random() * 100); // Beispielhaftes Element zum Einfügen
            synchronized (buffer) {

                buffer.insertItem(item);
                buffer.notify(); // Benachrichtige Verbraucher, dass Elemente hinzugefügt wurden
            }
            System.out.println(name + " produced: " + item);
        }
    }
}
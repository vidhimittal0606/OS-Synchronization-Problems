import java.util.concurrent.Semaphore;

public class ReaderWriter {
    static int data = 0;
    static int readerCount = 0;

    static Semaphore mutex = new Semaphore(1);
    static Semaphore rwMutex = new Semaphore(1);

    static class Reader extends Thread {
        int id;
        Reader(int id) {
            this.id = id;
        }
        @Override
        public void run() {
            try {
                mutex.acquire();  // Protect readerCount
              
                readerCount++;
                if (readerCount == 1) {   // First reader blocks writers
                    rwMutex.acquire();
                }

                mutex.release();

                System.out.println("Reader " + id + " read data: " + data);  // Reading section
                
                mutex.acquire();  // Reader finished
              
                readerCount--;
                if (readerCount == 0) {  // Last reader allows writers
                    rwMutex.release();
                }

                mutex.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer extends Thread {
        int id;
        Writer(int id) {
            this.id = id;
        }
        @Override
        public void run() {
            try {
                rwMutex.acquire();  // Writer gets exclusive access
              
                data++;  // Writing section
                System.out.println("Writer " + id + " updated data to: " + data);

                rwMutex.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Reader r1 = new Reader(1);
        Reader r2 = new Reader(2);
        Reader r3 = new Reader(3);

        Writer w1 = new Writer(1);
        Writer w2 = new Writer(2);

        r1.start();
        r2.start();
        r3.start();

        w1.start();
        w2.start();
    }
}

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {

    static final int N = 5;
    static Semaphore[] chopstick = new Semaphore[N];
    static Semaphore mutex = new Semaphore(1);
  
    static class Philosopher extends Thread {
        int id;
        Philosopher(int id) {
            this.id = id;
        }
       @Override
        public void run() {
            try {
                mutex.acquire();  // Only one philosopher can pick chopsticks at a time

                if (id % 2 == 0) {  // Even philosopher
                    chopstick[id].acquire();
                    chopstick[(id + 1) % N].acquire();

                } else {   // Odd philosopher
                    chopstick[(id + 1) % N].acquire();
                    chopstick[id].acquire();
                }
                mutex.release();  // Allow another philosopher to pick chopsticks

                System.out.println("Philosopher " + id + " is eating");

                chopstick[id].release();   // Release chopsticks
                chopstick[(id + 1) % N].release();

                System.out.println("Philosopher " + id + " finished eating");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {  // Initialize chopsticks
            chopstick[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[N];  // Create philosophers

        for (int i = 0; i < N; i++) {
            philosophers[i] = new Philosopher(i);
            philosophers[i].start();
        }
    }
}

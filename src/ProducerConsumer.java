import java.util.concurrent.Semaphore;

class practice {
		static int[] buffer = new int[5];
	    static int in = 0;
	    static int out = 0;

	    static Semaphore empty = new Semaphore(5);
	    static Semaphore full = new Semaphore(0);
	    static Semaphore mutex = new Semaphore(1);

	    static class Producer extends Thread {
          @Override
	        public void run() {

	            for (int item = 1; item <= 10; item++) {

	                try {
	                    empty.acquire();  // Wait for an empty space
	                    mutex.acquire();  // Enter critical section

	                    buffer[in] = item;
	                    System.out.println("Produced: " + item);

	                    in = (in + 1) % 5;

	                    mutex.release();   // Leave critical section
	                    full.release();    // Inform consumer that an item is available

	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    static class Consumer extends Thread {

	        public void run() {

	            for (int i = 1; i <= 10; i++) {
                  @Override
	                try {
	                    full.acquire();  // Wait for an available item
	                    mutex.acquire();  // Enter critical section

	                    int item = buffer[out];

	                    System.out.println("Consumed: " + item);

	                    out = (out + 1) % 5;

	                    mutex.release();  // Leave critical section
	                    empty.release();  // Inform producer that an empty space is available

	                } catch (InterruptedException e) {
	                    Thread.currentThread().interrupt();
	                }
	            }
	        }
	    }

	    public static void main(String[] args) {

	        Producer producer = new Producer();
	        Consumer consumer = new Consumer();

	        producer.start();
	        consumer.start();

          System.out.println("Producer & consumer execution is completed.");
	    }
}

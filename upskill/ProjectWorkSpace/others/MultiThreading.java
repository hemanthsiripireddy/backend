public class MultiThreading {
     public static void main(String[] args) {
        //using Thread class
        // MyThread thread1 = new MyThread();
        // MyThread thread2 = new MyThread();
        // thread1.start();
        // thread2.start();
        //using Runnable interface
        // Thread runnableThread1 = new Thread(new MyRunnable());
        // Thread runnableThread2 = new Thread(new MyRunnable());
        // runnableThread1.start();
        // runnableThread2.start();
        Counter counter = new Counter();
        Thread thread1 = new Thread(new SyncExample(counter));
        Thread thread2 = new Thread(new SyncExample(counter));
        thread1.start();
        thread2.start();
    
}
}

//using Thread class
 class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println("Count: " + i + " from " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

   
}

//using Runnable interface
 class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable Thread is running: " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println("Count: " + i + " from " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}

//synchronization example
 class SyncExample implements Runnable {
    private Counter counter;

    public SyncExample(Counter counter) {
        this.counter = counter;
    }

   

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.counter.increment();
            
        }
        System.out.println("Final Count (unsynchronized): " + counter.getCount());
        
    }
}

// thread safety using synchronized block
 class Counter {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            System.out.println("Current Thread: " + Thread.currentThread().getName() + " - Incrementing count");
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
//thread safe using synchronized method
 class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        System.out.println("Current Thread: " + Thread.currentThread().getName() + " - Incrementing count");
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

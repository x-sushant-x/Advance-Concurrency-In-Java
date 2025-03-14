package org.sushant.o1;

class Mutex {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        if(isLocked) {
            wait();
        }

        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

public class CustomMutex {
    public static void main(String[] args) {
        Mutex mutex = new Mutex();

        Runnable task = () -> {
            try {
                mutex.lock();
                System.out.println(Thread.currentThread().getName() + " acquired lock.");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " released lock.");
                mutex.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread tOne = new Thread(task, "Thread One");
        Thread tTwo = new Thread(task, "Thread Two");

        tOne.start();
        tTwo.start();
    }
}

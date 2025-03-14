package org.sushant.o1;

class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits == 0) {
            wait();
        }
        permits--;
    }

    public synchronized void release() {
        permits++;
        notify();
    }
}

public class CustomSemaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);

        Runnable task = () -> {
            try {
                s.acquire();
                System.out.println(Thread.currentThread().getName() + " acquired lock.");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " released lock.");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread tOne = new Thread(task, "Thread One");
        Thread tTwo = new Thread(task, "Thread Two");
        Thread tThree = new Thread(task, "Thread Three");

        tOne.start();
        tTwo.start();
        tThree.start();
    }
}
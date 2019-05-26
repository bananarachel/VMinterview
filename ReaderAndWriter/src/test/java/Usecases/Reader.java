package Usecases;

import Utils.api.IOQueue;
import Utils.impl.MyIOQueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Reader implements Runnable {
    IOQueue<String> queue = null;
    String name;
    private volatile boolean isRunning = true;
    IOQueue<String> uQueue = null;

    public Reader(String name, IOQueue<String> queue, IOQueue<String> uQueue) {
        this.name = name;
        this.queue = queue;
        this.uQueue = uQueue;
    }

    public Reader(String name, IOQueue<String> queue) {
        this.name = name;
        this.queue = queue;
        this.uQueue = new MyIOQueue<>();
    }

    @Override
    public void run() {
        System.out.println("Reader " + name + " started");
        Random r = new Random();
        try {
            while (isRunning) {
                System.out.println("Reader " + name + " fetching ");
                String data = queue.read(IOQueue.TIME_OUT_LIMIT, TimeUnit.SECONDS);
                if(data == null)
                {
                    System.out.println("Reader " + name + " exceeds waiting limit.");
                    isRunning = false;
                }
                else
                {
                    System.out.println("Reader " + name + " fetched：" + data);
                    uQueue.write(data);
                    Thread.sleep(r.nextInt(1000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Reader " + name + " exit ");
        }
    }

    public void stop(){
        isRunning = false;
    }
}

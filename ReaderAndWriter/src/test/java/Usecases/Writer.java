package Usecases;

import Utils.api.IOQueue;
import Utils.impl.MyIOQueue;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable{
    IOQueue uQueue = null;
    IOQueue queue = null;
    private volatile boolean isRunning = true;
    private String name;
    private AtomicInteger count = new AtomicInteger();

    public Writer(String name, IOQueue queue, IOQueue uQueue) {
        this.queue = queue;
        this.name = name;
        this.uQueue = uQueue;
    }

    public Writer(String name, IOQueue queue) {
        this(name, queue, new MyIOQueue());
    }

    @Override
    public void run() {
        String data = "";
        Random rand = new Random();

        System.out.println("Writer " + name + " starts writing: ");
        try {
            while(isRunning){

                System.out.println("Writer " + name + " is generating ");

                Thread.sleep(rand.nextInt(1000));

                data = name + count.incrementAndGet();
                System.out.println("Writer " + name + " writing ："+data);
                queue.write( data);
                uQueue.write( data);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally {
            System.out.println("writer " + name + " exit.");
        }
    }

    public void stop(){
        isRunning = false;
    }
}

package Utils.impl;

import Utils.api.IOQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyIOQueue<String> implements IOQueue<String> {

    private final ReentrantLock readLock = new ReentrantLock();

    private final Condition notEmpty = readLock.newCondition();

    private final ReentrantLock writeLock = new ReentrantLock();

    private final Condition notFull = writeLock.newCondition();

    private final int capacity;

    private final AtomicInteger count = new AtomicInteger();

    private Queue<String> queue;

    public MyIOQueue()
    {
        this(Integer.MAX_VALUE);
    }

    public MyIOQueue(int cap)
    {
        if(cap < 0) throw new IllegalArgumentException();
        this.capacity = cap;
        this.queue = new LinkedList<>();
    }

    public int size() {
        return count.get();
    }

    public boolean write(String object) throws InterruptedException {
        if (object == null) throw new NullPointerException();
        int c = -1;
        final ReentrantLock writeLock = this.writeLock;
        final AtomicInteger count = this.count;
        writeLock.lockInterruptibly();
        try {
            while (count.get() == capacity) {
                notFull.await();
            }
            queue.offer(object);
            c = count.getAndIncrement();
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            writeLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
        return c >= 0;
    }

    public String read(long timeout, TimeUnit unit) throws InterruptedException {
        String x = null;
        int c = -1;
        long nanos = unit.toNanos(timeout);
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.readLock;
        takeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            x = queue.poll();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return x;
    }

    private void signalNotEmpty() {
        final ReentrantLock readLock = this.readLock;
        readLock.lock();
        try {
            notEmpty.signal();
        } finally {
            readLock.unlock();
        }
    }

    private void signalNotFull() {
        final ReentrantLock writeLock = this.writeLock;
        writeLock.lock();
        try {
            notFull.signal();
        } finally {
            writeLock.unlock();
        }
    }
}

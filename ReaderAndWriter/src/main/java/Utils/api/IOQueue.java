package Utils.api;

import java.util.concurrent.TimeUnit;

public interface IOQueue<String> {
    long TIME_OUT_LIMIT = 1L;

    /**
     * try to read an elements,
     * if empty, will wait for sometime,
     * when time limit exceeds, will give up waiting
     * @param time waiting time
     * @param unit time unit of the waiting time
     * @return read element, if null, then no available elements within waiting time limits
     * @throws InterruptedException
     */
    String read(long time, TimeUnit unit) throws InterruptedException;

    /**
     * write an elements into the queue,
     * if the capacity limits exceeds
     * will wait until someone read the queue and make spare space in the queue
     * @param object the elements wants to write
     * @return true if writing succeed
     * @throws InterruptedException
     */
    boolean write(String object)throws InterruptedException ;

    /**
     * return the number of elements in the queue
     * @return
     */
    int size();
}

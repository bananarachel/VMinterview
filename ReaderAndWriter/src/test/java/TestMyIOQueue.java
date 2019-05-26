import Usecases.Reader;
import Usecases.Writer;
import Utils.api.IOQueue;
import Utils.impl.MyIOQueue;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMyIOQueue {
    @Test
    public void testPainterPool() throws Exception {
        int cap = 5;
        IOQueue<String> queue = new MyIOQueue<>(cap);
        //unlimited queue, for test purpose
        IOQueue<String> unLimitedWriterQueue = new MyIOQueue<>();
        IOQueue<String> unLimitedReadQueue = new MyIOQueue<>();
        Writer writer1 = new Writer("John" ,queue, unLimitedWriterQueue);
        Writer writer2 = new Writer("Mike",queue, unLimitedWriterQueue);
        Reader reader1 = new Reader("Sherry",queue, unLimitedReadQueue);
        Reader reader2 = new Reader("Jane",queue, unLimitedReadQueue);

        ExecutorService service = Executors.newFixedThreadPool(5);

        service.execute(writer1);
        service.execute(writer2);
        // wait enough time to full fill the pool.
        Thread.sleep(5000);
        //ensure current pool size equals to capability
        Assert.assertTrue(queue.size()== cap);

        service.execute(reader1);
        service.execute(reader2);

        Thread.sleep(5000);
        writer1.stop();
        writer2.stop();

        //reader stops if time limits exceeded or we manually stop it.
        Thread.sleep(IOQueue.TIME_OUT_LIMIT + 1);
        reader1.stop();
        reader2.stop();

        //close executor
        service.shutdown();

        Assert.assertTrue(unLimitedReadQueue.size() <= unLimitedWriterQueue.size());
    }
}

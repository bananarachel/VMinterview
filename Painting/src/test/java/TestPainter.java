import api.ICanvas;
import impl.Canvas;
import impl.PainterPool;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Painter
 */
public class TestPainter
{
    @Test
    public void testPainterPool() throws Exception {
        ICanvas canvas = new Canvas(100);
        PainterPool painter = PainterPool.getPainterPool();
        Assert.assertTrue(canvas.size() == 0);
        painter.getPainter("Circle").draw(canvas);
        Assert.assertTrue(canvas.size() == 1);
        painter.getPainter("Square").draw(canvas);
        Assert.assertTrue(canvas.size() == 2);
        painter.getPainter("Line").draw(canvas);
        Assert.assertTrue(canvas.size() == 3);
        //undefined painter, nothing happened
        painter.getPainter("Meaningless").draw(canvas);
        Assert.assertTrue(canvas.size() == 3);
    }
}

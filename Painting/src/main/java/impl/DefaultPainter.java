package impl;

import api.ICanvas;
import api.IPaint;

/**
 * Default painter when user calls for a undefined painter, basically, drawing with this painter will do nothing on the canvas
 * The underlying pre-, post- operation is defined in this class
 */
public class DefaultPainter implements IPaint {
    String name = NAME_DEFAULT;

    public DefaultPainter() {
    }

    public void draw(ICanvas canvas) {
        preProcess(canvas);
        doDrawing(canvas);
        postProcess(canvas);
    }

    protected void doDrawing(ICanvas canvas) {
        System.out.println("Draw " + name);
    }

    /**
     * pre-operation, hiding from user
     * @param canvas
     */
    private void preProcess(ICanvas canvas) {
        System.out.println("Before: We have " + canvas.size() + " paintings.");
    }

    /**
     * post-operation, hiding from user
     * @param canvas
     */
    private void postProcess(ICanvas canvas) {
        System.out.println("After: We have " + canvas.size() + " paintings");
    }
}

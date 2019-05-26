package impl;

import api.ICanvas;

/**
 * Painter used to draw a circle
 */
public class CirclePainter extends DefaultPainter {
    public CirclePainter() {
        this.name = NAME_CIRCLE;
    }

    @Override
    protected void doDrawing(ICanvas canvas) {
        System.out.println("Draw a " + name);
        canvas.addPainting(name);
    }
}

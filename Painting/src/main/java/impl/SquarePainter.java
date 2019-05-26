package impl;

import api.ICanvas;

/**
 * Painter used to draw a square
 */
public class SquarePainter extends DefaultPainter {
    public SquarePainter() {
        this.name = DefaultPainter.NAME_SQUARE;
    }

    @Override
    protected void doDrawing(ICanvas canvas) {
        System.out.println("Draw a square" + name);
        canvas.addPainting(name);
    }
}

package impl;

import api.ICanvas;

/**
 * Painter used to draw a line
 */
public class LinePainter extends DefaultPainter {
    public LinePainter() {
        this.name = DefaultPainter.NAME_LINE;
    }

    @Override
    protected void doDrawing(ICanvas canvas) {
        System.out.println("Draw a line" + name);
        canvas.addPainting(name);
    }
}

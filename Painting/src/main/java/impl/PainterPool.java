package impl;

import api.IPaint;

/**
 * Painter factory that allows user to get pre-defined painters
 */
public class PainterPool {
    public static PainterPool instance;
    private IPaint painter;

    private PainterPool() {

    }

    /**
     *
     * @return the singleton instance of painter pool
     */
    public static PainterPool getPainterPool() {
        if (null == instance) {
            instance = new PainterPool();
        }
        return instance;
    }

    public IPaint getPainter(String shape) {
        if (shape.equalsIgnoreCase(DefaultPainter.NAME_CIRCLE)) {
            painter = new CirclePainter();
        } else if (shape.equalsIgnoreCase(DefaultPainter.NAME_LINE)) {
            painter = new LinePainter();
        } else if (shape.equalsIgnoreCase(DefaultPainter.NAME_SQUARE)) {
            painter = new SquarePainter();
        } else {
            painter = new DefaultPainter();
        }
        return painter;
    }
}

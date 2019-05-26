package impl;

import api.ICanvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Container of all the paintings
 */
public class Canvas implements ICanvas {
    List<String> canvas;
    private int curSize = 0;

    public Canvas(int initSize) {
        canvas = new ArrayList<>(initSize);
    }

    @Override
    public void clearCanvas() {
        canvas.clear();
        curSize = 0;
    }

    @Override
    public void addPainting(String name) {
        canvas.add(name);
        curSize ++;
    }

    public int size() {
        return curSize;
    }
}

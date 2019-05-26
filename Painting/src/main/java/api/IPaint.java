package api;

public interface IPaint
{
    String NAME_CIRCLE  = "Circle";
    String NAME_SQUARE  = "Square";
    String NAME_LINE    = "Line";
    String NAME_DEFAULT = "Nothing";
    void draw(ICanvas canvas);
}

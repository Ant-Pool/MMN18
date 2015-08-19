public class Box{
    private double height;
    private double side;
    
    public Box(double _s, double _h)
    {
        height = _h;
        side = _s;
    }
    
    public double getHeight()
    {
        return height;
    }
    
    public double getSide()
    {
        return side;
    }
    
    public void setHeight(double _h)
    {
        height = _h;
    }
    
    public void setSide(double _s)
    {
        side = _s;
    }
    
    public String toString()
    {
        return "Side: " + side + ". Height: " + height + ".";
    }
}
public class SmallHeightNode {
     public int color;
    public double data;
    public SmallHeightNode parent;
    public SmallHeightNode left;
    public SmallHeightNode right;

    public final int RED = 0;
    public final int BLACK = 1;


    public SmallHeightNode(double _data)
    {
        color = RED;
        data = _data;
        parent = null;
        left = null;
        right = null;
    }
    public SmallHeightNode(int _data, SmallHeightNode _parent, SmallHeightNode _left, SmallHeightNode _right)
    {
        color = RED;
        data = _data;
        parent = _parent;
        left = _left;
        right = _right;
    }
}

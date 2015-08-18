public class SmallSideNode {
    public int color;
    public double data;
    public SmallSideNode parent;
    public SmallSideNode left;
    public SmallSideNode right;

    public final int RED = 0;
    public final int BLACK = 1;


    public SmallSideNode(double _data)
    {
        color = RED;
        data = _data;
        parent = null;
        left = null;
        right = null;
    }
    public SmallSideNode(int _data, SmallSideNode _parent, SmallSideNode _left, SmallSideNode _right)
    {
        color = RED;
        data = _data;
        parent = _parent;
        left = _left;
        right = _right;
    }
    
    public boolean isLeaf()
    {
        return left == null && right == null;
    }

}

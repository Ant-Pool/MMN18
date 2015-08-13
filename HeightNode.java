public class HeightNode {
    public int color;
    public double data;
    public HeightNode parent;
    public HeightNode left;
    public HeightNode right;

    public SmallSideNode sideRoot = new SmallSideNode(0);
    private SmallSideNode nil = new SmallSideNode(0);

    public final int RED = 0;
    public final int BLACK = 1;


    public HeightNode(double _data, double side)
    {
        color = RED;
        data = _data;
        parent = null;
        left = null;
        right = null;
        sideRoot = new SmallSideNode(side);
    }
    public HeightNode(int _data, HeightNode _parent, HeightNode _left, HeightNode _right, double side)
    {
        color = RED;
        data = _data;
        parent = _parent;
        left = _left;
        right = _right;
        sideRoot = new SmallSideNode(side);
    }
    
    
    private void leftRotate(SmallSideNode x)
    {
        if(x.right != nil && sideRoot.parent == nil) {
            SmallSideNode y = x.right;//Set y.
            x.right = y.left;//Turn y's left subtree into x's right subtree
            if (y.left != null)
                (y.left).parent = x;
            y.parent = x.parent;//Link x's parent to y.
            if (x.parent == this.nil)
                this.sideRoot = y;
            else {
                if (x == x.parent.left)
                    x.parent.left = y;
                else
                    x.parent.right = y;
            }
            y.left = x;
            x.parent = y;
        }
    }



    public void rightRotate(SmallSideNode x)
    {
        if(x.left != null && nil == sideRoot.parent)
        {
            SmallSideNode y = x.left;// y now points to node to left of x
            x.left = y.right;// y's right subtree becomes x's left subtree
            if(y.right != null)
                (y.right).parent = x;// right subtree of y gets a new parent
            y.parent = x.parent;// y's parent is now x's parent

            // if x is at sideRoot then y becomes new sideRoot
            if(x.parent == nil)
                sideRoot = y;
            else
            {
                if(x == x.parent.right)
                    x.parent.right = y;
                else
                    x.parent.left = y;
            }
            y.right = x;
            x.parent = y;
        }
      
    }

    public void rbInsert(double z)
    {
        SmallSideNode a = new SmallSideNode(z);
        SmallSideNode y = null;
        SmallSideNode x = sideRoot;
        while(x != null)
        {
            y = x;
            if(z < x.data)
                x = x.left;
            else
                x = x.right;
        }
        a.parent = y;
        if(y == null)
            sideRoot = a;
        else
        {
            if(z < y.data)
                y.left = a;
            else
                y.right = a;
         
        }
        
        a.left = null;
        a.right = null;
    
        rbInsertFixup(a);

    }

    public void rbInsertFixup(SmallSideNode z)
    {
        SmallSideNode y;
        assert (z.parent) != null;
        while(z.parent != null && (z.parent).color == RED && z.parent.parent != null)
        {

            if (z.parent == z.parent.parent.left)
            {
                y = z.parent.parent.right;
                if (y != null && y.color == RED) {//CASE 1
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {//CASE 2
                        z = z.parent;
                        leftRotate(z);
                    }//CASE 3
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                }
            }else{
                y = z.parent.parent.left;
                if (y != null && y.color == RED) {//CASE 1
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {//CASE 2
                        z = z.parent;
                        rightRotate(z);
                    }//CASE 3
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }

        sideRoot.color = BLACK;
    }

    public SmallSideNode rbDelete(SmallSideNode z)
    {
        if(z != null) {
            SmallSideNode y;
            SmallSideNode x;
            if (z.left == null || z.right == null)
                y = z;
            else
                y = treeSuccessor(z);


            if (y.left != null)
                x = y.left;
            else
                x = y.right;


            if (x != null)
                x.parent = y.parent;

            if (y.parent == null)
                sideRoot = x;
            else {
                if (y == y.parent.left)
                    y.parent.left = x;
                else
                    y.parent.right = x;
            }

            if (y != z)
                z.data = y.data;
            if (y.color == BLACK)
                rbDeleteFixup(x);
            return y;
        }
        return null;
    }

    public void rbDeleteFixup(SmallSideNode x)
    {
        SmallSideNode w;
        while(x != sideRoot && x.color == BLACK)
        {
            if(x == x.parent.left)
            {
                w = x.parent.right;
                if(w.color == RED)//CASE 1
                {
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if(x.left.color == BLACK && w.right.color == BLACK)//CASE 2
                {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if(w.right.color == BLACK)//CASE 3
                    {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = w.parent.right;
                    }
                }
                //CASE 4
                w.color = x.parent.color;
                x.parent.color = BLACK;
                w.right.color = BLACK;
                leftRotate(x.parent);
                x = sideRoot;
            } else {

                w = x.parent.left;
                if(w.color == RED)//CASE 1
                {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if(x.right.color == BLACK && w.left.color == BLACK)//CASE 2
                {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if(w.left.color == BLACK)//CASE 3
                    {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = w.parent.left;
                    }
                }
                //CASE 4
                w.color = x.parent.color;
                x.parent.color = BLACK;
                w.right.color = BLACK;
                rightRotate(x.parent);
                x = sideRoot;
            }
        }
    }

    public SmallSideNode treeSearch(SmallSideNode x, double k)
    {
        if(x == null || k == x.data)
            return x;
        if(k < x.data)
            return treeSearch(x.left, k);
        else
            return treeSearch(x.right, k);
    }

    public SmallSideNode treeSuccessor(SmallSideNode x)
    {
        if(x.right != null)
            return treeMinimum(x.right);
        SmallSideNode y = x.parent;
        while(y != null && x == y.right)
        {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public SmallSideNode treeMinimum(SmallSideNode x)
    {
        while(x.left != null)
            x = x.left;
        return x;
    }

    public SmallSideNode treeMaximum(SmallSideNode x)
    {
        while(x.right != null)
            x = x.right;
        return x;
    }

    public void inorderTreeWalk(SmallSideNode x)
    {
        if(x != null)
        {
            inorderTreeWalk(x.left);
            double leftData = -1;/* TODO: Earse */
            double rightData = -1;
            if(x.left != null)
                leftData = x.left.data;
            if(x.right != null)
                rightData = x.right.data;
            System.out.println("Value: " + x.data + " Color: " + x.color + " and his left is " + leftData  + " his right is " + rightData);
            inorderTreeWalk(x.right);
        }
    }

    
    public SmallSideNode getSideRoot()
    {
        return sideRoot;
    }
}

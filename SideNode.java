public class SideNode {
    public int color;
    public double data;
    public SideNode parent;
    public SideNode left;
    public SideNode right;

    public SmallHeightNode heightRoot = new SmallHeightNode(0);
    private SmallHeightNode nil = new SmallHeightNode(0);

    public final int RED = 0;
    public final int BLACK = 1;


    public SideNode(double _data, double height)
    {
        color = RED;
        data = _data;
        parent = null;
        left = null;
        right = null;
        heightRoot = new SmallHeightNode(height);
    }
    public SideNode(int _data, SideNode _parent, SideNode _left, SideNode _right, double height)
    {
        color = RED;
        data = _data;
        parent = _parent;
        left = _left;
        right = _right;
        heightRoot = new SmallHeightNode(height);
    }
    
    
    private void leftRotate(SmallHeightNode x)
    {
        if(x.right != nil && heightRoot.parent == nil) {
            SmallHeightNode y = x.right;//Set y.
            x.right = y.left;//Turn y's left subtree into x's right subtree
            if (y.left != null)
                (y.left).parent = x;
            y.parent = x.parent;//Link x's parent to y.
            if (x.parent == this.nil)
                this.heightRoot = y;
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



    public void rightRotate(SmallHeightNode x)
    {
        if(x.left != null && nil == heightRoot.parent)
        {
            SmallHeightNode y = x.left;// y now points to node to left of x
            x.left = y.right;// y's right subtree becomes x's left subtree
            if(y.right != null)
                (y.right).parent = x;// right subtree of y gets a new parent
            y.parent = x.parent;// y's parent is now x's parent

            // if x is at heightRoot then y becomes new heightRoot
            if(x.parent == nil)
                heightRoot = y;
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
        SmallHeightNode a = new SmallHeightNode(z);
        SmallHeightNode y = null;
        SmallHeightNode x = heightRoot;
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
            heightRoot = a;
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

    public void rbInsertFixup(SmallHeightNode z)
    {
        SmallHeightNode y;
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

        heightRoot.color = BLACK;
    }

    public SmallHeightNode rbDelete(SmallHeightNode z)
    {
        if(z != null) {
            SmallHeightNode y;
            SmallHeightNode x;
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
                heightRoot = x;
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

    public void rbDeleteFixup(SmallHeightNode x)
    {
        SmallHeightNode w;
        while(x != heightRoot && x.color == BLACK)
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
                x = heightRoot;
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
                x = heightRoot;
            }
        }
    }

    public SmallHeightNode treeSearch(SmallHeightNode x, double k)
    {
        if(x == null || k == x.data)
            return x;
        if(k < x.data)
            return treeSearch(x.left, k);
        else
            return treeSearch(x.right, k);
    }

    public SmallHeightNode treeSuccessor(SmallHeightNode x)
    {
        if(x.right != null)
            return treeMinimum(x.right);
        SmallHeightNode y = x.parent;
        while(y != null && x == y.right)
        {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public SmallHeightNode treeMinimum(SmallHeightNode x)
    {
        while(x.left != null)
            x = x.left;
        return x;
    }

    public SmallHeightNode treeMaximum(SmallHeightNode x)
    {
        while(x.right != null)
            x = x.right;
        return x;
    }

    public void inorderTreeWalk(SmallHeightNode x)
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

    
    public SmallHeightNode getHeightRoot()
    {
        return heightRoot;
    }
}

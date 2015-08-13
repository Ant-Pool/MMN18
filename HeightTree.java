public class HeightTree {
    private HeightNode root = new HeightNode(0, 0);
    private HeightNode nil = new HeightNode(0, 0);

    public final int RED = 0;
    public final int BLACK = 1;

    public HeightTree(double _data, double side)
    {
        root = new HeightNode(_data, side);
        root.color = BLACK;
        nil = root.parent;
        root.parent = nil;
    }

    private void leftRotate(HeightNode x)
    {
        if(x.right != nil && root.parent == nil) {
            HeightNode y = x.right;//Set y.
            x.right = y.left;//Turn y's left subtree into x's right subtree
            if (y.left != null)
                (y.left).parent = x;
            y.parent = x.parent;//Link x's parent to y.
            if (x.parent == this.nil)
                this.root = y;
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



    public void rightRotate(HeightNode x)
    {
        if(x.left != null && nil == root.parent)
        {
            HeightNode y = x.left;// y now points to node to left of x
            x.left = y.right;// y's right subtree becomes x's left subtree
            if(y.right != null)
                (y.right).parent = x;// right subtree of y gets a new parent
            y.parent = x.parent;// y's parent is now x's parent

            // if x is at root then y becomes new root
            if(x.parent == nil)
                root = y;
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

    public void rbInsert(double z, double side)
    {
        HeightNode a = new HeightNode(z, side);
        a.sideRoot = null;
        HeightNode y = null;
        HeightNode x = root;
        boolean flag = true;
        while(x != null)
        {
            y = x;
            if(z == x.data){
                flag = false;
                a = x;
                break;
            }
            if(z < x.data)
                x = x.left;
            else
                x = x.right;
        }
        if(flag)
        {
            a.parent = y;
            if(y == null)
                root = a;
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
        /*Inserting to the small side tree */
        a.rbInsert(side);
    }

    public void rbInsertFixup(HeightNode z)
    {
        HeightNode y;
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

        root.color = BLACK;
    }

    public HeightNode rbDelete(HeightNode z)
    {
        if(z != null) {
            HeightNode y;
            HeightNode x;
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
                root = x;
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

    public void rbDeleteFixup(HeightNode x)
    {
        HeightNode w;
        while(x != root && x.color == BLACK)
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
                x = root;
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
                x = root;
            }
        }
    }

    public HeightNode treeSearch(HeightNode x, double k)
    {
        if(x == null || k == x.data)
            return x;
        if(k < x.data)
            return treeSearch(x.left, k);
        else
            return treeSearch(x.right, k);
    }

    public HeightNode treeSuccessor(HeightNode x)
    {
        if(x.right != null)
            return treeMinimum(x.right);
        HeightNode y = x.parent;
        while(y != null && x == y.right)
        {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public HeightNode treeMinimum(HeightNode x)
    {
        while(x.left != null)
            x = x.left;
        return x;
    }

    public HeightNode treeMaximum(HeightNode x)
    {
        while(x.right != null)
            x = x.right;
        return x;
    }

    public void inorderTreeWalk(HeightNode x)
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

    public HeightNode getRoot()
    {
        return root;
    }
}

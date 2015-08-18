public class Factory {
    public HeightTree ht;//TODO: change back to private
    public SideTree st;
    
    public Factory()
    {
        ht = null;
        st = null;
    }
    
    /*
    Adds a new box to the data structure.
    Run Time: O(max(log(n),log(m)))
    */
    public void insertBox(double side, double height)
    {
        if(ht == null && st == null)
        {
            ht = new HeightTree(height, side);
            st = new SideTree(side, height);
        } else {
            ht.rbInsert(height, side);
            st.rbInsert(side, height);
        }
    }
    /*
    Remove a box from the data structure.
    Run Time: O(log(n)*log(m))
    */
    public void removeBox(double side, double height)
    {
        if(!(ht==null && st == null))
        {
            HeightNode hn = ht.treeSearch(ht.getRoot(), height);
            SideNode sn = st.treeSearch(st.getRoot(), side);
            if(hn.isMoreThanOne())//O(1)
            {
                hn.rbDelete(hn.treeSearch(hn.sideRoot, side));
            }else {
                ht.rbDelete(hn);
            }
            if(sn.isMoreThanOne())
            {
                sn.rbDelete(sn.treeSearch(sn.getHeightRoot(), height));
            }else {
                st.rbDelete(sn);
            }
            
            if(ht.treeSearch(ht.getRoot(), height)==null)
                ht.size--;
            if(st.treeSearch(st.getRoot(), side)==null)
                st.size--;
        }
    }
    /*
    Check if exists a box that can contain the parameters
    Run time: O(max(n, m))
    */
    public boolean checkBox(double side, double height)
    {
        if(!(ht==null && st == null))
        {
           Object[] heights = ht.inorderTreeWalk();
           Object[] maxSides = ht.maxInorderTreeWalk();
           Object[] sides = st.inorderTreeWalk();
           Object[] maxHeights = st.maxInorderTreeWalk();
           int i = 0;
           while(i < heights.length && i < sides.length)
               if(((double)heights[i] >= (double)height && (double)maxSides[i] >= side) || ((double)sides[i] >= side && (double)maxHeights[i] >= height))
                return true;
               i++;
           }
           return false;
    }
    
    public Box getBox(double side, double height)
    {
        if(!(ht==null && st==null))
        {
            int n = ht.size;
            int m = st.size;
            
            if(n*(Math.log(m)/Math.log(2)) >= m*(Math.log(n)/Math.log(2)))//Checking which tree will take us less time
            {
                HeightNode x = ht.getRoot();
                
                    System.out.println("[11] x is " + x.data);
                  
                while(!x.isLeaf())
                {
                    if(x.data < height )
                    {
                        if( x.right == null)
                            break;
                        else
                             x = x.right;
                    }  else {
                        if( x.left == null)
                            break;
                        else
                             x = x.left;
                    }
                }
                if(x.data < height)
                    x = x.parent;
                while(x != null && x.max < side)
                {
                    x = ht.treeSuccessor(x);
                }
                if(x == null)
                    return null;
                st.printInorderTreeWalk(st.getRoot());
                System.out.println("X is " + x.data);
                SmallSideNode y = x.getSideRoot();
                 while(!y.isLeaf())
                {
                    if(y.data < side )
                    {
                        if( y.right == null)
                            break;
                        else
                             y = y.right;
                    }  else {
                        if( y.left == null)
                            break;
                        else
                             y = y.left;
                    }
                }
                Box box = new Box(y.data, x.data);
                removeBox(y.data, x.data);
                return box;
            } else {
                 SideNode x = st.getRoot();
                while(!x.isLeaf())
                {
                    if(x.data < side )
                    {
                        if( x.right == null)
                            break;
                        else
                             x = x.right;
                    }  else {
                        if( x.left == null)
                            break;
                        else
                             x = x.left;
                    }
                }
                if(x.data < side)
                    x = x.parent;
                while(x != null && x.max < height)
                    x = st.treeSuccessor(x);
                SmallHeightNode y = x.getHeightRoot();
                 while(!y.isLeaf())
                {
                    if(y.data < height )
                    {
                        if( y.right == null)
                            break;
                        else
                             y = y.right;
                    }  else {
                        if( y.left == null)
                            break;
                        else
                             y = y.left;
                    }
                }
                Box box = new Box(x.data, y.data);
                removeBox(x.data, y.data);
                return box;
            }
        }
        return null;
    }
    
    
    public void command(String _command)
    {
        System.out.println("Command is " + _command);
        _command = _command.toLowerCase();
        String[] command = _command.split(" ");
        
        if(command[0].equals("insertbox"))
        {
            System.out.println("Inserting a new box.");
            insertBox(Double.parseDouble(command[1]),Double.parseDouble(command[2]));
            System.out.println("New box is now in the data structure");
        }
        if(command[0].equals("removebox"))
        {
            System.out.println("Removing the box.");
            removeBox(Double.parseDouble(command[1]),Double.parseDouble(command[2]));
            System.out.println("Box has removed from the data structure");
        }   
    }
}

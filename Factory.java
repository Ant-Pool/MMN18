public class Factory {
    private HeightTree ht;
    private SideTree st;
    
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
            if(hn!= null){
            if(hn.isMoreThanOne())//O(1)
            {
                hn.rbDelete(hn.treeSearch(hn.sideRoot, side));
            }else {
                ht.rbDelete(hn);
            }
            }
            if(sn != null){
            if(sn.isMoreThanOne())
            {
                sn.rbDelete(sn.treeSearch(sn.getHeightRoot(), height));
            }else {
                st.rbDelete(sn);
            }
            }
            
            if(ht.treeSearch(ht.getRoot(), height)==null && hn != null)
                ht.size--;
            if(st.treeSearch(st.getRoot(), side)==null && sn != null)
                st.size--;
        }
    }
    /*
    Check if exists a box that can contain the parameters
    Run time: O(n)
    */
    public boolean checkBox(double side, double height)
    {
        if(!(ht==null && st == null))
        {
           if(checkBox2(ht.getRoot(),side, height ))
            return true;
        }
        return false;
    }
    
    public boolean checkBox2(HeightNode hn, double side, double height)
    {
        if(!(ht==null && st == null))
        {
          
           if(hn != null)
           {
               if(height <= hn.data && side <= hn.max)
            return true;
            else
                return checkBox2(hn.left, side, height) || checkBox2(hn.right, side, height);
             }
             return false;
        }
        else
        return false;
    }
    
    /* 
    Returning and removing the minimal box who the present fits into.
    Run Time: 
    */
    public Box getBox(double side, double height)
    {
        if(!(ht==null && st==null))
        {
            int n = ht.size;
            int m = st.size;
            
            if(n*(Math.log(m)/Math.log(2)) >= m*(Math.log(n)/Math.log(2)))//Checking which tree will take us less time
            {
                HeightNode x = ht.getRoot();
                  
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
                 if(x==null)
                 {
                     return null;
                 }
                while( !x.isLeaf())
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
        
        _command = _command.toLowerCase();
        String[] command = _command.split(" ");
        System.out.println("Command is " + _command);
        if(command[0].equals("insertbox"))
        {
            System.out.println("===Inserting a new box...");
            insertBox(Double.parseDouble(command[1]),Double.parseDouble(command[2]));
            System.out.println("===New box is now in the data structure");
        }
        if(command[0].equals("removebox"))
        {
            System.out.println("===Removing the box...");
            removeBox(Double.parseDouble(command[1]),Double.parseDouble(command[2]));
            System.out.println("===Box has removed from the data structure");
        }
        if(command[0].equals("checkbox"))
        {
            System.out.println("===Looking for a box...");
            if(checkBox(Double.parseDouble(command[1]),Double.parseDouble(command[2])))
            {
                System.out.println("===Box exists.");
            } else {
                System.out.println("===No box was found.");
            }
            
        }
        if(command[0].equals("getbox"))
        {
            System.out.println("===Searching for a box...");
            Box box = getBox(Double.parseDouble(command[1]),Double.parseDouble(command[2]));
            if(box != null)
                System.out.println("===Returned box, " + box.toString());
            else
                System.out.println("===Not found");
        }
    }
}

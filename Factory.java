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
}

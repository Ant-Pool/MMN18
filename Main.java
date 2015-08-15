public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        HeightNode h = new HeightNode(5, 9);
        SideTree st = new SideTree(5, 0);
        HeightTree ht = new HeightTree(5, 0);
        Factory fct = new Factory();
        ht.printInorderTreeWalk(ht.getRoot());
        ht.rbInsert(2, 0);
        ht.rbInsert(7, 0);
        ht.rbInsert(7, 9);
        ht.rbInsert(7, 1);
        ht.rbInsert(8, 0);
        ht.rbInsert(3, 0);
        ht.rbInsert(10, 0);
        ht.rbInsert(6, 0);
        ht.rbInsert(4, 0);
        ht.rbInsert(6, 1);
        ht.rbInsert(6, 2);
        System.out.println("The new root: " + ht.getRoot().data);
        ht.printInorderTreeWalk(ht.getRoot());
                                                                      
        
    }
}

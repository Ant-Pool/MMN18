public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        SideTree ht = new SideTree(5, 0);
        ht.inorderTreeWalk(ht.getRoot());
        ht.rbInsert(2, 0);
        ht.rbInsert(7, 0);
        ht.rbInsert(7, 1);
        ht.rbInsert(8, 0);
        ht.rbInsert(3, 0);
        ht.rbInsert(10, 0);
        ht.rbInsert(6, 0);
        ht.rbInsert(4, 0);
        ht.rbInsert(6, 1);
        ht.rbInsert(6, 2);
        System.out.println("The new root: " + ht.getRoot().data);
        ht.inorderTreeWalk(ht.getRoot());
    }
}

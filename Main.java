public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        HeightTree ht = new HeightTree(5);
        ht.inorderTreeWalk(ht.getRoot());
        ht.rbInsert(2);
        ht.rbInsert(7);
        ht.rbInsert(7);
        ht.rbInsert(8);
        ht.rbInsert(3);
        ht.rbInsert(10);
        ht.rbInsert(6);
        ht.rbInsert(4);
        ht.rbInsert(6);
        ht.rbInsert(6);
        System.out.println("The new root: " + ht.getRoot().data);
        ht.inorderTreeWalk(ht.getRoot());
    }
}

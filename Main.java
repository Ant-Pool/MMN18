public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        
        Factory fct = new Factory();
        fct.command("INSERTBOX 6 3");
       fct.command("INSERTBOX 7 4");
        fct.st.printInorderTreeWalk(fct.st.getRoot());
         fct.ht.printInorderTreeWalk(fct.ht.getRoot());
        System.out.println(fct.getBox(6, 2).toString());
        
                                                                      
        
    }
}

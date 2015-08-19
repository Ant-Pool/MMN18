import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Please enter a command: *Command Name* *side* *height*. type 'exit' to exit.");
        Scanner userInput = new Scanner( System.in );
        String command = "";
        Factory fct = new Factory();
      
        while(!command.equals("exit"))
        {
            System.out.print(">>>");
            command = userInput.nextLine();
            
            fct.command(command);
        }
        System.out.println("Exiting...");
        
    }
}

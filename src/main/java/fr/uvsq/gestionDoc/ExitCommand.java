package fr.uvsq.gestionDoc;

public class ExitCommand implements Command {

   public ExitCommand() {
   }

   public void execute() {
      System.exit(1);
   }
}

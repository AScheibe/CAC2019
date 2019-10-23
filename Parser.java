import java.util.*;

public class Parser 
{
    public static CommandWords commands;  // holds all valid command words
    public static Scanner reader;         // source of command input
    
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

   public static Command setCommand(String firstWord, String secondCommandWord, String thirdCommandWord)
    {
        String word1 = firstWord;
        String word2 = secondCommandWord;
        String word3 = thirdCommandWord;
        
          if(commands.isCommand(word1)) {
            return new Command(word1, word2, word3);
        }
        else {
            return new Command(null, word2, word3); 
        }
        
    }
    
    /**
     * @return The next command from the user.
     */
    public static Command getCommand() 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;
        String word3 = null;
        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
                    if(tokenizer.hasNext()) {
                        word3 = tokenizer.next();      // get second word
                        // note: we just ignore the rest of the input line.
                    }
            }
            
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2, word3);
        }
        else {
            return new Command(null, word2, word3); 
        }
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}

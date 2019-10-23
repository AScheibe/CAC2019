
public class Command
{
    public static String commandWord;
    public static String secondWord;
    public static String thirdWord;

        
    
    /**
     * Create aa command object. First and second word must be supplied, but
     * either one (or both) can be null.
     * @param firstWord The first word of the command. Null if the command
     *                  was not recognised.
     * @param secondWord The second word of the command.
     */
    public Command(String firstWord, String secondWord, String thirdWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
        
    }
   
     public static void setCommand(String firstWord, String secondCommandWord, String thirdCommandWord)
    {
        commandWord = firstWord;
        secondWord = secondCommandWord;
        thirdWord = thirdCommandWord;
        
    }
    
    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return The command word.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    public String getThirdWord()
    {
        return thirdWord;
    }
    
    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
}


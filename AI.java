import java.util.*;

public class AI{

    
public AI()
{

}

private void collectRoom()
{
    
    
}

public void scheduleCommand(String firstWord, String secondWord, String thirdWord)
{               
   
            System.out.println("\n" + "Thinking...");
               try{Thread.sleep(2000);
                } catch(Exception e)
                {
               System.out.println("ERROR");
            }
               setCommand(firstWord, secondWord, thirdWord);     
}    


public void setCommand(String firstWord, String secondWord, String thirdWord)
{
    Parser.setCommand(firstWord, secondWord, thirdWord);
}

public void checkName(String name)
{
  
}

















}




import java.util.*;


public class AI{

int playerHealthAi;

public AI()
{

}


//runs math for AI enemy fight 
public void fightMath(int eHealth, int pHealth, int pAD, int eAD)
{
    double pHealthCalc;
    int pHealthCalcInteger;

    double times = eHealth / pAD;

    double healthMinus = times * eAD;

    pHealthCalc = pHealth - healthMinus;

    pHealthCalcInteger = (int)Math.ceil(pHealthCalc);

    playerHealthAi = pHealthCalcInteger;

}

//
public int getHealth()
{

return playerHealthAi;

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

public void runRope(ArrayList<Item> items)
{
    for(Item i : items)
     {
        if(i.getItemName().equals("rope"))
        {
            scheduleCommand("use", "rope", null);
        }
        else
        {
            scheduleCommand("go", "left", null);
        }
     }

}

public void potionCheck(int health)
{
    if(health < 50)
    {
        scheduleCommand("use", "potion", null);
    }
    else
    {
        scheduleCommand(null, null, null);
    }
}

public void setCommand(String firstWord, String secondWord, String thirdWord)
{
    Parser.setCommand(firstWord, secondWord, thirdWord); 
}

public void checkName(String name)
{
  
}
}




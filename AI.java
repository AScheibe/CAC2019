import java.util.*;

public class AI{

int playerHealthAi;

public AI()
{

}



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


public void setCommand(String firstWord, String secondWord, String thirdWord)
{
    Parser.setCommand(firstWord, secondWord, thirdWord); 
}

public void checkName(String name)
{
  
}


















}




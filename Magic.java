
/**
 * Abstract class Magic - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Magic
{
    // instance variables - replace the example below with your own
    public int damage;
    public int level;
    public Magic()
    {
        damage = 1;
    }
    
    public int useMagic()
    {
      return damage;  
    }
    
    public void levelUp()
    {
       if(level == 0)
       {
           damage = 5;
           level++;
       }
       else
       {
       damage = damage + 5;
       level++;
       System.out.println(level);
       }
    }
    
    public void increaseDamage(int increase)
    {
      damage = damage + increase;
    }

}

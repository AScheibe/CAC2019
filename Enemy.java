public class Enemy
{
    // instance variables - replace the example below with your own
    private int attackDamage;
    private int health;
    private String name;
    
    /**
     * Constructor for objects of class Enemy
     */
    public Enemy(String name, int attackDamage, int health)
    {
        this.name = name;
        this.attackDamage = attackDamage;
        this.health = health;
    }
    
    public String getName()
    {
       return name;
    }
    
    public int getAttack()
    {
        return attackDamage;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public void decreaseHealth(int damage)
    {
        health = health - damage;
    }
  
    
    

}

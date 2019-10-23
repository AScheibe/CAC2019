
public class Weapon
{
    // instance variabless - replace the example below with your own
    private int damage;
    private boolean takeAble;
    private String name;
    private String description;
    /**
     * Constructor for objects of class Item
     */
    public Weapon(String name, String description, int damage, boolean takeAble)
    {
        // initialise instance variables
        this.damage = damage;
        this.takeAble = takeAble;
        this.name = name;
        this.description = description;
    }
    
   public String getWeaponName()
    {
        return name;
    }
    
    public boolean getAbleToTake()
    {
        return takeAble;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public void setAbleToTake(boolean takeAble)
    {
        this.takeAble = takeAble;
    }
    
    public String getDescription()
    {
        return description;
    }
}

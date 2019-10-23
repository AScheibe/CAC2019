
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Player
{
    // instance variables - replace the example below with your own
    private int health;
    private int attackDamage;
    public ArrayList<Weapon> weaponInventory;
    private ArrayList<Item> itemInventory;
    private String name;
    private Room currentRoom;
    private int size;
    private Fire fireSpell;
    private Freeze freezeSpell;
    private Laser laserSpell;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        health = 100;
        attackDamage = 0;
        itemInventory = new ArrayList<Item>();
        weaponInventory = new ArrayList<Weapon>();
        name = new String("Greggory");
        currentRoom = new Room(null, null);
        size = 0;
        fireSpell = new Fire();
        freezeSpell = new Freeze();
        laserSpell = new Laser(); 
    }
    
    public ArrayList<Item> getInventory()
    {
       return itemInventory; 
    }
    
    
    public int useFire()
    {
        int damage = fireSpell.useMagic();
        fireSpell.levelUp();
        return damage;
    }
    
    public int useFreeze()
    {
        int damage = freezeSpell.useMagic();
        freezeSpell.levelUp();
        return damage;   
    }
    
    public int useLaser()
    {
        int damage = laserSpell.useMagic();
        laserSpell.levelUp();
        return damage;   
    }
    
    public ArrayList<Weapon> getWeapon()
    {
        return weaponInventory;
    }
    
    public void setName(String name)
    {
     this.name = name;
    }
    
    public void removeItemSpecific(Item item)
    {
     itemInventory.remove(item);   
    }
  
    
    //Adds items 
    
    public void pickUpItem(Command command)
    {
       String itemFirst = command.getSecondWord();
       String itemSecond = command.getThirdWord();
       Iterator<Item> it = currentRoom.getItems().iterator(); 
       while(it.hasNext()){//make sure the item you wish to take is in the current room
           Item i = it.next();
            if(i.getItemName().equals(itemFirst) && i.getAbleToTake() || i.getItemName().equals(itemFirst + " " + itemSecond) && i.getAbleToTake()){
                itemInventory.add(i);
                i.setAbleToTake(false);
                System.out.println(i.getItemName() + " picked up");
            }
            else if(i.getItemName().equals(itemFirst) && !i.getAbleToTake() || i.getItemName().equals(itemFirst + itemSecond) && !i.getAbleToTake()){
             System.out.println("The " + i.getItemName() + " is not able to be taken.");  
            }
        }
    }
    
  
    //Allows the user to store a weapon in the weapon inventory
    public void pickUpWeapon(Command command)
    {
       String weaponFirst = command.getSecondWord();
       String weaponSecond = command.getThirdWord();
       Iterator<Weapon> it = currentRoom.getWeapons().iterator(); 
       while(it.hasNext()){//make sure the item you wish to take is in the current room
           Weapon w = it.next();
            if(w.getWeaponName().equals(weaponFirst) && w.getAbleToTake() && size == 0 || w.getWeaponName().equals(weaponFirst + " " + weaponSecond) && w.getAbleToTake() && size == 0){
                weaponInventory.add(w);
                it.remove();
                //currentRoom.removeWeapon(w);
                System.out.println(w.getWeaponName() + " picked up");
                size = 1;
                attackDamage = w.getDamage();
            }
            else if(w.getWeaponName().equals(weaponFirst) && !w.getAbleToTake() || w.getWeaponName().equals(weaponFirst + " " + weaponSecond) && !w.getAbleToTake() || w.getWeaponName().equals(weaponFirst) && size == 1 || size == 1 && w.getWeaponName().equals(weaponFirst + " " + weaponSecond) ){
             System.out.println("The " + w.getWeaponName() + " is not able to be taken. Try dropping your current weapon. You may only hold one at a time.");  
            }
        }
    }
    
    //Allows the user to drop a weapon
    public void dropWeapon(Command command)
    {
           for(int i = 0; i < weaponInventory.size(); i++) {
            System.out.println(weaponInventory.get(i).getWeaponName() + " dropped");
            Weapon weapon = weaponInventory.get(i);
            currentRoom.setWeapon(weapon);
            weaponInventory.get(i).setAbleToTake(true);
        }
         
        weaponInventory.clear();
        
           
           
           
           size = 0;
           attackDamage = 0;
    }

    
    public void printInventory()
    {
        System.out.println("Items in your inventory are: ");
        for(Item i : itemInventory)
        {
         System.out.println(i.getItemName() + i.getDescription());   
        }
        
        
    }
    
    //The reason why this is returning null is beacause if I return a string, it runs before any of the system.outs thus ruining the format of the game.
    public String printWeapon()
    {
        String weapon;
        
        weapon = "fist";
        
        for(Weapon w : weaponInventory)
        {
          weapon = w.getWeaponName();
        }
        
        System.out.println(weapon);
     
        return weapon;
    }
    
    public String getWeaponName()
    {
      String weapon;
        
        weapon = "fist";
        
        for(Weapon w : weaponInventory)
        {
          weapon = w.getWeaponName();
        }
    
     
        return weapon;  
    }
    
    public String printWeaponDescription()
    {
        String description;
        description = "does 2 damage";
        
        for(Weapon w : weaponInventory)
        {
          description = w.getDescription();
        }
        
        System.out.println(description);
        return description;
    }
    
    public void setRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }
    
    public int getAttackDamage()
    {
        return attackDamage + 2;
    }
    
    public String getName()
    {
      return name;
    }
    
        public void setHealth(int takenDamage)
    {
        health = health - takenDamage;
    }

    public void setHealthExact(int newHealth)
    {
        health = newHealth;
    }
    
    public void setHealthHealing(int healthAdd)
    {
       health = healthAdd;
       
       if(health > 100)
       {
         health = 100;  
       }
       
    }
    
    public int getHealth()
    {
        return health;
    }
    


    
}

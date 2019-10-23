import java.util.*;
public class Room 
{
    private String description;
    private String name;
    private int enemyHealth;
    private HashMap<String, Room> exits; 
    private ArrayList<Item> items;
    private ArrayList<Weapon> weapons;
    private ArrayList<Enemy> enemies;
    // stores exits of this room.

    public Room(String name, String description) 
    {
        this.description = description;
        this.name = name;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        weapons = new ArrayList<Weapon>(); 
        enemies = new ArrayList<Enemy>();
    }
    
    public String getName()
    {
     return name;
    }
    
 
    
    public void removeWeapon(Weapon weapon)
    {
       weapons.remove(weapon); 
    }
    
    public void removeItem(Item item)
    {
       items.remove(item); 
    }
 
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public void removeExit(String direction, Room neighbor)
    {
        exits.remove(direction, neighbor);
    }
    
    public void setItem(Item item)
    {
        items.add(item);
    }
    
    public void setEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }
    
    public ArrayList<Enemy> getEnemies()
    {
      return enemies;
    }
    
    public void removeEnemy()
    {
       enemies.clear(); 
    }
    
    public int getEnemyDamage()
    {
       int damage;
       damage = 0;
       
       for(Enemy e : enemies)
       {
         damage = damage + e.getAttack();  
       }
       
       return damage;
    }
    
    public boolean containsEnemy()
    {
        if(enemies.size() < 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void setEnemyHealth(int attackDamage)
    {
        for(Enemy e : enemies)
      {
         e.decreaseHealth(attackDamage);
      }
    }
    
    public int getEnemyHealth()
    {
      int health;
      health = 0;
      
      for(Enemy e : enemies)
      {
         health = health + e.getHealth(); 
      }
      
      return health;
    }
    
    public void setWeapon(Weapon weapon)
    {
        weapons.add(weapon);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    public ArrayList<Item> getItems()
    {
        return items;
    }
    
    public ArrayList<Weapon> getWeapons()
    {
        return weapons;
    }
    
    /*public ArrayList<Item> printItems()
    {
        for(Item i : items)
        {
         System.out.println("Item:" + i.getItemName());   
        }
        
        
        return items;
    }
    */
  
  
    public String getLongDescription()
    {
        return "\n" + "You are in the " + description + "\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits: \n";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += exit + "\n";
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
    return exits.get(direction);
    }
}


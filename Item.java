
public class Item
{
    private String name;
    private String description;
    private boolean takeAble;
    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, boolean takeAble)
    {
     this.name = name;
     this.description = description;
     this.takeAble = takeAble;
    }
    
    public String getItemName()
    {
        return name;
    }
    
    public boolean getAbleToTake()
    {
        return takeAble;
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

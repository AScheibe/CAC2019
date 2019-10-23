import java.util.*;


public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Item shovel, rope, potion;
    private Weapon woodSword, rustedSword, silverSword, goldSword, titaniumSword, infantryBow, silverSpear;
    Room prison, promenade, sewers, ramparts, depths, ossuary, bridge, crypt, graveyard, forest, tower, castle, throne, deathRoom1, deathRoom2, deathRoom3, deathRoom4, throneRoomEntrance; 
    private Enemy scorpion, skeleton, zombie, ghoul, ghost, cthulu, concierge, rusch, wookie;
    private Player player;
    private String name;
    private boolean enemyPresent;
    private int rand;
    public AI ai;
    private Command command;
    private ArrayList<Enemy> currentEnemies;
    private boolean wantToQuit;
    private boolean alive;
    Random generator = new Random();
    private String enemyName;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        name = new String();
        player = new Player();
        ai = new AI();
        createRooms();
        rand = -1;
        enemyPresent = false;
        wantToQuit = false;
        alive = true;
        createItems();
        createWeapons();
        createEnemy();
        setEnemy();
        setItems();
        setWeapons();
        setExits();
    }
    
    //sets a random generator
    private void setRand(){
      rand = generator.nextInt(9) + 1;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        prison = new Room("Prison", "Prison. You awake in a cold, dark cell. Luckily, the wall next to you has been blown open. \nUnaware of your current circumstances, you press on... \n");
        promenade = new Room("Promenade","Promenade. After stumbling upon a ladder, you decided to climb up. \n" + 
                            "You appear outside a sprawling promenade. There appears to be something shiny stuck in the ground... \n");
        sewers = new Room("Sewers","Sewers. It smells... interesting. As you dive deeper, you hear something creak");
        ramparts = new Room("Ramparts", "Ramparts. You feel queasy as you peer down below. \nAs you make your way along, you're greated by a wounded soldier." +
                            "\nIn clear agony, he slowly closes his eyes as he says, 'the king... ki.. kil... kill the king..' \n");
        depths = new Room("Depths", "Depths. You can hardly see as to where you're going..." + "\n");
        ossuary = new Room("Ossuary", "Ossuary. A chill runs down your spine as you examine the skulls... \n" +
                           "but wait... is.. is one of them... moving? \n" +"\n" + "\n" + "There seems to be a chest in this room!" + "\n");
        bridge = new Room("Bridge", "Bridge. A LOUD SHREIK RINGS OUT BEHIND YOU!!! \n");
        crypt = new Room("Crypt", "Crypt. An eerire feeling begins to set in. Something about this place doesn't seem quite right..." + "\n");
        graveyard = new Room("Graveyard", "Graveyard. The soil looks rather soft. \n" +
                             "As you being to dive deeper, you begin to hear moans coming from behind you...");
        forest = new Room("Forest", "Forest. It used to be gorgeous, and gleaming with life; that is, until the king came..." + "\n" +
                          "there's quite a tall tower in front of you... if only you had something to climb it." + "\n");
        tower = new Room("Tower", "Tower. As you look over the land, you're astounded by the ruin and destruction the malice and king have left behind. \n");
        castle = new Room("Castle", "Castle. Used to be the most elegant and awe-inspiring building in the land. \n" +
                          "That is, before the king showed up... \n" +
                          "wait... is that... is that a chest? \n");
        throneRoomEntrance = new Room("Throne Entrance", "You have made it to the throne room entrance. Press on, if you dare.");
        throne = new Room("Throne Room", "You have entered the throne room. As you enter, the door slams shut behind you! \n" +
                           "Before you stands, the king of all malice and destruction, Mr. Rusch" + "\n");
        deathRoom1 = new Room("Death Room", "THE DOOR SLAMS SHUT BEHIND YOU");
        deathRoom2 = new Room("Death Room", "THE DOOR SLAMS SHUT BEHIND YOU");
        deathRoom3 = new Room("Death Room", "THE DOOR SLAMS SHUT BEHIND YOU");
        deathRoom4 = new Room("Death Room", "depths of the earth... well part of you at least." + "\n" + "You fell off the peaks of the castle to your untimely death");
       
        // initialise room exits

        currentRoom = prison;  // start game outside player.setRoom(currentRoom);
        player.setRoom(currentRoom);
        
    }

    //sets all the exits
    private void setExits()
    {
        promenade.setExit("up", ossuary);
        promenade.setExit("deep", depths);
        
        ossuary.setExit("down", promenade);
        ossuary.setExit("left", forest);
        ossuary.setExit("straight", crypt);
        
        forest.setExit("right", ossuary);
        forest.setExit("left", graveyard);
        
        tower.setExit("straight", ramparts);
        tower.setExit("down", forest);
        tower.setExit("left", deathRoom4);
        
        ramparts.setExit("down", bridge);
        ramparts.setExit("straight", castle);
        ramparts.setExit("left", deathRoom4);
        
        prison.setExit("up", promenade);
        prison.setExit("down", sewers);
        prison.setExit("straight", deathRoom2);
        
        
        sewers.setExit("down", depths);
        sewers.setExit("left", deathRoom1);
        
        depths.setExit("up", sewers);
        depths.setExit("right", crypt);
        depths.setExit("left", deathRoom3);
       
        
        crypt.setExit("up", graveyard);
        crypt.setExit("straight", deathRoom3);
        crypt.setExit("left", depths);
        crypt.setExit("back", ossuary);
        
        graveyard.setExit("right", forest);
        graveyard.setExit("straight", bridge);
        graveyard.setExit("down", crypt);
        
        bridge.setExit("up", ramparts);
        bridge.setExit("back", graveyard);
        bridge.setExit("down", deathRoom2);
        
        throneRoomEntrance.setExit("straight", throne);
        
    }

    //removes all the exits so you can't exit the room. Was later "deprecated" by the implementation of logic in the process command class
    private void clearExits()
    {
        promenade.removeExit("up", ossuary);
        
        ossuary.removeExit("down", promenade);
        ossuary.removeExit("left", forest);
        ossuary.removeExit("straight", crypt);
        
       
        
        tower.removeExit("straight", ramparts);
        tower.removeExit("down", forest);
        
        ramparts.removeExit("down", bridge);
        ramparts.removeExit("straight", castle);
        
        
        prison.removeExit("up", promenade);
        prison.removeExit("down", sewers);
        prison.removeExit("straight", deathRoom2);
        
        sewers.removeExit("down", depths);
        sewers.removeExit("left", deathRoom1);
        
        depths.removeExit("up", sewers);
        depths.removeExit("right", crypt);
        
        crypt.removeExit("up", graveyard);
        crypt.removeExit("straight", deathRoom3);
        crypt.removeExit("left", depths);
        crypt.removeExit("back", ossuary);
        
        graveyard.removeExit("straight", bridge);
        graveyard.removeExit("down", crypt);
        
        bridge.removeExit("up", ramparts);
        bridge.removeExit("down", deathRoom2);
        
        throneRoomEntrance.removeExit("straight", throne);
        
    }

   //creates items
     private void createItems(){
      rope = new Item("rope", " | you are able to climb with this, like maybe a wall?", true); 
      potion = new Item("potion", " | use this to heal your health", true);
      shovel = new Item("shovel", " | use it to dig, like maybe a grave?", true);
    }
    
   
    // +2 damage is added to all weapons by default since I didn't want the player to start defenseless.
    private void createWeapons()
    {
      woodSword = new Weapon("wood sword", " | does 12 damage", 10, true); 
      rustedSword = new Weapon("rusted sword", " | does 17 damage", 15, true);
      silverSword = new Weapon("silver sword", " | does 32 damage", 30, true);
      goldSword = new Weapon("gold sword", " | does 22 damage", 20, true);
      titaniumSword = new Weapon("titanium sword", " | does 52 damage", 50, true);
      silverSpear = new Weapon("silver spear", " | does 25 damage", 23, true);
      infantryBow = new Weapon("infantry bow", " | does 30 damage", 28, true);
    }
    
    //only prints the info if there are objects in the room
    private void printInfo()
    {
        if(currentRoom.getItems().size() >= 1)
        {
        System.out.println("\n" + "Items in room are: " );
        ArrayList<Item> items = currentRoom.getItems();
        
        for(Item i : items)
        {
          System.out.println(i.getItemName() + i.getDescription());  
        }
        
        }
        
      
        
        if(currentRoom.getWeapons().size() >= 1)
        {
        System.out.println("\n" + "Weapons in room are: " );
        
        ArrayList<Weapon> weapons = currentRoom.getWeapons();
        for(Weapon w : weapons)
        {
          System.out.println(w.getWeaponName() + w.getDescription());
        }
        
        }
        
        
    }
    
    //sets the items in their rooms
    private void setItems()
    {
       sewers.setItem(rope);    
       promenade.setItem(rope);
       bridge.setItem(potion);
       tower.setItem(potion);
       throneRoomEntrance.setItem(potion);
       depths.setItem(shovel);
       crypt.setItem(shovel);
    }
   
    //sets the weapon in their rooms
    private void setWeapons()
    {
      sewers.setWeapon(woodSword);
      promenade.setWeapon(woodSword);
      depths.setWeapon(rustedSword);
      forest.setWeapon(goldSword);
      bridge.setWeapon(silverSpear);
      ramparts.setWeapon(infantryBow);
    }
    
    //('Name', attack damage, health)
    private void createEnemy()
    {
      scorpion = new Enemy("Giant Scorpion", 5, 10);  
      rusch = new Enemy("Mr. Rusch", 10, 1000);
      skeleton = new Enemy("Skeleton", 10, 50);
      zombie = new Enemy("Zombie", 12, 25);
      ghoul = new Enemy("Ghoul", 15, 45);
      ghost = new Enemy("Ghost", 400, 100);
      cthulu = new Enemy("Cthulu", 10000000, 999999);
      concierge = new Enemy("Concierge", 6, 100);
      wookie = new Enemy("Savage Wookie", 100, 300);
    }
    
    //sets enemies in their rooms
    private void setEnemy(){
      depths.setEnemy(scorpion);
      throne.setEnemy(rusch);
      ossuary.setEnemy(skeleton);
      graveyard.setEnemy(zombie);
      ramparts.setEnemy(ghoul);
      bridge.setEnemy(concierge);
      deathRoom1.setEnemy(ghost);
      deathRoom2.setEnemy(cthulu);
      deathRoom3.setEnemy(wookie); 
    }
    
    //sets the room in the player class
    private void setPlayerRoom(){
    player.setRoom(currentRoom);
    }
  
    //handles the main enemy encounter
    private void enemyEncounter()
    {
        currentEnemies = currentRoom.getEnemies();
        
        if(currentEnemies.size() > 0)
        {
            for(Enemy e : currentEnemies)
            {
              System.out.println("A " + e.getName() + " stares menacingly!");
              enemyName = e.getName();
              
            }
            enemyPresent = true;
            clearExits();
        }
        else{
           setExits(); 
        }
    }

    
    

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();    
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
         
        boolean finished = false;
        while (! finished && alive == true) {
            checkHealth();
            command = parser.getCommand();
            finished = processCommand(command);
            setPlayerRoom();
            enemyEncounter(); 
            checkVictory();
            deathRoom();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Hammerfell");
        System.out.println("Hammerfell is an unforgiving, lonely world, brimming with monsters, theieves, and death.");
        System.out.println("For years, Malice has plagued the land. Turning what once was a world gleaming with hope and prosperity into a desolate waste land.");
        System.out.println("You will surely not make it out alive, and your death will most likely end in the brutal whipping and nashing of teeth from your enemies.");
        System.out.println("Good Luck :)");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Name is set to Greggory. If you desire to change it, type name.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    public void AI(String firstWord, String secondWord, String thirdWord)
    {
        
    }
    //checks the player health
    public void checkHealth()
    {
        if(player.getHealth() <= 0)
        {
            System.out.println("You are dead :(");
            alive = false;
        }
    }
    
    //Alot happens here from complex math to determine damage taken/dealt + determining which commands can be run
    private boolean processCommand(Command command) 
    {
        wantToQuit = false;
        String commandWord = command.getCommandWord();
        String commandWord2 = command.getSecondWord();
        
        
        if(enemyPresent == true)
        {
            sewers.removeItem(rope);  
            if(command.isUnknown()) {
            System.out.println("Now is not the time. How would you like to attack, with weapon, laser, fire or shrink?");
            return false;
            }        
            else if(commandWord.equals("fire")){
              int enemyHealth;
              setRand(); 
              int damage;
              
              damage = player.useFire();
              currentRoom.setEnemyHealth(damage);
              
              int damageDone = damage;
              
              if(currentRoom.getEnemyHealth() < 0){
               enemyHealth = 0; 
              }
              else {
              enemyHealth = currentRoom.getEnemyHealth();  
                }
                
              System.out.println("\n" + damageDone + " damage done to enemy with fire spell! Fire level increased!");
              
              System.out.println("Enemy health at " + enemyHealth + "!");
              
              if(currentRoom.getEnemyHealth() > 0)
              {
              int playerHealth;
              player.setHealth(currentRoom.getEnemyDamage());
              
              checkHealth();
              
              if(player.getHealth() < 0)
              {
              playerHealth = 0;
            }
            else{
                playerHealth = player.getHealth();
            }
            
            System.out.println(currentRoom.getEnemyDamage() + " damage taken! " + "Health at " + playerHealth + "!" + "\n");
                
            }
        }
       
        else if(commandWord.equals("freeze")){
              int enemyHealth;
              setRand(); 
              int damage;
              
              damage = player.useFreeze();
              currentRoom.setEnemyHealth(damage);
              
              int damageDone = damage;
              
              if(currentRoom.getEnemyHealth() < 0){
                  enemyHealth = 0; 
              }
              else {
                  enemyHealth = currentRoom.getEnemyHealth();  
                }
                
              System.out.println("\n" + damageDone + " damage done to enemy with fire spell! Freeze level increased!" + "\n");
              
              System.out.println("Enemy health at " + enemyHealth + "!");
              
              if(currentRoom.getEnemyHealth() > 0)
              {
              int playerHealth;
              player.setHealth(currentRoom.getEnemyDamage());
              
              checkHealth();
              
              if(player.getHealth() < 0)
              {
              playerHealth = 0;
            }
            else{
                playerHealth = player.getHealth();
            }
            
            System.out.println(currentRoom.getEnemyDamage() + " damage taken! " + "Health at " + playerHealth + "!" + "\n");
                
            }
        }        
        
         else if(commandWord.equals("laser")){
              int enemyHealth;
              setRand(); 
              int damage;
              
              damage = player.useLaser();
              currentRoom.setEnemyHealth(damage);
              
              int damageDone = damage;
              
            if(currentRoom.getEnemyHealth() < 0){
                  enemyHealth = 0; 
            }
              else {
                  enemyHealth = currentRoom.getEnemyHealth();  
                   }
                
              System.out.println("\n" + damageDone + " damage done to enemy with fire spell! Laser level increased!" + "\n");
              
              System.out.println("Enemy health at " + enemyHealth + "!");
              
            if(currentRoom.getEnemyHealth() > 0)
            {
              int playerHealth;
              player.setHealth(currentRoom.getEnemyDamage());
              
              checkHealth();
              
              if(player.getHealth() < 0)
              {
              playerHealth = 0;
            }
            
            else
            {
                playerHealth = player.getHealth();
            }
            
            System.out.println(currentRoom.getEnemyDamage() + " damage taken! " + "Health at " + playerHealth + "!" + "\n");
                
            }
          }
               
            
         else if(commandWord.equals("weapon")){
              
  
              int enemyHealth;
              setRand();
              
              currentRoom.setEnemyHealth(player.getAttackDamage());
              
              int damageDone = player.getAttackDamage();
              
              if(currentRoom.getEnemyHealth() < 0){
               enemyHealth = 0; 
              }
              else {
              enemyHealth = currentRoom.getEnemyHealth();  
                }
                
              System.out.println("\n" + damageDone + " damage done to enemy with "  + player.getWeaponName() + "!");
              
              System.out.println("Enemy health at " + enemyHealth + "!");
              
              if(currentRoom.getEnemyHealth() > 0)
              {
              int playerHealth;
              player.setHealth(currentRoom.getEnemyDamage());
              
              checkHealth();
              
              if(player.getHealth() < 0)
              {
              playerHealth = 0;
            }
            else{
                playerHealth = player.getHealth();
            }
            
            System.out.println(currentRoom.getEnemyDamage() + " damage taken! " + "Health at " + playerHealth + "!" + "\n");
                
            }
          }
            
             else if(commandWord.equals("flee")){
               setRand();
               if(rand <= 8)
               {
                 System.out.println("Pity, you've been snatched by the monster and killed." + "\n");
                 alive = false;
               }
               else{
                System.out.println("You got away!");
                enemyPresent = false;
                currentRoom.removeEnemy();  
               }
            }
            
            else if (commandWord.equals("use")){
                useItem(command);   
            }
           
           else{
            System.out.println("Now is not the time. How would you like to attack, with weapon, laser, fire, shrink, or flee?");  
           }
            
            if(currentRoom.getEnemyHealth() <= 0)
              {
                System.out.println("Enemy vanquished!" + "\n");
                enemyPresent = false;
                currentRoom.removeEnemy();
              }
      
            
        }
        
        else
        {
            if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
            }
            if (commandWord.equals("help")) {
                printHelp();
            }
            else if(commandWord.equals("Ai")){
            

             if (commandWord2.equals("one"))
             {
                 startAi(1);
             }
             else if (commandWord2.equals("two"))
             {
                startAi(2);
             }
             else if (commandWord2.equals("three"))
             {
                startAi(3);
             }
             else if (commandWord2.equals("four"))
             {
                startAi(4);
             }
             else if (commandWord2.equals("five"))
             {
                 startAi(5);
             }
             else if (commandWord2.equals("six"))
             {
                 startAi(6);
             }
             else if(commandWord2.equals("all"))
             {
                 startAi(7);
             }
            else
            {
            System.out.println("I don't know what you mean...");
            return false; 
            }
            
        }
            else if (commandWord.equals("go")) {
                goRoom(command);
            }
            
            else if (commandWord.equals("take")){
                player.pickUpWeapon(command);
                player.pickUpItem(command);
            }
            else if(commandWord.equals("health")){
                System.out.println("Health is at: " + player.getHealth());
            }
            else if(commandWord.equals("drop"))
            {
                player.dropWeapon(command);
            }
            else if(commandWord.equals("boss"))
            {
               currentRoom = throneRoomEntrance; 
            }
            else if (commandWord.equals("use")){
                useItem(command);   
            }
            else if (commandWord.equals("open")){
               openChest(command);
            }
            else if (commandWord.equals("quit")) {
                wantToQuit = quit(command);
            }
            else if (commandWord.equals("inventory")){
                player.printInventory();
            }
            else if (commandWord.equals("name")){
                Scanner reader = new Scanner(System.in);
                System.out.println("Enter a name: ");
                name = reader.next();
                player.setName(name);
                reader.close();
                System.out.println("New name is: " + name);
                player.getName();
               
            }
        }
            
         return wantToQuit;
        }
        
        /*
        automatically "skips" enemy fights, just do like 
        "system.out.println(*enemy name* defeated!)" then adjust the math accordingly. I want to have it so that you can have the
        AI skip a certain number of rooms - i.e. "Ai skip 2" then skips 2 rooms.

        
        */
     public void startAi(int num){
          int times = num;
          int test = 0;
            while(test < times){

                if (currentRoom.containsEnemy())
                {
                    int eHealth = currentRoom.getEnemyHealth();
                    int pHealth = player.getHealth();
                    int pAD = player.getAttackDamage();
                    int eAD = currentRoom.getEnemyDamage();
                    ai.fightMath(eHealth, pHealth, pAD, eAD);
                    System.out.println("test");
                    currentRoom.removeEnemy();
                    player.setHealthExact(ai.getHealth());
                    System.out.println(player.getHealth());
                }
                
                else if(currentRoom.equals(prison))
                {
                ai.scheduleCommand("go", "up", null);
                processCommand(command); 
                test++;
                }

                else if(currentRoom.equals(promenade)){
                currentRoom = promenade;
                player.setRoom(currentRoom);
                ai.scheduleCommand("take", "wood", "sword");
                processCommand(command);
                ai.scheduleCommand("go", "up", null);
                processCommand(command);
                test++;
                }    
            
                else if(currentRoom.equals(ossuary)){
                currentRoom = ossuary;
                player.setRoom(currentRoom);
                test++;
                }
            }        
        }
                 
               
               
             
            
     

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    //kills you if you go into deathRoom4
    private void deathRoom()
    {
        if(currentRoom.equals(deathRoom4))
        {
            alive = false;
        }
    }
    
    //Use Item method
    private void useItem(Command command)
    {
        String item = command.getSecondWord();
        int healthMath = 0;
        ArrayList<Item> items = player.getInventory();
        if(item == null){
            System.out.println("Use what?");
        }
        //individual use methods for each item in the game
        else if(item.equals("rope") && currentRoom == castle || item.equals("rope") && currentRoom == forest){
            System.out.println("Success! You may now go up.");
            castle.setExit("up", throneRoomEntrance);
            forest.setExit("up", tower);
        }
        else if(item.equals("potion") && items.contains(potion))
        {
            healthMath = player.getHealth();
            player.setHealthHealing(healthMath + 75);
            player.removeItemSpecific(potion);
            potion.setAbleToTake(true);
            System.out.println("Health is at: " + player.getHealth());
        }
        else if(item.equals("shovel") && currentRoom == graveyard && items.contains(shovel))
        {
            System.out.println("*titanium sword appeared!* \n it does 52 damage!");
            graveyard.setWeapon(titaniumSword);
        }
    }
    
    //Opens a "chest" if there's one in the room.
    private void openChest(Command command)
    {
        String chest = command.getSecondWord();
        if(chest == null){
            System.out.println("Open what?");
        }
        //individual use methods for each item in the game
        else if(chest.equals("chest") && currentRoom == castle){
            System.out.println("You died. Mighty unfortunate.");
            alive = false;
        }
        else if(chest.equals("chest") && currentRoom == ossuary){
          System.out.println("Well aren't you a lucky lucky person." + "\n");
          System.out.println("*silver sword appeared!*" + "\n" + "*potion appeared!*" + "\n");
          ossuary.setWeapon(silverSword);
          ossuary.setItem(potion);
        }
    }
       
    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
       
       
        
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction); 
            
            if (nextRoom == null) 
            {
                    System.out.println("There is no door!");
            }
            else 
            {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                printInfo();
            }
        
    }
        
    //Checks if the final boss is defeated. 
    private void checkVictory()
    {
        if(throne.getEnemyHealth() <= 0)
        {
           System.out.println("Congrats! The land is now free from all chaos and destruction and you have taken your grand oppurtunity" + "\n" + 
           "and became a grusome facist dictator over the land." + "\n" + "And there goes the story of the French Revolution."
           );
           alive = false;
        }
    }
    
    //quits the game
    private boolean quit(Command command){
    
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        
        }
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
	//for random numbers use --- Math.floor(Math.random()*(max-min+1)+min)
	//for string contantonations use --- String.valueOf()
	//for x in range use --- int x = 0; and while (x < (n) { and x++
	//need this for use in all static thingys
	static String output;
  //Scanner scan = new Scanner(System.in);
	//change all numbers later
	//player hp and total hp
	static boolean fightstart = false;
	static int php = 100;
	static int pthp = 100;
	static String playername = "";
	//enemy hp and total hp
	static int ehp = 100;
	static int ethp = 100;
	//player magic and total magic
	static int pmg = 30;
	static int ptmg = 30;
	static boolean edead = false; //if enemy is dead
	static List<String> itemlist = new ArrayList<String>();
	static List<Integer> avaibleitems = new ArrayList<Integer>();//a temp list
	static List<Integer> playeritems = new ArrayList<Integer>();
	//player items = [0, 0, 0]
	static int hPot = 25; //health pot healing
	static int mPot = 10; //magic pot healing
	static int fight = 0; //what fight you are in. Eg. Frog Fight = 0, Bird Fight = 1
	//available spells
	static boolean critfrog = false;
	//all below are story things
	static boolean photo = false;
	static boolean oldcatsword = false;
	static boolean birddead = false;
	static boolean backstab = false;
	static boolean witchcrit = false;
	//achievements
	static boolean critfrogachievement = false;
	static boolean critmissachievement = false;
	static int gimmebackachievement = 0;
	static boolean sirachievement = false;
  static boolean starwarsachievement = false;
  static boolean yourmomachievement = false;
	//names of attackers
	//ideas - Small Doge, Doge, Large Doge
	static List<String> fightname = new ArrayList<String>();
	//fightname = ["Frog", "Bird", "Troll", "King Troll", "Witch Elk"]
	
	static int outputnum;
	
	static List<String> storyparts = new ArrayList<String>();
	
	static int numtest;
	//terminates program
	static void quit() {
		
		System.exit(0);
	
	}

	static void clear() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }
	
	static void wait(int x) throws InterruptedException {
		sleep(x);
		clear();
	}
	
	static void wait(double x) throws InterruptedException {
		sleep(x);
		clear();
	}
	
	static void randoms(int min, int max) {
		outputnum = (int) Math.floor(Math.random()*(max-min+1)+min);
	}
	
	static int randomsr(int min, int max) {
		return (int) Math.floor(Math.random()*(max-min+1)+min);
	}
	
	static void prhp() {
		  if (playername == "") {
		    print("??? Health: " + String.valueOf(php) + "/" + String.valueOf(pthp));
		    print("??? Magic: " + String.valueOf(pmg) + "/" + String.valueOf(ptmg));
		  }
		  else {
		    print(playername +  " Health: " + String.valueOf(php) + "/" + String.valueOf(pthp));
		    print(playername +  " Magic: " + String.valueOf(pmg) + "/" + String.valueOf(ptmg));
		  }
		  print(fightname.get(fight) + " Health: " + String.valueOf(ehp) + "/" + String.valueOf(ethp));
	}
	
	static void attackmenu() throws InterruptedException {
		  clear();
		  String name = fightname.get(fight);
		  name = name.toLowerCase();
		  print("You hit the " + name + " with your sword.");
		  int damage = (int)Math.floor(Math.random()*(15-5+1)+5);
		  int crit = (int)Math.floor(Math.random()*(30-1+1)+30);
		  sleep(1);
		  if (crit == 29 || crit == 30) {
		    print("It's a Crit!");
		    sleep(0.5);
		    damage = damage * 2;
		    print("You hit for " +  String.valueOf(damage) + " damage.");
		    ehp = ehp - damage;
		  }
		  else if (crit == 1) {
		    print("It's a Critical Miss!");
		    sleep(1);
		    print("You stubbed your hoove and hit yourself for " +  String.valueOf(damage) + " damage.");
		    php = php - damage;
		    sleep(4);
		  
		    if (critmissachievement == false) {
		      clear();
		      print("Achievement Unlocked - Stubbed Hoove");
		      sleep(6);
		      critmissachievement = true;
		    }
		  }
		  else {
		    print("You hit for " +  String.valueOf(damage) + " damage.");
		    ehp = ehp - damage;
		  sleep(1);
		  counter();
		  }
	}
	
	static void magicmenu() throws InterruptedException {
		  int x = 3;
		  clear();
		  prhp();
		  print("\n\n0. Back");
		  print("\n1. Uproot");
      print("\n2. Branches");
      print("\n3. Kamikaze");
		  if (critfrog == true) {
		    x = x + 1;
		    print("");
		    print(String.valueOf(x) + ". Crit the Frog");
		  }
		  selectb("", x);
		  if (outputnum == 0) {
        combatmenu();
      }
      else if (outputnum == 1) {
		    if (pmg >= 10) {
		      pmg = pmg - 10;
		      int damage = randomsr(10, 15);
		      ehp = ehp - damage;
		      if (ehp < 0) {
		        edead = true;
		      }
		      print("You shake the ground and cause " + String.valueOf(damage) + " damage!");
		      sleep(2);
		    }
		    else {
		      print("You don't have enough magic!");
		      sleep(2);
		    }
		  }
      else if (outputnum == 2) {
        if (pmg >= 15) {
		      pmg = pmg - 15;
		      int damage = randomsr(8, 20);
		      ehp = ehp - damage;
		      if (ehp < 0) {
		        edead = true;
		      }
		      print("Branches come from the ground and cause " + String.valueOf(damage) + " damage!");
		      sleep(2);
		    }
		    else {
		      print("You don't have enough magic!");
		      sleep(2);
		    }
      }
      else if (outputnum == 3) {
      edead = true;
      print("You stab yourself, and your enemy is so confused it dies of shock.");
      sleep(5);
      int i = randomsr(1, 2);
      if (i == 1) {
        pthp = pthp/2;
        print("You somehow survive with a permanent wound.");
        sleep(5);
      }
      else {
      print("You bleed out on the ground.");
      sleep(5);
      php = 0;
      }
      counter();
		  }
    else if (outputnum == 4) {
		    if (pmg >= 5) {
		      if (fight == 0) {
		        print("You crit the frog, doing so much damage that you even kill it's immortal soul.");
		        ehp = 0;
		        sleep(4);
		        if (critfrogachievement == false) {
		          clear();
		          print("Achievement Unlocked - Destroy a Frog's Soul");
		          critfrogachievement = true;
		          sleep(6);
		        }
		      }
		      else {
		        print("It's not a frog, so it does 0 damage!");
		        sleep(3);
		      }
		      pmg = pmg - 5;
		    }
		    else {
		      print("You don't have enough magic!");
		      sleep(2);
		    }
		  }
    }
	static void itemmenu() throws InterruptedException {
		  clear();
		  prhp();
		  boolean checknone = true;
		  int x = 0;
		  while (x + 1 < (itemlist.size())) {
			  x++;
		    if (!(playeritems.get(x) == 0)) {
		      checknone = false;
		    }
		  }
		  if (checknone == true) {
		    print("\n\nYou don't have anything!");
		    sleep(4);
		    combatmenu();
		  }
		  else {
		    print("");
		    print("");
		    print("0. Back");
		    print("");
		    avaibleitems.clear();
		    int length = itemlist.size();
		    x = 0;
		    while (x + 1 < (length)) {
		      x++;
		      if (!(playeritems.get(x) == 0)) {
		        avaibleitems.add(x);
		      }
		    }
		    x = 0;
		    while (x < avaibleitems.size()) {
		    	print(String.valueOf(x + 1) + ". " + itemlist.get(avaibleitems.get(x)) + " - " + String.valueOf(playeritems.get(avaibleitems.get(x))));
		        print("");
          x++;
		    }
		    x = 0;
		    while (true) {
		      inputnum("");
		      if (outputnum > 0 && outputnum <= avaibleitems.size()) {
		          break;
		      }
		    }
	
		  }
		    if (outputnum == 0) {
		      combatmenu();
			}	      
		    else if (avaibleitems.get(outputnum - 1) == 0) {
		      php = php + hPot;
		      if (php > pthp) {
		        php = pthp;
		      }
		      playeritems.set(0, (playeritems.get(0) - 1));
		      combatmenu();
		    }
		    else if ((int)avaibleitems.get(outputnum - 1) == 1) {
		      pmg = pmg + mPot;
		      if (pmg > ptmg) {
		        pmg = ptmg;
		      }
		      playeritems.set(1, (playeritems.get(1) - 1));
		      combatmenu();
		    }
		    else if (((int)avaibleitems.get(outputnum - 1)) == 2) {
		      clear();
		      print("You drink the mystical potion.");
		      sleep(4);
		      print("\nAnd collapse to the ground!");
		      sleep(4);
		      clear();
		      print("You died!");
		      quit();
		    }
	}
		  
  static void talkmenu() throws InterruptedException {
    	  clear();
    	  if (fight == 0) {
    	    print("You try to reason with the frog.");
    	    sleep(2);
    	    print("\nIt\'s only response is \"Ribbit\".");
    	    sleep(2);
    	  }
    	  else if (fight == 1) {
    	    print("You try to reason with the bird.");
    	    sleep(2);
    	    print("\nAll it does is squawk back at you.");
    	    sleep(2);
    	  }
    	  else if (fight == 2) {
    	    print("You try to reason with the troll.");
    	    sleep(2);
    	    print("\nIt responds with a low growl, seemingly not understanding what you are saying.");
    	    sleep(2);
    	  }
    	  else if (fight == 3) {
    	    print("You try to reason with King Troll.");
    	    sleep(2);
    	    print("\n*Troll Noises*.");
    	    sleep(2);
    	  }
    	  else if (fight == 4) {
    	    print("You try to reason with the Witch Elk.");
    	    sleep(2);
    	    print("\n\"You attacked me first you idoit!\"");
    	    sleep(2);
    	  }
    	  combatmenu();
      }
		      
	static void enemyinfo() throws InterruptedException {
		  clear();
		  if (fight == 0) {
		    print("It's a frog, ribbit");
		  }
		  else if (fight == 1) {
		    print("It\'s a very large bird");
		  }
		  else if (fight == 2) {
		    print("It\'s a troll, kind of like the one from Harry Potter.");
		    sleep(4);
		    print("\nWait, what is Harry Potter?");
		    sleep(4);
		    print("\nNevermind.");
		  }
		  else if (fight == 3) {
		    print("It\'s the king of the trolls, and he looks visibly angry.");
		  }
		  else if (fight == 4) {
		    print("A kind witch, turned mean by your aggression.");
		  }
		  sleep(4);
		  combatmenu();
	  }

  static void achievements() throws InterruptedException {
    clear();
    if (critfrogachievement == false && critmissachievement == false && gimmebackachievement < 2 && sirachievement == false && starwarsachievement == false && yourmomachievement == false)  {
      print("No Achievements Got!");
      sleep(4);
      combatmenu();
    }
    else {
      print("Achievements:");
      if (yourmomachievement == true) {
        print("\nYour Mom - Gottem");
      }
      if (critfrogachievement == true) {
        print("\nDestroyed a Frog's Soul - You Merked a Frog");
      }
      if (critmissachievement == true) {
        print("\nStubbed Hoove - HEHE, ye stubbed yer hoove");
      }
      if (gimmebackachievement == 2) {
        print("\nStab All The Backs - Well, Off To Visit Your Mother! (https://youtu.be/ronPG90mvr8)");
      }
      if (sirachievement == true) {
        print("\nHello, Sir - Fancyyyyy");
      }
      if (starwarsachievement == true){
        print("\nStar Wars lol");
      }
      enter();
      combatmenu();
    }
  }
    
	static void counter() throws InterruptedException {
		  int damage;
		  int heal;
		  clear();
		  if (ehp < 1 || edead == true) {
		    edead = true;
		    combatmenu();
		  }
		  else {
		    if (fight == 0) {
		      print("The frog hits back for 0 damage!");
		    }
		    else if ((fight == 1)) {
		      damage = (int) Math.floor(Math.random()*(7-3+1)+3);
		      print("The bird swoops down and attacks for " + String.valueOf(damage) + " damage!" );
		      php = php - damage;
		    }
		    else if ((fight == 2)) {
		      damage = (int) Math.floor(Math.random()*(10-5+1)+5);
		      print("The troll attacks you with his bat, doing " + String.valueOf(damage) + " damage!");
		      php = php - damage;
		    }
		    else if ((fight == 3)) {
		      damage = (int) Math.floor(Math.random()*(14-8+1)+8);
		      print("King Troll attacks you with his large bat, doing " + String.valueOf(damage) + " damage!" );
		      php = php - damage;
		    }
		    else if ((fight == 4)) {
		      damage = (int) Math.floor(Math.random()*(14-8+1)+8);
		      heal = (int) Math.floor(Math.random()*(6-3+1)+3);
		      print("The Witch Elk attacks you with her magic, doing " + String.valueOf(damage) + " damage!" );
		      sleep(2);
		      print("Her black magic also heals her for " + String.valueOf(heal) + " health!");
		      php = php - damage;
		      ehp = ehp + heal;
		    }
		    else {
		    	print("error 1, please contact kaden");
		    	quit();
		    }
		    sleep(2);
		    combatmenu();
		  }
		    
	}
	
	static void combatmenu() throws InterruptedException {
	  clear();
	  if (fightstart == false) {
	    if (fight == 0) {
	      ehp = 30;
	      ethp = 30;
	    }
	    else if (fight == 1) {
	      ehp = 50;
	      ethp = 50;
	    }
	    else if (fight == 2) {
	      ehp = 100;
	      ethp = 100;
	    }
	    else if (fight == 3) {
	      if (backstab == false) {
	        ehp = 150;
	        ethp = 150;
	      }
	      else {
	        ehp = 100;
	        ethp = 150;
	      }
	    }
	    else if (fight == 4) {
	      if (witchcrit == false) {
	        ehp = 100;
	        ethp = 100;
	      }
	      else {
	        ehp = 75;
	        ethp = 100;
	      }
	  }
	  }
	  fightstart = true;
	  if (php < 1) {
	    print("You died!");
	    quit();
	  }
	  else if (edead == true) {
	    print("You Won!");
	    edead = false;
	    fightstart = false;
	    ehp = ethp;
	    if (critfrog == false) {
	      if (fight == 0) {
	        print("\nYou learned the spell \"Crit The Frog\", this spell crits all frogs!");
	        critfrog = true;
	      }
	    }
	    pmg = ptmg;
	    php = pthp;
	    sleep(3);
	    clear();
	    }
	  else {
	    prhp();
	    print("");
	    print("");
	    print("(1) Attack");
	    print("");
	    print("(2) Magic Attack");
	    print("");
	    print("(3) Use An Item");
	    print("");
	    print("(4) Talk");
	    print("");
	    print("(5) Enemy Info");
	    print("");
      print("(6) Achievements");
	    print("");
	    select("1, 2, 3, 4, 5, or 6", 6);  
	    if (outputnum == 1) {
	       attackmenu();
	    }
	    else if (outputnum == 2) {
	       magicmenu();
	    }
	    else if (outputnum == 3) {
	        itemmenu();
	    }
	    else if (outputnum == 4) {
	        talkmenu();
	    }
	    else if (outputnum == 5) {
	        enemyinfo();
	    }
      else if (outputnum == 6) {
	        achievements();
	    }
	    else {
	    	print("error 2, please contact kaden");
	    	quit();
	    }
	  }
	  }

  static void dots(int x) throws InterruptedException {
  	while (x < 10) {
  	  print(".");
  	  wait(.05);
  	  print("..");
  	  wait(.05);
  	  print("...");
  	  wait(.05);
  	  x += 1;
  	  }
  }
  
	static void sleep(double x) throws InterruptedException {
		
		x = x * 1000.0;
		
		Thread.sleep((int) x);
	}
	
	static void sleep(int x) throws InterruptedException {
		
		x = x * 1000;
		
		Thread.sleep(x);
	}
	
	static void inputnum(String x, int y, int z) {
		//initilize scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
				
		System.out.print(x);
				
		//below wont allow for anything other that int to be inputted
		while (!input.hasNextInt()) {
			input.next();
			System.out.print(x);
		}
		
		numtest = input.nextInt();
		if (!(numtest >= y && numtest <= z)) {
			inputnum(x, y, z);
		}
		//assignment
		else {
		outputnum = numtest;
		}
	}
	
	static void inputnum(String x){
			
		//initilize scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.print(x);
		
		//below wont allow for anything other that int to be inputted
    while (!input.hasNextInt()) {
			input.next();
			System.out.print(x);
		}
		
		//assignment
		outputnum = input.nextInt();
		
	}
	
	static void input(String x) {
		
		//initilize scanner
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.print(x);
		
		//assignment
		output = input.toString();
	}
	//input return
	static String inputr(String x) throws InterruptedException {
		
		//initilize scanner
		@SuppressWarnings("resource")
		Scanner inputs = new Scanner(System.in);
		
		System.out.print(x);
		//assignment
		String tempoutput =  inputs.nextLine();
		return tempoutput;
	}
	//for yes or no questions
	static void ask(String currentquestion) throws InterruptedException {
		String answer = inputr(currentquestion);
		while (true) {
			answer = answer.toLowerCase();
			if (answer.equals("yes") || answer.equals("y") || answer.equals("ye") || answer.equals("yessir") || answer.equals("yar") || answer.equals("roger")) {
		      if (answer.equals("roger")) {
            starwarsachievement = true;
            print("Star Wars lol");
          }
          outputnum = 1;
		      clear();
		      break;
		    }
		    
		    else if (answer.equals("no") || answer.equals("n") || answer.equals("na") || answer.equals("nah")) {
		      outputnum = 0;
		      clear();
		      break;
		    }
		    else {
		      answer = inputr("Please answer yes or no\n");
		    }
		  }
	}
	
	static void enter() throws InterruptedException {
		
		String test = inputr("Press Enter ");
		
		while (true) {
		test += " ";
		if (test.equals(" ")) {
			break;
		}
		
		else {
			test = inputr("Press Enter");
		}
			
		}
		
	}

  static void closet(int x) throws InterruptedException {
    print("                                  .,\n                              (%######%(\n                         .%%#%#%#%#%#%#%#%%%.\n                        *//@%%%################%,\n                        **//,*/&&%#%#%#%#%#%#%#%#%#%(\n                        **//////**/#@%%%##############%#.\n                        **////////*//,/(@%%%#%#%#%#(((((.\n                        **////////*//////,//@%#(((((((((.\n                        **////////*/////////,&((((((((((.\n                        **////////*/////////,&((((((((((.\n                        **////////*/////////,&((((((((((.\n                        **///////%*/////////,&((((((((((.\n                        **////////*/(///////,&((((((((((.\n                        **////////*/////////,&((((((((((.\n                        **////////*/////////,&((((((((((.\n                        **////////*/////////,&((((((((((.\n                        **////////*/////////,&((((((((((.\n                        *&%/*,////*/////////,&((((((((((.\n                            .&%//,*/////////,&((((((((((.\n                                 &&//,//////,&(((((((((#,\n                                     #&(/*//,&(((((%@#\n                                         (&#/&(@@*");
    sleep(x);
    }
  
	static void select(String currentquestion, int totalselections) {
		  int answer = 0;
		  print(currentquestion);
		  while (true) {
		      inputnum("");
		      answer = outputnum;
		      if (answer > 0 && answer < (totalselections + 1)) {
		        outputnum = answer;
		        break;
		      }
		    else {
		    	print(currentquestion);
		    }
		    }
		  
		  	clear();
		  
	}
		static void selecti(String currentquestion,int totalselections, String options) {
		  int answer = 0;
		  print(currentquestion);
      
		  while (true) {
		      inputnum("");
		      answer = outputnum;
		      if (answer > 0 && answer < (totalselections + 1)) {
		        outputnum = answer;
		        break;
		      }
		    else {
		    	print(currentquestion);
		    }
		    }
		  
		  	clear();
		  
	}
	static void selectb(String currentquestion, int totalselections){
		  int answer = 0;
		  print(currentquestion);
		  while (true) {
		      inputnum("");
		      answer = outputnum;
		      if (answer > -1 && answer < (totalselections + 1)) {
            break;
		      }
		    else {
		    	print(currentquestion);
		    }
		    }
		  
		  	clear();
	}
	
	static void story() throws InterruptedException {
		String x = storyparts.get(0);
		print(x);
		  enter();
		  clear();
		  int parton = 1;
		  int temppart = 0;
		  while (true) {
		    while (!(temppart == parton)) {
		      print(storyparts.get(temppart));
		      temppart += 1;
		    }
		    print(storyparts.get(parton));
		    temppart = 0;
		    parton += 1;
		    if (!(parton < storyparts.size())) {
		      break;
		    }
		    enter();
		    clear();
		    
		  }
	}
	
	static void print(String x) {
		
		System.out.println(x);
		  
	}
	
	static void print(int x) {
		
		System.out.println(x);
		  
	}
	
	static void print(double x) {
		
		System.out.println(x);
		  
	}
	
	static void nprint(String x) {
			
		System.out.print(x);
			
	}
	
	static void nprint(int x) {
		
		System.out.print(x);
		  
	}
	
	static void nprint(double x) {
		
		System.out.print(x);
		  
	}
	
	static String capr(String x) {
		String y;
		y = x.substring(0, 1);
		y = y.toUpperCase();
		return (y + x.substring(1));
	}
	
	static void cap(String x) {
		String y;
		y = x.substring(0, 1);
		y = y.toUpperCase();
		output = (y + x.substring(1));
	}
	
	public static void main(String[] args) throws InterruptedException {
	//list of all items
  itemlist.add("Health Potion");
	itemlist.add("Mana Potion");
	itemlist.add("Mystical Potion");
	playeritems.add(0);
	playeritems.add(0);
	playeritems.add(0);
	//all fights
	fightname.add("Frog");
	fightname.add("Bird");
	fightname.add("Troll");
	fightname.add("King Troll");
	fightname.add("Witch Elk");
	storyparts.add("Standing in the rubble of a once great nation, turned to a disaster.");
	storyparts.add("A once flourishing place filled with life and beauty, turned to death and destruction.");
	storyparts.add("You are now going to pick up the pieces of the ruined kingdom.");
	storyparts.add("And this is where you start, where the last hero ended.");
	storyparts.add("A new beginning to what was going to be an end.");
	storyparts.add("Find the history of this forgotten kingdom and figure out how to stop history from repeating itself.");
	storyparts.add("The clock is ticking, you have to hurry before it happens again.");
	
	enter();

	clear();

  inputnum("Achievement Code? ");

  if (outputnum == 3818) {
  print("Achievement Unlocked - Your Mom");
  yourmomachievement = true;
    sleep(5);
  }
  else {
    print("WRONG");
    sleep(3);
  }

  clear();
    
	story();

	input("Press enter to continue to game ");

	clear();

	print("Standing in the rubble, you want to look around. You see a crater where it all happened, a Grand Oak, and rubble nearby.");
	print("\n\n\n1. Search the Rubble");
	print("\n2. Observe the Crater");
	print("\n3. Climb the Grand Oak\n");

	select("What do you search first?", 3);

	if (outputnum == 1) {
	  print("You find a photo of an elk family, and put it in your pocket");
	  photo = true;
	  print("Standing in the rubble, you want to look around. You see a crater where it all happened and a Grand Oak\n\n\n");

	  print("1. Observe the Crater\n");

	  print("2. Climb the Grand Oak\n");

	  select("What do you do now?", 2);

	  if (outputnum == 1) {
	    print("You notice something shiny, you walk towards it and see that it's a sword.");
	    print("\n\n");
	    ask("Do you pick it up the sword? ");

	    if (outputnum == 1) {
	      print("You pick up the sword\n");
	      oldcatsword = true;
	      sleep(2);
	      print("A frog jumps out at you!");
	      fight = 0;
	      sleep(2);
	      combatmenu();
	    }

	    else if (outputnum == 2) {
	      print("You decide to leave the sword where it is");
	      oldcatsword = false;
	    }
	    wait(2);
	    print("Off in the distance, you see an old tree that you believe you can better view from, so you decide to climb it");
	  }
	}
	else if (outputnum == 2) {
	  print("You notice something shiny, you walk towards it and see that it's a sword.");
	  print("\n\n");
	  ask("Do you pick it up the sword? ");

	  if (outputnum == 1) {
	    print("You pick up the sword\n");
	    oldcatsword = true;
	    sleep(2);
	    print("A frog jumps out at you!");
	    fight = 0;
	    sleep(2);
	    combatmenu();
	  }
	  else if (outputnum == 2) {
	    print("You decide to leave the sword where it is,\n");
	    oldcatsword = false;
	  }

	  print("Standing in the rubble, you want to look around. You see a crater where it all happened, a Grand Oak, and rubble nearby.");
	  print("\n\n\n1. Search the Rubble");
	  print("\n2. Climb the Grand Oak\n");

	  select("What do you do now?", 2);

	  if (outputnum == 1) {
	    print("You find a photo of an elk family, and put it in your pocket.");
	    photo = true;
	    wait(2);
	    print("Off in the distance you see an old tree that you believe you can better view from, so you decide to climb it.");
	  }
	  }
	storyparts.clear();
	storyparts.add("You climb the tree and see an old shack about 60 yards away from you.");
	storyparts.add("You climb back down the tree, and head towards the shack.");
	storyparts.add("The door to the shack is ajar. You push it open and it gives a slow, creak.");
	storyparts.add("As you walk through the doorway into the house, you can feel the floor give with each step.");
	storyparts.add("A part of you is worried it could give out, but you continue anyways. As you look around you hear something, a small whisper perhaps?\n");
	storyparts.add("\"What are you doing in my home?\" a gravelly voice asks from behind you.\n");
	storyparts.add("You whip around to see a tall, black and white, scruffy-looking cat standing on 2 legs, you don't respond to his question for a second.\n ");
	storyparts.add("You see a flicker of metal and look down. The mysterious cat has a sword pointed at your long brown elk throat.\n");
	storyparts.add("\"I said what are you doing in my house?\"\n ");
	storyparts.add("At this, you gain your voice back to say, \"I\'m here to figure out what happened to King Doge and why my Grandfather disappeared.\"\n");
	storyparts.add("At this, the old cat responds with, \"What do you want to know?\"\n");
	storyparts.add("\"I want to know what happened, if they are dead, and why they disappeared.\"\n");
	storyparts.add("The old cat responds with \"Are you sure you want to go down that path because it is a journey many people have tried, but few have succeeded.\"\n");

	story();
	print("\n\n\n");
	ask("Are you sure if you want to continue on?\n");
	if (outputnum == 0) {
	  print("\"No. I\'m not ready.\" you say. You turn around leaving the shack, the crater, the grand oak behind. You are leaving your Grandfather and the missing King Doge a mystery.\nOne you may never find out now. But all that's left is you and the sound of your hooves clacking on the ground as you walk.");
	  quit();
	}
	print("\"I will help you, but first you must retrieve my old sword, and bring me a dead bird.\""); //hungry catt
	sleep(2);
	if (oldcatsword == true) {
	  print("\"Oh, and since I see you already stole my sword from that crater, you can use it to kill the bird\"");
	  sleep(4);
	  fight = 1;
	  print("You put the potions in your pocket. You walk out the door and quickly find a bird to kill");
	  sleep(4);
	  combatmenu();
	}
	else {
	  print("\"Go grab my sword from the crater, you can use it to kill the bird\"");
	  sleep(4);
	  print("You walk back to the crater and pick up the sword");
	  sleep(4);
	  print("A frog jumps out at you!");
	  fight = 0;
	  sleep(3);
	  combatmenu();
	  print("You find a bird to kill for the cat.");
	  fight = 1;
	  sleep(4);
	  combatmenu();
	}
	storyparts.clear();
	storyparts.add("Once you handed the dead bird to the cat he thanks you and promptly rips the flesh off and eats the bird. You turn away in disgust until the cat is finished with the bird.\n");
	storyparts.add("\"Thank you for that. It\'s been forever since I\'ve had fresh bird. \nI never did tell you my name. I\'m Mr. Meowster, an old General for the Cat Kingdom army.\"\n");
	storyparts.add("What is your name brave elk warrior?\n");
	story();
	while (true) {
	    String answer = inputr("");
	    if (answer.length() < 17 && answer.length() > 0) {
	      playername = answer;
	      playername = capr(playername);
	      break;
	    }
	    else {
	    print("Your name, Sir.");
	    }
	}
	if (playername.equals("Sir")) {
		sirachievement = true;
    print("Achievement Unlocked - Hello, Sir");
	}
	storyparts.clear();
	storyparts.add("\"Thank you " + playername + ". For your help I grant you 3 Health potions, 3 Mana Potions, and 1 Mystical Potion. Be wary when you use the Mystical Potion, for the potion has not been tested yet.");
	storyparts.add("You have no clue what he means by that, but it doesn\'t matter, right now at least.");
	storyparts.add("\nThe old cat continues, \"A long long time ago there lived happy Kingdoms. The Cat Kingdom held many slumber parties and invited others from all over.\"");
	storyparts.add("\n\"King Doge was angered by this and declared war on us cats. He had surrounded our armies and we had to call your people, the elks for help. Together the elks and the cats were able to drive the doges back, but there was a downfall the kingdom was ruined.\n It took years, but the kingdom had finally grown back to a bustling city full of life.\"");
	storyparts.add("\"This is where things went wrong because King Doge still wanted to join the slumber parties, but we said no. He declared war again and his army tore our homes apart. They surrounded the castle, and the elks came to our aid. The greatest elk warrior of all time, Albert the Great, was sent in to kill King Doge.\"");
	storyparts.add("\"Nobody knows what happened in their fight, because once the dust settled around the kingdom neither were to be found. They are believed to be dead, but others, myself included believe they could still be alive\"");
	storyparts.add("\"You must travel down the wander\'s path to the Troll Kingdom. From there you must collect the sacred bronze Crown that is believed to belong to the once King Doge. It is believed the give the one who wears if special abilities.\"");
	storyparts.add("At this, you turn away and gallop out of the house and into the woods. You have two ways you can go one to the path towards a bright, sparkling spring, or the dark spooky-looking path that leads to the mountains.");
	story();
	playeritems.set(0, 3);
	playeritems.set(1, 3);
	playeritems.set(2, 1);
	print("1. Sparkling Spring ");
	print("2. Head towards the spooky mountains ");
	select("Which path do you choose?", 2);
	if (outputnum == 1) {
	  print("You turn down that path heading towards the spring once you get to the spring you see a shimmer at the bottom of the spring.");
	  ask("Do you wish to swim down and find out what the shimmer is?");
	  if (outputnum == 1) {
	    print("You leap into the water, and begin to swim down slowly, as hooves are not great for swimming.");
	    sleep(4);
	    print("Suddenly, a tentacle wraps around your torso and you get pulled down to the murky depths. You try to struggle but it is no use.");
	    sleep(4);
	    clear();
	    print("You Died!");
	    quit();
	  }
	  else if (outputnum == 2) {
	    print("You decide to not swim down as you are an elk and elk can't swim, so that would be a stupid decision.");
	    sleep(4);
	  }
	}
	storyparts.clear();
	storyparts.add("You trot down the spooky path that head towards the bottom of the mountains, after a bit you hear the woods get quiet. At this you become on edge. Quiet woods are not safe woods. You hear a crunch behind you.");
	storyparts.add("You whip around, stumbling over your hooves, to see...");
	story();
	sleep(.5);
	print("A rabbit munching on some leaves.");
	print("You give a neigh of relief and turn back around to continue on your journey.");
	sleep(3);
	print("There is a big ugly looking troll in front of you, breathing heavily.");
	sleep(3);
	print("You scream, alerting any trolls nearby, but for now, you have to deal with the one infront of you (Click --> https://youtu.be/vmlN5W6CWs8?t=18)");
	enter();
	sleep(5);
	fight = 2;
	combatmenu();
	//sphyics ans denemy ask question then kill
	//eat bed = gloden apple minecraf
	storyparts.clear();
	storyparts.add("After killing the troll you get a quick breath before you hear a stampede.");
	storyparts.add("You look to your left and see a clan of trolls running after you.");
	storyparts.add("You have very little time to gather your things and run.");
	storyparts.add("You see a small building a little father into the village.");
	storyparts.add("You gallop as fast as you can towards that building.");
	storyparts.add("As you get closer you are able to see a sign.");
	story();
	print("|--------------------------|");
	print("|                          |");
	print("|      Bed N Breakfast     |");
	print("|  Come N Take a Bed or 2  |");
	print("|                          |");
	print("|--------------------------|");
	print("              |             ");
	print("              |             ");
	print("              |             ");
	wait(10);
	storyparts.clear();
	storyparts.add("Once you get close enough to the bed N breakfast, you race to the door.");
	storyparts.add("Once you make it up the front porch, you burst through the door, run in and slam it shut.");
	storyparts.add("You turn to look out a window nearby and see the clan of trolls running past the Bed N Breakfast.");
	storyparts.add("You give a neigh of relief.");
	storyparts.add("There is a set of stairs infront of you.");
	storyparts.add("You decide to go down the stairs.");
	storyparts.add("Heading down the lowly light stairs you end in a dusty hallway.");
	storyparts.add("There is a door to your left that you decide to go into.");
	storyparts.add("In the room there is a small bed in the corner and other furniture covered by cloth.");
	storyparts.add("Deciding you are done with this room you leave, but once in the hallway you see another troll. He is wearing a crooked crown.");
	storyparts.add("It clicks in your head, this is the King Troll.");
	story();
	ask("Do you want to hide from King Troll? ");
	if (outputnum == 1) {
	  print("You hurry back into the room softly closing the door and quickly running into the closet.");
    closet(20);
	  print("Silly elk the troll sense you.");
	  sleep(2);
	  print("The troll busts through the room and rips the closet door off of the hinges.");
	  sleep(4);
	}
	else if (outputnum == 0) {
	  print("The troll is turned away from you.");
	  sleep(2);
	  print("You take this as an oppourtunity to surprise attack the King.");
	  backstab = true;
	  gimmebackachievement += 1;
	  sleep(4);
	print("You are now in battle with King Troll");
	sleep(6);
	fight = 3;
	combatmenu();
	storyparts.clear();
	storyparts.add("Once you check to make sure the King troll is actually dead, you look a little closer at his crown.");
	storyparts.add("You see an inscription of what looks like a doge? ");
	storyparts.add("A little voice in your head is telling to to put the crown on. ");
	story();
	ask("Do you put the crown on? ");
	if (outputnum == 1) {
		storyparts.clear();
		storyparts.add("Gently picking the crown up and setting it on your head, you start to feel tired, as if the crown is putting you to sleep.");
		storyparts.add("You head towards the bed thinking this is a great time to take a nap.");
		storyparts.add("Laying down you drift into what you thought would be a peaceful sleep.");
		storyparts.add("You look around, and see dust settling.");
		storyparts.add("You see a figure about 20 yards from you but you can\'t make out who it is.");
		storyparts.add("You turn and you run towards the woods, running through them and over the mountain.");
		storyparts.add("Whoever the figure is, is on your tail.");
		storyparts.add("Literally because you have a tail.");
		storyparts.add("You try to push yourself to run faster.");
		storyparts.add("Looking back you can see you are gaining distances.");
		storyparts.add("You continue running, running till you can no longer feel your legs.");
		storyparts.add("You look down...");
		storyparts.add("You have paws now.");
		storyparts.add("Looking back you can no longer see the figure chasing you.");
		storyparts.add("You begin to slow down, taking a look at your surroundings, you are in a snowy biome.");
		storyparts.add("You hear a growl behind you.");
		storyparts.add("Turning around, the last thing you see is a black and white figure jumping at you.");
		storyparts.add("You wake up with a jolt.");
		storyparts.add("At least it was a dream, but it felt so real.");
		storyparts.add("It must be the crown...");
		storyparts.add(" You start to feel hungry, but there are no rations in your bag, looking around you see the bed.");
		storyparts.add(" Should you eat the bed?");
		story();
	}
	ask("Do you eat the bed?");
	if (outputnum == 1) {
	  pthp = pthp + 50;
	  php = pthp;
	  print("Omnnomnomnomnomnom");
	  sleep(4);
	  print("Then a frog jumps out of the bed at you!");
	  sleep(4);
	  fight = 0;
	  combatmenu();
	}
	else if (outputnum == 0) {
	  print("You decide not to eat the beds, who knows what or who slept in them.");
	}
	storyparts.clear();
	storyparts.add("You decided you have spent enough time in the troll village.");
	storyparts.add("Leaving the village you continue out towards the mountain.");
	storyparts.add(" Looking at it, you think it looks familiar...");
	storyparts.add(" Its the mountain from your dream.");
	storyparts.add("You decide you should follow it, and see if it leads anywhere.");
	clear();
  storyparts.add("You are officially following your dreams.");
	dots(10);
    storyparts.add("As you canter onward to the mountain, wandering along a long forgotten and beaten up path.");
    storyparts.add("You've lost track of how long you were walking, it's just about sundown now.");
    storyparts.add("There is smoke coming from the trees ahead.");
    storyparts.add("Pushing through the fatigue, you carry on.");
    storyparts.add("As you approach the area where the smoke was coming from, you see a cottage up ahead.");
    storyparts.add("Walking towards the cottage you begin to feel an eerie sensation come over you.");
    storyparts.add("You are on edge and you don't know why.");
    storyparts.add("What could be so bad?");
    storyparts.add("As you are lost in your thoughts something jumps in front of you.");
    storyparts.add("You let out a bugle of surprise.");
    storyparts.add("It is another Elk.");
    storyparts.add("A witch Elk.");
    storyparts.add("They ask what you are doing here.");
    print("\n\n\n 1.\"Your mom\"");
    print("\n2.\"On a super secret mission\"");
    print("\n3.\"To find my grandfather\"");
    ask("What do you reply with?");
    if(outputnum == 1){
      clear();
      storyparts.clear();
      storyparts.add("Crickets...");
      storyparts.add("The sound of silence is all you heard.");
      storyparts.add("After you said that hurtful insult, you turn to walk away and continue on your journey.");
      storyparts.add("What you didn't expect is for the witch to want revenge.");
      storyparts.add("This is how your story ends, but don't worry you can complete this jorney.");
      print("Restart the game and enter the code: 3818");
    }
    else
      storyparts.clear();
    storyparts.add("The witch then asks you if are willing to trade for resources along your journey.");
    ask("Are you willing to trade?");
	//storyparts = [""]
	//storystorystorystorystorystorystorystorystorystorystory
	print("\n\n\n1. Barter with Witch Elk.");
	print("\n2. Politly decline and leave.");
	print("\n3. Stab her in the back.");
	select("What now?", 3);
	if (outputnum == 1) {
	  print("\"What do you have to trade with me?\" she asks");
	  print("\nYou take out everything in your pockets, and something catches her eye.");
	  if (photo == true) {
	    print("\n\"That photo, where did you get it?\"");
	    sleep(4);
	    print("\nYou shrug in response");
	    sleep(4);
	    print("\"That's a photo of my family I lost the other day, I will give you 3 Health Potions and 1 Mana Potion\"");
	    playeritems.set(0, (playeritems.get(0) + 3));
	    playeritems.set(1, (playeritems.get(1)) + 1);
	    sleep(4);
	    print("You hand over the picture and she gives you the potions. You walk out the door and you continue up the mountain.");
	  }
	  else {
	    print("\n\"Your sword, I want it\" she says.");
	    sleep(4);
	    print("\nYou quickly grab your sword off the table and the Witch Elk attacks you for it.");
	    sleep(4);
	    fight = 4;
	    combatmenu();
	  }
	}
	else if (outputnum == 2) {
	  print("as you try to leave she says no one refuse my offer ooh ee ooh ah ah");
	  fight = 4;
	  sleep(4);
	  print("fight time");
	  sleep(4);
	  combatmenu();
	}
	else if (outputnum == 3) {
	  print("You ask her to grab you something the shelf behind her, and then you promptly drive the sword into her back. She reels up and says \"Ooh ee ooh ah ah ting tang walla walla bing bang\"");
	  witchcrit = true;
	  gimmebackachievement += 1;
	  sleep(4);
	  fight = 4;
	  print("She turns to face you, the gash on her back slowly healing itself with black magic");
	  sleep(4);
	  combatmenu();
	}
	if (gimmebackachievement == 2) {
	  print("Achievement Unlocked - Stab All The Backs");
	  wait(4);
	}
	wait(4);
	print("You continue on your quest to find your grandfather.\nUntil next time.");
	sleep(5);
	}
	}
}
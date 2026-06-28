import sheffield.*;

public class Weapon {

   private final static int MAX_NUMBER=6;
   private static Weapon [] allTheWeapons;

   public static void initializeThem() {
	 EasyReader input = new EasyReader("weapons.txt");
	 int count=0;
	 allTheWeapons = new Weapon[MAX_NUMBER];
	 
	 while (!input.eof()) {
		 Weapon object = new Weapon(input.readString());
		 
		 allTheWeapons[count++] = object;		 
	 }
	 
	 listTheWeapons();

   }

   public static Weapon askWhichOne(EasyReader keyboard)  {
	 String whoWeapon = keyboard.readString("With what weapon? ");
	 
	 // Check for a match
	 for (int i=0; i<MAX_NUMBER; i++) {
		 whoWeapon = whoWeapon.toLowerCase().replace("_", " ");
		 
		 if (whoWeapon.contains(allTheWeapons[i].weapon.toLowerCase().replace("_"," "))) {
			 System.out.println("Weapon Found: " + allTheWeapons[i]);
			 return allTheWeapons[i];
		 }
	 }
	 
     return null;
   }

   public static void listTheWeapons()  {
     System.out.print("The weapons are "+allTheWeapons[0]);
     for (int i = 1; i<MAX_NUMBER-1; i++)
        System.out.print(", "+allTheWeapons[i]);
     System.out.println(" and "+allTheWeapons[MAX_NUMBER-1]);
   }

   public static Weapon pickedAtRandom() {
	 int randomWeapon = (int)(Math.random()*MAX_NUMBER);
	 
	 return allTheWeapons[randomWeapon];
   }

   private String weapon;

   private Weapon (String n)  {  weapon = n;  }

   public String toString()  {  return "the "+weapon.toLowerCase();  }

}

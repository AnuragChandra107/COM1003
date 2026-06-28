import sheffield.*;

public class Suspect {

   private final static int MAX_NUMBER=10;

   private static Suspect [] allTheSuspects;
   private static int numberOfSuspects;

   public static void initializeEveryone() {
     //Fill in this method body with your code for Task 1
	 EasyReader input = new EasyReader("suspects.txt");
	 
	 allTheSuspects = new Suspect[MAX_NUMBER];
	 numberOfSuspects=0;
	 
	 while (!input.eof()) {
		 Suspect object = new Suspect(input.readString());
		 
		 allTheSuspects[numberOfSuspects++] = object;		 
	 }
	 
	 listTheSuspects();
   }

   public static Suspect askWho(EasyReader keyboard)  {
     //Replace in this method body with your code for Task 4
	 String whoSuspect = keyboard.readString("Who do you accuse? ");
	 
	 // Check for a match
	 for (int i=0; i<numberOfSuspects; i++) {
		 whoSuspect = whoSuspect.toLowerCase().replace("_", " ");
		 
		 if (allTheSuspects[i].name.toLowerCase().replace("_"," ").contains(whoSuspect)) {
			 
			 return allTheSuspects[i];
		 }
	 }
     return null;
   }

   public static void listTheSuspects()  {
     System.out.print("The suspects are "+allTheSuspects[0]);
     for (int i = 1; i<numberOfSuspects-1; i++)
        System.out.print(", "+allTheSuspects[i]);
     System.out.println(" and "+allTheSuspects[numberOfSuspects-1]);
   }

   public static Suspect pickedAtRandom() {
     //Replace in this method body with your code for Task 3
	 int randomSuspect = (int)(Math.random()*numberOfSuspects);
	 
	 return allTheSuspects[randomSuspect];
   }

   private String name;

   private Suspect (String n)  {  name = n;  }

   public String toString()  {  return name;  }

}

import sheffield.*;

public class Scenario {

   private Suspect attacker;
   private Room attackedIn;
   private Weapon attackedWith;

   // Set the Random attacker, room and weapon
   public void setAtRandom() {
      attackedWith = Weapon.pickedAtRandom();
      attacker = Suspect.pickedAtRandom();
      attackedIn = Room.pickedAtRandom();
   }

   // Set the Guessed attacker, room and weapon
   public void setByAsking(EasyReader keyboard) {
      attackedIn = Room.askWhichOne(keyboard);
      attacker = Suspect.askWho(keyboard);
      attackedWith = Weapon.askWhichOne(keyboard);
   }

   public void askAboutWrongOnes(Scenario rightOne, EasyReader keyboard) {
	 // Wrong ones chosen
     if  (  attackedIn != rightOne.attackedIn )
          attackedIn = Room.askWhichOne(keyboard);
     if  (  attacker != rightOne.attacker  )
          attacker = Suspect.askWho(keyboard);
     if  (  attackedWith != rightOne.attackedWith  )
          attackedWith = Weapon.askWhichOne(keyboard);
   }

   public boolean hasBeenDiscovered(Scenario guess) {	 	 
	 // In case of null, return false
	 if ((guess.attackedIn == null) || (guess.attacker == null) || (guess.attackedWith == null)) 
		 return false;
	 
	 // Check if there is a match
	 if ((guess.attackedIn.name().toLowerCase().replace("_"," ").contains(
			attackedIn.name().toLowerCase().replace("_"," "))) &&
		 (guess.attacker.toString().toLowerCase().contains(
			attacker.toString().toLowerCase())) &&
		 (guess.attackedWith.toString().toLowerCase().contains(
			attackedWith.toString().toLowerCase())))
		return true; 
	 else {
		String message = "";
		 if (!guess.attackedIn.name().toLowerCase().replace("_"," ").contains(
			attackedIn.name().toLowerCase().replace("_"," "))) {
				if (guess.attackedIn.name().toLowerCase().replace("_", " ").contains("the"))
					message += " in " + guess.attackedIn.name().toLowerCase().replace("_"," ");
				else
					message += " in the " + guess.attackedIn.name().toLowerCase().replace("_"," ");
			}
		 if (!guess.attacker.toString().toLowerCase().contains(
			attacker.toString().toLowerCase())) 
				message += " by " + guess.attacker.toString();
		 if (!guess.attackedWith.toString().toLowerCase().contains(
			attackedWith.toString().toLowerCase())) 
				message += " with " + guess.attackedWith.toString();
		 
		 System.out.println("The victim was not attacked" + message);
		 
		return false;
	 }
	 
  }
  
  public boolean isUnknown() {
      return attacker == null || attackedIn == null || attackedWith == null;
  }

  public String toString() {
    return  "The victim was attacked in " + attackedIn + " by " +
                attacker + " with "+ attackedWith;
  }

}

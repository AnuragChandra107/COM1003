import sheffield.*;

public enum Room {

   KITCHEN,
   BALLROOM,
   CONSERVATORY,
   BILLIARD_ROOM,
   LIBRARY,
   STUDY,
   HALL,
   LOUNGE,
   DINING_ROOM;

   static int roomCount = Room.values().length;

   static Room askWhichOne(EasyReader keyboard)  {
	 String whichRoom = keyboard.readString("Where are you? ");
	 
	 Room rooms[] = Room.values();
	 
	 // Check for a match
	 for (int i=0; i<roomCount; i++) {
		 whichRoom = whichRoom.toLowerCase().replace("_", " ");
		 
		 if (whichRoom.contains(rooms[i].name().toLowerCase().replace("_"," "))) {
			 return rooms[i];
		 }
	 }
	 
     return null;
   }

   static Room pickedAtRandom() {
	 int randomRoom = (int)(Math.random()*roomCount);
	 	 
     return Room.values()[randomRoom];
   }

   public static void listThem() {
	 Room rooms[] = Room.values();
	 
	 System.out.print("The rooms are "+rooms[0]);
     for (int i = 1; i<roomCount-1; i++)
        System.out.print(", "+rooms[i]);
     System.out.println(" and "+rooms[roomCount-1]);
   }

   public String toString() {
     return "the "+name().toLowerCase().replace("_"," ");
   }

}

/*
* This program converts the weight of anything in pounds to it's weight in kilograms.
* Then, it computes the weight in another planet. 
* Finally, it displays the summary of the weights in different planets. 
*/

import sheffield.*;

public class Assignment1 { 
	
	public static void main(String args[]) {
		// Weight in pounds on Earth
		int weightInPounds; 
		
		// Weight in kilograms on Earth 
		double weightInKilograms;
		
		// Get an object of EasyReader 
		EasyReader input = new EasyReader();
		
		// Read the weight in pounds from the user. 
		weightInPounds = input.readInt("Please type in a weight in pounds : ");
		
		// Convert weight in pounds to kilogram. 
		weightInKilograms = weightInPounds * 0.453592;
		
		//Get an object of EasyWriter 
		EasyWriter output = new EasyWriter();
		
		// Print the weigjht in kilograms 
		output.print("That is "); 
		output.print(weightInKilograms,3);
		output.println(" kilograms\n"); 
		
		// Open the input file planets.txt 
		EasyReader fileInput = new EasyReader("planets.txt"); 
		
		// Read the first planet (planetOne)
		String planetOne = fileInput.readString();
		 
		// First planet's name 
		String planetName = planetOne.substring(0, planetOne.indexOf("'"));
		
		// Gravity on the first planet 
		String gravityOnPlanet = planetOne.substring(planetOne.lastIndexOf(" "));
		
		// Convert the gravity from string to double 
		Double gravityWithoutg = Double.valueOf(gravityOnPlanet.substring(0,gravityOnPlanet.indexOf("g")));
		
		// Print the first planet's details
		output.println(planetOne); 
		
		// Display the weight on Earth in kilograms
		output.print(weightInPounds + " pounds on Earth weighs ");
		output.print(weightInKilograms,4);
		output.println(" kilograms");
		
		// Display the weight on the first planet 
		output.print(weightInPounds + " pounds on " + planetName + " weighs "); 
		output.print(weightInKilograms*gravityWithoutg,4);
		output.println(" kilograms\n"); 
		
		// Print the summary of the weight on Earth 
		output.print("Earth"); 
		output.println(weightInKilograms,2,20-"Earth".length()); 
		
		// Print the summary of the weight on the first planet 
		output.print(planetName);
		output.println(weightInKilograms*gravityWithoutg,2,20-planetName.length());
		
		// Read the second planet (planetTwo)
		String planetTwo = fileInput.readString();
		 
		// Name of the second planet- reusing the variable planetName
		planetName = planetTwo.substring(0, planetTwo.indexOf("'"));
		
		// Gravity on planet two - reusing the variable gravityOnPlanet
		gravityOnPlanet = planetTwo.substring(planetTwo.lastIndexOf(" ")); 
		
		// Convert the string gravityOnPlanet to a double value
		gravityWithoutg = Double.valueOf(gravityOnPlanet.substring(0,gravityOnPlanet.indexOf("g")));
		
		// Print the summary of the weight on second planet
		output.print(planetName);
		output.println(weightInKilograms*gravityWithoutg,2,20-planetName.length());
		output.println(); 
		
	}
	
}
		
		
		
		 
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	






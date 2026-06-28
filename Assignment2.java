/************************************************************
** @author Anurag Chandra
** Assignment 2 
** COM1003
************************************************************/

import sheffield.*;

public class Assignment2 {

    public static void main(String args[]) {

        // Number of rows and columns in input data file 
        final int NUM_ROWS = 128;
        final int NUM_COLS = 120;

        // Scale for width and height of the screen  
        final int SCALE_WIDTH = 5;
        final int SCALE_HEIGHT = 3;

        final int SMALL_TREE_SCALE = 1;
        final int BIG_TREE_SCALE = 2;

        final int SMALL_TREE_WIDTH = 120 * SMALL_TREE_SCALE;
        final int SMALL_TREE_HEIGHT = 128 * SMALL_TREE_SCALE;

        final int BIG_TREE_WIDTH = 120 * BIG_TREE_SCALE;
        final int BIG_TREE_HEIGHT = 128 * BIG_TREE_SCALE;

        // Width and Height of the screen (600 x 768)
        final int SCREEN_WIDTH = SMALL_TREE_WIDTH * SCALE_WIDTH;
        final int SCREEN_HEIGHT = BIG_TREE_HEIGHT * SCALE_HEIGHT;

        // Percentage of horizon that must be green in colour
        final double HORIZON_PERCENT = 0.40;

        // Type of both small and big trees
        final int SMALL_TREE_TYPE = 0;
        final int BIG_TREE_TYPE = 1;

		// Read the input data file
        EasyReader input = new EasyReader("tree.txt");

        // Two-dimensional array to store the tree elements 
		//(Branch, Leaf, Trunk etc) 
        char[][] treeElements = new char[NUM_ROWS][NUM_COLS];

        // For loop to assign values for the background, leaves, trunk 
		// and branches. Reading the data file and applying the encryption 
		// rules to store the type of Tree Element
        for (int i = NUM_ROWS - 1; i >= 0; i--) {

            for (int j = NUM_COLS - 1; j >= 0; j--) {

                int value = (int) input.readChar();

                if (value % 2 == 0)

                    // G is the background
                    treeElements[i][j] = 'G';

                else {

                    // L is the leaves
                    treeElements[i][j] = 'L';

                    // T is the trunk of the tree
                    if (value % 5 == 0)
                        treeElements[i][j] = 'T';

                    // B is the branches of the tree
                    if (value % 7 == 0)
                        treeElements[i][j] = 'B';

                }

            }

        }
		
		////////////////////////////////////////////////////////////////////
		// First set of loops to draw the Trees that have only BRANCHES 
		// (WINTER)
		////////////////////////////////////////////////////////////////////

        // Randomising the total number of trees, small trees 
        double trees = Math.random();
        int numTrees = (int)(trees * 10 + 15);
		
		// Number of Trees in each row
        int rowTrees = numTrees / 2;
		
		// Percentage of Small Trees
        int smallTrees = (int)(numTrees * 0.2);

		// Array to store the tree type to make it easy to draw
        int[] treeType = new int[numTrees];

		// Loop to assign all Trees as BIG
        for (int k = 0; k < numTrees; k++) {

            treeType[k] = BIG_TREE_TYPE;
        }

        int smallTreeCount = 0;

		// Loop to set 20% of Trees to SMALL
        while (smallTreeCount < smallTrees) {

            int index = (int)(Math.random() * numTrees);

            if (treeType[index] != SMALL_TREE_TYPE) {

                treeType[index] = SMALL_TREE_TYPE;

                smallTreeCount++;

            }

        }

		// Initialize the Graphics Screen
        EasyGraphics tree = new EasyGraphics(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Assigning the values of the width, height and scale of the image 
        int imageWidth = BIG_TREE_WIDTH;
        int imageHeight = BIG_TREE_HEIGHT;
        int imageScale = BIG_TREE_SCALE;

        // Position from the base of the screen 
        int screenBase = 0;

        // Position of the horizon and the trees from the base of the screen
		// The HORIZON should upto 40% from the BASE
        int horizonPosition = screenBase + (int)(0.4 * BIG_TREE_HEIGHT);
		
		// The First Row of Trees should be at 30% between the BASE and HORIZON
        int treePosition = screenBase + 
			(int)((horizonPosition - screenBase) * 0.3);

        // Initialising the value of the number of tree treeElements to 0
        int numBackgroundTreeElements = 0;

        // For Loop to identify the number of elements in data file that is
		// indicated as belonging to the Background
        for (int i = NUM_ROWS - 1; i >= 0; i--) {

            for (int j = NUM_COLS - 1; j >= 0; j--) {

                int y = imageScale * i;

                if (treeElements[i][j] == 'G') {

                    if (y >= 0 && y <= horizonPosition)

                        numBackgroundTreeElements++;

                }

            }

        }

		// Randomly patterned 20% of ground should be green
        int greenBackgroundCount = (int)(0.2 * (
            double) numBackgroundTreeElements);

		// Array to store the Green patterned background
        int[][] greenTreetreeElements = new int[NUM_ROWS][NUM_COLS];

        int count = 0;

        // For loop for the brown background with green dots
        for (int k = 0; k < SCALE_WIDTH; k++) {

            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    int x = imageScale * j + k * SMALL_TREE_WIDTH;
                    int y = imageScale * i;

                    double num = Math.random();
                    int greenCount = (int)(num * 10);

                    if (treeElements[i][j] == 'G') {

                        if (y >= 0 && y <= horizonPosition) {

                            if (greenCount == 0) {

                                if (count / SCALE_WIDTH <=
                                    greenBackgroundCount) {

                                    tree.setColor(0, 200, 0);

                                    if (greenTreetreeElements[i][j] == 0) {

                                        greenTreetreeElements[i][j] = 1;

                                        count++;

                                    }

                                } else {

                                    tree.setColor(179, 77, 0);

                                }

                            } else {

                                tree.setColor(179, 77, 0);

                            }

                        } else

                            tree.setColor(135, 206, 235);

                    }

                    tree.fillRectangle(x, y, imageScale, imageScale);
                    tree.fillRectangle(x - 1, y - 1, imageScale,
                        imageScale);

                }

            }

        }

		// Number used to randomly position the tree horizontally
        double pos = Math.random() / 2 - 0.1;

		// First row of trees at 30% from BASE
        for (int k = 0; k < rowTrees; k++) {

            pos = pos + Math.random() / 2 - 0.1;

			// Check whether we need to draw the SMALL or BIG Tree
            if (treeType[k] == SMALL_TREE_TYPE) {

                // Assigning the values of the width, height and 
				// scale of the small trees
                imageWidth = SMALL_TREE_WIDTH;
                imageHeight = SMALL_TREE_HEIGHT;
                imageScale = SMALL_TREE_SCALE;

				// Small Trees should be positioned a little higher
                screenBase = imageHeight / 2;

                // Position of the trees from the base of the screen 
                treePosition = screenBase + (int)((horizonPosition -
                    screenBase) * 0.3);

            } else {

                imageWidth = BIG_TREE_WIDTH;
                imageHeight = BIG_TREE_HEIGHT;
                imageScale = BIG_TREE_SCALE;
                screenBase = 0;

				// Tree position at 30% from BASE
                treePosition = screenBase + (int)((horizonPosition -
                    screenBase) * 0.3);

            }

            // For loop for the branched trees
            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    // Value of integers x and y coordinates on screen
                    int x = imageScale * j + k * SMALL_TREE_WIDTH - 
						(int)(pos * imageWidth);
                    int y = imageScale * i + treePosition;

                    // Boolean to draw the tree
                    boolean drawTree = true;

                    if (treeElements[i][j] == 'B') {

                        // Generating a random number for the colour
                        double num = Math.random();
                        int green = (int)(num * 155 + 100);

                        tree.setColor(green, green / 2, 0);

                        // Assigning value for the trunk 
                    } else if (treeElements[i][j] == 'T') {

                        tree.setColor(118, 92, 72);

                    } else {

                        drawTree = false;

                    }

                    if (drawTree == true) {

                        // The image is being filled by colours here
                        tree.fillRectangle(x, y, imageScale, imageScale);
                        tree.fillRectangle(x - 1, y - 1, imageScale,
                            imageScale);

                    }

                }

            }

        }

        pos = Math.random() / 2 - 0.1;

        // Second Row of Trees starting at the BASE
        for (int k = rowTrees; k < numTrees; k++) {

            pos = pos + Math.random() / 2 - 0.1;

			// Check whether we need to draw the SMALL or BIG Tree
            if (treeType[k] == SMALL_TREE_TYPE) {

                // Width, height and scale of the small trees
                imageWidth = SMALL_TREE_WIDTH;
                imageHeight = SMALL_TREE_HEIGHT;
                imageScale = SMALL_TREE_SCALE;

                screenBase = imageHeight / 2;

            } else {

                // Width, height and scale of the big trees
                imageWidth = BIG_TREE_WIDTH;
                imageHeight = BIG_TREE_HEIGHT;
                imageScale = BIG_TREE_SCALE;

				// Drawing the Tree at the BASE
                screenBase = 0;

            }

            // For loop to draw the trees in the middle of the graphic screen 
            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    int x = imageScale * j + (k - rowTrees) *
                        SMALL_TREE_WIDTH - (int)(pos * imageWidth);
                    int y = imageScale * i + screenBase;

                    boolean drawTree = true;

                    // To draw the branches
                    if (treeElements[i][j] == 'B') {

                        double num = Math.random();
                        int green = (int)(num * 100 + 100);

						// Randomizing Brown Color
                        tree.setColor(green, green / 2, 0);

                        // To draw the trunk 
                    } else if (treeElements[i][j] == 'T') {

                        tree.setColor(118, 92, 72);

                    } else {

                        drawTree = false;

                    }

                    if (drawTree == true) {

                        tree.fillRectangle(x, y, imageScale, imageScale);
                        tree.fillRectangle(x - 1, y - 1, imageScale,
                            imageScale);

                    }

                }

            }

        }
		
		////////////////////////////////////////////////////////////////////
		// Second set of loops to draw the Trees that are the AUTUMN VERSION
		// The code is the same as the previous set of Trees except the 
		// following:
		// BASE where the tree starts will be different
		// TREE will have brown coloured leaves
		// Random number of trees and positions
		/////////////////////////////////////////////////////////////////////

        // Randomizing the values for the number of trees
        trees = Math.random();
        numTrees = (int)(trees * 10 + 15);
        rowTrees = numTrees / 2;
        smallTrees = (int)(numTrees * 0.2);

        treeType = new int[numTrees];

        for (int k = 0; k < numTrees; k++) {

            treeType[k] = BIG_TREE_TYPE;

        }

        smallTreeCount = 0;

        while (smallTreeCount < smallTrees) {

            int index = (int)(Math.random() * numTrees);

            if (treeType[index] != SMALL_TREE_TYPE) {

                treeType[index] = SMALL_TREE_TYPE;

                smallTreeCount++;

            }

        }

        imageWidth = BIG_TREE_WIDTH;
        imageHeight = BIG_TREE_HEIGHT;
        imageScale = BIG_TREE_SCALE;

		// Re-calculate the BASE 
        screenBase = SCREEN_HEIGHT / SCALE_HEIGHT;

        horizonPosition = screenBase + (int)(0.4 * BIG_TREE_HEIGHT);

        treePosition = screenBase + 
			(int)((horizonPosition - screenBase) * 0.3);

        // For loop to draw the background 
        for (int k = 0; k < SCALE_WIDTH; k++) {

            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    int x = imageScale * j + k * SMALL_TREE_WIDTH;
                    int y = imageScale * i + screenBase;

                    if (treeElements[i][j] == 'G') {

                        if (y >= 0 && y <= horizonPosition)

                            tree.setColor(0, 200, 0);

                        else

                            tree.setColor(135, 206, 235);

                    }

                    tree.fillRectangle(x, y, imageScale, imageScale);
                    tree.fillRectangle(x - 1, y - 1, imageScale,
                        imageScale);

                }

            }

        }

        pos = Math.random() / 2 - 0.1;

		// First Row of Trees
        for (int k = 0; k < rowTrees; k++) {

            pos = pos + Math.random() / 2 - 0.1;

            if (treeType[k] == SMALL_TREE_TYPE) {

                imageWidth = SMALL_TREE_WIDTH;
                imageHeight = SMALL_TREE_HEIGHT;
                imageScale = SMALL_TREE_SCALE;

                screenBase = SCREEN_HEIGHT / SCALE_HEIGHT + imageHeight / 2;

                treePosition = screenBase + (int)((horizonPosition -
                    screenBase) * 0.3);

            } else {

                imageWidth = BIG_TREE_WIDTH;
                imageHeight = BIG_TREE_HEIGHT;
                imageScale = BIG_TREE_SCALE;
                screenBase = SCREEN_HEIGHT / SCALE_HEIGHT;

                treePosition = screenBase + (int)((horizonPosition -
                    screenBase) * 0.3);

            }

            // For loop to draw the trees
            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    // Integers x and y which are the positions on the x and y axis  
                    int x = imageScale * j + k * SMALL_TREE_WIDTH - (int)(
                        pos * imageWidth);
                    int y = imageScale * i + treePosition;

                    boolean drawTree = true;

                    // To draw the branches and leaves - Brown in color
                    if (treeElements[i][j] == 'B' || 
							treeElements[i][j] == 'L') {

                        double num = Math.random();
                        int green = (int)(num * 100 + 100);

                        tree.setColor(green, green / 2, 0);

                        // To draw the trunk 
                    } else if (treeElements[i][j] == 'T') {

                        tree.setColor(118, 92, 72);

                    } else {

                        drawTree = false;

                    }

                    if (drawTree == true) {

                        tree.fillRectangle(x, y, imageScale, imageScale);
                        tree.fillRectangle(x - 1, y - 1, imageScale,
                            imageScale);

                    }

                }

            }

        }

        pos = Math.random() / 2 - 0.1;

		// Second row of trees
        for (int k = rowTrees; k < numTrees; k++) {

            pos = pos + Math.random() / 2 - 0.1;

            if (treeType[k] == SMALL_TREE_TYPE) {

                imageWidth = SMALL_TREE_WIDTH;
                imageHeight = SMALL_TREE_HEIGHT;
                imageScale = SMALL_TREE_SCALE;

                screenBase = SCREEN_HEIGHT / SCALE_HEIGHT + imageHeight / 2;

            } else {

                imageWidth = BIG_TREE_WIDTH;
                imageHeight = BIG_TREE_HEIGHT;
                imageScale = BIG_TREE_SCALE;

                screenBase = SCREEN_HEIGHT / SCALE_HEIGHT;

            }

            // For loop to draw the small trees 
            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    int x = imageScale * j + (k - rowTrees) *
                        SMALL_TREE_WIDTH - (int)(pos * imageWidth);
                    int y = imageScale * i + screenBase;

                    boolean drawTree = true;

                    // Branches and leaves that will be the same colour 
                    if (treeElements[i][j] == 'B' || 
							treeElements[i][j] == 'L') {

                        double num = Math.random();
                        int green = (int)(num * 125 + 100);

                        tree.setColor(green, green / 2, 0);

                        // Trunk of the tree 
                    } else if (treeElements[i][j] == 'T') {

                        tree.setColor(118, 92, 72);

                    } else {

                        drawTree = false;

                    }

                    if (drawTree == true) {

                        tree.fillRectangle(x, y, imageScale, imageScale);
                        tree.fillRectangle(x - 1, y - 1, imageScale,
                            imageScale);

                    }

                }

            }

        }

		////////////////////////////////////////////////////////////////////
		// Third set of loops to draw the Trees that are the SUMMER VERSION
		// The code is the same as the previous set of Trees except the 
		// following:
		// BASE where the tree starts will be different
		// TREE will have green coloured leaves
		// Random number of trees and positions
		////////////////////////////////////////////////////////////////////
		
        imageWidth = BIG_TREE_WIDTH;
        imageHeight = BIG_TREE_HEIGHT;
        imageScale = BIG_TREE_SCALE;

		// Re-calculate the BASE
        screenBase = (SCREEN_HEIGHT / SCALE_HEIGHT) * 2;

        horizonPosition = screenBase + (int)(0.4 * BIG_TREE_HEIGHT);

        treePosition = screenBase + (int)((horizonPosition - screenBase) *
            0.3);

        // To draw the background on the top 
        for (int k = 0; k < SCALE_WIDTH; k++) {

            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    // Two integers x and y coordinates on screen
                    int x = imageScale * j + k * SMALL_TREE_WIDTH;
                    int y = imageScale * i + screenBase;

                    if (treeElements[i][j] == 'G') {

                        if (y >= 0 && y <= horizonPosition)

                            tree.setColor(0, 200, 0);

                        else

                            tree.setColor(135, 206, 235);

                    }

                    tree.fillRectangle(x, y, imageScale, imageScale);
                    tree.fillRectangle(x - 1, y - 1, imageScale,
                        imageScale);

                }

            }

        }

        pos = Math.random() / 2 - 0.1;

		// First row of Trees
        for (int k = 0; k < rowTrees; k++) {

            pos = pos + Math.random() / 2 - 0.1;

            if (treeType[k] == SMALL_TREE_TYPE) {

                imageWidth = SMALL_TREE_WIDTH;
                imageHeight = SMALL_TREE_HEIGHT;
                imageScale = SMALL_TREE_SCALE;

                // Base of the screen 
                screenBase = (SCREEN_HEIGHT / SCALE_HEIGHT) * 2 +
                    imageHeight / 2;

                treePosition = screenBase + (int)((horizonPosition -
                    screenBase) * 0.3);

            } else {

                imageWidth = BIG_TREE_WIDTH;
                imageHeight = BIG_TREE_HEIGHT;
                imageScale = BIG_TREE_SCALE;

                // Base of the screen 
                screenBase = SCREEN_HEIGHT / SCALE_HEIGHT * 2;

                treePosition = screenBase + (int)((horizonPosition -
                    screenBase) * 0.3);

            }

            // For loop to draw the trees 
            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    int x = imageScale * j + k * SMALL_TREE_WIDTH - (int)(
                        pos * imageWidth);
                    int y = imageScale * i + treePosition;

                    boolean drawTree = true;

                    // The trees and branches will be green in colour 
                    if (treeElements[i][j] == 'B' || 
						treeElements[i][j] == 'L') {

                        // Random number to assign green colour 
                        double num = Math.random();
                        int green = (int)(num * 100 + 155);

                        tree.setColor(124, green, 0);

                        // The trunk is being coloured brown 
                    } else if (treeElements[i][j] == 'T') {

                        tree.setColor(118, 92, 72);

                    } else {

                        drawTree = false;

                    }

                    if (drawTree == true) {

                        tree.fillRectangle(x, y, imageScale, imageScale);
                        tree.fillRectangle(x - 1, y - 1, imageScale,
                            imageScale);

                    }

                }

            }

        }

        pos = Math.random() / 2 - 0.1;

		// Second Row of Trees
        for (int k = rowTrees; k < numTrees; k++) {

            pos = pos + Math.random() / 2 - 0.1;

            if (treeType[k] == SMALL_TREE_TYPE) {

                imageWidth = SMALL_TREE_WIDTH;
                imageHeight = SMALL_TREE_HEIGHT;
                imageScale = SMALL_TREE_SCALE;
                screenBase = SCREEN_HEIGHT / SCALE_HEIGHT * 2 +
                    imageHeight / 2;

            } else {

                imageWidth = BIG_TREE_WIDTH;
                imageHeight = BIG_TREE_HEIGHT;
                imageScale = BIG_TREE_SCALE;
                screenBase = SCREEN_HEIGHT / SCALE_HEIGHT * 2;

            }

            // For loop to draw the trees 
            for (int i = NUM_ROWS - 1; i >= 0; i--) {

                for (int j = NUM_COLS - 1; j >= 0; j--) {

                    // Integers x and y to define positions on the x and y axis 
                    int x = imageScale * j + (k - rowTrees) *
                        SMALL_TREE_WIDTH - (int)(pos * imageWidth);
                    int y = imageScale * i + screenBase;

                    boolean drawTree = true;

                    // Assigning a greenish colour for the branches and leaves 
                    if (treeElements[i][j] == 'B' || treeElements[i][j] == 'L') {

                        double num = Math.random();
                        int green = (int)(num * 125 + 130);

                        tree.setColor(127, green, 0);

                        // Brown coloured trunk  
                    } else if (treeElements[i][j] == 'T') {

                        tree.setColor(118, 92, 72);

                    } else {

                        drawTree = false;

                    }

                    if (drawTree == true) {

                        tree.fillRectangle(x, y, imageScale, imageScale);
                        tree.fillRectangle(x - 1, y - 1, imageScale,
                            imageScale);

                    }

                }

            }

        }

        // Drawing the two black lines
        tree.setColor(0, 0, 0);
        tree.moveTo(0, SCREEN_HEIGHT / SCALE_HEIGHT * 2);
        tree.lineTo(SCREEN_WIDTH - 1, SCREEN_HEIGHT / SCALE_HEIGHT * 2);

        tree.moveTo(0, SCREEN_HEIGHT / SCALE_HEIGHT);
        tree.lineTo(SCREEN_WIDTH - 1, SCREEN_HEIGHT / SCALE_HEIGHT);

    }

}
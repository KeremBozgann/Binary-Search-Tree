/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
 *
 * @author Kerem Bozgan
 * @version 2022-10-17
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.awt.Rectangle;

public class RectangleDB {
    /**
     * @param args
     *     Command line parameters
     */
    public Rectangle rect; 
    public String name;
    public int x ; 
    public int y; 
    public int width; 
    public int height;
    
    RectangleDB() { 
        rect = new Rectangle();
    }

    RectangleDB(int x, int y, int width, int height) { 
        this.x = x; 
        this.y = y; 
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
    }
    
    RectangleDB(String name, int x, int y, int width, int height) { 
        this.name = name;
        this.x = x; 
        this.y = y; 
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
    }
    
    
    public static void main(String[] args) {
        BST<KVPair<String, RectangleDB>> bst = new BST<KVPair<String, RectangleDB>>();

        String filename = args[0];
        FileReader reader = new FileReader(filename);
        String cmd;
        String[] commandArgs;
        boolean endOfFile = false;
        while (true) {
            endOfFile = reader.readNextLine();
            if (endOfFile) {
                break;
            }
            else {
                // if line is empty, do nothing and continue
                if (reader.checkIfBlankCommand())
                    continue;
                else {
                    cmd = reader.getCurrentCommand();
                    commandArgs = reader.getCurrentCommandArgs();
                    CommandProcessor.processCommand(commandArgs, cmd, bst);

                }
            }

        }
    }
    
    public String toString() { 
        return "(" + name + ", " + x + ", " + y + ", " 
            + width + ", " + height + ")";
    }
    
    public int[] getCoords() {
        int[] coords = new int[4];
        coords[0] = x;
        coords[1] = y; 
        coords[2] = width; 
        coords[3] = height;
        return coords;
    }
}

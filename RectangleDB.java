/**
 * The class containing the main method.
 *
 * @author Kerem Bozgan
 * @version 2022-10-17
 */


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

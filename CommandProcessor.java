import java.awt.Rectangle;
public class CommandProcessor{
        
    /**
     * Command is processed.
     * SkipList methods and static methods in Rectangle1
     * is called from here.
     * 
     * @param commandArgs
     *            command arguments in a list
     * @param cmd
     *            unsplit command string
     * @return info string
     */
    public static String processCommand(String[] commandArgs, String cmd, BST<KVPair<String, RectangleDB>> bst) {
        // if command is executed successfully or not:
        // Switch the first in the command line
        switch (commandArgs[0]) {
            case "insert":// Found an add command
                // test to check if the last 4 command args are integers (this
                // test was not really necessary to satisfy project
                // requirements):

                switch (checkInsertArgs(commandArgs)) {
                    case "InvalidNumberOfArgs":
                        return "InvalidNumberOfArgs";

                    case "SomeArgumentsAreNotInt":
                        return "SomeArgumentsAreNotInt";

                    case "Passed":
                        break;
                }

                // get rectangle name:
                String rectName = commandArgs[1];
                // get rectangle coords and dims
                int[] rectVals = getRectVal(commandArgs);
                
                RectangleDB newRect = new RectangleDB(rectName, 
                    rectVals[0], rectVals[1], rectVals[2], rectVals[3]);
                
                // check if rectangle is in world box and have positive
                // dims:
                if (!checkRectValidity(commandArgs)) {

                    System.out.println("Rectangle rejected: " + newRect.toString());
                    return "Rejected";
                }
                else {
                    // insert new rectangle:

                    KVPair<String, RectangleDB> newPair = new  KVPair<String, RectangleDB>(rectName, newRect);
        
                    bst.insert(newPair);
                    System.out.println("Rectangle accepted: " + newRect.toString());
                }

            case "remove":

                // remove by name:
                if (commandArgs.length == 2 && !isInteger(commandArgs[1])) {
                    RectangleDB dummyRect = new RectangleDB(); 
                    KVPair<String, RectangleDB> dummyPair = new  KVPair<String, RectangleDB>(commandArgs[1], dummyRect);

                    int result = bst.remove(dummyPair);
                    
                    if (result == -1)
                        {System.out.println("Rectangle rejected: " + commandArgs[1]);
                        return "NotInList";}
                    return "RemovedByName";
                }
                // remove by coords (again some non-required input check sinside
                // the
                // following if):
                else if (commandArgs.length == 5 && isInteger(commandArgs[1])
                    && isInteger(commandArgs[2]) && isInteger(commandArgs[3])
                    && isInteger(commandArgs[4])) {
                    
                    
                    if (!checkRectValidityRemove(commandArgs)) {

                        System.out.println("Rectangle rejected: " + "("
                            + commandArgs[1] + ", " + commandArgs[2] + ", "
                            + commandArgs[3] + ", " + commandArgs[4] + ")");

                        return "Rejected";
                    }
                    else { // combine integer args in a string:
                        
                        int[] rectCoords = getRectVal(commandArgs);

                        int res = World.removeByCoords(rectCoords, bst);
                        
                        
                        if (res == -1)
                            System.out.println("Rectangle rejected: " + "(" + 
                                rectCoords[0] + ", " + rectCoords[1]+ ", " 
                                + rectCoords[2] + ", " + rectCoords[3] + ")");
                        
                    }
                }
                else
                    return "InvalidArgs";

            case "search":// Found a search command
               
                return World.search(commandArgs[1], bst, true);
                    
                
            case "dump": // dump:
                bst.print();
                return "Dumped";

            case "regionsearch":
                // check if rectangle satisfies requirements:
                if (Integer.parseInt(commandArgs[3]) <= 0 || Integer.parseInt(
                    commandArgs[4]) <= 0) {
//                    System.out.println("Rectangle rejected: " + "("
//                        + commandArgs[1] + ", " + commandArgs[2] + ", "
//                        + commandArgs[3] + ", " + commandArgs[4] + ")");
                    return "Rejected";
                }
                else {
                    int[] regionRectVals = getRectVal(commandArgs);
                    return World.regionSearch(regionRectVals, bst);
                }

            case "intersections":
                return World.intersections(bst);

            default:// Found an unrecognized command
                System.out.println("Unrecognized input " + cmd);
                System.out.println(
                    "Unsuccessful operation due to unrecognized input,"
                    + " moving on to next line to get command");
                return "CommandNotRecognized";
        }
    }
    
    
    /**
     * 
     * check if last arguments are integer following insert command:
     * 
     * @param commandArgs
     *            a list of strings
     * @return info message
     */

    public static String checkInsertArgs(String[] commandArgs) {
        if (commandArgs.length != 6) {
            System.out.println(
                "Invalid number of arguments given after insert command");
            return "InvalidNumberOfArgs";
        }
        for (int i = 2; i < commandArgs.length; i++) {
            if (!isInteger(commandArgs[i]))
                return "SomeArgumentsAreNotInt";
        }
        return "Passed";
    }
    
    /**
     * 
     * 
     * @param commandArgs
     *            list containing args past from the file
     * @return rectValues from commandArgs get rectangle parameters, combine
     *         them into one string
     */
    public static int[] getRectVal(String[] commandArgs) {
        int[] rectValues  = new int[4];
        int ind ;
        for (int i = 0; i <= 3; i++) {
                ind = commandArgs.length - 4 + i;
                rectValues[i]= Integer.parseInt(commandArgs[ind]);
        }

        return rectValues;

    }
    

    /**
     * 
     * Check if given command parameters for the insert command are valid.
     * 
     * @param commandArgs
     *            list containing args past from the file
     * @return false if parameters do not satisfy conditions
     */
    public static boolean checkRectValidity(String[] commandArgs) {
        if (Integer.parseInt(commandArgs[2]) < 0 || Integer.parseInt(
            commandArgs[3]) < 0 || Integer.parseInt(commandArgs[4]) <= 0
            || Integer.parseInt(commandArgs[5]) <= 0 || Integer.parseInt(
                commandArgs[2]) + Integer.parseInt(commandArgs[4]) > 1024
            || Integer.parseInt(commandArgs[3]) + Integer.parseInt(
                commandArgs[5]) > 1024)
            return false;
        else
            return true;
    }
    
    /**
     * 
     * check if argument is integer
     * 
     * @param s
     *            a string
     * @return false if String contents is not integer
     */
    // checks if given string has integer value
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }

    }
    
    /**
     * 
     * Check if given command parameters for the remove(RemoveByCoord) command
     * are valid.
     * 
     * @param commandArgs
     *            list containing args past from the file
     * @return false if parameters do not satisfy conditions
     */
    public static boolean checkRectValidityRemove(String[] commandArgs) {
        if (Integer.parseInt(commandArgs[1]) < 0 || Integer.parseInt(
            commandArgs[2]) < 0 || Integer.parseInt(commandArgs[3]) <= 0
            || Integer.parseInt(commandArgs[4]) <= 0 || Integer.parseInt(
                commandArgs[1]) + Integer.parseInt(commandArgs[3]) > 1024
            || Integer.parseInt(commandArgs[2]) + Integer.parseInt(
                commandArgs[4]) > 1024)
            return false;
        else
            return true;
    }
    
    
}
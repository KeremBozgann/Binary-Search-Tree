public class World { 
    
    
    
    public static int removeByCoords(int[] coords, BST<KVPair<String, RectangleDB>> bst) {         
           
        BST<KVPair<String, RectangleDB>>.iter it = bst.new iter();
        
        KVPair<String, RectangleDB> current;
        while(it.hasNext()) { 
            current = it.next();
            if (isMatch(current.value().getCoords(), coords))
               return bst.remove(current);
        }
        return -1;
        
    };   // removes the given value from the BST
    
    public static boolean isMatch (int[] coords1, int[] coords2){ 
        for (int i = 0; i< coords1.length; i++) { 
            if (coords1[i] != coords2[i])
                return false;
        }
        return true;
    }
    
    //Find Intersections with the given region
    public static String regionSearch(int[] rectCoords, BST<KVPair<String, RectangleDB>> bst) {
        BST<KVPair<String, RectangleDB>>.iter it = bst.new iter();

        
        boolean rectsFound = false;
        int count= 0; 

        KVPair<String, RectangleDB> current;
        
        // iterate over all nodes:
        while (it.hasNext()) {
            current = it.next();

            RectangleDB rectBst = current.value();
            
            RectangleDB region = new RectangleDB(
                rectCoords[0], rectCoords[1], rectCoords[2], rectCoords[3]);

            // check if overlap
            if (rectBst.rect.intersects(region.rect))
                {rectsFound = true;
                count ++ ;
            // print message if intersections exist
            if (count == 1)
                System.out.println("Rectangles intersecting region: "
                    + "(" + 
                    rectCoords[0] + ", " + rectCoords[1]+ ", " 
                    + rectCoords[2] + ", " + rectCoords[3] + ")");
            System.out.println(rectBst.toString());

                }
                }

        if (rectsFound)
            return "RectsFound";
        else
            return "NoRectsFound";

    }
    public static String intersections(BST<KVPair<String, RectangleDB>> bst) { 
        BST<KVPair<String, RectangleDB>>.iter itOut = bst.new iter();
        BST<KVPair<String, RectangleDB>>.iter itInner = bst.new iter();
        
        KVPair<String, RectangleDB> currentOut;
        KVPair<String, RectangleDB> currentIn;
        
        System.out.println("Intersection pairs:");
        boolean intersection = false;
        while (itOut.hasNext()) { 
            {currentOut = itOut.next();
            while (itInner.hasNext())
            {currentIn = itInner.next();
            if (currentOut.value().rect.intersects(currentIn.value().rect))
                {System.out.println("(" + currentOut.value().toString() + " | "
                    +currentIn.value().toString() );
                intersection = true;}
            }
            }
        }
        if (intersection) 
            return "IntersectsFound";
        else
            return "NoIntersectsFound";
        
    }
    
    public static String search(String name, BST<KVPair<String, RectangleDB>> bst, boolean printFlag) {
        RectangleDB dummyRect = new RectangleDB(); 
        KVPair<String, RectangleDB> dummyPair = new  KVPair<String, RectangleDB>(name, dummyRect);

        
        LStack<KVPair<String, RectangleDB>> pairStack = bst.search(dummyPair);
        if (pairStack.isEmpty())
            {if (printFlag)
                System.out.println("Rectangle not found: " + name);
            return "NotFound";}
        else 
            {        KVPair<String, RectangleDB> current;
            if (printFlag)
                System.out.println("Rectangles found:");

            while (!pairStack.isEmpty())
                {current = pairStack.pop();
            System.out.println(current.value().toString());}
            return "Found";
                }
    }
}



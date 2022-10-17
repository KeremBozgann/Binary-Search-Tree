/**
 * Stub for binary search tree class
 * 
 * @author {Your Name Here}
 * @param <T> the generic type; extends Comparable
 */
public class BST<T extends Comparable<T>>{

    /** The root. */
    protected Node<T> root;

    /** The size. */
    private int size;

    /**
     * Instantiates a new Binary Search Tree.
     */
    public BST() {
        root = null;
        size = 0;
    }
    
    public static void main(String[] args) {
        // This is the main file for the program.
        BST<String> myBST  = new BST<String>(); 
        myBST.insert("c");
        myBST.insert("d");
        myBST.insert("b");
        myBST.insert("k");
        myBST.insert("n");
        myBST.insert("e");
        myBST.insert("d");
        myBST.insert("z");
        myBST.insert("h");
        myBST.insert("q");
        myBST.insert("a");
        myBST.insert("b");
//        myBST.print();
        myBST.remove("n");
        myBST.remove("q");
        myBST.remove("e");
        myBST.print();

        
    }
    
    public void clear() { 
        this.root = null; // clears the tree
    };
    
    public int size() { 
        return this.size; 
    };  // returns the number of nodes in the tree
    
    public boolean isEmpty() { 
        if (this.size ==0)
            return true;
        return false;
    };   // check if the tree is empty 
    
    
    //Recursive insert called from original insert
    private Node<T> insertRecurs(Node<T> root, Node<T> newNode, T value) { 
                
        if (root == null) { 
            root = newNode; 
            return root; 
        }
        
        if (value.compareTo(root.value) <=0)
            {root.left = insertRecurs(root.left, newNode, value); 
            return root;}
        else 
            {root.right = insertRecurs(root.right, newNode, value); 
            return root;}
           
    }
    
    public void insert(T value) { 
        
        Node<T> newNode = new Node<T>(value); 
        
        root = insertRecurs(root, newNode, value);
           
    };   // insert the given value in the BST
    
    
    private Node<T> findRecurs(Node<T> root, T value) { 
        
        if (root == null) 
            return null; 
        
        if (value.compareTo(root.value) == 0)
            return root;
        
        if (value.compareTo(root.value) < 0)
            return findRecurs(root.left, value);
            
        else 
            return findRecurs(root.right, value);
        
    }
    
    public Node<T> find(T value) { 
        return findRecurs(root, value);
    };    // searches for a given value in the tree
    
    
    private LStack<T> searchRecurs(Node<T> root, T value, LStack<T> nodeStack) { 
        
        if (root == null) 
            return nodeStack; 
        
        if (value.compareTo(root.value) == 0)
            nodeStack.push(root.value);
       
        if (value.compareTo(root.value) <= 0)
            return searchRecurs(root.left, value, nodeStack);
            
        else 
            return searchRecurs(root.right, value, nodeStack);

    }
    
    public LStack<T> search(T value) { 
        LStack<T> nodeStack = new LStack<T>();
        return searchRecurs(root, value, nodeStack);
    };    // searches for a given value in the tree
    
    
    
    private Node<T> swapLargestFromLeft (Node<T> root){ 

        Node<T> current = root.left;
        if (current.right == null)
            {root.value = current.value;
            root.left = current.left;
            return root;}
            
        Node<T> currentParent = root; 
        while(current.right != null) {
            currentParent = current;
            current = current.right;
        }   
        root.value = current.value;
        currentParent.right = current.left; 
        return root;
    }
    
    private Node<T> swapSmallestFromRight (Node<T> root){ 
        
        Node<T> current = root.right;
        if (current.left == null)
            {root.value = current.value;
            root.right = current.right;
            return root;}
            
        Node<T> currentParent = root; 
        while(current.left != null) {
            currentParent = current;
            current = current.left;
        }   
        root.value = current.value;
        currentParent.left = current.right; 
        return root;
    }
    
    public int remove(T value) { 
        Node<T> match =  find(value);
        
        if (match == null)
            return -1;  
        
        if (match.left == null && match.right == null)
            match = null;

        else if (match.left!= null)
            match = swapLargestFromLeft(match); 
        else 
            match = swapSmallestFromRight(match); 

        return 1;
    };   // removes the given value from the BST

    
    private int printRecursInOrder(Node<T> root, int depth) {
        
        int size = 0; 
        if (root == null)
            return size;
        
        size += printRecursInOrder(root.left, depth+1); 
        System.out.println("Node has depth " + depth + ", Value " + root.value.toString());
        size ++;
        size += printRecursInOrder(root.right, depth+1); 
        return size;
    }
    public void print() { 
        System.out.println("BST dump:");
        int size;
        if (root == null)
         {System.out.println("Node has depth " + 0 + ", Value " + "(null)");
         System.out.println("BST size is: " + 0);
             return;}
        size = printRecursInOrder(root, 0);
        System.out.println("BST size is: " + size);
    }; // print the entirety of the BST (inorder traversal) 
    
//    private LStack<T> dumpRecursInOrder(Node<T> root, int depth, LStack<T> nodeStack) {
//        
//        if (root == null)
//            return nodeStack; 
//        
//        dumpRecursInOrder(root.left, depth+1); 
//        System.out.println("Node has depth " + depth + ", Value " + root.value.toString());
//        dumpRecursInOrder(root.right, depth+1); 
//        
//    }
//    public void dump() { 
//        LStack<T> nodeStack = new LStack<T>();
//        dumpRecursInOrder(root, 1, nodeStack);
//    }; // print the entirety of the BST (inorder traversal) 
//    
//    
    public T removeNode(Node<T> nodeToRemove) { 
        return null;
    };   // removes the given node from the BST
    
    
    private LStack<T> pushRecurs(LStack<T> nodeStack, Node<T> root) { 
        if (root == null)
            return nodeStack; 
        nodeStack = pushRecurs(nodeStack, root.left);
       nodeStack.push(root.value);
       nodeStack = pushRecurs(nodeStack, root.right);
       return nodeStack;
    }
    
    public class iter { 
        private LStack<T> nodeStack = new LStack<T>(); 
        public iter() {
        
        nodeStack = pushRecurs(nodeStack, root);
        }
        
        public boolean hasNext() { 
            if (nodeStack.isEmpty())
                return false;
            else
                return true;
        }
        
        public T next() { 
            return nodeStack.pop();
        }
        
    }

}


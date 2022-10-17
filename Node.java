/**
 * Stub for Node class
 * 
 * @author CS Staff
 * @param <T> the generic type; extends Comparable
 */

public class Node<T extends Comparable<T>> {
    
    
    public Node<T> right;
    public Node<T> left;
    public T value;
    
    /**
     * Instantiates a new node.
     *
     * @param value
     *            the value
     */
    
    public Node(T value) {
        this.right = null; 
        this.left = null; 
        this.value = value; 
    }
    
    
    public void setRight(Node<T> right) { 
        this.right = right;
    };
    
    public void setLeft(Node<T> left) { 
        this.left = left; 
    };
    
    public void setValue(T value) { 
        this.value = value;
    };
    
    public boolean isLeaf (){ 
        if (this.right == null && this.left == null) 
            return true;
        return false;
    };
   
}

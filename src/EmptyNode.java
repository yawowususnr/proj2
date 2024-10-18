/**
 
A modified node class for empty nodes using flyweight design patterns
@author Yaw Owusu Snr
@version 10/9/23

 **/
class EmptyNode implements BintreeNode {
    private static EmptyNode instance; // the empty node instance


  private EmptyNode() {}


public static EmptyNode getInstance() {
    if (instance == null) {
        instance = new EmptyNode();}
    return instance;} 



     


public boolean isInternal() {
    return false;} 


     

public boolean isLeaf() {
    return false;}

public boolean isEmpty() {
    return true;
}



public BintreeNode insert(Seminar seminar, int level, BoundingBox bbox) {// Replace the empty node with a leaf node
    return new LeafNode(seminar);}



public BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
    return EmptyNode.getInstance();
    }
}
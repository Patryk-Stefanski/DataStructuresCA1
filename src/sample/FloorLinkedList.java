/**
 * The responsibility of this class is to create a linked list of floor nodes
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;
public class FloorLinkedList {
    FloorNode FloorHead;

    /** connects previous floor to new floor node in a linked list */
    public void addFloor(Floor e) {
        FloorNode fn = new FloorNode();//creates new floor node
        fn.setContents(e); //set new floor nodes contents
        fn.next = FloorHead;//connects new floor node to floor head reference
        FloorHead = fn; // sets new floor node as head reference

}

    public String printFloorList() {
        FloorNode temp = FloorHead;
        String list = "LinkedList + \n";

        while (temp != null) {
            list += temp.getContents().getFloorNum() + "\n";
            temp = temp.next;
        }
        return list;
    }

}

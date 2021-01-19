/**
 * The responsibility of this class is to create a linked list of aisle nodes
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;

public class AisleLinkedList {
    AisleNode aisleHead;

    /** connects previous aisle to new aisle node in a linked list */
    public void addAisle(Aisle e) {
        AisleNode na = new AisleNode(); //creates new aisle node
        na.setContents(e); //set new aisle nodes contents
        na.next = aisleHead;  //connects new aisle node to aisle head reference
        aisleHead = na;   // sets new aisle node as head reference
    }

    public String printAisleList() {
        AisleNode temp = aisleHead;
        String aisleList = "LinkedList + \n ";

        while (temp != null) {
            aisleList += temp.getContents().getAisleId();
            temp = temp.next;
        }
        return aisleList;
    }
}

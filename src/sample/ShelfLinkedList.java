/**
 * The responsibility of this class is to create a linked list of shelf nodes
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;

public class ShelfLinkedList {
    ShelfNode shelfHead;


    /** connects previous shelf to new shelf node in a linked list */
    public void addShelf(Shelf e) {
        ShelfNode ns = new ShelfNode();//creates new shelf node
        ns.setContents(e);//set new shelf nodes contents
        ns.next = shelfHead;//connects new shelf node to shelf head reference
        shelfHead = ns; // sets new shelf node as head reference
    }

    public String printShelfList(){
        ShelfNode temp=shelfHead;
        String shelfList= "LinkedList + \n ";

        while (temp!=null){
            shelfList+= temp.getContents().getShelfNumber();
            temp=temp.next;
        }
        return  shelfList;
    }
}

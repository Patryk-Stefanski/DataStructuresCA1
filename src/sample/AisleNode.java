/**
 * The responsibility of this class is to create a node with the properties of a aisle
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;

public class AisleNode {

    public AisleNode next=null;
    private Aisle contents;

    /**Creates a list of  shelves  and connects it to a aisle */
    ShelfLinkedList SLL =new ShelfLinkedList();

    public Aisle getContents(){
        return  contents;
    }
    public void setContents( Aisle c){
        contents=c;
    }



    public  void  addShelfToAisle(Shelf e){
        SLL.addShelf(e);
    }

    public ShelfLinkedList getSLL() {
        return SLL;
    }
}

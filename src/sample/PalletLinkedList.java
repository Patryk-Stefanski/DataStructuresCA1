/**
 * The responsibility of this class is to create a linked list of pallet nodes
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;

public class PalletLinkedList {
    PalletNode palletHead;
    Controller controller;


    /** connects previous pallet to new pallet node in a linked list */
    public void addPallet(Pallet e) {
        PalletNode pn=new PalletNode();//creates new pallet node
        pn.setContents(e);//set new pallet nodes contents
        pn.next=palletHead; //connects new pallet node to pallet head reference
        palletHead=pn; // sets new pallet node as head reference

    }

    public  String printPalletList(){
        PalletNode temp=palletHead;
        String list= "LinkedList + \n";

        while (temp !=null){
            list += temp.getContents().getDescription() + "\n";
            temp=temp.next;
        }
        return list;
    }



}

package sample;

public class ShelfNode {

    public ShelfNode next=null;
    private Shelf contents;
    PalletLinkedList PLL=new PalletLinkedList();


    public Shelf getContents(){
        return  contents;
    }
    public void setContents(Shelf c){
        contents=c;
    }

    public void addPalletToShelf(Pallet e){
        PLL.addPallet(e);
    }

    public PalletLinkedList getPLL() {
        return PLL;
    }
}

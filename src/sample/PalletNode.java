package sample;

public class PalletNode {

    public  PalletNode next = null;
    private  Pallet contents;

    public Pallet getContents() {
        return contents;
    }
    public void setContents(Pallet c){
        contents=c;
    }
}

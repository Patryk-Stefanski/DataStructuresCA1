package sample;

public class FloorNode {

    public FloorNode next=null;
    private  Floor contents;


    public Floor getContents(){
        return contents;
    }
    public void setContents(Floor c){
        contents=c;
    }


    AisleLinkedList ALL = new AisleLinkedList();


}

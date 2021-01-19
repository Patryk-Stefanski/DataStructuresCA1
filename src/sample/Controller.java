/**
 * The responsibility of this class is to control the warehouse app (add/delete floors,pallets,etc...)
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    String palletLocation = "";
    public Button drillDown;
    FloorLinkedList FLL = new FloorLinkedList();


    public TextField floorNum, aisleFloorNum, aisleId, aisleWidth, aisleDepth, shelfAisleId, shelfNum, palletAisleId, palletShelfNum, palletDescription, palletQuantity, palletMinTemp, palletMaxTemp, searchForGoods, deletePalletShelfNum, deletePalletAisleId, deletePalletDescription;
    public ChoiceBox floorTemp, palletValue, securityLevel;
    public Button smartAddPallet, load1, load, save1, save, reset, removePallet, addFloor, addAisle, addShelf, addPallet, viewAllButton, searchButton;
    public TreeView treeView;
    public ListView viewAll;


    @Override
    /** Populates  choice boxes with options */
    public void initialize(URL location, ResourceBundle resources) {
        securityLevel.getItems().addAll("LOW", "MODERATE", "HIGH");
        palletValue.getItems().addAll("<$1,000", ">$1,000", ">$10,0000");
        floorTemp.getItems().addAll("25", "13", "5", "0");


    }
    /** Creates drill down menu in GUI */
    public void drillDown() {

        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp;
        ShelfNode shelfTemp;
        PalletNode palletTemp;

        TreeItem warehouseItems = new TreeItem("Warehouse Layout");


        while (floorTemp != null) {
            TreeItem floorItem = new TreeItem("Floor : " + floorTemp.getContents().getFloorNum());
            warehouseItems.getChildren().add(floorItem);
            aisleTemp = floorTemp.ALL.aisleHead;


            while (aisleTemp != null) {
                TreeItem aisleItem = new TreeItem("Aisle : " + aisleTemp.getContents().getAisleId());
                floorItem.getChildren().add(aisleItem);

                shelfTemp = aisleTemp.SLL.shelfHead;


                while (shelfTemp != null) {

                    TreeItem shelfItem = new TreeItem("Shelf : " + shelfTemp.getContents().getShelfNumber());
                    aisleItem.getChildren().add(shelfItem);

                    palletTemp = shelfTemp.PLL.palletHead;


                    while (palletTemp != null) {

                        TreeItem palletItem = new TreeItem("Pallet : " + palletTemp.getContents().getDescription());
                        shelfItem.getChildren().add(palletItem);
                        palletTemp = palletTemp.next;
                    }
                    shelfTemp = shelfTemp.next;
                }
                aisleTemp = aisleTemp.next;
            }

            floorTemp = floorTemp.next;

        }
        treeView.setRoot(warehouseItems);


    }

    /** adds floor to chosen floor */
    public void addFloor(ActionEvent actionEvent) {
        if (readAddFloor() != null) {
            FLL.addFloor(readAddFloor());
        } else {
            Utilities.errorPopOut("Invalid Floor Input");
        }
    }

    /** Searches for goods by name and return all pallets with the same description */
    public void searchForGoods(ActionEvent actionEvent) {
        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp;
        ShelfNode shelfTemp;
        PalletNode palletTemp;
        String listOfPallets = "";
        viewAll.getItems().add(" Searched For : " + searchForGoods.getText() + "\n");


        while (floorTemp != null) {
            aisleTemp = floorTemp.ALL.aisleHead;
            while (aisleTemp != null) {
                shelfTemp = aisleTemp.SLL.shelfHead;
                while (shelfTemp != null) {
                    palletTemp = shelfTemp.PLL.palletHead;
                    while (palletTemp != null) {
                        if (palletTemp.getContents().getDescription().equals(searchForGoods.getText())) {
                            listOfPallets = "Floor : " + floorTemp.getContents().getFloorNum() + " , " + "Aisle : " + aisleTemp.getContents().getAisleId() + " , " + "Shelf : " + shelfTemp.getContents().getShelfNumber() + " , " + "Quantity : " + palletTemp.getContents().getQuantity() + "\n";
                            viewAll.getItems().add(listOfPallets);

                        }
                        palletTemp = palletTemp.next;
                    }
                    shelfTemp = shelfTemp.next;
                }
                aisleTemp = aisleTemp.next;
            }
            floorTemp = floorTemp.next;
        }
    }

    /** Prints out all the floors,aisle,shelves and pallets stored */
    public void viewStock(ActionEvent actionEvent) {
        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp;
        ShelfNode shelfTemp;
        PalletNode palletTemp;
        String palletList;


        viewAll.getItems().add("View All :" + "\n");

        while (floorTemp != null) {
            aisleTemp = floorTemp.ALL.aisleHead;
            while (aisleTemp != null) {
                shelfTemp = aisleTemp.SLL.shelfHead;
                while (shelfTemp != null) {
                    palletTemp = shelfTemp.PLL.palletHead;
                    while (palletTemp != null) {
                        palletList = (" Floor Number : " + floorTemp.getContents().getFloorNum()) + (" , ") + ("Aisle : ") + (aisleTemp.getContents().getAisleId()) + (" , ") + ("Shelf : ") + (shelfTemp.getContents().getShelfNumber()) + ("Pallet : ") + palletTemp.getContents().getDescription() + (" , ") + ("pallet Location ") + palletTemp.getContents().getPalletLocation() + ("\n");

                        palletTemp = palletTemp.next;
                        viewAll.getItems().add(palletList);


                    }
                    shelfTemp = shelfTemp.next;
                }
                aisleTemp = aisleTemp.next;
            }
            floorTemp = floorTemp.next;
        }
    }

    /** Adds an aisle to a chosen floor */
    public void addAisle(ActionEvent actionEvent) {
        FloorNode floorTemp = FLL.FloorHead;

        if (Utilities.onlyContainsNumbers(readAddAisleFloor()) == false || readAddAisle() == null) {
            Utilities.errorPopOut("Invalid Floor Number or Aisle Input");
        } else {

            while (floorTemp != null) {
                if (floorTemp.getContents().getFloorNum() == Integer.parseInt(readAddAisleFloor())) {
                    floorTemp.ALL.addAisle(readAddAisle());
                }
                floorTemp = floorTemp.next;
            }
            System.out.println("Successfully added Aisle");
        }
    }

    /** Adds shelf to chosen aisle */
    public void addShelf(ActionEvent actionEvent) {
        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp = FLL.FloorHead.ALL.aisleHead;


        while (floorTemp != null) {
            while (aisleTemp != null) {
                if (aisleTemp.getContents().getAisleId().equals(readAddShelfAisle())) {
                    aisleTemp.addShelfToAisle(readAddShelf());
                    System.out.println(readAddShelf().toString());
                }
                aisleTemp = aisleTemp.next;
            }
            floorTemp = floorTemp.next;
        }
        System.out.println("Successfully added Shelf");
    }

    /** Counts the number of pallets on a shelf */
    public int palletCount(AisleNode a, ShelfNode b) {
        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp;
        ShelfNode shelfTemp;
        PalletNode palletTemp;
        int palletAmount = 0;


        while (floorTemp != null) {
            aisleTemp = floorTemp.ALL.aisleHead;
            while (aisleTemp != null) {
                shelfTemp = aisleTemp.SLL.shelfHead;
                if (aisleTemp.getContents().getAisleId().equals(a.getContents().getAisleId())) {
                    while (shelfTemp != null) {
                        palletTemp = shelfTemp.PLL.palletHead;
                        if (shelfTemp.getContents().getShelfNumber() == b.getContents().getShelfNumber()) {
                            while (palletTemp != null) {
                                palletAmount = palletAmount + 1;
                                palletTemp = palletTemp.next;

                            }
                        }
                        shelfTemp = shelfTemp.next;
                    }
                }
                aisleTemp = aisleTemp.next;
            }
            floorTemp = floorTemp.next;
        }
        return palletAmount;

    }

    /** Adds pallet to a chosen shelf and aisle */
    public void addPallet(ActionEvent actionEvent) {
        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp = FLL.FloorHead.ALL.aisleHead;
        ShelfNode shelfTemp = FLL.FloorHead.ALL.aisleHead.SLL.shelfHead;

        int shelfSpace = aisleTemp.getContents().getAisleDepth() * aisleTemp.getContents().getAisleWidth();


        while (floorTemp != null) {
            while (aisleTemp != null) {
                while (shelfTemp != null) {
                    if (shelfSpace > palletCount(aisleTemp, shelfTemp)) {
                        if (aisleTemp.getContents().getAisleId().equals(readAddPalletAisle()) && shelfTemp.getContents().getShelfNumber() == Integer.parseInt(readAddPalletShelf())) {
                            shelfTemp.addPalletToShelf(readAddPallet());
                            System.out.println("Successfully added Pallet");
                            System.out.println(palletCount(aisleTemp, shelfTemp));
                        }
                    }
                    if (shelfTemp.getContents().getShelfNumber() == Integer.parseInt(readAddPalletShelf()) && shelfSpace <= palletCount(aisleTemp, shelfTemp)) {
                        Utilities.PopOut("Shelf is Now Full");
                    }
                    shelfTemp = shelfTemp.next;
                }
                aisleTemp = aisleTemp.next;
            }
            floorTemp = floorTemp.next;
        }
    }

    /** Adds pallet to a shelf automatically by comparing the security level needed and temperature required and matching it to an appropriate floor */
    public void smartAddPallet(ActionEvent actionEvent) {
        System.out.println(palletMinTemp.getText());
        System.out.println(palletMaxTemp.getText());
        int myNumber = (Integer.parseInt(palletMinTemp.getText()) + Integer.parseInt(palletMaxTemp.getText())) / 2 ;

        String palletSecurityLevel = "";

        if (palletValue.getValue().toString().equals("<$1,000")) {
            palletSecurityLevel = "LOW";
        }
        if (palletValue.getValue().toString().equals(">$1,000")) {
            palletSecurityLevel = "MODERATE";
        }
        if (palletValue.getValue().toString().equals(">$10,000")) {
            palletSecurityLevel = "HIGH";
        }


        int[] nums = {25, 13, 5, 0};
        int tempMatched = 0;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int curDistance = Math.abs(nums[i] - myNumber);  //returns absolute value
            if (curDistance < minDistance) {
                tempMatched = nums[i];
                minDistance = curDistance;
            }
        }
        System.out.println(tempMatched);


        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp = FLL.FloorHead.ALL.aisleHead;
        ShelfNode shelfTemp = FLL.FloorHead.ALL.aisleHead.SLL.shelfHead;

        int shelfSpace = aisleTemp.getContents().getAisleDepth() * aisleTemp.getContents().getAisleWidth();


        while (floorTemp != null) {
            if (floorTemp.getContents().getFloorSecurityLevel().equals(palletSecurityLevel) && floorTemp.getContents().getFloorTemperature() == tempMatched) {
                while (aisleTemp != null) {
                    while (shelfTemp != null) {
                        if (shelfSpace > palletCount(aisleTemp, shelfTemp)) {
                                shelfTemp.addPalletToShelf(readAddPallet());
                                System.out.println("Successfully added Pallet");
                                System.out.println(palletCount(aisleTemp, shelfTemp));
                        }
                        shelfTemp = shelfTemp.next;
                    }
                    aisleTemp = aisleTemp.next;
                }
                floorTemp = floorTemp.next;

            }
            else if(!floorTemp.getContents().getFloorSecurityLevel().equals(palletSecurityLevel) && floorTemp.getContents().getFloorTemperature()!=tempMatched) {
                if (floorTemp.getContents().getFloorTemperature() == 13 && floorTemp.getContents().getFloorSecurityLevel().equals("MODERATE")) {
                    while (aisleTemp != null) {
                        while (shelfTemp != null) {
                            if (shelfSpace > palletCount(aisleTemp, shelfTemp)) {
                                shelfTemp.addPalletToShelf(readAddPallet());
                                System.out.println("Successfully added Pallet");
                                System.out.println(palletCount(aisleTemp, shelfTemp));
                            }
                            shelfTemp = shelfTemp.next;
                        }
                        aisleTemp = aisleTemp.next;
                    }
                    floorTemp = floorTemp.next;

                }
            }
        }
    }

    /** Return the pallets location on a shelf (width,depth) */
    public String palletLoc() {
        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp = FLL.FloorHead.ALL.aisleHead;
        ShelfNode shelfTemp = FLL.FloorHead.ALL.aisleHead.SLL.shelfHead;
        int depthCoord = aisleTemp.getContents().getAisleDepth();
        int widthCoord = 0;


        int shelfSpace = aisleTemp.getContents().getAisleDepth() * aisleTemp.getContents().getAisleWidth();
        int pCount = palletCount(aisleTemp, shelfTemp) + 1;


        while (floorTemp != null) {
            while (aisleTemp != null) {
                while (shelfTemp != null) {
                    if (shelfSpace > palletCount(aisleTemp, shelfTemp)) {

                        if (pCount <= aisleTemp.getContents().getAisleWidth()) {
                            widthCoord = pCount;
                        }
                        while (pCount > aisleTemp.getContents().getAisleWidth()) {
                            pCount = pCount - aisleTemp.getContents().getAisleWidth();
                            depthCoord = depthCoord - 1;
                            widthCoord = pCount;
                        }
                        palletLocation = (Integer.toString(widthCoord) + Integer.toString(depthCoord));
                    }
                    shelfTemp = shelfTemp.next;
                }
                aisleTemp = aisleTemp.next;
            }
            floorTemp = floorTemp.next;
        }
        return palletLocation.toString();
    }

    /** Removes chosen  pallet from a chose aisle */
    public void removePallet(ActionEvent actionEvent) {
        FloorNode floorTemp = FLL.FloorHead;
        AisleNode aisleTemp = floorTemp.ALL.aisleHead;
        ShelfNode shelfTemp = aisleTemp.SLL.shelfHead;
        PalletNode palletTemp = shelfTemp.PLL.palletHead;


        while (floorTemp != null) {
            while (aisleTemp != null) {
                while (shelfTemp != null) {
                    while (palletTemp != null && palletTemp.next != null) {
                        if (palletTemp.getContents().getDescription().equals(readRemovePalletDescription()) && aisleTemp.getContents().getAisleId().equals(readRemovePalletAisleId())) {
                            shelfTemp.PLL.palletHead = shelfTemp.PLL.palletHead.next;
                            System.out.println("Successfully removed Pallet");
                        }
                        if (palletTemp.next.getContents().getDescription().equals(readRemovePalletDescription()) && aisleTemp.getContents().getAisleId().equals(readRemovePalletAisleId())) {
                            palletTemp.next = palletTemp.next.next;
                            System.out.println("Successfully removed Pallet");
                        } else {
                            palletTemp = palletTemp.next;
                        }
                    }
                    shelfTemp = shelfTemp.next;
                }
                aisleTemp = aisleTemp.next;
            }
            floorTemp = floorTemp.next;
        }

    }

    /** Reads in pallet information and creates a new pallet object */
    public Pallet readAddPallet() {
        Pallet e = new Pallet(palletDescription.getText(), Utilities.validNonNegative(Integer.parseInt(palletQuantity.getText())), Integer.parseInt(palletMinTemp.getText()), Integer.parseInt(palletMaxTemp.getText()), palletValue.getValue().toString(), palletLoc());
        return e;
    }

    /** Reads in what shelf the pallet is meant to be added to */
    public String readAddPalletShelf() {
        return palletShelfNum.getText();
    }

    /** Reads in what aisle the pallet is meant to be added to  */
    public String readAddPalletAisle() {
        return palletAisleId.getText().toUpperCase();

    }

    /** Reads in the description of the pallet that is meant to be removed */
    public String readRemovePalletDescription() {
        return deletePalletDescription.getText();
    }

    /** Reads in the aisle from which a pallet is meant to be removed from*/
    public String readRemovePalletAisleId() {
        return deletePalletAisleId.getText().toUpperCase();
    }

    /** Reads in shelf information and creates a new shelf object */
    public Shelf readAddShelf() {
        Shelf e = new Shelf((Utilities.validNonNegative(Integer.parseInt(shelfNum.getText()))));

        return e;
    }

    /** Reads in  aisle  to which the shelf is meant to be added to*/
    public String readAddShelfAisle() {
        return Utilities.validAisleID(shelfAisleId.getText().toUpperCase());
    }

    /** Reads in aisle information and creates a new aisle object also checks that the aisle is added to a valid floor */
    public Aisle readAddAisle() {
        Aisle e = null;
        if (aisleFloorNum.getText().isEmpty() || aisleId.getText().isEmpty() || aisleDepth.getText().isEmpty() || aisleWidth.getText().isEmpty()) {
            Utilities.errorPopOut("Please Fill in All Required Fields ");
        } else {
            e = new Aisle(Utilities.validAisleID(aisleId.getText().toUpperCase()), Utilities.validNonNegative(Integer.parseInt(aisleDepth.getText())), Utilities.validNonNegative(Integer.parseInt(aisleWidth.getText())));
        }
        return e;
    }

    /** Reads in what floor the aisle is meant to be added to  */
    public String readAddAisleFloor() {
        return aisleFloorNum.getText();
    }

    /** Reads in floor information and creates a new floor object also validates the floornum input so its only a number */
    public Floor readAddFloor() {
        Floor e = null;

        if (floorNum.getText().isEmpty() || securityLevel.getValue() == null || floorTemp.getValue() == null) {
            Utilities.errorPopOut("Please Fill in All Required Fields ");
        }
        if (!Utilities.onlyContainsNumbers(floorNum.getText())) {
            Utilities.errorPopOut("Please Only Enter Positive Numbers");
        } else {
            e = new Floor(Utilities.validNonNegative(Integer.parseInt(floorNum.getText())), securityLevel.getValue().toString(), Integer.parseInt(floorTemp.getValue().toString()));

        }
        return e;
    }


    /** Saves current warehouse  layout */
    @SuppressWarnings("unchecked")
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("FLL.xml"));
        out.writeObject(FLL);
        out.close();
    }

    /** Loads previously saved warehouse layout */
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("FLL.xml"));
        FLL = (FloorLinkedList) is.readObject();
        is.close();
    }


    /** Completely resets the warehouse layout */
    public void reset(ActionEvent actionEvent) throws Exception {
        FLL = new FloorLinkedList();
        save();
    }


}

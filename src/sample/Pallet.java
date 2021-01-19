/**
 * The responsibility of this class is to create an object of Pallet
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;

public  class Pallet {
    private String description, palletValue,palletLocation;
    private int minTemp, maxTemp, quantity;




    /** constructor */
    public Pallet(String description, int quantity, int minTemp, int maxTemp, String palletValue, String palletLocation) {
        this.description = description;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.quantity = Utilities.validNonNegative(quantity);
        this.palletValue = palletValue;
        this.palletLocation=palletLocation;
    }



    /** getter */

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public String getPalletValue() {
        return palletValue;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public String getPalletLocation() {
        return palletLocation;
    }


     /** setter */

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = Utilities.validNonNegative(quantity);
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }


    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }


    public void setPalletValue(String palletValue) {
        this.palletValue = palletValue;
    }

    public void setPalletLocation(String palletLocation) {
        this.palletLocation = palletLocation;
    }


    @Override
    public String toString() {
        return "Pallet{" +
                ", description='" + description + '\'' +
                ", palletValue='" + palletValue + '\'' +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", quantity=" + quantity +
                '}';
    }

}

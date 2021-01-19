/**
 * The responsibility of this class is to create an object of aisle
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;


public class Aisle {
    public Utilities U;

    private String aisleId;
    private int aisleWidth;
    private int aisleDepth;

    /** constructor */
    public Aisle(String aisleId, int aisleDepth, int aisleWidth) {
        this.aisleDepth = Utilities.validNonNegative(aisleDepth);
        this.aisleId = Utilities.validAisleID(aisleId);
        this.aisleWidth = Utilities.validNonNegative(aisleWidth);
    }


    /** getter */

    public String getAisleId() {
        return aisleId;
    }

    public int getAisleWidth() {
        return aisleWidth;
    }

    public int getAisleDepth() {
        return aisleDepth;
    }


    /** setter */

    public void setAisleId(String aisleId) {
        this.aisleId = Utilities.validAisleID(aisleId);
    }

    public void setAisleWidth(int aisleWidth) {
        this.aisleWidth = Utilities.validNonNegative(aisleWidth);
    }

    public void setAisleDepth(int aisleDepth) {
        this.aisleDepth = Utilities.validNonNegative(aisleDepth);
    }

    @Override
    public String toString() {
        return "Aisle{" +
                ", aisleId='" + aisleId + '\'' +
                ", aisleWidth=" + aisleWidth +
                ", aisleDepth=" + aisleDepth +
                '}';
    }

}

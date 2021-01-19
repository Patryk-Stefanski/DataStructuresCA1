/**
 * The responsibility of this class is to create an object of shelf
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;

public class Shelf {
    int shelfNumber;

    /** constructor */
    public Shelf(int shelfNumber) {
        this.shelfNumber = Utilities.validNonNegative(shelfNumber);
    }


    /** getter */

    public int getShelfNumber() {
        return shelfNumber;
    }

    /** setter */
    public void setShelfNumber(int shelfNumber) {
            this.shelfNumber = Utilities.validNonNegative(shelfNumber);
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "shelfNumber=" + shelfNumber +
                '}';
    }

}

/**
 * The responsibility of this class is to create an object of Floor
 *
 * @author  Patryk Stefanski
 * @since   20/10/2020
 */
package sample;


public  class Floor {

    private int floorNum;
    private String floorSecurityLevel;
    private int floorTemperature;




    /** constructor */
    public Floor(int floorNum, String floorSecurityLevel, int floorTemperature) {
        this.floorNum = Utilities.validNonNegative(floorNum);
        this.floorSecurityLevel = floorSecurityLevel;
        this.floorTemperature = floorTemperature;
    }







    /** getters */

    public int getFloorNum() {
        return floorNum;
    }

    public String getFloorSecurityLevel() {
        return floorSecurityLevel;
    }

    public int getFloorTemperature() {
        return floorTemperature;
    }

    /** setters */

    public void setFloorNum(int floorNum) {
            this.floorNum = Utilities.validNonNegative(floorNum);
    }

    public void setFloorSecurityLevel(String floorSecurityLevel) {
        this.floorSecurityLevel = floorSecurityLevel;
    }

    public void setFloorTemperature(int floorTemperature) {
        this.floorTemperature = floorTemperature;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorNum=" + floorNum +
                ", floorSecurityLevel='" + floorSecurityLevel + '\'' +
                ", floorTemperature=" + floorTemperature +
                '}';
    }
}

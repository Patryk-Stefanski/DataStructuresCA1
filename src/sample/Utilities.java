package sample;

import javafx.scene.control.Alert;

public class Utilities {


    static boolean onlyContainsNumbers(String text) {
        return (text.matches("[0-9]+"));
    }
    static boolean onlyContainsLetters(String text) {
        return (text.matches("[A-Z]+"));
    }

    static String validAisleID(String aisleID) {
        String validAisleID = "INVALID";
        if (aisleID.length() == 2 && onlyContainsNumbers(aisleID.substring(1, 2)) && onlyContainsLetters(aisleID.substring(0,1))) {
            validAisleID = aisleID;
        }
        else{
          errorPopOut("Invalid Aisle ID");

        }
        return validAisleID;
    }




    static boolean nonNegative(int number) {
        return (number >= 0);
    }

    static int validNonNegative(int number) {
        if (nonNegative(number) && onlyContainsNumbers(Integer.toString(number))) {
            return number;
        }
        else {
            errorPopOut("Invalid Int");
            return 0;
        }
    }

    static String max30Chars(String string) {
        return (string.length() <= 30) ? string : string.substring(0, 30);
    }

    public static void errorPopOut(String r){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Input Error");
        alert.setContentText(r);


        alert.showAndWait();
    }

    public static void PopOut(String r){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Warning");
        alert.setContentText(r);


        alert.showAndWait();
    }



}

package game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Field {

    protected int value;
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if(value <= 8 && value >= -1) {

            this.value = value;
        }
    }

}

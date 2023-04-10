package new3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label myLabel;


    public void showLabel(ActionEvent e) {
        myLabel.setOpacity(100);
    }
}

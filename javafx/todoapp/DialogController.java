package todoapp;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import todoapp.datamodel.TodoData;
import todoapp.datamodel.TodoItem;

public class DialogController {
    
    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadlinePicker;

    public TodoItem processResult(){
        String shortDescription = shortDescriptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadline = deadlinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescription, details, deadline);
        TodoData.getInstance().addToDoItem(newItem);
        return newItem;
    }
}

package todoapp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import todoapp.datamodel.TodoData;
import todoapp.datamodel.TodoItem;

public class Controller {

        private List<TodoItem> todoItems;

        @FXML
        private ListView<TodoItem> todoListView;

        @FXML
        private Label deadlineLabel;

        @FXML
        private TextArea itemDetailsTextArea;

        @FXML
        private BorderPane mainWindow;

        @FXML
        private ContextMenu lisContextMenu;

        @FXML
        private ToggleButton filterToggleButton;

        private FilteredList<TodoItem> filteredList;

        public void initialize() {
                // TodoItem item1 = new TodoItem("Mail birthday card",
                // "Buy a 30th birthday card for John",
                // LocalDate.of(2016, Month.APRIL, 25));
                // TodoItem item2 = new TodoItem("Doctor's Appointment",
                // "See Dr. Smith at 123 Main Street. Bring paperwork",
                // LocalDate.of(2016, Month.MAY, 23));
                // TodoItem item3 = new TodoItem("Finish design proposal for client",
                // "I promised Mike I'd email website mockups by Friday 22nd April",
                // LocalDate.of(2016, Month.APRIL, 22));
                // TodoItem item4 = new TodoItem("Pickup Doug at the train station",
                // "Doug's arriving on March 23 on the 5:00 train",
                // LocalDate.of(2016, Month.MARCH, 23));
                // TodoItem item5 = new TodoItem("Pick up dry cleaning",
                // "The clothes should be ready by Wednesday",
                // LocalDate.of(2016, Month.APRIL, 20));

                // todoItems = new ArrayList<TodoItem>();
                // todoItems.add(item1);
                // todoItems.add(item2);
                // todoItems.add(item3);
                // todoItems.add(item4);
                // todoItems.add(item5);

                lisContextMenu = new ContextMenu();
                MenuItem deleteMenuItem = new MenuItem("Delete");
                deleteMenuItem.setOnAction((e) -> {
                        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                        if (item != null) {
                                deleteItem(item);
                        }
                });

                lisContextMenu.getItems().addAll(deleteMenuItem);

                todoListView.getSelectionModel().selectedItemProperty().addListener((e) -> {
                        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                        if (item != null) {
                                itemDetailsTextArea.setText(item.getDetails());
                                DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy - EEEE");
                                deadlineLabel.setText(df.format(item.getDeadline()));
                        }
                });

                // Comparator<TodoItem> byDeadline = (o1, o2)->
                // o1.getDeadline().compareTo(o2.getDeadline());
                // passing it as second argument to new SortedList<> working

                filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), e -> true);

                SortedList<TodoItem> sortedList = new SortedList<>(filteredList,
                                new Comparator<TodoItem>() {

                                        @Override
                                        public int compare(TodoItem o1, TodoItem o2) {
                                                return o1.getDeadline().compareTo(o2.getDeadline());
                                        }

                                });


                // todoListView.setItems(TodoData.getInstance().getTodoItems());
                todoListView.setItems(sortedList);
                todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                todoListView.getSelectionModel().selectFirst();

                todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {

                        @Override
                        public ListCell<TodoItem> call(ListView<TodoItem> param) {
                                ListCell<TodoItem> cell = new ListCell<TodoItem>() {
                                        @Override
                                        protected void updateItem(TodoItem item, boolean empty) {
                                                super.updateItem(item, empty);
                                                if (empty) {
                                                        setText(null);
                                                } else {
                                                        setText(item.getShortDescription());
                                                        if (item.getDeadline().equals(LocalDate.now())) {
                                                                setTextFill(Color.GREEN);
                                                        } else if (item.getDeadline()
                                                                        .equals(LocalDate.now().plusDays(1))) {
                                                                setTextFill(Color.BLUE);
                                                        } else if (item.getDeadline().compareTo(LocalDate.now()) < 0) {
                                                                setTextFill(Color.RED);
                                                        } else {
                                                                setTextFill(Color.YELLOW);
                                                        }
                                                }
                                        }
                                };

                                cell.emptyProperty().addListener(
                                                (obs, wasEmpty, isNowEmpty) -> {
                                                        if (isNowEmpty) {
                                                                cell.setContextMenu(null);
                                                        } else {
                                                                cell.setContextMenu(lisContextMenu);
                                                        }
                                                });

                                return cell;
                        };
                });

        };

        public void deleteItem(TodoItem item) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Todo Item");
                alert.setHeaderText("Delete Item: " + item.getShortDescription());
                alert.setContentText("Are you sure to delete this item? Press OK to confirm, or Cancel to back out.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                        TodoData.getInstance().deleteTodoItem(item);
                }
        }

        public void deleteSelectedItem() {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                if (item != null) {
                        deleteItem(item);
                }
        }

        @FXML
        public void showNewItemDialogPane() {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Add New Item");
                dialog.setHeaderText("Use this dialog to create a new todoItem");
                dialog.initOwner(mainWindow.getScene().getWindow());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("todoItemDialog.fxml"));
                try {
                        dialog.getDialogPane().setContent(loader.load());

                        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

                        Optional<ButtonType> result = dialog.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                                DialogController controller = loader.getController();
                                TodoItem newItem = controller.processResult();
                                todoListView.getSelectionModel().select(newItem);

                                // todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
                                // System.out.println("OK pressed");
                        }

                } catch (IOException e) {
                        System.out.println("Couldn't load the dialog");
                        e.printStackTrace();
                        return;
                }

        }

        @FXML
        public void showAboutDialogPane() {
                Dialog<Void> dialog = new Dialog<>();
                dialog.setTitle("About");
                dialog.setHeaderText("İnternetten yardım alın!!!");
                dialog.initOwner(mainWindow.getScene().getWindow());

                dialog.getDialogPane().getButtonTypes().add((ButtonType.CLOSE));

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("help.fxml"));
                try {
                        dialog.getDialogPane().setContent(loader.load());
                        dialog.showAndWait();

                } catch (IOException e) {
                        System.out.println("Couldn't load the dialog");
                        e.printStackTrace();
                        return;
                }

        }

        @FXML
        public void handleKeyPressed(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                        deleteSelectedItem();
                }

        }

        public void handleFilterButton() {
                TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
                if (filterToggleButton.isSelected()) {  
                        filteredList.setPredicate(e -> e.getDeadline().equals(LocalDate.now()));
                        if(filteredList.isEmpty()){
                                itemDetailsTextArea.clear();
                                deadlineLabel.setText(null);
                        }else if(filteredList.contains(selectedItem)){
                                todoListView.getSelectionModel().select(selectedItem);
                        }else{
                                todoListView.getSelectionModel().selectFirst();
                        }
                } else {
                        filteredList.setPredicate(e-> true);
                        todoListView.getSelectionModel().select(selectedItem);
                }
        }

        @FXML
        public void handleExit(){
                Platform.exit();
                System.exit(0);
        }

        // @FXML
        // public void handleClickListView() {
        // TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        // itemDetailsTextArea.setText(item.getDetails());
        // deadlineLabel.setText(item.getDeadline().toString());
        // }

}

package todoapp;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import todoapp.datamodel.TodoData;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root, 900, 500);

        Image image = new Image("doki.jpg");

        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.setTitle("Todo App");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            TodoData.getInstance().storeTodoItems();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            TodoData.getInstance().loadTodoItems();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    

}

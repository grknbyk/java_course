package deneme4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage myStage) throws Exception {
        FXMLLoader loader = new FXMLLoader().load(getClass().getResource("scene.fxml"));
        Parent root = loader.load();
        Scene myScene = new Scene(root);
        myStage.setScene(myScene);

        myStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

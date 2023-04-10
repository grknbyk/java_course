package new3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primarStage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("new.fxml"));
            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            String css = getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);

            Image image = new Image("doki.jpg");

            primarStage.getIcons().add(image);
            primarStage.setTitle("Wh00 Wh00!");
            primarStage.setScene(scene);
            primarStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
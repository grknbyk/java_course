package new1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main2
 */
public class Main2 extends Application {

    @Override
    public void start(Stage primarStage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("new2.fxml"));
            Scene scene = new Scene(root);

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
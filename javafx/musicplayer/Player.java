package musicplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Player extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
        Parent root = loader.load();

        Image icon = new Image("doki.jpg");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Mp3 Player");
        stage.getIcons().add(icon);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent arg0) {
                Platform.exit();
                System.exit(0);
            }

        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}

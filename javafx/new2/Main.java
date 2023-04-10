import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application{
    public static void main(String[] args) {
        //Application.launch(args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Stage stage = new Stage();

        Group root = new Group();
        Scene scene = new Scene(root,790,490, Color.GRAY);
        //primaryStage.setWidth(790);
        //primaryStage.setHeight(490);

        Text text = new Text();
        text.setText("Whoooo!");
        text.setX(45);
        text.setY(200);
        text.setFont(Font.font("Times New Roman",20));
        text.setFill(Color.WHITE);

        Line line = new Line();
        line.setStartX(45);
        line.setStartY(210);
        line.setEndX(250);
        line.setEndY(210);
        line.setStrokeWidth(10);
        line.setStroke(Color.BLUE);
        line.setOpacity(0.3);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(5);
        rectangle.setStroke(Color.RED);

        Polygon triangle = new Polygon();
        triangle.getPoints().setAll(
            300.0,300.0,
            350.0,350.0,
            300.0,200.0
        );
        triangle.setFill(Color.YELLOW);

        Circle circle = new Circle();
        circle.setCenterX(500);
        circle.setCenterY(100);
        circle.setRadius(70);
        circle.setFill(Color.PURPLE);

        Image image2 = new Image("111.jpg");
        ImageView imageView = new ImageView(image2);
        imageView.setX(450);
        imageView.setY(250);
        imageView.setFitWidth(200.0);
        imageView.setFitHeight(200.0);


        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(rectangle);
        root.getChildren().add(triangle);
        root.getChildren().add(circle);
        root.getChildren().add(imageView);

        Image icon = new Image("doki.jpg");

        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Stage Demo Program w00T w00T");

        primaryStage.setResizable(false);
        primaryStage.setX(20);
        primaryStage.setY(100);

        // fullscrenn methods
        // primaryStage.setFullScreen(true);
        // primaryStage.setFullScreenExitHint("You cant escape without pressing s");
        // primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("s"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

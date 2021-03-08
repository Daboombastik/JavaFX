package JavaFX.BlurGlass;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BlurGlass extends Application {
    private double posX;
    private double posY;
    double orgTranslateX, orgTranslateY;

    @Override
    public void start(Stage stage) throws Exception {
//        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Hello World");
        stage.setScene(new Scene(createScene(stage), null));
        stage.show();
    }

    private Parent createScene(Stage stage) {
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        //
        Robot robot = new Robot();
        WritableImage image = robot.getScreenCapture(null, new Rectangle2D(0, 0, 1980, 1080));
        ImageView view = new ImageView(image);
        //
        view.setEffect(new BoxBlur());
        //
        view.setOnMousePressed(e -> {
            posX = e.getScreenX() - stage.getX();
            posY = e.getScreenY() - stage.getY();
        });

        view.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - posX);
            stage.setY(e.getScreenY() - posY);
        });
        //
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                view.setViewport(new Rectangle2D(stage.getX(),stage.getY(),stage.getWidth(), stage.getHeight()));
            }
        };
        timer.start();
        //
        Button btnClose = new Button("Close");
        btnClose.setOnAction(e -> stage.close());
        //
        root.getChildren().addAll(view, btnClose);
        //
        return root;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

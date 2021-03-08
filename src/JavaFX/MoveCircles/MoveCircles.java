package JavaFX.MoveCircles;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MoveCircles extends Application {

    Circle circle_Red, circle_Green, circle_Blue;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @Override
    public void start(Stage primaryStage) {

        //Create Circles
        circle_Red = new Circle(50.0f, Color.RED);
        circle_Red.setCursor(Cursor.HAND);
        circle_Red.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Red.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        circle_Green = new Circle(50.0f, Color.GREEN);
        circle_Green.setCursor(Cursor.MOVE);
        circle_Green.setCenterX(150);
        circle_Green.setCenterY(150);
        circle_Green.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Green.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        circle_Blue = new Circle(50.0f, Color.BLUE);
        circle_Blue.setCursor(Cursor.CROSSHAIR);
        circle_Blue.setTranslateX(300);
        circle_Blue.setTranslateY(100);
        circle_Blue.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Blue.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        Group root = new Group();
        root.getChildren().addAll(circle_Red, circle_Green, circle_Blue);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 400, 350));

        primaryStage.setTitle("Move Circles");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //MouseHandlers
    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    orgSceneX = e.getSceneX();
                    orgSceneY = e.getSceneY();
                    orgTranslateX = ((Circle) (e.getSource())).getTranslateX();
                    orgTranslateY = ((Circle) (e.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent e) {
                    double offsetX = e.getSceneX() - orgSceneX;
                    double offsetY = e.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle) (e.getSource())).setTranslateX(newTranslateX);
                    ((Circle) (e.getSource())).setTranslateY(newTranslateY);
                }
            };
}

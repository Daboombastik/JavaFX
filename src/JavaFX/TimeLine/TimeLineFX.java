package JavaFX.TimeLine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimeLineFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage)
    {
// Create the Text using text object
        Text text = new Text("Hello Hello!");
        VBox root = new VBox(text);
// Setting the text font size
        text.setFont(Font.font(12));
// Creating the Scene using its object
        Scene scene = new Scene(root);
// Adding the Scene to the Stage
        stage.setScene(scene);
// Now Setting the title of the above Stage
        stage.setTitle("This is stage title");
// Displaying the Stage using the show() method
        stage.show();
// Getting the Scene width and the Text width
        double sw = scene.getWidth();
        double tw = text.getLayoutBounds().getWidth();
// Defining the Durations of animations
        Duration startD = Duration.ZERO;
        Duration endD = Duration.seconds(50);
//Creating the keyFrames to use in timeline
        KeyValue startValue = new KeyValue(text.translateXProperty(), sw);
        KeyFrame startFrame = new KeyFrame(startD, startValue);
        KeyValue endValue = new KeyValue(text.translateXProperty(), -6.0 * tw);
        KeyFrame endFrame = new KeyFrame(endD, endValue);
// Creating a Timeline using above values
        Timeline t1 = new Timeline(startFrame, endFrame);
// Setting the cycle count of animation
        t1.setCycleCount(Animation.INDEFINITE);
//Setting the auto reverse property of animation
        t1.setAutoReverse(false);
// Running the animation using play() method
        t1.play();
    }
}
package JavaFX.FileChooserAndSave;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 javafx.stage.FileChooser provides support for standard file dialogs.
 The showSaveDialog(Window ownerWindow) method shows a new file save dialog.
 The method doesn't return until the displayed file save dialog is dismissed.
 The return value specifies the file chosen by the user or null if no selection has been made.
 If the owner window for the file dialog is set,
 input to all windows in the dialog's owner chain is blocked while the file dialog is being shown.
 */
public class FileChooserAndSave extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("java-buddy.blogspot.com");
        Group root = new Group();

        final String Santa_Claus_Is_Coming_To_Town =
                "You better watch out\n"
                        + "You better not cry\n"
                        + "Better not pout\n"
                        + "I'm telling you why\n"
                        + "Santa Claus is coming to town\n"
                        + "\n"
                        + "He's making a list\n"
                        + "And checking it twice;\n"
                        + "Gonna find out Who's naughty and nice\n"
                        + "Santa Claus is coming to town\n"
                        + "\n"
                        + "He sees you when you're sleeping\n"
                        + "He knows when you're awake\n"
                        + "He knows if you've been bad or good\n"
                        + "So be good for goodness sake!\n"
                        + "\n"
                        + "O! You better watch out!\n"
                        + "You better not cry\n"
                        + "Better not pout\n"
                        + "I'm telling you why\n"
                        + "Santa Claus is coming to town\n"
                        + "Santa Claus is coming to town\n";

        Text textSong = new Text(Santa_Claus_Is_Coming_To_Town);

        Button buttonSave = new Button("Save");

        buttonSave.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                File file = fileChooser.showSaveDialog(primaryStage);

                if(file != null){
                    SaveFile(Santa_Claus_Is_Coming_To_Town, file);
                }
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(textSong, buttonSave);

        root.getChildren().add(vBox);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();

    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FileChooserAndSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
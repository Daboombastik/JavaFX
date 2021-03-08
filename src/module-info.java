module JavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires java.logging;

//    opens JavaFX.Calendar to javafx.base;

    exports JavaFX.MoveCircles;
    exports JavaFX.BlurGlass;
    exports JavaFX.TableViewButtons;
    exports JavaFX.CanvasToPNG;
    exports JavaFX.FileChooserAndSave;
    exports JavaFX.DraggablePanels;
    exports JavaFX.TranslateExample;
    exports JavaFX.RotateExample;
    exports JavaFX.ScalingExample;
    exports JavaFX.Calendar;
    exports JavaFX.TimeLine;
    exports JavaFX.ProgressBar;
    exports JavaFX.DragAndDrop;
    exports JavaFX.ShadowEffect;
}
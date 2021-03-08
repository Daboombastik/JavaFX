package JavaFX.Calendar;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class formController implements Initializable {
    public GridPane grid;
    public ComboBox<Month> monthList;
    public Supplier<DateCell> dateCellSupplier;
    public Pane panelCalendar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cellInit();
        monthListInit();
        labelInit();
        gridInit();
        gridClear();
        gridFill(LocalDate.now());
    }

    private void labelInit() {
        panelCalendar.getChildren().stream()
                .filter(node -> node.getClass().getSimpleName().equals("Label"))
                .map(node -> (Label) node)
                .forEach(label -> {
                    label.setAlignment(Pos.CENTER);
                    label.setOnMouseClicked(event -> {
                        if (label.getBackground() == null) {
                            label.setStyle("-fx-background-color:  #1b6cd7;" +
                                    "-fx-text-fill: #ffffff;" +
                                    "-fx-border-radius: 3;" +
                                    "-fx-background-radius: 3");
                        } else {
                            label.setBackground(null);
                            label.setStyle("-fx-text-fill: #898383");
                        }
                    });
                });
    }

    private void cellInit() {
        dateCellSupplier = () -> {
            var cell = new DateCell();
            cell.setStyle("-fx-font-weight: bold");
            cell.setAlignment(Pos.CENTER);
            cell.setOnMouseEntered(event -> cell.setEffect(new DropShadow(15, Color.BLUE)));
            cell.setOnMouseExited(event -> cell.setEffect(null));
            return cell;
        };
    }

    private void gridInit() {
        for (int row = 0; row < grid.getRowCount(); row++) {
            for (int column = 0; column < grid.getColumnCount(); column++) {
                grid.add(dateCellSupplier.get(), column, row);
            }
        }
        grid.setGridLinesVisible(true);
    }

    private void gridClear() {
        for (int i = 1; i < grid.getChildren().size(); i++) {
            var newDate = (DateCell) grid.getChildren().get(i);
            newDate.setText("");
        }
    }

    private void gridFill(LocalDate currentDate) {
        gridClear();

        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentMonthLength = currentDate.lengthOfMonth();

        int index = LocalDate.of(currentYear, currentMonth, 1).getDayOfWeek().getValue();
        int count = 1;
        for (int i = 0; i < currentMonthLength; i++) {
            var newDate = (DateCell) grid.getChildren().get(i + index);
            newDate.setText(String.valueOf(LocalDate.of(currentYear, currentMonth, count++).getDayOfMonth()));
        }
    }

    private void monthListInit() {
        monthList.getItems().addAll(Month.values());
        monthList.setValue(LocalDate.now().getMonth());
        monthList.setOnAction(event -> {
            Month selectedMonth = monthList.getSelectionModel().getSelectedItem();
            gridFill(LocalDate.of(2021, selectedMonth, 1));
        });
    }
}

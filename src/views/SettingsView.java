package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import javafx.scene.text.*;

import javax.swing.text.LabelView;

public class SettingsView {

    private views.MazeView mazeView;
    private Label titleLabel;

    /**
     * The representation of the view that opens once the settings button is clicked
     */
    public SettingsView(MazeView mazeView)
    {
        this.mazeView = mazeView;

        //Title of the view
        this.titleLabel = new Label("Settings");
        this.titleLabel.setFont(new Font("Verdana", 20));
        this.titleLabel.setStyle("-fx-text-fill: #000000");

        final Stage dialog = new Stage(); //dialogue box
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(mazeView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #808080;");


        VBox display = new VBox(30, this.titleLabel);
        display.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().add(display);


        //Adding the  options to switch the path color

        ToggleGroup toggleGroupPath = new ToggleGroup();

        Label pathColorLabels = new Label("Trail Color: ");
        pathColorLabels.setFont(new Font("Arial", 15));
        pathColorLabels.setStyle("-fx-text-fill: #000000");
        pathColorLabels.setAlignment(Pos.CENTER);

        RadioButton greenPathSelect = new RadioButton("Green");
        greenPathSelect.setAlignment(Pos.CENTER);
        greenPathSelect.setToggleGroup(toggleGroupPath);
        greenPathSelect.setSelected(true);
        greenPathSelect.setUserData(Color.BLACK);
        greenPathSelect.setFont(new Font(16));
        greenPathSelect.setStyle("-fx-text-fill: #000000");

        RadioButton redPathSelect = new RadioButton("Red");
        redPathSelect.setToggleGroup(toggleGroupPath);
        redPathSelect.setUserData(Color.BLACK);
        redPathSelect.setFont(new Font(16));
        redPathSelect.setStyle("-fx-text-fill: #000000");

        RadioButton bluePathSelect = new RadioButton("Blue");
        bluePathSelect.setToggleGroup(toggleGroupPath);
        bluePathSelect.setUserData(Color.BLACK);
        bluePathSelect.setFont(new Font(16));
        bluePathSelect.setStyle("-fx-text-fill: #000000");

        HBox pathColorOptions = new HBox(30, greenPathSelect, redPathSelect, bluePathSelect);
        VBox pathColorDisplay = new VBox(10, pathColorLabels, pathColorOptions);

        //Adding the  options to switch the character color
        ToggleGroup toggleGroupChar = new ToggleGroup();

        Label charColorLabels = new Label("Character Color: ");
        charColorLabels.setFont(new Font("Arial", 15));
        charColorLabels.setStyle("-fx-text-fill: #000000");
        charColorLabels.setAlignment(Pos.CENTER);

        RadioButton greenCharSelect = new RadioButton("Green");
        greenCharSelect.setAlignment(Pos.CENTER);
        greenCharSelect.setToggleGroup(toggleGroupChar);
        greenCharSelect.setUserData(Color.BLACK);
        greenCharSelect.setFont(new Font(16));
        greenCharSelect.setStyle("-fx-text-fill: #000000");

        RadioButton redCharSelect = new RadioButton("Red");
        redCharSelect.setAlignment(Pos.CENTER);
        redCharSelect.setToggleGroup(toggleGroupChar);
        redCharSelect.setUserData(Color.BLACK);
        redCharSelect.setFont(new Font(16));
        redCharSelect.setStyle("-fx-text-fill: #000000");

        RadioButton blueCharSelect = new RadioButton("Blue");
        blueCharSelect.setAlignment(Pos.CENTER);
        blueCharSelect.setSelected(true);
        blueCharSelect.setToggleGroup(toggleGroupChar);
        blueCharSelect.setUserData(Color.BLACK);
        blueCharSelect.setFont(new Font(16));
        blueCharSelect.setStyle("-fx-text-fill: #000000");

        HBox charColorOptions = new HBox(30, greenCharSelect, redCharSelect, blueCharSelect);
        VBox charColorDisplay = new VBox(10, charColorLabels, charColorOptions);

        //Adding the option to switch the color of the movable portion of the maze

        ToggleGroup toggleGroupGoal = new ToggleGroup();

        Label goalColorLabels = new Label("Goal Color: ");
        goalColorLabels.setFont(new Font("Arial", 15));
        goalColorLabels.setStyle("-fx-text-fill: #000000");
        goalColorLabels.setAlignment(Pos.CENTER);

        RadioButton redGoalSelect = new RadioButton("Red");
        redGoalSelect.setToggleGroup(toggleGroupGoal);
        redGoalSelect.setAlignment(Pos.CENTER);
        redGoalSelect.setSelected(true);
        redGoalSelect.setUserData(Color.BLACK);
        redGoalSelect.setFont(new Font(16));
        redGoalSelect.setStyle("-fx-text-fill: #000000");

        RadioButton orangeGoalSelect = new RadioButton("Orange");
        orangeGoalSelect.setAlignment(Pos.CENTER);
        orangeGoalSelect.setToggleGroup(toggleGroupGoal);
        orangeGoalSelect.setUserData(Color.BLACK);
        orangeGoalSelect.setFont(new Font(16));
        orangeGoalSelect.setStyle("-fx-text-fill: #000000");

        RadioButton yellowGoalSelect = new RadioButton("Yellow");
        yellowGoalSelect.setAlignment(Pos.CENTER);
        yellowGoalSelect.setToggleGroup(toggleGroupGoal);
        yellowGoalSelect.setUserData(Color.BLACK);
        yellowGoalSelect.setFont(new Font(16));
        yellowGoalSelect.setStyle("-fx-text-fill: #000000");

        HBox goalColorOptions = new HBox(30, redGoalSelect, orangeGoalSelect, yellowGoalSelect);
        VBox goalColorDisplay = new VBox(10, goalColorLabels, goalColorOptions);

        //Adding the button that allows you to apply changes

        Button apply = new Button("Apply");
        apply.setId("applyChanges");

        HBox buttonPanel = new HBox(10, apply);
        buttonPanel.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().add(pathColorDisplay);
        dialogVbox.getChildren().add(charColorDisplay);
        dialogVbox.getChildren().add(goalColorDisplay);
        dialogVbox.getChildren().add(buttonPanel);


        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();

        apply.setOnAction(e -> {
            RadioButton path = (RadioButton)toggleGroupPath.getSelectedToggle();
            RadioButton character= (RadioButton)toggleGroupChar.getSelectedToggle();
            RadioButton goal = (RadioButton)toggleGroupGoal.getSelectedToggle();

            String pathColor = path.getText();
            String charColor = character.getText();
            String goalColor = goal.getText();

            this.mazeView.applySetting(pathColor, charColor, goalColor);
        });
    }
}
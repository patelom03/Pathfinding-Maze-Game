package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TutorialView {

    views.MazeView mazeView;

    private Label titleLabel;

    private TextFlow text;

    /**
     * The representation of the view that opens once the tutorial button is clicked
     */
    public TutorialView(views.MazeView mazeView)
    {
        this.mazeView = mazeView;
        //Title of the tutorial view
        this.titleLabel = new Label("How to Play");
        this.titleLabel.setFont(new Font("Verdana", 20));
        this.titleLabel.setStyle("-fx-text-fill: #000000");

        //Body of text that is displayed in the view
        Text t1 = new Text("\n\nThe purpose of this game is to reach the end of the maze from the starting position.");
        Text t2 = new Text("\n\nThe character can move up, down, left and right. " +
                "If there is a wall in the direction the character is trying to move in, then it will not be able to move.");
        Text t3 = new Text("\n\nUse the arrow keys to navigate through the maze.");
        Text t4 = new Text("\n\nGood luck!");

        //Adding the text together
        this.text = new TextFlow();
        this.text.getChildren().add(t1);
        this.text.getChildren().add(t2);
        this.text.getChildren().add(t3);
        this.text.getChildren().add(t4);

        final Stage dialog = new Stage(); //dialogue box
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(mazeView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #808080;");


        VBox display = new VBox(30, this.titleLabel);
        display.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().add(display);
        dialogVbox.getChildren().add(text);

        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}

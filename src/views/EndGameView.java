package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EndGameView {

    private views.MazeView mazeView;
    private Label titleLabel;
    private TextFlow text;

    /**
     * Pop-up for when the game is won.
     *
     * @param mazeView view of the current maze
     */
    public EndGameView(views.MazeView mazeView) {
        this.mazeView = mazeView;

        if (!this.mazeView.computer) {
            this.titleLabel = new Label("You WIN!!!");
        } else {
            this.titleLabel = new Label("Computer WINS!!!");
        }
        this.titleLabel.setFont(new Font("Verdana", 20));
        this.titleLabel.setStyle("-fx-text-fill: #000000");

        Text t1;
        if (!this.mazeView.computer) {
            t1 = new Text("It took " + mazeView.movesMade.getText().substring(12) + " moves!");
        } else {
            t1 = new Text("The computer was able to solve this maze in " + mazeView.movesMade.getText().substring(12) + " moves!");
        }

        this.text = new TextFlow();
        this.text.getChildren().add(t1);
        this.text.setTextAlignment(TextAlignment.CENTER);

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(mazeView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #808080;");

        VBox display = new VBox(30, this.titleLabel);
        display.setAlignment(Pos.CENTER);

        dialogVbox.getChildren().add(display);
        dialogVbox.getChildren().add(text);

        Scene dialogScene = new Scene(dialogVbox, 400, 100);
        dialog.setScene(dialogScene);
        dialog.show();

        dialog.setOnCloseRequest(event -> {
            mazeView.reset();
        });
    }
}

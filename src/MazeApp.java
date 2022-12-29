import models.MazeModel;
import views.MazeView;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A Maze Application, in JavaFX
 * (Borrowed idea from Tetris Assignment)
 */
public class MazeApp extends Application {
    MazeModel model;
    MazeView view;

    /**
     * Main method
     *
     * @param args argument, if any
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method.  Control of application flow is dictated by JavaFX framework
     *
     * @param primaryStage stage upon which to load GUI elements
     */
    @Override
    public void start(Stage primaryStage) {
        this.model = new MazeModel(); // create a model
        this.view = new MazeView(model, primaryStage); //tie the model to the view
    }

}
package views;

import decorator.ColorCharacter;
import decorator.ColorGoal;
import decorator.ColorTrail;
import decorator.Grid;
import javafx.scene.text.Font;
import models.MazeModel;
import commands.ResetCommand;
import commands.ColourCommand;
import commands.ButtonInvoker;

import moves.Moves;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

/**
 * The maze UI class which visualizes the maze. The maze can be reset once a visualization begins
 * and has the ability to toggle between two colours.
 * The receiver of the commands.
 * (Borrowed ideas from Tetris Assignment)
 */
public class MazeView {

    MazeModel model; //reference to model
    Stage stage;

    Button helpButton, settingButton, resetButton, setSizeButton, toggleColour;
    TextField widthField, heightField;

    Label movesMade;

    BorderPane borderPane;
    Timeline timeline;

    boolean computer = false;

    Canvas canvas;
    GraphicsContext gc;

    int blockWidth = 20; //width of block on display

    private double width; //height and width of canvas
    private double height;

    private int state = 0; // toggle between state 0 and 1 to identify colour
    private boolean toggle = false;

    public MazeView(MazeModel model, Stage stage)
    {
        this.model = model;
        this.stage = stage;
        initUI();
    }

    private void initUI()
    {
        this.stage.setTitle("Maze Game");

        this.width = this.model.getWidth()*this.blockWidth + 2;
        this.height = this.model.getHeight()*this.blockWidth + 2;

        borderPane = new BorderPane();

        //add canvas
        canvas = new javafx.scene.canvas.Canvas(this.width, this.height);
        canvas.setId("Canvas");
        gc = canvas.getGraphicsContext2D();

        //timeline structures the animation, and speed between application "ticks"
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        ButtonInvoker bt = new ButtonInvoker(); // command-manager

        this.resetButton = new Button("Reset");
        resetButton.setId("Start");
        resetButton.setOnAction(e -> {
            // ResetCommand in action!
            bt.acceptCommand(new ResetCommand(this));
            bt.executeCommand();
        });

        this.settingButton = new Button("Settings");
        settingButton.setId("Settings");
        settingButton.setOnAction(e ->
        {
            createSettingView();
            borderPane.requestFocus();
        });

        this.helpButton = new Button("Help");
        helpButton.setId("Help");
        helpButton.setOnAction(e ->{
            createTutorialView();
            borderPane.requestFocus();
        });

        this.setSizeButton = new Button("Set Size");
        setSizeButton.setId("SetSize");
        setSizeButton.setOnAction(e -> {
            setSize();
        });

        this.toggleColour = new Button("☀");
        toggleColour.setOnAction(e -> {
            // ColourCommand in action!
            bt.acceptCommand(new ColourCommand(this));
            bt.executeCommand();
        });

        Label widthLabel = new Label("width:");
        Label heightLabel = new Label("height:");
        this.widthField = new TextField();
        this.heightField = new TextField();
        this.widthField.setPrefWidth(40);
        this.heightField.setPrefWidth(40);
        this.widthField.setText("25");
        this.heightField.setText("25");
        this.movesMade = new Label("moves made");
        this.movesMade.setText("Moves made: 0");
        this.movesMade.setFont(new Font(20));
        this.movesMade.setStyle("-fx-text-fill: #000000");

        HBox controls = new HBox(20, this.toggleColour, this.settingButton, this.resetButton, this.helpButton,
                widthLabel, this.widthField, heightLabel, this.heightField, this.setSizeButton);
        controls.setPadding(new Insets(20, 20, 20, 20));
        controls.setAlignment(Pos.CENTER);

        // the following are on-screen buttons to navigate the maze
        // this feature makes the game more accessible
        HBox onScreen = new HBox();
        Button up = new Button("Up⬆");
        up.setId("Up⬆");
        up.setOnAction(e ->
        {
            model.moveCharacter(MazeModel.MoveType.UP);
            toggle = true;
            paintBoard();
            borderPane.requestFocus();
        });
        Button down = new Button("Down⬇");
        down.setOnAction(e ->
        {
            model.moveCharacter(MazeModel.MoveType.DOWN);
            toggle = true;
            paintBoard();
            borderPane.requestFocus();
        });
        Button left = new Button("Left⬅");
        left.setOnAction(e ->
        {
            model.moveCharacter(MazeModel.MoveType.LEFT);
            toggle = true;
            paintBoard();
            borderPane.requestFocus();
        });
        Button right = new Button("Right➡");
        right.setOnAction(e ->
        {
            model.moveCharacter(MazeModel.MoveType.RIGHT);
            toggle = true;
            paintBoard();
            borderPane.requestFocus();
        });
        Button autoFill = new Button("Give Up");
        autoFill.setOnAction(e ->
        {
            computer = true;
            auto();
            borderPane.requestFocus();
        });
        onScreen.getChildren().addAll(movesMade, up, down, left, right, autoFill);
        onScreen.setAlignment(Pos.CENTER);
        onScreen.setSpacing(10);

        borderPane.setBottom(controls);
        borderPane.setTop(onScreen);
        borderPane.setCenter(canvas);


        borderPane.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                //TO DO
                if (k.getCode() == KeyCode.LEFT) {
                    model.moveCharacter(MazeModel.MoveType.LEFT);
                } else if (k.getCode() == KeyCode.RIGHT) {
                    model.moveCharacter(MazeModel.MoveType.RIGHT);
                } else if (k.getCode() == KeyCode.DOWN) {
                    model.moveCharacter(MazeModel.MoveType.DOWN);
                } else if (k.getCode() == KeyCode.UP) {
                    model.moveCharacter(MazeModel.MoveType.UP);
                }
                toggle = true;
                paintBoard();
                borderPane.requestFocus();
            }
        });

        var scene = new Scene(borderPane, 600, 600);
        this.stage.setScene(scene);
        this.stage.show();
        paintBoard();
    }

    private final int yPixel(int y) {
        return (int) Math.round(this.height -1 - (y+1)*dY());
    }
    private final int xPixel(int x) {
        return (int) Math.round((x)*dX());
    }
    private final float dX() {
        return( ((float)(this.width-2)) / this.model.getBoard().getWidth() );
    }
    private final float dY() {
        return( ((float)(this.height-2)) / this.model.getBoard().getHeight() );
    }

    public void auto() {
        moves.Moves x;
        x = moves.Moves.getInstance();
        x.reset();
        int[][] board = this.model.getBoard().getRightPath();
        int width = this.model.getBoard().getWidth();
        int height = this.model.getBoard().getHeight();
        double deltaX = this.width / width;
        double deltaY = this.height / height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j] == 1) {
                    gc.setFill(Color.FIREBRICK);
                    gc.fillRect(1 + deltaX * i, 1 + deltaY * j, deltaX, deltaY);
                    x = moves.Moves.getInstance();
                }
            }
        }
        movesMade.setText("Moves made: " + (moves.Moves.movesMade() - 1));
        createEndGameView();
    }

    /**
     * Paint the MazeGrid corresponding to the settings and buttons pressed.
     */
    public void paintBoard() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Draw a rectangle around the whole screen
        if (this.state == 0) {
            gc.setFill(Color.BLACK);
        } else {
            gc.setFill(Color.ANTIQUEWHITE);
        }

        gc.fillRect(0, 0, this.width-1, this.height-1);

        // vertical lines
        gc.setStroke(Color.BLUE);
        for(int i = 1 ; i < this.width ; i+=this.width/this.model.getBoard().getWidth()){
            gc.strokeLine(i, 0, i, this.height);
        }

        // horizontal lines
        gc.setStroke(Color.BLUE);
        for(int i = 1 ; i < this.height ; i+=this.height/this.model.getBoard().getHeight()){
            gc.strokeLine(0, i, this.width, i);
        }

        if (this.toggle) {
            this.toggle = false;
        } else {
            this.model.getBoard().setPath();
            this.model.getBoard().makeWalls();
        }
        int width = this.model.getBoard().getWidth();
        int height = this.model.getBoard().getHeight();
        int[][] board = this.model.getBoard().getMazeGrid();
        double deltaX = this.width / width;
        double deltaY = this.height / height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j] == 2) {
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(1 + deltaX * i, 1 + deltaY * j, deltaX, deltaY);
                } else if (board[i][j] == 3) {
                    gc.setFill(this.model.getBoard().getTrailColor());
                    gc.fillRect(1 + deltaX * i, 1 + deltaY * j, deltaX, deltaY);
                } else if (board[i][j] == 4) {
                    gc.setFill(this.model.getBoard().getTrailColor());
                    gc.fillRect(1 + deltaX * i, 1 + deltaY * j, deltaX, deltaY);
                    gc.setFill(this.model.getBoard().getCharColor());
                    gc.fillRect((1 + deltaX * i)+(deltaX/4), (1 + deltaY * j)+(deltaY/4),
                            deltaX/2, deltaY/2);
                }
            }
        }
        gc.setFill(this.model.getBoard().getGoalColor());
        gc.fillRect(1 + deltaX*(width-1), 1 + deltaY*(height-1), deltaX, deltaY);
        updateMoves();
        if (board[width - 1][height - 1] == 4) {
            createEndGameView();
            borderPane.requestFocus();
        }
    }


    /**
     * Function used to create new maze model with user desired size. It is called
     * on click of the set size button and reads from width and height fields.
     */
    public void setSize() {
        int width = Integer.parseInt(widthField.getText());
        int height = Integer.parseInt(heightField.getText());
        this.model = new MazeModel(width, height);
        paintBoard();
    }

    /**
     * Reset the maze to begin new visualization.
     */
    public void reset() {
        this.model = new MazeModel(this.model.getBoard().getWidth(), this.model.getBoard().getHeight());
        moves.Moves x;
        x = moves.Moves.getInstance();
        x.reset();
        computer = false;
        this.paintBoard();
    }

    /**
     * Update moves made tracker on UI
     */
    private void updateMoves() {
        movesMade.setText("Moves made: " + moves.Moves.movesMade());
    }

    public void applySetting(String pathColor, String charColor, String goalColor)
    {
        Grid currBoard = this.model.getBoard();
        currBoard = new ColorTrail(currBoard, pathColor);
        currBoard = new ColorCharacter(currBoard, charColor);
        currBoard = new ColorGoal(currBoard, goalColor);
        this.toggle = true;
        paintBoard();
    }

    /**
     * Change the colour of the maze.
     */
    public void colourChange() {
        this.state = (this.state == 0) ? 1 : 0;
        this.toggle = true;
        this.paintBoard();
    }

    /**
     * Create the view that shows up once the settings button is pressed
     */
    private void createSettingView()
    {
        SettingsView sv = new SettingsView(this);
    }

    /**
     * Create the view that shows up once the tutorial button is pressed
     */
    private void createTutorialView()
    {
        TutorialView tv = new TutorialView(this);
    }

    /**
     * Create the view that shows up once the game is won
     */
    private void createEndGameView() {
        EndGameView ev = new EndGameView(this);
    }
}

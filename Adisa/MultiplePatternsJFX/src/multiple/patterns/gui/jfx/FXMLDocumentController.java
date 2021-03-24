package multiple.patterns.gui.jfx;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import multiple.patterns.library.dp.command.ChangeColorCommand;
import multiple.patterns.library.dp.command.CloneCommand;
import multiple.patterns.library.dp.command.CommandRepository;
import multiple.patterns.library.dp.command.CreateCommand;
import multiple.patterns.library.dp.command.DeleteCommand;
import multiple.patterns.library.dp.command.MoveCommand;
import multiple.patterns.library.dp.command.ScaleCommand;
import multiple.patterns.library.model.ColorPalette;
import multiple.patterns.library.model.Coordinate;
import multiple.patterns.library.model.Shape;
import multiple.patterns.library.model.ShapeManager;
import multiple.patterns.library.model.ShapeType;

/**
 * This class is the controller of the GUI
 * It also represents the client in the structure of the command
 * design pattern
 */
public class FXMLDocumentController implements Initializable {
    /**
     * Style for the clicked action buttons
     */
    private final String CLICKED_STYLE = "-fx-background-color: gray ; -fx-text-fill: white; -fx-border-color: white; -fx-border-width: 2 0 0 0;";
    /**
     * Basic style for a button
     */
    private final String BASIC_STYLE = "-fx-background-color: silver ; -fx-border-color: black; -fx-border-width: 0 0 2 0; -fx-text-fill: white;";

    /**
     * The Invoker
     */
    private CommandRepository repository = new CommandRepository();
    /**
     * The receiver
     */
    private ShapeManager shapeManager = new ShapeManager();

    // -------- The visual components
    @FXML
    private VBox globalBox;

    @FXML
    private Button addTriangleButton;

    @FXML
    private Button addDiamondButton;

    @FXML
    private Button addPentagonButton;

    @FXML
    private Button undoButton;

    @FXML
    private Button redoButton;

    @FXML
    private GridPane inputCreateGrid;

    @FXML
    private Button redSelect;

    @FXML
    private Button greenSelect;

    @FXML
    private Button blueSelect;

    @FXML
    private Label labelColor;

    @FXML
    private TextField inputX;

    @FXML
    private TextField inputRadius;

    @FXML
    private TextField inputY;

    @FXML
    private Label labelX;

    @FXML
    private Label labelY;

    @FXML
    private Label labelRadius;

    @FXML
    private MaterialDesignIconView createTriangle;

    @FXML
    private Button moveButton;

    @FXML
    private Button scaleButton;

    @FXML
    private Button cloneButton;

    @FXML
    private Button changeColorButton;

    @FXML
    private Button deleteButton;

    @FXML
    private HBox actionsBox;

    @FXML
    private HBox footerBox;

    @FXML
    private Label footerLabel;

    @FXML
    private Group canvas;

    @FXML
    private HBox creationBox;

    @FXML
    private Button createAction;

    @FXML
    private Button moveAction;

    @FXML
    private MaterialDesignIconView moveIcon;

    @FXML
    private Button cloneAction;

    @FXML
    private MaterialDesignIconView cloneIcon;

    @FXML
    private Button changeColorAction;

    @FXML
    private MaterialDesignIconView changeColorIcon;

    @FXML
    private Button scaleAction;

    @FXML
    private MaterialDesignIconView scaleIcon;

    /**
     * the selected color by the user
     */
    private ColorPalette selectedColor;

    /**
     * The chosen type in the creation of the shape
     */
    private ShapeType currentType;

    /**
     * The shape selected by the user or just created
     */
    private Shape selectedShape;

    /**
     * Initialization of the creation of a shape
     *
     * @param event
     */
    @FXML
    void initCreateShape(MouseEvent event) {
        //Init of the input area
        initCreateGrid();
        // Style change
        Button source = (Button) event.getSource();
        source.setStyle(CLICKED_STYLE);
        // Setting the current type creation
        if (source.equals(addTriangleButton)) {
            currentType = ShapeType.TRIANGLE;
        } else if (source.equals(addDiamondButton)) {
            currentType = ShapeType.DIAMOND;
        } else if (source.equals(addPentagonButton)) {
            currentType = ShapeType.PENTAGON;
        }

    }

    /**
     * Initialization of the move action
     * @param event 
     */
    @FXML
    void initMoveShape(MouseEvent event) {
        //Init of the input area
        initMoveGrid();
        // Style change
        Button source = (Button) event.getSource();
        source.setStyle(CLICKED_STYLE);
    }

    /**
     * Initialization of the clone action
     * @param event 
     */
    @FXML
    void initCloneShape(MouseEvent event) {
        //Init of the input area
        initCloneGrid();
        // Style change
        Button source = (Button) event.getSource();
        source.setStyle(CLICKED_STYLE);
    }

    /**
     * Initialization of the color change action
     * @param event 
     */
    @FXML
    void initChangeColorShape(MouseEvent event) {
        //Init of the input area
        initChangeColorGrid();
        // Style change
        Button source = (Button) event.getSource();
        source.setStyle(CLICKED_STYLE);
    }

     /**
     * Initialization of the scale action
     * the factor is a positive double
     * if it is greater than one the shape's size will increase
     * if it is lower than 1 and strictly greater than 0 the shape's size will decrease
     * @param event 
     */
    @FXML
    void initScaleShape(MouseEvent event) {
        //Init of the input area
        initScaleGrid();
        // Style change
        Button source = (Button) event.getSource();
        source.setStyle(CLICKED_STYLE);
    }

    /**
     * Init creation grid inputs
     */
    void initCreateGrid() {
        // reset selected selements  
        selectedShape = null;
        selectedColor = null;

        // refresh the view
        refresh();

        // show creation box and hide modification box
        creationBox.setVisible(true);
        actionsBox.setVisible(false);

        // reset color values
        redSelect.setText("");
        greenSelect.setText("");
        blueSelect.setText("");
        redSelect.setVisible(true);
        greenSelect.setVisible(true);
        blueSelect.setVisible(true);

        // init inputs and labels
        inputX.setVisible(true);
        inputY.setVisible(true);
        inputRadius.setVisible(true);
        inputX.setText("");
        inputY.setText("");
        inputRadius.setText("");

        labelX.setText("X :");
        labelY.setText("Y :");
        labelRadius.setText("Radius :");
        labelColor.setText("Color :");

        labelX.setVisible(true);
        labelY.setVisible(true);
        labelRadius.setVisible(true);
        labelColor.setVisible(true);

        moveAction.setVisible(false);
        createAction.setVisible(true);
        cloneAction.setVisible(false);
        changeColorAction.setVisible(false);
        scaleAction.setVisible(false);
    }

    /**
     * Init move grid inputs
     */
    void initMoveGrid() {
        // reset color values
        redSelect.setVisible(false);
        greenSelect.setVisible(false);
        blueSelect.setVisible(false);

        // init inputs and labels
        inputX.setVisible(true);
        inputY.setVisible(true);
        inputRadius.setVisible(false);
        inputX.setText("0");
        inputY.setText("0");

        labelX.setText("ΔX :");
        labelY.setText("ΔY :");

        labelX.setVisible(true);
        labelY.setVisible(true);
        labelRadius.setVisible(false);
        labelColor.setVisible(false);

        creationBox.setVisible(true);

        moveAction.setVisible(true);
        createAction.setVisible(false);
        cloneAction.setVisible(false);
        changeColorAction.setVisible(false);
        scaleAction.setVisible(false);
    }

    /**
     * Init clone grid inputs
     */
    void initCloneGrid() {
        // reset color values
        redSelect.setVisible(false);
        greenSelect.setVisible(false);
        blueSelect.setVisible(false);

        // init inputs and labels
        inputX.setVisible(true);
        inputY.setVisible(true);
        inputRadius.setVisible(false);
        inputX.setText("0");
        inputY.setText("0");

        labelX.setText("Relative X :");
        labelY.setText("Relative Y :");

        labelX.setVisible(true);
        labelY.setVisible(true);
        labelRadius.setVisible(false);
        labelColor.setVisible(false);

        creationBox.setVisible(true);

        moveAction.setVisible(false);
        createAction.setVisible(false);
        cloneAction.setVisible(true);
        changeColorAction.setVisible(false);
        scaleAction.setVisible(false);
    }

    /**
     * Init change color grid inputs
     */
    void initChangeColorGrid() {
        // reset color values
        redSelect.setVisible(true);
        greenSelect.setVisible(true);
        blueSelect.setVisible(true);
        selectedColor = null;
        redSelect.setText("");
        greenSelect.setText("");
        blueSelect.setText("");

        // init inputs and labels
        inputX.setVisible(false);
        inputY.setVisible(false);
        inputRadius.setVisible(false);

        labelX.setVisible(false);
        labelY.setVisible(false);
        labelRadius.setVisible(false);
        labelColor.setVisible(true);

        creationBox.setVisible(true);

        moveAction.setVisible(false);
        createAction.setVisible(false);
        cloneAction.setVisible(false);
        changeColorAction.setVisible(true);
        scaleAction.setVisible(false);
    }

    /**
     * Init scale grid inputs
     */
    void initScaleGrid() {
        // reset color values
        redSelect.setVisible(false);
        greenSelect.setVisible(false);
        blueSelect.setVisible(false);

        // init inputs and labels
        inputX.setVisible(false);
        inputY.setVisible(false);
        inputRadius.setVisible(true);
        inputRadius.setText("");

        labelRadius.setText("Scaling factor :");

        labelX.setVisible(false);
        labelY.setVisible(false);
        labelRadius.setVisible(true);
        labelColor.setVisible(false);

        creationBox.setVisible(true);

        moveAction.setVisible(false);
        createAction.setVisible(false);
        cloneAction.setVisible(false);
        changeColorAction.setVisible(false);
        scaleAction.setVisible(true);
    }

    /**
     *
     * Validates the inputs and creates the shape
     *
     * @param event
     */
    @FXML
    void createShape(MouseEvent event) {
        // Check of the input
        if (checkInteger(inputX) && checkInteger(inputY) && checkDouble(inputRadius) && selectedColor != null) {
            // Execution of the action
            selectedShape = repository.execute(new CreateCommand(shapeManager, new Coordinate(Integer.valueOf(inputX.getText()), Integer.valueOf(inputY.getText())), Double.valueOf(inputRadius.getText()), selectedColor, currentType));
            // refresh of the GUI to update the drawing area and button status
            refresh();
        } else {
            // Error message
            footerLabel.setText("Please fill all the fields correctly");
            footerLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Executes the move action
     *
     * @param event
     */
    @FXML
    void moveShape(MouseEvent event) {
        if (checkInteger(inputX) && checkInteger(inputY)) {
            // Execution of the action
            repository.execute(new MoveCommand(shapeManager, new Coordinate(Integer.valueOf(inputX.getText()), Integer.valueOf(inputY.getText())), selectedShape));
            // refresh of the GUI to update the drawing area and button status
            refresh();
        } else {
            // Error message
            footerLabel.setText("Please fill all the fields correctly");
            footerLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Executes the clone action
     *
     * @param event
     */
    @FXML
    void cloneShape(MouseEvent event) {
        if (checkInteger(inputX) && checkInteger(inputY)) {
            // Execution of the action
            selectedShape = repository.execute(new CloneCommand(shapeManager, selectedShape, new Coordinate(Integer.valueOf(inputX.getText()), Integer.valueOf(inputY.getText()))));
            // refresh of the GUI to update the drawing area and button status
            refresh();
        } else {
            // Error message
            footerLabel.setText("Please fill all the fields correctly");
            footerLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Execute the change color action
     *
     * @param event
     */
    @FXML
    void changeColorShape(MouseEvent event) {
        if (selectedColor != null) {
            // Execution of the action
            repository.execute(new ChangeColorCommand(shapeManager, selectedColor, selectedShape));
            // refresh of the GUI to update the drawing area and button status
            refresh();
        } else {
            // Error message
            footerLabel.setText("Please fill all the fields correctly");
            footerLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Executes the scaling of the shape
     *
     * @param event
     */
    @FXML
    void scaleShape(MouseEvent event) {
        if (checkDouble(inputRadius)) {
            // Execution of the action
            repository.execute(new ScaleCommand(shapeManager, selectedShape, Double.valueOf(inputRadius.getText())));
            // refresh of the GUI to update the drawing area and button status
            refresh();
        } else {
            // Error message
            footerLabel.setText("Please fill all the fields correctly");
            footerLabel.setStyle("-fx-text-fill: red;");
        }
    }

    /**
     * Executes the deletion of the shape
     *
     * @param event
     */
    @FXML
    void deleteShape(MouseEvent event) {
        // Execution of the action
        repository.execute(new DeleteCommand(shapeManager, selectedShape));
        // refresh of the GUI to update the drawing area and button status
        refresh();
    }
    
    /**
     * Undoes the last action
     * @param event 
     */
    @FXML
    void undoAction(MouseEvent event) {
        // Execution of the action
        repository.undo();
        // Reset the selected shape
        selectedShape = null;
        // Execution of the action
        refresh();
    }
    
    /**
     * Redoes the last undone action
     * @param event 
     */
    @FXML
    void redoAction(MouseEvent event) {
        // Execution of the action
        repository.redo();
        // Reset the selected shape
        selectedShape = null;
        // Execution of the action
        refresh();
    }

    /**
     * Checks if the value of the input is an integer
     *
     * @param input input to check
     * @return true if integer false otherwise
     */
    boolean checkInteger(TextField input) {
        try {
            Integer.valueOf(input.getText());
            input.setStyle("-fx-text-fill: black;");
            return true;
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage());
            input.setStyle("-fx-text-fill: red;");
            return false;
        }
    }

    /**
     * Checks if the value of the input is a double
     *
     * @param input input to check
     * @return true if double false otherwise
     */
    boolean checkDouble(TextField input) {
        try {
            Double value = Double.valueOf(input.getText());
            if (0d>=value) {
                return false;
            }
            input.setStyle("-fx-text-fill: black;");
            return true;
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage());
            input.setStyle("-fx-text-fill: red;");
            return false;
        }
    }

    /**
     * Initialization of the view
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        creationBox.setVisible(false);
        actionsBox.setVisible(false);
    }

    /**
     * Manages the choice of the color
     *
     * @param event
     */
    @FXML
    void checkColor(MouseEvent event) {
        redSelect.setText("");
        greenSelect.setText("");
        blueSelect.setText("");
        Button button = (Button) event.getSource();
        button.setText("x");
        // Changes the color for the selected shape
        if (button.equals(redSelect)) {
            selectedColor = ColorPalette.RED;
        } else if (button.equals(greenSelect)) {
            selectedColor = ColorPalette.GREEN;
        } else if (button.equals(blueSelect)) {
            selectedColor = ColorPalette.BLUE;
        }
    }

    /**
     * Refreshes the GUI
     */
    void refresh() {
        // Clears the canvas area
        canvas.getChildren().clear();
        // Gets the status of the undo and redo buttons
        undoButton.setDisable(!repository.undoable());
        redoButton.setDisable(!repository.redoable());
        // Resets the GUI if there is a selected element or not
        if (selectedShape != null) {
            actionsBox.setVisible(true);
            creationBox.setVisible(false);
            addTriangleButton.setStyle(BASIC_STYLE);
            addDiamondButton.setStyle(BASIC_STYLE);
            addPentagonButton.setStyle(BASIC_STYLE);
            moveButton.setStyle(BASIC_STYLE);
            cloneButton.setStyle(BASIC_STYLE);
            changeColorButton.setStyle(BASIC_STYLE);
            scaleButton.setStyle(BASIC_STYLE);
            footerLabel.setText(selectedShape.toString());
            footerLabel.setStyle("-fx-text-fill: black;");
        } else {
            footerLabel.setText("");
        }
        
        // Using the iterator DP to list the shapes
        Iterator<Shape> iterator = shapeManager.iterator();
        while (iterator.hasNext()) {
            // Getting the next Shape
            Shape shape = iterator.next();
            // Preparing the shape to draw
            Polygon polygon = new Polygon();
            getShapePoints(polygon, shape);
            if (selectedShape != null && Objects.equals(shape.getId(), selectedShape.getId())) {
                polygon.setFill(getLightColor(shape.getColor()));
            } else {
                polygon.setFill(Color.valueOf(shape.getColor().toString()));
            }
            // Identifying the shape by the same id as the shape
            polygon.setId(shape.getId().toString());
            // The event that selects a shape when clicked
            polygon.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    // Get the Shape by it's id from the shapes map
                    Shape sel = shapeManager.getShape(Integer.parseInt(polygon.getId()));
                    // set the selected shape
                    selectedShape = sel;
                    // Refresh the GUI
                    refresh();

                }
            });
            // Adding the shape to the canvas
            canvas.getChildren().add(polygon);
        }
        // Resets the position of the canvas to the 0,0 point
        Line line = new Line(0, 0, 1, 1);
        canvas.getChildren().add(line);
    }

    /**
     * Calculate the points depending on the center of the shape the radius and
     * number of sides
     *
     * @param polygon
     * @param shape
     */
    void getShapePoints(Polygon polygon, Shape shape) {
        final double angleStep = Math.PI * 2 / shape.getSides();
        // The first point has to be directly over the center point
        double angle = 0;
        // Getting the coordinates of the comosing points of the shape
        for (int i = 0; i < shape.getSides(); i++, angle += angleStep) {
            polygon.getPoints().addAll(
                    Math.sin(angle) * shape.getRadius() + shape.getCoordinates().getX(), // x coordinate of the corner
                    shape.getCoordinates().getY() - Math.cos(angle) * shape.getRadius()// y coordinate of the corner
            );
        }
    }

    /**
     * Get Selection color
     *
     * @param color
     * @return Color
     */
    public Color getLightColor(ColorPalette color) {
        switch (color) {
            case RED:
                return Color.HOTPINK;
            case GREEN:
                return Color.LIME;
            case BLUE:
                return Color.SKYBLUE;
            default:
                return Color.valueOf(color.toString());
        }
    }
}

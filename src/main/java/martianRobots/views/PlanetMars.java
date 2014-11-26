package martianRobots.views;

import com.google.inject.Inject;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import martianRobots.Mars;
import martianRobots.exceptions.ValidationException;
import martianRobots.factories.RobotFactory;
import martianRobots.lang.Compass;
import martianRobots.lang.Messages;
import martianRobots.robots.Robot;

import java.io.IOException;
import java.util.Arrays;

import static martianRobots.lang.Max.MAX_NUMBER_COORDS;
import static martianRobots.lang.Messages.EMPTY;
import static martianRobots.lang.Messages.MARS;
import static martianRobots.lang.Messages.MULTI_SPACE;
import static martianRobots.lang.Messages.NOT_A_COMPASS;
import static martianRobots.lang.Messages.NOT_A_NUMBER;

public class PlanetMars extends Parent {

    private final Mars mars;
    private final RobotFactory robotFactory;
    private Button reset;
    private Button go;
    private Label messages;
    private BorderPane planet;
    private TextField position;
    private TextField instructions;
    private TextArea output;

    @Inject
    public PlanetMars(Mars mars, RobotFactory robotFactory) {
        this.mars = mars;
        this.robotFactory = robotFactory;
        planet = getFXML();
        reset = (Button) planet.getTop().lookup("#reset");
        go = (Button) planet.getTop().lookup("#go");
        Button move = (Button) planet.getCenter().lookup("#move");
        TextField x = (TextField) planet.getTop().lookup("#x");
        TextField y = (TextField) planet.getTop().lookup("#y");
        position = (TextField) planet.getCenter().lookup("#position");
        instructions = (TextField) planet.getCenter().lookup("#instructions");
        output = (TextArea) planet.getCenter().lookup("#output");
        messages = (Label) planet.getBottom();
        setVisible(false, planet.getCenter(), reset);
        go.setOnMouseClicked(goToMars(x, y));
        reset.setOnMouseClicked(leaveMars(x, y));
        move.setOnMouseClicked(move(position, instructions, output));
        this.getChildren().add(planet);
    }

    private EventHandler<MouseEvent> move(TextField position, TextField instructions, TextArea output) {
        return event -> {
            messages.setText(EMPTY.toString());
            String[] pos = position.getText().trim().split(MULTI_SPACE.toString());
            try {
                if ((pos.length < MAX_NUMBER_COORDS.getMax()) || (pos.length > MAX_NUMBER_COORDS.getMax())) {
                    messages.setText(formatMessage("Max number of coordinates is", MAX_NUMBER_COORDS));
                } else {
                    int x = Integer.parseInt(pos[0]);
                    int y = Integer.parseInt(pos[1]);
                    Compass orientation = Compass.valueOf(pos[2].toUpperCase());

                    Robot robot = robotFactory.createRobot(x, y, orientation);
                    mars.setRobot(robot);
                    mars.move(instructions.getText().toUpperCase());
                    output.setText(mars.getRobot());
                }
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            } catch (NumberFormatException e) {
                messages.setText(formatMessage(e.getMessage(), NOT_A_NUMBER));
            } catch (IllegalArgumentException e) {
                messages.setText(formatMessage(pos[2], NOT_A_COMPASS));
            }
        };
    }

    private String formatMessage(Object info, Object message) {
        return info.toString() + Messages.SPACE + message;
    }

    private EventHandler<MouseEvent> leaveMars(TextField x, TextField y) {
        return event -> {
            messages.setText(EMPTY.toString());
            setVisible(false, planet.getCenter(), reset);
            resetTextFields(x, y, position, instructions);
            output.clear();
            y.setPromptText("Y");
            x.setPromptText("X");
            setVisible(true, x, y, go);
        };
    }

    private void resetTextFields(TextField... fields) {
        Arrays.stream(fields).forEach(field -> {
            field.deselect();
            field.clear();
        });
    }

    private EventHandler<MouseEvent> goToMars(TextField x, TextField y) {
        return event -> {
            try {
                messages.setText(EMPTY.toString());
                mars.setup(Integer.parseInt(x.getText().trim()), Integer.parseInt(y.getText().trim()));
                setVisible(true, planet.getCenter(), reset);
                setVisible(false, go, x, y);
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            } catch (NumberFormatException e) {
                messages.setText(formatMessage(e.getMessage(), NOT_A_NUMBER));
            }
        };
    }

    private void setVisible(boolean visible, Node... nodes) {
        Arrays.stream(nodes).forEach(node -> node.setVisible(visible));
    }

    private BorderPane getFXML() {
        try {
            return FXMLLoader.load(getClass().getResource(MARS.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

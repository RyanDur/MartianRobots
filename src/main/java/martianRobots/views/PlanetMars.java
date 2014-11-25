package martianRobots.views;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import martianRobots.Mars;
import martianRobots.exceptions.ValidationException;
import martianRobots.factories.RobotFactory;
import martianRobots.lang.Compass;
import martianRobots.lang.Constants;
import martianRobots.robots.Robot;

import java.io.IOException;
import java.util.Arrays;

public class PlanetMars extends Parent {

    private final Mars mars;
    private final RobotFactory robotFactory;
    private Button reset;
    private Button go;
    private Label messages;
    private BorderPane planet;

    public PlanetMars(Mars mars, RobotFactory robotFactory) {
        this.mars = mars;
        this.robotFactory = robotFactory;
        planet = getFXML();
        reset = (Button) planet.getTop().lookup("#reset");
        go = (Button) planet.getTop().lookup("#go");
        Button move = (Button) planet.getCenter().lookup("#move");
        TextField x = (TextField) planet.getTop().lookup("#x");
        TextField y = (TextField) planet.getTop().lookup("#y");
        TextField position = (TextField) planet.getCenter().lookup("#position");
        TextField instructions = (TextField) planet.getCenter().lookup("#instructions");
        messages = (Label) planet.getBottom();
        setVisible(false, planet.getCenter(), reset);
        go.setOnMouseClicked(goToMars(x, y));
        reset.setOnMouseClicked(leaveMars(x, y));
        move.setOnMouseClicked(move(position, instructions));
        this.getChildren().add(planet);
    }

    private EventHandler<MouseEvent> move(TextField position, TextField instructions) {
        return event -> {
            String[] pos = position.getText().trim().split(" +");
            try {
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                Compass orientation = Compass.valueOf(pos[2].toUpperCase());

                Robot robot = robotFactory.createRobot(x, y, orientation);
                mars.setRobot(robot);
                mars.move(instructions.getText().toUpperCase());
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            } catch (NumberFormatException e) {
                String message = " is not a number!!";
                messages.setText(e.getMessage() + message);
            } catch (IllegalArgumentException e) {
                String message = " is not a Compass position!";
                messages.setText(pos[2] + message);
            }
        };
    }

    private EventHandler<MouseEvent> leaveMars(TextField x, TextField y) {
        return event -> {
            setVisible(false, planet.getCenter(), reset);
            resetTextFields(x, y);
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
                messages.setText(Constants.EMPTY);
                mars.setup(Integer.parseInt(x.getText().trim()), Integer.parseInt(y.getText().trim()));
                setVisible(true, planet.getCenter(), reset);
                setVisible(false, go, x, y);
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            } catch (NumberFormatException e) {
                String message = " is not a number!!";
                messages.setText(e.getMessage() + message);
            }
        };
    }

    private void setVisible(boolean visible, Node... nodes) {
        Arrays.stream(nodes).forEach(node -> node.setVisible(visible));
    }

    private BorderPane getFXML() {
        try {
            return FXMLLoader.load(getClass().getResource(Constants.MARS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

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
import martianRobots.lang.View;
import martianRobots.robots.Robot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static martianRobots.lang.Max.MAX_NUMBER_COORDS;
import static martianRobots.lang.Messages.EMPTY;
import static martianRobots.lang.Messages.MAX_NUMBER_COORDS_IS;
import static martianRobots.lang.Messages.MULTI_SPACE;
import static martianRobots.lang.Messages.NOT_A_COMPASS;
import static martianRobots.lang.Messages.NOT_A_NUMBER;
import static martianRobots.lang.Messages.X_PROMPT;
import static martianRobots.lang.Messages.Y_PROMPT;
import static martianRobots.lang.View.GO_ID;
import static martianRobots.lang.View.INS_ID;
import static martianRobots.lang.View.MARS;
import static martianRobots.lang.View.OUTPUT_ID;
import static martianRobots.lang.View.POS_ID;
import static martianRobots.lang.View.RESET_ID;
import static martianRobots.lang.View.X_ID;
import static martianRobots.lang.View.Y_ID;

public class Earth extends Parent {

    private final Mars mars;
    private final RobotFactory robotFactory;
    private Button reset;
    private Button go;
    private Label messages;
    private BorderPane planet;
    private TextField position;
    private TextField instructions;
    private TextArea output;
    private TextField x;
    private TextField y;

    @Inject
    public Earth(Mars mars, RobotFactory robotFactory) {
        this.mars = mars;
        this.robotFactory = robotFactory;
        planet = getFXML();
        setupText();
        setupButtons();
        toggleVisible(false, true);
        this.getChildren().add(planet);
    }

    private EventHandler<MouseEvent> move() {
        return event -> {
            messages.setText(EMPTY.toString());
            String[] pos = position.getText().trim().split(MULTI_SPACE.toString());
            if ((pos.length < MAX_NUMBER_COORDS.getMax()) || (pos.length > MAX_NUMBER_COORDS.getMax())) {
                messages.setText(formatMessage(MAX_NUMBER_COORDS_IS, MAX_NUMBER_COORDS));
            } else try {
                mars.setRobot(getRobot(pos));
                mars.move(instructions.getText().toUpperCase());
                output.setText(mars.getRobot());
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            }
        };
    }

    private EventHandler<MouseEvent> leaveMars() {
        return event -> {
            messages.setText(EMPTY.toString());
            toggleVisible(false, true);
            resetTextFields(x, y, position, instructions);
            output.clear();
            y.setPromptText(Y_PROMPT.toString());
            x.setPromptText(X_PROMPT.toString());
        };
    }

    private EventHandler<MouseEvent> goToMars() {
        return event -> {
            try {
                messages.setText(EMPTY.toString());
                Integer[] coords = parseInt(x.getText().trim(), y.getText().trim());
                mars.setup(coords[0], coords[1]);
                toggleVisible(true, false);
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            }
        };
    }

    private Robot getRobot(String[] pos) {
        try {
            Integer[] coords = parseInt(pos[0], pos[1]);
            Compass orientation = Compass.valueOf(pos[2].toUpperCase());
            return robotFactory.createRobot(coords[0], coords[1], orientation);
        } catch (IllegalArgumentException e) {
            messages.setText(formatMessage(pos[2], NOT_A_COMPASS));
        }
        return null;
    }


    private Integer[] parseInt(String... ints) {
        List<Integer> list = new ArrayList<>();
        for (String num : ints) {
            try {
                list.add(Integer.parseInt(num));
            } catch (NumberFormatException e) {
                messages.setText(formatMessage(e.getMessage(), NOT_A_NUMBER));
            }
        }
        return list.toArray(new Integer[list.size()]);
    }

    private void setupButtons() {
        reset = (Button) planet.getTop().lookup(RESET_ID.toString());
        go = (Button) planet.getTop().lookup(GO_ID.toString());
        Button move = (Button) planet.getCenter().lookup(View.MOVE_ID.toString());
        go.setOnMouseClicked(goToMars());
        reset.setOnMouseClicked(leaveMars());
        move.setOnMouseClicked(move());
    }

    private void setupText() {
        x = (TextField) planet.getTop().lookup(X_ID.toString());
        y = (TextField) planet.getTop().lookup(Y_ID.toString());
        position = (TextField) planet.getCenter().lookup(POS_ID.toString());
        instructions = (TextField) planet.getCenter().lookup(INS_ID.toString());
        output = (TextArea) planet.getCenter().lookup(OUTPUT_ID.toString());
        messages = (Label) planet.getBottom();
    }

    private String formatMessage(Object info, Object message) {
        return info.toString() + Messages.SPACE + message;
    }

    private void resetTextFields(TextField... fields) {
        Arrays.stream(fields).forEach(field -> {
            field.deselect();
            field.clear();
        });
    }

    private void toggleVisible(boolean control, boolean menu) {
        setVisible(control, planet.getCenter(), reset);
        setVisible(menu, x, y, go);
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

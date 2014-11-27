package martianRobots.views;

import com.google.inject.Inject;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
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
import static martianRobots.lang.Messages.GO;
import static martianRobots.lang.Messages.MAX_NUMBER_COORDS_IS;
import static martianRobots.lang.Messages.MULTI_SPACE;
import static martianRobots.lang.Messages.NEW_LINE;
import static martianRobots.lang.Messages.NOT_A_COMPASS;
import static martianRobots.lang.Messages.NOT_A_NUMBER;
import static martianRobots.lang.Messages.RESET;
import static martianRobots.lang.Messages.SPACE;
import static martianRobots.lang.Messages.X_PROMPT;
import static martianRobots.lang.Messages.Y_PROMPT;
import static martianRobots.lang.View.GO_ID;
import static martianRobots.lang.View.INSTRUCTIONS_ID;
import static martianRobots.lang.View.MARS;
import static martianRobots.lang.View.POS_ID;
import static martianRobots.lang.View.X_ID;
import static martianRobots.lang.View.Y_ID;

public class Earth extends Parent {

    private final Mars mars;
    private final RobotFactory robotFactory;
    private Label messages;
    private BorderPane planet;
    private TextField position;
    private TextField instructions;
    private TextArea output;
    private TextField x;
    private TextField y;
    private TextArea start;
    private TextArea ins;

    @Inject
    public Earth(Mars mars, RobotFactory robotFactory) {
        this.mars = mars;
        this.robotFactory = robotFactory;
        planet = getFXML();
        setupText();
        planet.getTop().lookup(GO_ID.toString()).setOnMouseClicked(goToMars());
        planet.getCenter().lookup(View.MOVE_ID.toString()).setOnMouseClicked(move());
        toggleDisplay(false, true);
        this.getChildren().add(planet);
    }

    private EventHandler<MouseEvent> move() {
        return event -> {
            String[] pos = position.getText().trim().split(MULTI_SPACE.toString());
            if ((pos.length < MAX_NUMBER_COORDS.getMax()) || (pos.length > MAX_NUMBER_COORDS.getMax())) {
                messages.setText(MAX_NUMBER_COORDS_IS.toString() + SPACE + MAX_NUMBER_COORDS.getMax());
            } else try {
                mars.setRobot(getRobot(pos));
                mars.move(instructions.getText().toUpperCase());
                setOutput(mars.getRobot(), output);
                setOutput(position.getText(), start);
                setOutput(instructions.getText(), ins);
                resetTextFields(position, instructions);
                messages.setText(EMPTY.toString());
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            }
        };
    }

    private EventHandler<MouseEvent> leaveMars() {
        return event -> {
            toggleDisplay(false, true);
            resetTextFields(x, y, position, instructions);
            output.clear();
            start.clear();
            ins.clear();
            messages.setText(EMPTY.toString());
            y.setPromptText(Y_PROMPT.toString());
            x.setPromptText(X_PROMPT.toString());
            setButton((Button) event.getSource(), GO, goToMars());
        };
    }

    private EventHandler<MouseEvent> goToMars() {
        return event -> {
            try {
                messages.setText(EMPTY.toString());
                Integer[] coords = parseInts(x.getText(), y.getText());
                mars.setup(coords[0], coords[1]);
                toggleDisplay(true, false);
                setButton((Button) event.getSource(), RESET, leaveMars());
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            }
        };
    }

    private void setOutput(String put, TextArea area) {
        String out = area.getText();
        if (out.length() != 0) out += NEW_LINE;
        area.setText(out + put);
    }

    private void setButton(Button source, Messages message, EventHandler<MouseEvent> func) {
        source.setText(message.toString());
        source.setOnMouseClicked(func);
    }

    private Robot getRobot(String[] pos) throws ValidationException {
        Integer[] coords = parseInts(pos[0], pos[1]);
        try {
            return robotFactory.createRobot(coords[0], coords[1], Compass.valueOf(pos[2].toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new ValidationException(pos[2], NOT_A_COMPASS);
        }
    }

    private Integer[] parseInts(String... ints) throws ValidationException {
        List<Integer> list = new ArrayList<>();
        for (String num : ints) {
            try {
                list.add(Integer.parseInt(num.trim()));
            } catch (NumberFormatException e) {
                throw new ValidationException(num, NOT_A_NUMBER);
            }
        }
        return list.toArray(new Integer[list.size()]);
    }

    private void setupText() {
        x = (TextField) planet.getTop().lookup(X_ID.toString());
        y = (TextField) planet.getTop().lookup(Y_ID.toString());
        position = (TextField) planet.getCenter().lookup(POS_ID.toString());
        instructions = (TextField) planet.getCenter().lookup(INSTRUCTIONS_ID.toString());
        ScrollPane scroll = (ScrollPane) planet.getRight().lookup("#right").lookup("#scroll");
        output = (TextArea) scroll.getContent().lookup("#output");
        messages = (Label) planet.getBottom();
        ScrollPane scrollL = (ScrollPane) planet.getLeft();
        SplitPane split = (SplitPane) scrollL.getContent();
        start = (TextArea) split.getItems().get(0).lookup("#start");
        ins = (TextArea) split.getItems().get(1).lookup("#ins");
    }

    private void resetTextFields(TextField... fields) {
        Arrays.stream(fields).forEach(field -> {
            field.deselect();
            field.clear();
        });
    }

    private void toggleDisplay(boolean control, boolean menu) {
        planet.getCenter().setVisible(control);
        x.setDisable(!menu);
        y.setDisable(!menu);
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

package martianRobots.views;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import martianRobots.Mars;
import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Constants;
import martianRobots.robots.Robot;

import java.io.IOException;

public class PlanetMars extends Parent {

    private final Mars mars;
    private final Robot robot;
    private Button reset;
    private Button go;
    private Label messages;
    private BorderPane planet;

    public PlanetMars(Mars mars, Robot robot) {
        this.mars = mars;
        this.robot = robot;
        planet = getFXML();
        planet.getCenter().setVisible(false);
        reset = (Button) planet.getTop().lookup("#reset");
        go = (Button) planet.getTop().lookup("#go");
        TextField x = (TextField) planet.getTop().lookup("#x");
        TextField y = (TextField) planet.getTop().lookup("#y");
        messages = (Label) planet.getBottom();
        reset.setVisible(false);
        go.setOnMouseClicked(goToMars(x, y));
        this.getChildren().add(planet);
    }

    private EventHandler<MouseEvent> goToMars(TextField x, TextField y) {
        return event -> {
            try {
                messages.setText(Constants.EMPTY);
                mars.setup(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
                planet.getCenter().setVisible(true);
                reset.setVisible(true);
            } catch (ValidationException e) {
                messages.setText(e.getMessage());
            } catch (NumberFormatException e) {
                String message = " is not a number!!";
                messages.setText(e.getMessage() + message);
            }
        };
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

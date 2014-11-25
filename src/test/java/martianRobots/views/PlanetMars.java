package martianRobots.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import martianRobots.Mars;
import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Constants;
import martianRobots.robots.Robot;

import java.io.IOException;

public class PlanetMars extends Parent {

    private final Mars mars;
    private final Robot robot;

    public PlanetMars(Mars mars, Robot robot) {
        this.mars = mars;
        this.robot = robot;
        BorderPane planet = getFXML();
        planet.getCenter().setVisible(false);
        Button reset = (Button) planet.getTop().lookup("#reset");
        Button go = (Button) planet.getTop().lookup("#go");
        TextField x = (TextField) planet.getTop().lookup("#x");
        TextField y = (TextField) planet.getTop().lookup("#y");
        go.setOnMouseClicked(event -> {
            try {
                mars.setup(Integer.parseInt(x.getText()), Integer.parseInt(y.getText()));
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });
        reset.setVisible(false);
        this.getChildren().add(planet);
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

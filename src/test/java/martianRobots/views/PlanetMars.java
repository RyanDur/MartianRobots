package martianRobots.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import martianRobots.lang.Constants;

import java.io.IOException;

public class PlanetMars extends Parent {

    public PlanetMars() {
        BorderPane mars = getFXML();
        this.getChildren().add(mars);
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

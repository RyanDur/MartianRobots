import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import martianRobots.modules.ViewModule;
import martianRobots.views.PlanetMars;

public class BlastOff extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Injector injector = Guice.createInjector(new ViewModule());
            PlanetMars pm = injector.getInstance(PlanetMars.class);
            stage.setScene(new Scene(pm));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

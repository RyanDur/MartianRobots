package martianRobots.views;

import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

public class PlanetMarsTest extends GuiTest{

    @Override
    protected Parent getRootNode() {
        return new PlanetMars();
    }

    @Test
    public void shouldHaveAGoToMarsButton() {
        verifyThat("#go", hasText("Go to Mars"));
    }
}
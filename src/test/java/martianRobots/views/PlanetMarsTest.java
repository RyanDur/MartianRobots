package martianRobots.views;

import javafx.scene.Parent;
import martianRobots.Mars;
import martianRobots.robots.Robot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesVisibleException;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.mock;

public class PlanetMarsTest extends GuiTest{

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Override
    protected Parent getRootNode() {
        Mars mars = mock(Mars.class);
        Robot robot = mock(Robot.class);
        return new PlanetMars(mars, robot);
    }

    @Test
    public void shouldHaveAGoToMarsButton() {
        verifyThat("#go", hasText("Go to Mars"));
    }

    @Test
    public void shouldHaveTheControlsHiddenOnStartup() {
        exception.expect(NoNodesVisibleException.class);
        find("#control");
    }
}
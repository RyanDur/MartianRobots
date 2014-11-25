package martianRobots.views;

import javafx.scene.Parent;
import martianRobots.Mars;
import martianRobots.exceptions.ValidationException;
import martianRobots.robots.Robot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesVisibleException;

import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlanetMarsTest extends GuiTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Mars mars = mock(Mars.class);

    @Override
    protected Parent getRootNode() {
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

    @Test
    public void shouldHideTheResetButtonOnStartup() {
        exception.expect(NoNodesVisibleException.class);
        find("#reset");
    }

    @Test
    public void shouldBeAbleToInputAGridSizeAndGoToMars() throws ValidationException {
        click("#x").type("5").click("#y").type("3").click("#go");
        verify(mars).setup(5, 3);
    }
}
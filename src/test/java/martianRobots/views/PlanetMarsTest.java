package martianRobots.views;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import martianRobots.Mars;
import martianRobots.exceptions.ValidationException;
import martianRobots.robots.Robot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesVisibleException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
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

    @Test
    public void shouldBeAbleToHandleIfInputIsNotANumberInX() {
        click("#x").type("mop").click("#y").type("4").click("#go");
        verifyThat("#messages", hasText("For input string: \"mop\" is not a number!!"));
    }

    @Test
    public void shouldBeAbleToHandleIfInputIsNotANumberInY() {
        click("#x").type("6").click("#y").type("tigger").click("#go");
        verifyThat("#messages", hasText("For input string: \"tigger\" is not a number!!"));
    }

    @Test
    public void shouldBeAbleToHandleValidationExceptionFromMars() throws ValidationException {
        doThrow(new ValidationException("Hello")).when(mars).setup(anyInt(), anyInt());
        click("#x").type("6").click("#y").type("4").click("#go");
        verifyThat("#messages", hasText("Hello"));
    }

    @Test
    public void shouldRemoveMessageIfInputIsCorrect() throws ValidationException {
        click("#x").type("foo").click("#y").type("4").click("#go");
        verifyThat("#messages", hasText("For input string: \"foo\" is not a number!!"));
        click("#x").push(KeyCode.BACK_SPACE).push(KeyCode.BACK_SPACE).push(KeyCode.BACK_SPACE).type("2").click("#go");
        verify(mars).setup(2, 4);
        verifyThat("#messages", hasText(""));
    }

    @Test
    public void shouldMakeControlVisibleWhenGoingToMars() {
        click("#x").type("5").click("#y").type("3").click("#go");
        assertThat(find("#control").isVisible(), is(true));
    }

    @Test
    public void shouldMakeResetVisibleWhenGoingToMars() {
        click("#x").type("5").click("#y").type("3").click("#go");
        assertThat(find("#reset").isVisible(), is(true));
    }

    @Test
    public void shouldMakeGoInvisibleWhenGoingToMars() {
        exception.expect(NoNodesVisibleException.class);
        click("#x").type("5").click("#y").type("3").click("#go");
        find("#go");
    }

    @Test
    public void shouldMakeXInvisibleWhenGoingToMars() {
        exception.expect(NoNodesVisibleException.class);
        click("#x").type("5").click("#y").type("3").click("#go");
        find("#x");
    }

    @Test
    public void shouldMakeYInvisibleWhenGoingToMars() {
        exception.expect(NoNodesVisibleException.class);
        click("#x").type("5").click("#y").type("3").click("#go");
        find("#y");
    }
}
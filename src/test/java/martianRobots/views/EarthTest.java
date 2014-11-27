package martianRobots.views;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import martianRobots.Mars;
import martianRobots.exceptions.ValidationException;
import martianRobots.factories.RobotFactory;
import martianRobots.lang.Compass;
import martianRobots.lang.Max;
import martianRobots.lang.Messages;
import martianRobots.robots.Robot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.exceptions.NoNodesVisibleException;

import static martianRobots.lang.Messages.GO;
import static martianRobots.lang.Messages.NOT_A_COMPASS;
import static martianRobots.lang.Messages.NOT_A_NUMBER;
import static martianRobots.lang.Messages.RESET;
import static martianRobots.lang.View.CONTROL_ID;
import static martianRobots.lang.View.GO_ID;
import static martianRobots.lang.View.INSTRUCTIONS_ID;
import static martianRobots.lang.View.INS_ID;
import static martianRobots.lang.View.MESSAGES_ID;
import static martianRobots.lang.View.MOVE_ID;
import static martianRobots.lang.View.OUTPUT_ID;
import static martianRobots.lang.View.POS_ID;
import static martianRobots.lang.View.START_ID;
import static martianRobots.lang.View.X_ID;
import static martianRobots.lang.View.Y_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EarthTest extends GuiTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Mars mars;
    private RobotFactory robotFactory;
    private Robot robot;

    @Override
    protected Parent getRootNode() {
        mars = mock(Mars.class);
        robotFactory = mock(RobotFactory.class);
        robot = mock(Robot.class);
        when(robotFactory.createRobot(anyInt(), anyInt(), any(Compass.class))).thenReturn(robot);
        return new Earth(mars, robotFactory);
    }

    @Test
    public void shouldHaveAGoToMarsButton() {
        verifyThat(GO_ID.toString(), hasText(GO.toString()));
    }

    @Test
    public void shouldBeAbleToInputAGridSizeAndGoToMars() throws ValidationException {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").click(GO_ID.toString());
        verify(mars).setup(5, 3);
    }

    @Test
    public void shouldBeAbleToHandleIfInputIsNotANumberInX() {
        String mop = "mop";
        click(X_ID.toString()).type(mop).click(Y_ID.toString()).type("3").click(GO_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText(mop + " " + NOT_A_NUMBER));
    }

    @Test
    public void shouldBeAbleToHandleIfInputIsNotANumberInY() {
        String tigger = "tigger";
        click(X_ID.toString()).type("6").click(Y_ID.toString()).type(tigger).click(GO_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText(tigger + " " + NOT_A_NUMBER));
    }

    @Test
    public void shouldBeAbleToHandleValidationExceptionFromMars() throws ValidationException {
        doThrow(new ValidationException("Hello", Messages.DOT)).when(mars).setup(anyInt(), anyInt());
        click(X_ID.toString()).type("6").click(Y_ID.toString()).type("4").click(GO_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText("Hello" + " " + Messages.DOT));
    }

    @Test
    public void shouldRemoveMessageIfInputIsCorrect() throws ValidationException {
        String text = "foo";
        when(mars.getRobot()).thenReturn("Hello");
        click(X_ID.toString()).type(text).click(Y_ID.toString()).type("4").click(GO_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText(text + " " + NOT_A_NUMBER));
        click(X_ID.toString())
                .push(KeyCode.BACK_SPACE).push(KeyCode.BACK_SPACE).push(KeyCode.BACK_SPACE)
                .type("2").click(GO_ID.toString());
        verify(mars).setup(2, 4);
        verifyThat(MESSAGES_ID.toString(), hasText(""));
    }

    @Test
    public void shouldMakeControlVisibleWhenGoingToMars() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").click(GO_ID.toString());
        assertThat(find(CONTROL_ID.toString()).isVisible(), is(true));
    }

    @Test
    public void shouldMakeGoIntoResetWhenGoingToMars() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").click(GO_ID.toString());
        verifyThat(GO_ID.toString(), hasText(RESET.toString()));
    }

    @Test
    public void shouldDisableXWhenGoingToMars() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").click(GO_ID.toString());
        assertThat(find(X_ID.toString()).disableProperty().get(), is(true));
    }

    @Test
    public void shouldDisableYWhenGoingToMars() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").click(GO_ID.toString());
        assertThat(find(Y_ID.toString()).disableProperty().get(), is(true));
    }

    @Test
    public void shouldMakeControlInvisibleWhenReset() {
        exception.expect(NoNodesVisibleException.class);
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").
                click(GO_ID.toString()).click(GO_ID.toString());
        find(CONTROL_ID.toString());
    }

    @Test
    public void shouldMakeYEditableWhenReset() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").
                click(GO_ID.toString()).click(GO_ID.toString());
        assertThat(find(Y_ID.toString()).disableProperty().get(), is(false));
    }

    @Test
    public void shouldMakeXEditableWhenReset() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").
                click(GO_ID.toString()).click(GO_ID.toString());
        assertThat(find(X_ID.toString()).disableProperty().get(), is(false));
    }

    @Test
    public void shouldMakeGoVisibleWhenReset() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").
                click(GO_ID.toString()).click(GO_ID.toString());
        verifyThat(GO_ID.toString(), hasText(GO.toString()));
    }

    @Test
    public void shouldToReInputNewBoundariesForMars() throws ValidationException {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").
                click(GO_ID.toString()).click(GO_ID.toString())
                .click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3").
                click(GO_ID.toString());
        verify(mars, times(2)).setup(5, 3);
    }

    @Test
    public void shouldCreateARobotWhenMoving() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("2 4 N").click(MOVE_ID.toString());
        verify(robotFactory).createRobot(2, 4, Compass.N);
    }

    @Test
    public void shouldMakeSureThereAreEnoughCoordinatesForARobot() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(MOVE_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText("Max number of coordinates is " + Max.MAX_NUMBER_COORDS.getMax()));
    }

    @Test
    public void shouldMakeSureThereIsNotMoreTHanEnoughCoordinatesForARobot() {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("1 2 N 4").click(MOVE_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText("Max number of coordinates is " + Max.MAX_NUMBER_COORDS.getMax()));
    }

    @Test
    public void shouldPlaceARobotWhenMoving() throws ValidationException {
        Robot robot = mock(Robot.class);
        when(robotFactory.createRobot(2, 4, Compass.N)).thenReturn(robot);
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("2 4 N").click(MOVE_ID.toString());
        verify(mars).setRobot(robot);
    }

    @Test
    public void shouldSetOrientationToUpperCaseWhenPlaceARobotWhenMoving() throws ValidationException {
        Robot robot = mock(Robot.class);
        when(robotFactory.createRobot(2, 4, Compass.N)).thenReturn(robot);
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("2 4 n").click(MOVE_ID.toString());
        verify(robotFactory).createRobot(2, 4, Compass.N);
    }

    @Test
    public void shouldTrimTheSpaceFromInputs() throws ValidationException {
        when(robotFactory.createRobot(2, 4, Compass.N)).thenReturn(robot);
        click(X_ID.toString()).type("5    ").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("  2   4 N  ").click(MOVE_ID.toString());
        verify(mars).setRobot(robot);
    }

    @Test
    public void shouldNotAllowNonNumbersFromInputs() throws ValidationException {
        when(robotFactory.createRobot(2, 4, Compass.N)).thenReturn(robot);
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("F 4 N").click(MOVE_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText("F " + NOT_A_NUMBER));
    }

    @Test
    public void shouldNotAllowNonCompassFromInputs() throws ValidationException {
        Robot robot = mock(Robot.class);
        when(robotFactory.createRobot(2, 4, Compass.N)).thenReturn(robot);
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("5 4 R").click(MOVE_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText("R " + NOT_A_COMPASS));
    }

    @Test
    public void shouldBeAbleToHandleMessagesFromMars() throws ValidationException {
        Robot robot = mock(Robot.class);
        doThrow(new ValidationException("Hello", Messages.DOT)).when(mars).setRobot(any(Robot.class));
        when(robotFactory.createRobot(2, 4, Compass.N)).thenReturn(robot);

        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString())
                .type("5 4 S").click(MOVE_ID.toString());
        verifyThat(MESSAGES_ID.toString(), hasText("Hello " + Messages.DOT));
    }

    @Test
    public void shouldBeAbleToSendInstructionsToMars() throws ValidationException {
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString()).type("5 4 S")
                .click(INSTRUCTIONS_ID.toString()).type("sdgsdfgsdfg").click(MOVE_ID.toString());
        verify(mars).move("SDGSDFGSDFG");
    }

    @Test
    public void shouldGetDataFromMars() {
        when(mars.getRobot()).thenReturn("Hello From Mars");
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString()).click(POS_ID.toString()).type("5 4 S")
                .click(INSTRUCTIONS_ID.toString()).type("sdgsdfgsdfg").click(MOVE_ID.toString());

        verifyThat(OUTPUT_ID.toString(), hasText("Hello From Mars"));
    }

    @Test
    public void shouldResetEverythingWhenReset() {
        when(mars.getRobot()).thenReturn("Hello From Mars");
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString())
                .click(POS_ID.toString()).type("5 4 S")
                .click(INSTRUCTIONS_ID.toString()).type("sdgsdfgsdfg")
                .click(MOVE_ID.toString())
                .click(GO_ID.toString())
                .click(X_ID.toString()).type("5")
                .click(Y_ID.toString()).type("3")
                .click(GO_ID.toString());

        verifyThat(POS_ID.toString(), hasText(""));
        verifyThat(INSTRUCTIONS_ID.toString(), hasText(""));
        verifyThat(OUTPUT_ID.toString(), hasText(""));
    }

    @Test
    public void shouldPutTheNextInoutOnTheFollowingLine() {
        when(mars.getRobot()).thenReturn("Hello From Mars", "Goodbye From Mars");
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString())
                .click(POS_ID.toString()).type("5 3 S")
                .click(INSTRUCTIONS_ID.toString()).type("s")
                .click(MOVE_ID.toString())
                .click(POS_ID.toString()).type("1 2 N")
                .click(INSTRUCTIONS_ID.toString()).type("sdg")
                .click(MOVE_ID.toString());
        verifyThat(OUTPUT_ID.toString(), hasText("Hello From Mars\nGoodbye From Mars"));
    }

    @Test
    public void shouldPutTheStartAndInsOnMutlipleLinesLine() {
        when(mars.getRobot()).thenReturn("Hello From Mars", "Goodbye From Mars");
        click(X_ID.toString()).type("5").click(Y_ID.toString()).type("3")
                .click(GO_ID.toString())
                .click(POS_ID.toString()).type("5 3 S")
                .click(INSTRUCTIONS_ID.toString()).type("s")
                .click(MOVE_ID.toString())
                .click(POS_ID.toString()).type("1 2 N")
                .click(INSTRUCTIONS_ID.toString()).type("sdg")
                .click(MOVE_ID.toString());
        verifyThat(START_ID.toString(), hasText("5 3 S\n1 2 N"));
        verifyThat(INS_ID.toString(), hasText("s\nsdg"));
    }
}
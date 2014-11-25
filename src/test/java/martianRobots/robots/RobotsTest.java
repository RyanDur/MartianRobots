package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import martianRobots.lang.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RobotsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Robots robots;

    @Before
    public void setup() {
        robots = new RobotsImpl();
    }

    @Test
    public void shouldBeAbleToCreateALeftRobot() throws ValidationException {
        assertThat(robots.createRobot('L', 1, 2, Compass.E), is(instanceOf(L.class)));
    }

    @Test
    public void shouldBeAbleToCreateARightRobot() throws ValidationException {
        assertThat(robots.createRobot('R', 1, 2, Compass.E), is(instanceOf(R.class)));
    }

    @Test
    public void shouldBeAbleToCreateAForwardRobot() throws ValidationException {
        assertThat(robots.createRobot('F', 1, 2, Compass.E), is(instanceOf(F.class)));
    }

    @Test
    public void shouldThrowAnExceptionForANonExistentClass() throws ValidationException {
        exception.expect(ValidationException.class);
        robots.createRobot('V', 1, 2, Compass.E);
    }

    @Test
    public void shouldThrowAHelpfulMessageForANonExistentClass() throws ValidationException {
        char direction = 'V';
        exception.expectMessage(direction + Constants.INVALID_DIRECTION);
        robots.createRobot(direction, 1, 2, Compass.E);
    }
}
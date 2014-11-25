package martianRobots.robots;

import com.google.inject.AbstractModule;

public class RobotModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Robots.class).to(RobotsImpl.class);
    }
}

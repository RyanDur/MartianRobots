package martianRobots.modules;

import com.google.inject.AbstractModule;
import martianRobots.robots.Robots;
import martianRobots.robots.RobotsImpl;

public class RobotFactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Robots.class).to(RobotsImpl.class);
    }
}

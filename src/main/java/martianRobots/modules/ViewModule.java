package martianRobots.modules;

import com.google.inject.AbstractModule;
import martianRobots.Mars;
import martianRobots.MarsImpl;
import martianRobots.factories.RobotFactory;
import martianRobots.factories.RobotFactoryImpl;

public class ViewModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Mars.class).to(MarsImpl.class);
        bind(RobotFactory.class).to(RobotFactoryImpl.class);
    }
}

package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HomeEventProcessor implements EventProcessor{
    private final EventCreator eventCreator;
    private final Logger logger;
    private final LightEventProcessor lightEventProcessor;
    private final DoorEventProcessor doorEventProcessor;
    private Event event;
    private SmartHomeImpl smartHome;

    public HomeEventProcessor(Logger logger) {
        this.eventCreator = new EventCreatorImpl();
        this.logger = logger;
        this.lightEventProcessor = new LightEventProcessor(logger);
        this.doorEventProcessor = new DoorEventProcessor(logger);
    }

    @Override
    public void processEvent(Object smartHome) {
        this.smartHome = (SmartHomeImpl) smartHome;
        event = eventCreator.getNextEvent();
        while (event != null) {
            logger.log("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                lightEventProcessor.processEvent(event, this.smartHome);

            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                doorEventProcessor.processEvent(event, this.smartHome);
            }
            event = eventCreator.getNextEvent();
        }
    }

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        //TO DO
    }
}

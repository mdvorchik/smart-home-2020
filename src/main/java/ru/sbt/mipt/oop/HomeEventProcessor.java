package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HomeEventProcessor implements EventProcessor{
    private final EventCreator eventCreator;
    private final Logger logger;
    private final LightEventProcessor lightEventProcessor;
    private final DoorEventProcessor doorEventProcessor;

    public HomeEventProcessor(EventCreator eventCreator, Logger logger, LightEventProcessor lightEventProcessor, DoorEventProcessor doorEventProcessor) {
        this.eventCreator = eventCreator;
        this.logger = logger;
        this.lightEventProcessor = lightEventProcessor;
        this.doorEventProcessor = doorEventProcessor;
    }

    @Override
    public void processEvent(Object smartHome) {
        SmartHome smartHome1 = (SmartHome) smartHome;
        Event event = eventCreator.getNextEvent();
        while (event != null) {
            logger.log("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                lightEventProcessor.processEvent(event, smartHome1);

            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                doorEventProcessor.processEvent(event, smartHome1);
            }
            event = eventCreator.getNextEvent();
        }
    }

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        //TO DO
    }
}

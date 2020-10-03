package ru.sbt.mipt.oop;

import java.util.List;

public class GenericEventProcessor implements  EventProcessor{

    private final EventCreator eventCreator;
    private final Logger logger;
    private final List<EventProcessor> eventProcessors;

    public GenericEventProcessor(EventCreator eventCreator, Logger logger, List<EventProcessor> eventProcessors) {
        this.eventCreator = eventCreator;
        this.logger = logger;
        this.eventProcessors = eventProcessors;
    }

    @Override
    public void processEvent(Event event, Object objectWhereIsEvent) {
        SmartHome smartHome = (SmartHome) objectWhereIsEvent;
        while (event != null) {
            logger.log("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(event, smartHome);
            }
            event = eventCreator.getNextEvent();
        }
    }
}

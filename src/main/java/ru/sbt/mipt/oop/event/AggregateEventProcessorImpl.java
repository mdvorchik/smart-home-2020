package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.house.SmartHome;

import java.util.List;

public class AggregateEventProcessorImpl implements AggregateEventProcessor {

    private final EventCreator eventCreator;
    private final Logger logger;
    private final List<EventProcessor> eventProcessors;

    public AggregateEventProcessorImpl(EventCreator eventCreator, Logger logger, List<EventProcessor> eventProcessors) {
        this.eventCreator = eventCreator;
        this.logger = logger;
        this.eventProcessors = eventProcessors;
    }

    @Override
    public void processEvents(Object objectWhereIsEvent) {
        SmartHome smartHome = (SmartHome) objectWhereIsEvent;
        Event event = eventCreator.getNextEvent();
        do {
            logger.log("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(event, smartHome);
            }
            event = eventCreator.getNextEvent();
        } while (event != null);
    }
}

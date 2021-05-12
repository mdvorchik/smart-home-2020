package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.house.SmartHome;

import java.util.List;

public class AggregateEventProcessorImpl implements AggregateEventProcessor {

    private final Logger logger;
    private final List<EventProcessor> eventProcessors;

    public AggregateEventProcessorImpl(Logger logger, List<EventProcessor> eventProcessors) {
        this.logger = logger;
        this.eventProcessors = eventProcessors;
    }

    @Override
    public void processEvents(EventCreator eventCreator, Object objectWhereIsEvents) {
        SmartHome smartHome = (SmartHome) objectWhereIsEvents;
        Event event = eventCreator.createNextEvent();
        do {
            logger.log("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(event, smartHome);
            }
            event = eventCreator.createNextEvent();
        } while (event != null);
    }
}

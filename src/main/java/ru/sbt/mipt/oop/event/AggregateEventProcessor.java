package ru.sbt.mipt.oop.event;

public interface AggregateEventProcessor {
    void processEvents(EventCreator eventCreator, Object objectWhereIsEvents);
}

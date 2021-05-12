package ru.sbt.mipt.oop.event;

public interface AggregateEventProcessor {
    void processEvents(Object objectWhereIsEvents);
}

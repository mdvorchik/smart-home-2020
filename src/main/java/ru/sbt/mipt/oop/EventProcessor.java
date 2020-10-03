package ru.sbt.mipt.oop;

public interface EventProcessor {
    void processEvent(Event event, Object objectWhereTheEventOccurs);
}

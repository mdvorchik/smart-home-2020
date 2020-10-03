package ru.sbt.mipt.oop;

public interface EventProcessor {
    void processEvent(Object objectWhereTheEventOccurs);
    void processEvent(Event event, Object objectWhereTheEventOccurs);
}

package ru.sbt.mipt.oop;

public interface EventProcessor {
    public void processEvent(Object objectWhereTheEventOccurs);
    public void processEvent(Event event, Object objectWhereTheEventOccurs);
}

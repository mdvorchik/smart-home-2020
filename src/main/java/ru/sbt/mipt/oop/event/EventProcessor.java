package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.event.Event;

public interface EventProcessor {
    void processEvent(Event event, Object objectWhereTheEventOccurs);
}

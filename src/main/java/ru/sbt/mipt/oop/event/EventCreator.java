package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.event.Event;

public interface EventCreator {
    Event getNextEvent();
}

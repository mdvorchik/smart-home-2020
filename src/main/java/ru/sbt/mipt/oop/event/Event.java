package ru.sbt.mipt.oop.event;

public interface Event {
    Enum getType();
    String getObjectId();
}

package ru.sbt.mipt.oop;

public interface Event {
    SensorEventType getType();
    String getObjectId();
}

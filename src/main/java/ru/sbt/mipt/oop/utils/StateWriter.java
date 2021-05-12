package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.house.SmartHome;

import java.io.IOException;

public interface StateWriter {
    void writeStateToDestination (SmartHome source, Object destination) throws IOException;
}

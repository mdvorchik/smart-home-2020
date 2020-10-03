package ru.sbt.mipt.oop;

import java.io.IOException;

public interface StateWriter {
    void writeStateToDestination (SmartHomeImpl source, Object destination) throws IOException;
}

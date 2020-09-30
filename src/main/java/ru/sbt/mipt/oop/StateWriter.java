package ru.sbt.mipt.oop;

import java.io.IOException;

public interface StateWriter {
    public void writeStateToDestination (SmartHomeImpl source, Object destination) throws IOException;
}

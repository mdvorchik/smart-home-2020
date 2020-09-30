package ru.sbt.mipt.oop;

import java.io.IOException;

public interface StateWriter {
    public void writeStateToDestination (SmartHome source, Object destination) throws IOException;
}

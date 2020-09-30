package ru.sbt.mipt.oop;

import java.io.IOException;

public interface StateReader {

    public SmartHomeImpl readStateOfHome (Object source) throws IOException;

}

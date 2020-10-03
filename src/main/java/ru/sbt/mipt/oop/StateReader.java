package ru.sbt.mipt.oop;

import java.io.IOException;

public interface StateReader {

    SmartHomeImpl readStateOfHome (Object source) throws IOException;

}

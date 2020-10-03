package ru.sbt.mipt.oop;

import java.io.IOException;

public interface StateReader {

    SmartHome readStateOfHome (Object source) throws IOException;

}

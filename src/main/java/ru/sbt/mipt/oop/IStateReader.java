package ru.sbt.mipt.oop;

import java.io.IOException;

public interface IStateReader {

    public SmartHome readStateOfHome (Object source) throws IOException;

}

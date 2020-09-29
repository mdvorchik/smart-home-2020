package ru.sbt.mipt.oop;

import java.io.IOException;

public interface IStateReader {

    public Object readState (Object source, Class classOfTargetObject) throws IOException;

}

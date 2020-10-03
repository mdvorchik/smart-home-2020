package ru.sbt.mipt.oop;

import java.util.Collection;

public interface Room {
    Collection<Light> getLights();
    Collection<Door> getDoors();
    String getName();
}

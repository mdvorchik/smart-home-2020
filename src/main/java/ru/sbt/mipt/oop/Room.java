package ru.sbt.mipt.oop;

import java.util.Collection;

public interface Room {
    public Collection<Light> getLights();

    public Collection<Door> getDoors();

    public String getName();
}

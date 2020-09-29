package ru.sbt.mipt.oop;

import java.util.Collection;

public interface IRoom {
    public Collection<ILight> getLights();

    public Collection<IDoor> getDoors();

    public String getName();
}

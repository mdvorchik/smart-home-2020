package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public interface ISmartHome {
    public void addRoom(IRoom room);
    public Collection<IRoom> getRooms();
}

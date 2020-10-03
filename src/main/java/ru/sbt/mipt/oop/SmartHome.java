package ru.sbt.mipt.oop;

import java.util.Collection;

public interface SmartHome {
    void addRoom(Room room);
    Collection<Room> getRooms();
}

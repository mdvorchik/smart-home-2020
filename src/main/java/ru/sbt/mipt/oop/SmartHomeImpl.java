package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHomeImpl {
    Collection<RoomImpl> rooms;

    public SmartHomeImpl() {
        rooms = new ArrayList<>();
    }

    public SmartHomeImpl(Collection<RoomImpl> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(RoomImpl room) {
        rooms.add(room);
    }

    public Collection<RoomImpl> getRooms() {
        return rooms;
    }

}

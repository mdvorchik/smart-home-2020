package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHomeImpl implements SmartHome {
    Collection<Room> rooms;

    public SmartHomeImpl() {
        rooms = new ArrayList<>();
    }

    public SmartHomeImpl(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public Collection<Room> getRooms() {
        return rooms;
    }

//    @Override
//    public Object createInstance(Type type) {
//        SmartHome smartHome = new SmartHome();
//        return smartHome;
//    }
}

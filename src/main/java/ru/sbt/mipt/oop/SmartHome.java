package ru.sbt.mipt.oop;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements ISmartHome {
    Collection<IRoom> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<IRoom> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    @Override
    public Collection<IRoom> getRooms() {
        return rooms;
    }

//    @Override
//    public Object createInstance(Type type) {
//        SmartHome smartHome = new SmartHome();
//        return smartHome;
//    }
}

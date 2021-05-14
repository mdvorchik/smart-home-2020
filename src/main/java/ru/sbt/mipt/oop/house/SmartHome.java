package ru.sbt.mipt.oop.house;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Actionable> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Actionable> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Actionable> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        for (Actionable room : this.getRooms()) {
            room.execute(action);
        }
    }
}

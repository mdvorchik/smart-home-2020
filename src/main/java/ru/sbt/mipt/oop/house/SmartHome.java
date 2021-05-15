package ru.sbt.mipt.oop.house;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    private Collection<Room> getRooms() {
        return rooms;
    }

    public String getRoomNameByLightId(String id) {
        for (Actionable room: this.getRooms()) {
            if (room instanceof Room && ((Room) room).containsLightWithId(id))
                return ((Room) room).getName();
        }
        return "not found";
    }

    public String getRoomNameByDoorId(String id) {
        for (Actionable room: this.getRooms()) {
            if (room instanceof Room && ((Room) room).containsDoorWithId(id))
                return ((Room) room).getName();
        }
        return "not found";
    }

    @Override
    public void execute(Action action) {
        for (Actionable room : this.getRooms()) {
            room.execute(action);
        }
    }
}

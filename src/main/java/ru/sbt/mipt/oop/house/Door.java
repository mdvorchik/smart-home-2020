package ru.sbt.mipt.oop.house;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;
import ru.sbt.mipt.oop.action.DoorCloseAction;
import ru.sbt.mipt.oop.action.DoorOpenAction;

public class Door implements Actionable {

    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getRoomName(SmartHome smartHome) {
        for (Actionable room: smartHome.getRooms()) {
            if (room instanceof Room && ((Room) room).containsDoorWithId(id))
                return ((Room) room).getName();
        }
        return "not found";
    }

    @Override
    public void execute(Action action) {
        if (action instanceof DoorOpenAction) {
            action.execute(this);
        }
        if (action instanceof DoorCloseAction) {
            action.execute(this);
        }
    }
}

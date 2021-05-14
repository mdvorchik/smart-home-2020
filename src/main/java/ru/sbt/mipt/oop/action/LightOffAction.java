package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;

public class LightOffAction implements Action {
    private String objectId;
    private SmartHome smartHome;
    private Logger logger;

    LightOffAction(String objectId, SmartHome smartHome, Logger logger) {
        this.objectId = objectId;
        this.smartHome = smartHome;
        this.logger = logger;
    }

    private String getRoomName() {
        for (Actionable room: smartHome.getRooms()) {
            if (room instanceof Room && ((Room) room).containsLight(objectId))
                return ((Room) room).getName();
        }
        return "not found";
    }

    @Override
    public void execute(Object component) {
        if (component instanceof Light && objectId.equals(((Light) component).getId())) {
            ((Light) component).setOn(false);
            logger.log("Light " + objectId + " in room " + getRoomName() + " was turned off.");
        }
    }
}
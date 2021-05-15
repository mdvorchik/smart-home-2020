package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;

public class DoorOpenAction implements Action {
    private String objectId;
    private SmartHome smartHome;
    private Logger logger;

    public DoorOpenAction(String objectId, SmartHome smartHome, Logger logger) {
        this.objectId = objectId;
        this.smartHome = smartHome;
        this.logger = logger;
    }

    @Override
    public void execute(Object component) {
        if (component instanceof Door && objectId.equals(((Door) component).getId())) {
            ((Door) component).setOpen(true);
            logger.log("Door " + objectId + " in room " + smartHome.getRoomNameByDoorId(objectId) + " was opened.");
        }
    }
}

package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;

public class LightOffAction implements Action {
    private String objectId;
    private SmartHome smartHome;
    private Logger logger;

    public LightOffAction(String objectId, SmartHome smartHome, Logger logger) {
        this.objectId = objectId;
        this.smartHome = smartHome;
        this.logger = logger;
    }

    @Override
    public void execute(Object component) {
        if (component instanceof Light && objectId.equals(((Light) component).getId())) {
            ((Light) component).setOn(false);
            logger.log("Light " + objectId + " in room " + ((Light) component).getRoomName(smartHome) + " was turned off.");
        }
    }
}
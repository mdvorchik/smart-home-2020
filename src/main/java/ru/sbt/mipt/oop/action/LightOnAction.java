package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;

public class LightOnAction implements Action {
    private String objectId;
    private SmartHome smartHome;
    private Logger logger;

    public LightOnAction(String objectId, SmartHome smartHome, Logger logger) {
        this.objectId = objectId;
        this.smartHome = smartHome;
        this.logger = logger;
    }

    @Override
    public void execute(Object component) {
        if (component instanceof Light && objectId.equals(((Light) component).getId())) {
            ((Light) component).setOn(true);
            logger.log("Light " + objectId + " in room " + smartHome.getRoomNameByLightId(objectId) + " was turned on.");
        }
    }
}


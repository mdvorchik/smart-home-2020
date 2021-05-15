package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;

public class DoorCloseAction implements Action {
    private String objectId;
    private SmartHome smartHome;
    private CommandSender commandSender;
    private Logger logger;

    public DoorCloseAction(String objectId, SmartHome smartHome, CommandSender commandSender, Logger logger) {
        this.objectId = objectId;
        this.smartHome = smartHome;
        this.commandSender = commandSender;
        this.logger = logger;
    }

    @Override
    public void execute(Object component) {
        if (component instanceof Door && objectId.equals(((Door) component).getId())) {
            ((Door) component).setOpen(false);
            logger.log("Door " + objectId + " in room " + smartHome.getRoomNameByDoorId(objectId) + " was closed.");
            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
            if (smartHome.getRoomNameByDoorId(objectId).equals("hall")) {
                smartHome.execute(new EveryLightOffAction(commandSender));
            }
        }
    }
}

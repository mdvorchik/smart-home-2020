package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.action.ForceEveryLightOffAction;
import ru.sbt.mipt.oop.command.Command;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    private final Logger logger;
    private final CommandSender commandSender;

    public DoorEventProcessor(Logger logger, CommandSender commandSender) {
        this.logger = logger;
        this.commandSender = commandSender;
    }

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        if (event.getType() != DOOR_OPEN && event.getType() != DOOR_CLOSED) return;
        SmartHome smartHome = (SmartHome) objectWhereTheEventOccurs;
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        logger.log("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        logger.log("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) { turnOffLight(smartHome); }
                    }
                }
            }
        }
    }

    private void turnOffLight (SmartHome smartHome) {
        smartHome.execute(new ForceEveryLightOffAction(commandSender));
    }
}

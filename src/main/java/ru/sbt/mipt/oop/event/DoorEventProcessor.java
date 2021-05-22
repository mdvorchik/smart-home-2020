package ru.sbt.mipt.oop.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.action.DoorCloseAction;
import ru.sbt.mipt.oop.action.DoorOpenAction;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;

import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_OPEN;

@Component
public class DoorEventProcessor implements EventProcessor {

    private final Logger logger;
    private final CommandSender commandSender;

    @Autowired
    public DoorEventProcessor(Logger logger, CommandSender commandSender) {
        this.logger = logger;
        this.commandSender = commandSender;
    }

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        if (event.getType() != DOOR_OPEN && event.getType() != DOOR_CLOSED) return;
        SmartHome smartHome = (SmartHome) objectWhereTheEventOccurs;
        if (event.getType() == DOOR_OPEN) {
            smartHome.execute(new DoorOpenAction(event.getObjectId(), smartHome, logger));
        } else {
            smartHome.execute(new DoorCloseAction(event.getObjectId(), smartHome, commandSender, logger));
        }
    }
}





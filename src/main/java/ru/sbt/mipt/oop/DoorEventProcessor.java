package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor{

    private final Logger logger;

    public DoorEventProcessor(Logger logger) {
        this.logger = logger;
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

    private void sendCommand(SensorCommand command) {
        logger.log("Pretent we're sending command " + command);
    }

    private void turnOffLight (SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }
}

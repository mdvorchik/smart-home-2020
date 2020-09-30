package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    static Logger logger = new LoggerToConsole();

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        StateReader stateReader = new JsonReader();
        SmartHome smartHome = stateReader.readStateOfHome("smart-home-1.js");
        // начинаем цикл обработки событий
        EventCreator eventCreator = new EventCreatorImpl();
        Event event = eventCreator.getNextEvent();
        while (event != null) {
            logger.log("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                // событие от источника света
                processingLightEvent(event, smartHome);

            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                // событие от двери
                processingDoorEvent(event, smartHome);
            }
            event = eventCreator.getNextEvent();
        }
    }

    private static void sendCommand(SensorCommand command) {
        logger.log("Pretent we're sending command " + command);
    }

    private static void processingLightEvent (Event event, SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn(true);
                        logger.log("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        logger.log("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    private static void processingDoorEvent (Event event, SmartHome smartHome) {
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

    private static void turnOffLight (SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }
}

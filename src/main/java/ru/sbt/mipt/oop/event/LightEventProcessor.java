package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.utils.Logger;

import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.event.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    private final Logger logger;

    public LightEventProcessor(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        if (event.getType() != LIGHT_ON && event.getType() != LIGHT_OFF) return;
        SmartHome smartHome = (SmartHome) objectWhereTheEventOccurs;
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
}

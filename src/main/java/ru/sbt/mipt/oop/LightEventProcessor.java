package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    private final Logger logger;

    public LightEventProcessor(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void processEvent(Object objectWhereTheEventOccurs) {

    }

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        SmartHomeImpl smartHome = (SmartHomeImpl) objectWhereTheEventOccurs;
        for (RoomImpl room : smartHome.getRooms()) {
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

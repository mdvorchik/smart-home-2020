package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.action.LightOffAction;
import ru.sbt.mipt.oop.action.LightOnAction;
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
                    if (event.getType() == LIGHT_ON) {
                        smartHome.execute(new LightOnAction(event.getObjectId(), smartHome, logger));
                    } else {
                        smartHome.execute(new LightOffAction(event.getObjectId(), smartHome, logger));
                    }
                }
            }
        }
    }
}

package ru.sbt.mipt.oop.event.api.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

import java.util.HashMap;
import java.util.Map;

public class CCSensorEventToSensorEventMapper {
    private static CCSensorEventToSensorEventMapper ccSensorEventToSensorEventMapper;
    private Map<String, SensorEventType> map = new HashMap();

    private CCSensorEventToSensorEventMapper() {
        map.put("LightIsOn", SensorEventType.LIGHT_ON);
        map.put("LightIsOff", SensorEventType.LIGHT_OFF);
        map.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        map.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
    }

    public static CCSensorEventToSensorEventMapper getInstance(){
        if (ccSensorEventToSensorEventMapper == null) {
            ccSensorEventToSensorEventMapper = new CCSensorEventToSensorEventMapper();
        }
        return ccSensorEventToSensorEventMapper;
    }

    public SensorEvent mapCCSensorEventToSensorEvent(CCSensorEvent ccSensorEvent) {
        return new SensorEvent(map.get(ccSensorEvent.getEventType()), ccSensorEvent.getObjectId());
    }
}

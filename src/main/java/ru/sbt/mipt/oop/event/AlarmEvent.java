package ru.sbt.mipt.oop.event;

public class AlarmEvent implements Event {
    private final AlarmEventType type;
    private final String objectId;
    private final String code;

    public AlarmEvent(AlarmEventType type, String objectId, String code) {
        this.type = type;
        this.objectId = objectId;
        this.code = code;
    }

    @Override
    public AlarmEventType getType() {
        return type;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }
}

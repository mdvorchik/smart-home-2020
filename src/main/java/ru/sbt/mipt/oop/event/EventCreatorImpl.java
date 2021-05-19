package ru.sbt.mipt.oop.event;

public class EventCreatorImpl implements EventCreator {
    @Override
    public Event createNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        if (Math.random() < 0.30) {
            return new AlarmEvent(AlarmEventType.ALARM_ACTIVATE, "1", "code");
        }
        if (Math.random() < 0.30) {
            return new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "1", "code");
        }
        if (Math.random() < 0.30) {
            return new AlarmEvent(AlarmEventType.ALARM_DEACTIVATE, "1", "wrong_code");
        }
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}

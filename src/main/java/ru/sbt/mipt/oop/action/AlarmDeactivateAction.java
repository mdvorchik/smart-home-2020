package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.house.alarm.Alarm;

public class AlarmDeactivateAction implements Action{
    private String objectId;
    private SmartHome smartHome;
    private String code;

    public AlarmDeactivateAction(String objectId, SmartHome smartHome, String code) {
        this.objectId = objectId;
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute(Object component) {
        if (component instanceof Alarm && objectId.equals(((Alarm) component).getId())) {
            ((Alarm) component).deactivate(code);
        }
    }
}

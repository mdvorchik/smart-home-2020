package ru.sbt.mipt.oop.house.alarm;

public class AlarmTriggered implements AlarmState {

    private Alarm alarm;

    public AlarmTriggered(Alarm alarm) {
        this.alarm = alarm;
        alarm.setAlarmState(this);
    }

    @Override
    public void activate(String code) {
        //Do nothing
        System.out.println("Alarm with id: " + alarm.getId() + " can not be activated in this state");
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setAlarmState(new AlarmDeactivated(alarm));
            System.out.println("Deactivate alarm with id: " + alarm.getId());
        }
    }

    @Override
    public void trigger() {
        //Do nothing
        System.out.println("Alarm with id: " + alarm.getId() + " is triggered already");
    }
}

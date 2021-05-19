package ru.sbt.mipt.oop.house.alarm;

public class AlarmActivated implements AlarmState{
    private Alarm alarm;

    public AlarmActivated(Alarm alarm) {
        this.alarm = alarm;
        alarm.setAlarmState(this);
    }

    @Override
    public void activate(String code) {
        //Do nothing
        System.out.println("Alarm with id: " + alarm.getId() + " is activated already");
    }

    @Override
    public void deactivate(String code) {
        if (alarm.getCode().equals(code)) {
            alarm.setAlarmState(new AlarmDeactivated(alarm));
            System.out.println("Deactivate alarm with id: " + alarm.getId());
        } else {
            trigger();
        }
    }

    @Override
    public void trigger() {
        alarm.setAlarmState(new AlarmTriggered(alarm));
        System.out.println("Trigger alarm with id: " + alarm.getId());
    }
}

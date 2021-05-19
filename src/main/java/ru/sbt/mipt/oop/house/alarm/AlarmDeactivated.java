package ru.sbt.mipt.oop.house.alarm;

public class AlarmDeactivated implements AlarmState {

    private Alarm alarm;

    public AlarmDeactivated(Alarm alarm) {
        this.alarm = alarm;
        alarm.setAlarmState(this);
    }

    @Override
    public void activate(String code) {
        alarm.setCode(code);
        alarm.setAlarmState(new AlarmActivated(alarm));
        System.out.println("Deactivate alarm with id: " + alarm.getId());
    }

    @Override
    public void deactivate(String code) {
        //Do nothing
        System.out.println("Alarm with id: " + alarm.getId() + " is deactivated already");
    }

    @Override
    public void trigger() {
        //Do nothing
        System.out.println("Alarm with id: " + alarm.getId() + " can not be triggered in this state");
    }
}

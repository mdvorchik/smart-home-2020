package ru.sbt.mipt.oop.house.alarm;

public class Alarm {
    private AlarmState alarmState;
    private String id;
    private String code;

    public Alarm(String id) {
        this.id = id;
        alarmState = new AlarmDeactivated(this);
    }

    void setAlarmState(AlarmState alarmState){
        this.alarmState = alarmState;
    }

    public AlarmState getAlarmState(){
        return alarmState;
    }

    public String getId() {
        return id;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public void activate(String code) {
        alarmState.activate(code);
    }

    public void deactivate(String code) {
        alarmState.deactivate(code);
    }

    public void trigger() {
        alarmState.trigger();
    }
}

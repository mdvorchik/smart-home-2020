package ru.sbt.mipt.oop;

public class LightImpl implements Light {
    private boolean isOn;
    private final String id;

    public LightImpl(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setOn(boolean on) {
        isOn = on;
    }
}

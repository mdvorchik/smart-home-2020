package ru.sbt.mipt.oop;

public class Light implements ILight{
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
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

package ru.sbt.mipt.oop.house;

import ru.sbt.mipt.oop.action.*;

public class Light implements Actionable {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        if (action instanceof LightOffAction) {
            action.execute(this);
        }
        if (action instanceof LightOnAction) {
            action.execute(this);
        }
        if (action instanceof EveryLightOffAction) {
            action.execute(this);
        }
    }
}

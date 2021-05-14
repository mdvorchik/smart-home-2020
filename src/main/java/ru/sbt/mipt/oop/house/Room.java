package ru.sbt.mipt.oop.house;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

import java.util.Collection;

public class Room implements Actionable {

    private final Collection<Actionable> lightsAndDoors;
    private final String name;

    public Room(Collection<Actionable> lightsAndDoors, String name) {
        this.lightsAndDoors = lightsAndDoors;
        this.name = name;
    }

    public Collection<Actionable> getLightsAndDoors() {
        return lightsAndDoors;
    }

    public boolean containsLight(String id) {
        boolean result = false;
        for (Actionable lightsAndDoors : this.getLightsAndDoors()) {
            if (lightsAndDoors instanceof Light && ((Light) lightsAndDoors).getId().equals(id))
                result = true;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        for (Actionable lightsAndDoors : this.getLightsAndDoors()) {
            lightsAndDoors.execute(action);
        }
    }

}

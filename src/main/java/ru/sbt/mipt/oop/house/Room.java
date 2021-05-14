package ru.sbt.mipt.oop.house;

import ru.sbt.mipt.oop.action.Action;
import ru.sbt.mipt.oop.action.Actionable;

import java.util.Collection;

public class Room implements Actionable {

    private final Collection<Light> lights;
    private final Collection<Door> doors;
    private final String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public boolean containsLightWithId(String id) {
        boolean result = false;
        for (Light light : this.getLights()) {
            if (light.getId().equals(id))
                result = true;
        }
        return result;
    }

    public boolean containsDoorWithId(String id) {
        boolean result = false;
        for (Door door : this.getDoors()) {
            if (door.getId().equals(id))
                result = true;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        for (Light light : this.getLights()) {
            light.execute(action);
        }
        for (Door door : this.getDoors()) {
            door.execute(action);
        }
    }
}

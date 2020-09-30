package ru.sbt.mipt.oop;

import java.util.Collection;

public class RoomImpl {
    private final Collection<LightImpl> lights;
    private final Collection<DoorImpl> doors;
    private final String name;

    public RoomImpl(Collection<LightImpl> lights, Collection<DoorImpl> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<LightImpl> getLights() {
        return lights;
    }

    public Collection<DoorImpl> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

}

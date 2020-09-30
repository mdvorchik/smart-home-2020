package ru.sbt.mipt.oop;

import java.util.Collection;

public class RoomImpl {
    private Collection<LightImpl> lights;
    private Collection<DoorImpl> doors;
    private String name;

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

//    @Override
//    public Object createInstance(Type type) {
//        Room room = new Room(new ArrayList<Light>(), new ArrayList<Door>(), "");
//        return new Room();
//    }
}

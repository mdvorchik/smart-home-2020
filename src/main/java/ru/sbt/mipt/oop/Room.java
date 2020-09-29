package ru.sbt.mipt.oop;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class Room implements IRoom {
    private Collection<ILight> lights;
    private Collection<IDoor> doors;
    private String name;

    public Room(Collection<ILight> lights, Collection<IDoor> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<ILight> getLights() {
        return lights;
    }

    public Collection<IDoor> getDoors() {
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

package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {
        Room kitchen = new RoomImpl(Arrays.asList(new LightImpl("1", false), new LightImpl("2", true)),
                Arrays.asList(new DoorImpl(false, "1")),
                "kitchen");
        Room bathroom = new RoomImpl(Arrays.asList(new LightImpl("3", true)),
                Arrays.asList(new DoorImpl(false, "2")),
                "bathroom");
        Room bedroom = new RoomImpl(Arrays.asList(new LightImpl("4", false), new LightImpl("5", false), new LightImpl("6", false)),
                Arrays.asList(new DoorImpl(true, "3")),
                "bedroom");
        Room hall = new RoomImpl(Arrays.asList(new LightImpl("7", false), new LightImpl("8", false), new LightImpl("9", false)),
                Arrays.asList(new DoorImpl(false, "4")),
                "hall");
        SmartHome smartHome = new SmartHomeImpl(Arrays.asList(kitchen, bathroom, bedroom, hall));
        StateWriter stateWriter = new JsonWriter();
        stateWriter.writeStateToDestination(smartHome, "output.js");
    }

}

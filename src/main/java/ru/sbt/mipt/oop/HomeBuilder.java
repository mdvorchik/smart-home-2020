package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;

public class HomeBuilder {
    private final StateWriter stateWriter;
    private final Logger logger;
    private SmartHomeImpl smartHome;
    private RoomImpl kitchen;
    private RoomImpl bathroom;
    private RoomImpl bedroom;
    private RoomImpl hall;

    public HomeBuilder(StateWriter stateWriter, Logger logger) {
        this.stateWriter = stateWriter;
        this.logger = logger;
    }

    public static void main(String[] args) throws IOException {
        HomeBuilder homeBuilder = new HomeBuilder(new JsonWriter(new LoggerToConsole()), new LoggerToConsole());

        homeBuilder.kitchen = new RoomImpl(Arrays.asList(new LightImpl("1", false), new LightImpl("2", true)),
                Arrays.asList(new DoorImpl(false, "1")),
                "kitchen");
        homeBuilder.bathroom = new RoomImpl(Arrays.asList(new LightImpl("3", true)),
                Arrays.asList(new DoorImpl(false, "2")),
                "bathroom");
        homeBuilder.bedroom = new RoomImpl(Arrays.asList(new LightImpl("4", false), new LightImpl("5", false), new LightImpl("6", false)),
                Arrays.asList(new DoorImpl(true, "3")),
                "bedroom");
        homeBuilder.hall = new RoomImpl(Arrays.asList(new LightImpl("7", false), new LightImpl("8", false), new LightImpl("9", false)),
                Arrays.asList(new DoorImpl(false, "4")),
                "hall");
        homeBuilder.smartHome = new SmartHomeImpl(Arrays.asList(homeBuilder.kitchen, homeBuilder.bathroom, homeBuilder.bedroom, homeBuilder.hall));
        homeBuilder.stateWriter.writeStateToDestination((SmartHomeImpl) homeBuilder.smartHome, "output.js");
    }

}

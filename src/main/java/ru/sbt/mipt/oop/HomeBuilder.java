package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.JsonWriter;
import ru.sbt.mipt.oop.utils.LoggerToConsole;
import ru.sbt.mipt.oop.utils.StateWriter;

import java.io.IOException;
import java.util.Arrays;

public class HomeBuilder {
    private final StateWriter stateWriter;
    private SmartHome smartHome;
    private Room kitchen;
    private Room bathroom;
    private Room bedroom;
    private Room hall;

    public HomeBuilder(StateWriter stateWriter) {
        this.stateWriter = stateWriter;
    }

    public static void main(String[] args) throws IOException {
        HomeBuilder homeBuilder = new HomeBuilder(new JsonWriter(new LoggerToConsole()));

        homeBuilder.kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        homeBuilder.bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        homeBuilder.bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        homeBuilder.hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(false, "4")),
                "hall");
        homeBuilder.smartHome = new SmartHome(Arrays.asList(homeBuilder.kitchen, homeBuilder.bathroom, homeBuilder.bedroom, homeBuilder.hall));
        homeBuilder.stateWriter.writeStateToDestination(homeBuilder.smartHome, "output.js");
    }

}

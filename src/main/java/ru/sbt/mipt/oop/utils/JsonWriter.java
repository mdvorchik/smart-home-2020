package ru.sbt.mipt.oop.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.house.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class JsonWriter implements StateWriter {
    @Autowired
    private final Logger logger;

    @Autowired
    public JsonWriter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void writeStateToDestination(SmartHome smartHome, Object destination) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        logger.log(jsonString);
        Path path = Paths.get(destination.toString());
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}

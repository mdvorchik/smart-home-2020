package ru.sbt.mipt.oop.utils;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.house.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader implements StateReader {
    @Override
    public SmartHome readStateOfHome(Object source) {
        Gson gson = new Gson();
        String pathToJsonFile = source.toString();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(pathToJsonFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SmartHome targetObject = gson.fromJson(json, SmartHome.class);
        return targetObject;
    }
}

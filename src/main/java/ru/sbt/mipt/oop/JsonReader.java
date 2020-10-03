package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader implements StateReader {
    @Override
    public SmartHome readStateOfHome(Object source) throws IOException {
        Gson gson = new Gson();
        String pathToJsonFile = source.toString();
        String json = new String(Files.readAllBytes(Paths.get(pathToJsonFile)));
        SmartHome targetObject = gson.fromJson(json, SmartHome.class);
        return targetObject;
    }
}

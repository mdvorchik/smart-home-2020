package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader implements IStateReader {
    @Override
    public Object readState(Object source, Class classOfTargetObject) throws IOException {
        Gson gson = new Gson();
        String pathToJsonFile = source.toString();
        String json = new String(Files.readAllBytes(Paths.get(pathToJsonFile)));
        Object targetObject = gson.fromJson(json, classOfTargetObject);
        return targetObject;
    }
}

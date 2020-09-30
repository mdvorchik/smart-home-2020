package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlReader implements StateReader {
    @Override
    public SmartHomeImpl readStateOfHome(Object source) throws IOException {
        XStream xstream = new XStream();
        String pathToXmlFile = source.toString();
        String xmlString = new String(Files.readAllBytes(Paths.get(pathToXmlFile)));
        SmartHomeImpl targetObject = (SmartHomeImpl) xstream.fromXML(xmlString, SmartHomeImpl.class);
        return targetObject;
    }
}

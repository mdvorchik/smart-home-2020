package ru.sbt.mipt.oop;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlWriter implements StateWriter{
    private final Logger logger;

    public XmlWriter(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void writeStateToDestination(SmartHomeImpl smartHome, Object destination) throws IOException {
        XStream xstream = new XStream();
        String xmlString = xstream.toXML(smartHome);
        logger.log(xmlString);
        Path path = Paths.get(destination.toString());
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(xmlString);
        }
    }
}

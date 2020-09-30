package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private final Logger logger;
    private final StateReader stateReader;
    private EventProcessor eventProcessor;
    private SmartHome smartHome;
    private Event event;

    public Application(Logger loger, StateReader stateReader){
        this.logger = loger;
        this.stateReader = stateReader;
    }

    public static void main(String... args) throws IOException {
        Application application = new Application(new LoggerToConsole(), new JsonReader());
        // считываем состояние дома из файла
        application.smartHome = application.stateReader.readStateOfHome("smart-home-1.js");
        // начинаем цикл обработки событий
        application.eventProcessor = new HomeEventProcessor(application.logger);
        application.eventProcessor.processEvent(application.smartHome);
    }
}

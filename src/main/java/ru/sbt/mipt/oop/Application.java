package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    private final Logger logger;
    private final StateReader stateReader;
    private EventProcessor eventProcessor;
    private SmartHome smartHome;

    public Application(Logger loger, StateReader stateReader){
        this.logger = loger;
        this.stateReader = stateReader;
    }

    public void run() throws IOException {
        // считываем состояние дома из файла
        smartHome = stateReader.readStateOfHome("smart-home-1.js");
        // начинаем цикл обработки событий
        eventProcessor = new HomeEventProcessor(logger);
        eventProcessor.processEvent(smartHome);
    }

    public static void main(String... args) throws IOException {
        Application application = new Application(new LoggerToConsole(), new JsonReader());
        application.run();

    }
}

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.JsonReader;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;
import ru.sbt.mipt.oop.utils.StateReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private final Logger logger;
    private final StateReader stateReader;
    private final EventCreator eventCreator;

    public Application(Logger loger, StateReader stateReader, EventCreator eventCreator){
        this.logger = loger;
        this.stateReader = stateReader;
        this.eventCreator = eventCreator;
    }

    public void run() throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = stateReader.readStateOfHome("smart-home-1.js");
        // создаем список обработчиков возможный событий
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        eventProcessorList.add(new DoorEventProcessor(logger));
        eventProcessorList.add(new LightEventProcessor(logger));
        // начинаем цикл обработки событий
        EventProcessor eventProcessor = new GenericEventProcessor(new EventCreatorImpl(), logger, eventProcessorList);
        eventProcessor.processEvent(eventCreator.getNextEvent(), smartHome);
    }

    public static void main(String... args) throws IOException {
        Application application = new Application(new LoggerToConsole(), new JsonReader(), new EventCreatorImpl());
        application.run();
    }
}

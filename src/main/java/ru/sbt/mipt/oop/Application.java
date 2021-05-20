package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.command.SmartHomeCommandSender;
import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.JsonReader;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;
import ru.sbt.mipt.oop.utils.StateReader;

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

    public void run() {
        // считываем состояние дома из файла
        SmartHome smartHome = stateReader.readStateOfHome("smart-home-1.js");
        // создаем список обработчиков возможный событий
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        eventProcessorList.add(new SecurityEventProcessor(new DoorEventProcessor(logger, new SmartHomeCommandSender(logger))));
        eventProcessorList.add(new SecurityEventProcessor(new LightEventProcessor(logger)));
        eventProcessorList.add(new AlarmEventProcessor());
        // начинаем цикл обработки событий
        AggregateEventProcessor eventProcessor = new AggregateEventProcessorImpl(logger, eventProcessorList);
        eventProcessor.processEvents(eventCreator, smartHome);
    }

    public static void main(String... args) {
        Application application = new Application(new LoggerToConsole(), new JsonReader(), new EventCreatorImpl());
        application.run();
    }
}

package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.command.SmartHomeCommandSender;
import ru.sbt.mipt.oop.event.DoorEventProcessor;
import ru.sbt.mipt.oop.event.LightEventProcessor;
import ru.sbt.mipt.oop.event.SecurityEventProcessor;
import ru.sbt.mipt.oop.event.api.coolcompany.FromEventProcessorToEventHandlerAdaptor;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.JsonReader;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;
import ru.sbt.mipt.oop.utils.StateReader;

public class Application {

    private final Logger logger;
    private final StateReader stateReader;

    public Application(Logger loger, StateReader stateReader){
        this.logger = loger;
        this.stateReader = stateReader;
    }

    public void run() {
        // считываем состояние дома из файла
        SmartHome smartHome = stateReader.readStateOfHome("smart-home-1.js");
        // создаем список обработчиков возможный событий
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new FromEventProcessorToEventHandlerAdaptor(
                new SecurityEventProcessor(new DoorEventProcessor(logger, new SmartHomeCommandSender(logger))),
                smartHome));
        sensorEventsManager.registerEventHandler(new FromEventProcessorToEventHandlerAdaptor(
                new SecurityEventProcessor(new LightEventProcessor(logger)),
                smartHome));
        // начинаем цикл обработки событий
        sensorEventsManager.start();
    }

    public static void main(String... args) {
        Application application = new Application(new LoggerToConsole(), new JsonReader());
        application.run();
    }
}

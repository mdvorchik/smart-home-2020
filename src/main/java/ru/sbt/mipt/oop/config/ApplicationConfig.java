package ru.sbt.mipt.oop.config;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.api.rc.SmartHomeRemoteController;
import ru.sbt.mipt.oop.api.rc.commands.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.event.DoorEventProcessor;
import ru.sbt.mipt.oop.event.LightEventProcessor;
import ru.sbt.mipt.oop.event.api.coolcompany.FromEventProcessorToEventHandlerAdaptor;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.StateReader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"ru.sbt.mipt.oop.utils", "ru.sbt.mipt.oop.command", "ru.sbt.mipt.oop.event"})
public class ApplicationConfig {
    @Autowired
    Logger logger;
    @Autowired
    CommandSender commandSender;
    @Autowired
    StateReader stateReader;

    @Bean
    SmartHome smartHome() {
        // считываем состояние дома из файла
        return stateReader.readStateOfHome("smart-home-1.js");
    }

    @Autowired
    @Bean
    EventHandler doorAndSecurityEventProcessor(DoorEventProcessor doorEventProcessor) {
        return new FromEventProcessorToEventHandlerAdaptor(doorEventProcessor, smartHome());
    }

    @Autowired
    @Bean
    EventHandler lightAndSecurityEventProcessor(LightEventProcessor lightEventProcessor) {
        return new FromEventProcessorToEventHandlerAdaptor(lightEventProcessor, smartHome());
    }

    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventHandler> eventHandlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        for (EventHandler eventHandler : eventHandlers){
            sensorEventsManager.registerEventHandler(eventHandler);
        }
        return sensorEventsManager;
    }

    @Bean
    @Scope("prototype")
    Map<String, RcCommand> rcCommandMap() {
        Map<String, RcCommand> rcCommandMap = new HashMap<>();
        rcCommandMap.put("A", new ActivateAlarmCommand(smartHome(), "code"));
        rcCommandMap.put("B", new CloseHallDoorCommand(smartHome(), commandSender));
        rcCommandMap.put("C", new TriggerAlarmCommand(smartHome()));
        rcCommandMap.put("D", new TurnOffAllTheLightsAtHomeCommand(smartHome(), commandSender));
        rcCommandMap.put("1", new TurnOnAllTheLightsAtHomeCommand(smartHome()));
        rcCommandMap.put("2", new TurnOnLightInTheHallCommand(smartHome()));
        rcCommandMap.put("3", null);
        rcCommandMap.put("4", null);
        return rcCommandMap;
    }

    @Bean
    RemoteControl remoteControl() {
        return new SmartHomeRemoteController("1", rcCommandMap());
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl(), "1");
        return remoteControlRegistry;
    }
}

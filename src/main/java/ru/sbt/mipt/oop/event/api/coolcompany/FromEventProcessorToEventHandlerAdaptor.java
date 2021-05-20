package ru.sbt.mipt.oop.event.api.coolcompany;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.event.EventProcessor;
import ru.sbt.mipt.oop.house.SmartHome;

public class FromEventProcessorToEventHandlerAdaptor implements EventHandler {
    private final EventProcessor eventProcessor;
    private final SmartHome smartHome;

    public FromEventProcessorToEventHandlerAdaptor(EventProcessor eventProcessor, SmartHome smartHome) {
        this.eventProcessor = eventProcessor;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent ccSensorEvent) {
        CCSensorEventToSensorEventMapper ccSensorEventToSensorEventMapper = CCSensorEventToSensorEventMapper.getInstance();
        eventProcessor.processEvent(ccSensorEventToSensorEventMapper.mapCCSensorEventToSensorEvent(ccSensorEvent), smartHome);
    }
}

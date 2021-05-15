package ru.sbt.mipt.oop.test.event;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.LightEventProcessor;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

public class LightEventProcessorTest extends TestCase {

    @Test
    public void testSmartHomeWasExecutedByLightEventProcessorWhenEventLightOnWasReceived() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        LightEventProcessor lightEventProcessor = new LightEventProcessor(new LoggerToConsole());
        Event event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        //when
        lightEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(1)).execute(Mockito.any());
    }

    @Test
    public void testSmartHomeWasExecutedByLightEventProcessorWhenEventLightOffWasReceived() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        LightEventProcessor lightEventProcessor = new LightEventProcessor(new LoggerToConsole());
        Event event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        //when
        lightEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(1)).execute(Mockito.any());
    }

    @Test
    public void testSmartHomeWasNotExecutedByLightEventProcessorWhenEventWithoutLightWasReceived() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        LightEventProcessor lightEventProcessor = new LightEventProcessor(new LoggerToConsole());
        Event event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        //when
        lightEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(0)).execute(Mockito.any());
    }
}
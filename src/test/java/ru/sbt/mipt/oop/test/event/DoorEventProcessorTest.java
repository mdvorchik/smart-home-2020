package ru.sbt.mipt.oop.test.event;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.command.SmartHomeCommandSender;
import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

public class DoorEventProcessorTest extends TestCase {

    @Test
    public void testSmartHomeWasExecutedByDoorEventProcessorWhenEventDoorOpenWasReceived() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(new LoggerToConsole(), new SmartHomeCommandSender(new LoggerToConsole()));
        Event event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        //when
        doorEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(1)).execute(Mockito.any());
    }

    @Test
    public void testSmartHomeWasExecutedByDoorEventProcessorWhenEventDoorClosedWasReceived() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(new LoggerToConsole(), new SmartHomeCommandSender(new LoggerToConsole()));
        Event event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        //when
        doorEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(1)).execute(Mockito.any());
    }

    @Test
    public void testSmartHomeWasNotExecutedByDoorEventProcessorWhenEventWithoutDoorWasReceived() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(new LoggerToConsole(), new SmartHomeCommandSender(new LoggerToConsole()));
        Event event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        //when
        doorEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(0)).execute(Mockito.any());
    }
}
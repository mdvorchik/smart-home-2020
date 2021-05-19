package ru.sbt.mipt.oop.test.event;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.command.SmartHomeCommandSender;
import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.house.alarm.Alarm;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

import static org.junit.Assert.*;

public class SecurityEventProcessorTest extends TestCase {

    @Test
    public void testSmartHomeWasExecutedBySomeEventProcessorWhenSomeEventWasReceivedAndAlarmWasDeactivated() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        Alarm alarm = new Alarm("1");
        Mockito.when(smartHome.getAlarm()).thenReturn(alarm);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(new LoggerToConsole(), new SmartHomeCommandSender(new LoggerToConsole()));
        SecurityEventProcessor securityEventProcessor = new SecurityEventProcessor(doorEventProcessor);
        Event event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        //when
        securityEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(1)).execute(Mockito.any());
    }

    @Test
    public void testSmartHomeWasNotExecutedBySomeEventProcessorWhenSomeEventWasReceivedAndAlarmWasActivated() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        Alarm alarm = new Alarm("1");
        alarm.activate("code");
        Mockito.when(smartHome.getAlarm()).thenReturn(alarm);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(new LoggerToConsole(), new SmartHomeCommandSender(new LoggerToConsole()));
        SecurityEventProcessor securityEventProcessor = new SecurityEventProcessor(doorEventProcessor);
        Event event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        //when
        securityEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(0)).execute(Mockito.any());
    }

    @Test
    public void testSmartHomeWasNotExecutedBySomeEventProcessorWhenSomeEventWasReceivedAndAlarmWasTriggered() {
        //given
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        Alarm alarm = new Alarm("1");
        alarm.activate("code");
        alarm.deactivate("wrong_code"); //after deactivate with wrong code alarm is triggered
        Mockito.when(smartHome.getAlarm()).thenReturn(alarm);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(new LoggerToConsole(), new SmartHomeCommandSender(new LoggerToConsole()));
        SecurityEventProcessor securityEventProcessor = new SecurityEventProcessor(doorEventProcessor);
        Event event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        //when
        securityEventProcessor.processEvent(event, smartHome);
        //verify
        Mockito.verify(smartHome, Mockito.times(0)).execute(Mockito.any());
    }
}
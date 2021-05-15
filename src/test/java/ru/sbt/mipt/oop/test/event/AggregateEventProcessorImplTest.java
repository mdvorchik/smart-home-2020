package ru.sbt.mipt.oop.test.event;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sbt.mipt.oop.event.*;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

import java.util.ArrayList;
import java.util.List;

public class AggregateEventProcessorImplTest extends TestCase {

    @Test
    public void testAggregateEventProcessorProcessAllTheReceivedEventsByAllTheGivenProcessors() {
        //given
        Logger logger = new LoggerToConsole();
        LightEventProcessor lightEventProcessor = Mockito.mock(LightEventProcessor.class);
        DoorEventProcessor doorEventProcessor = Mockito.mock(DoorEventProcessor.class);
        List<EventProcessor> eventProcessors = new ArrayList<>();
        eventProcessors.add(lightEventProcessor);
        eventProcessors.add(doorEventProcessor);
        AggregateEventProcessorImpl aggregateEventProcessor = new AggregateEventProcessorImpl(logger, eventProcessors);
        SmartHome smartHome = Mockito.mock(SmartHome.class);
        EventCreator eventCreator = Mockito.mock(EventCreator.class);
        Mockito.when(eventCreator.createNextEvent())
                .thenReturn(new SensorEvent(SensorEventType.LIGHT_ON, "1"))
                .thenReturn(new SensorEvent(SensorEventType.DOOR_CLOSED, "1"))
                .thenReturn(null);
        //when
        aggregateEventProcessor.processEvents(eventCreator, smartHome);
        //verify
        Mockito.verify(lightEventProcessor, Mockito.times(2)).processEvent(Mockito.any(), Mockito.any());
        Mockito.verify(doorEventProcessor, Mockito.times(2)).processEvent(Mockito.any(), Mockito.any());
    }
}
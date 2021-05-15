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
        //when
        aggregateEventProcessor.processEvents(new EventCreator() {
            int counter = 3;
            @Override
            public Event createNextEvent() {
                counter--;
                if (counter == 2) return new SensorEvent(SensorEventType.LIGHT_ON, "1");
                else if (counter == 1) return new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
                else return null;
            }
        }, smartHome);
        //verify
        Mockito.verify(lightEventProcessor, Mockito.times(2)).processEvent(Mockito.any(), Mockito.any());
        Mockito.verify(doorEventProcessor, Mockito.times(2)).processEvent(Mockito.any(), Mockito.any());
    }
}
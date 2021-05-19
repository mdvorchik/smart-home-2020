package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.house.alarm.Alarm;
import ru.sbt.mipt.oop.house.alarm.AlarmActivated;
import ru.sbt.mipt.oop.house.alarm.AlarmDeactivated;
import ru.sbt.mipt.oop.house.alarm.AlarmTriggered;

public class SecurityEventProcessor implements EventProcessor{
    private final EventProcessor eventProcessor;

    public SecurityEventProcessor(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        SmartHome smartHome = (SmartHome) objectWhereTheEventOccurs;
        if (smartHome.getAlarm().getAlarmState() instanceof AlarmDeactivated) {
            eventProcessor.processEvent(event, objectWhereTheEventOccurs);
        }
        if (smartHome.getAlarm().getAlarmState() instanceof AlarmActivated) {
            smartHome.getAlarm().trigger();
            System.out.println("Sending sms: alarm was triggered");
        }
    }
}

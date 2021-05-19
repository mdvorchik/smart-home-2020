package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.action.AlarmActivateAction;
import ru.sbt.mipt.oop.action.AlarmDeactivateAction;
import ru.sbt.mipt.oop.house.SmartHome;

import static ru.sbt.mipt.oop.event.AlarmEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.event.AlarmEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor{

    @Override
    public void processEvent(Event event, Object objectWhereTheEventOccurs) {
        if (event.getType() != ALARM_ACTIVATE && event.getType() != ALARM_DEACTIVATE) return;
        SmartHome smartHome = (SmartHome) objectWhereTheEventOccurs;
        if (event.getType() == ALARM_ACTIVATE) {
            if (event instanceof AlarmEvent) {
                smartHome.execute(new AlarmActivateAction(event.getObjectId(), smartHome, ((AlarmEvent) event).getCode()));
            }
        } else {
            if (event instanceof AlarmEvent) {
                smartHome.execute(new AlarmDeactivateAction(event.getObjectId(), smartHome, ((AlarmEvent) event).getCode()));
            }
        }
    }
}

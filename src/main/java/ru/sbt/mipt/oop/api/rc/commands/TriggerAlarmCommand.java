package ru.sbt.mipt.oop.api.rc.commands;

import ru.sbt.mipt.oop.house.SmartHome;

public class TriggerAlarmCommand implements RcCommand {
    private final SmartHome smartHome;

    public TriggerAlarmCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().trigger();
    }
}

package ru.sbt.mipt.oop.api.rc.commands;

import ru.sbt.mipt.oop.house.SmartHome;

public class ActivateAlarmCommand implements RcCommand {

    private final SmartHome smartHome;
    private final String code;

    public ActivateAlarmCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getAlarm().activate(code);
    }
}

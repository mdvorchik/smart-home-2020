package ru.sbt.mipt.oop.api.rc.commands;

import ru.sbt.mipt.oop.action.AllTheLightOffAction;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.house.SmartHome;

public class TurnOffAllTheLightsAtHomeCommand implements RcCommand {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public TurnOffAllTheLightsAtHomeCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.execute(new AllTheLightOffAction(commandSender));
    }
}

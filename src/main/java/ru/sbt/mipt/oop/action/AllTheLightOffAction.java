package ru.sbt.mipt.oop.action;

import ru.sbt.mipt.oop.command.Command;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.house.Light;

public class AllTheLightOffAction implements Action {
    private CommandSender commandSender;

    public AllTheLightOffAction(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void execute(Object component) {
        if (component instanceof Light) {
            ((Light) component).setOn(false);
            Command command = new SensorCommand(CommandType.LIGHT_OFF, ((Light) component).getId());
            commandSender.sendCommand(command);
        }
    }
}

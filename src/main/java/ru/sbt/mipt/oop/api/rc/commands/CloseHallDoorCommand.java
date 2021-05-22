package ru.sbt.mipt.oop.api.rc.commands;

import ru.sbt.mipt.oop.action.AllTheLightOffAction;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.SmartHome;

public class CloseHallDoorCommand implements RcCommand {
    private final SmartHome smartHome;
    private final CommandSender commandSender;

    public CloseHallDoorCommand(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void execute() {
        smartHome.execute((component) -> {
            if (component instanceof Door && smartHome.getRoomNameByDoorId(((Door) component).getId()).equals("hall")) {
                ((Door) component).setOpen(false);
                System.out.println("Hall door was closed.");
                smartHome.execute(new AllTheLightOffAction(commandSender));
            }
        });
    }
}

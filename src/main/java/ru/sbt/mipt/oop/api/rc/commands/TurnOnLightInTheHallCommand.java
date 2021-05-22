package ru.sbt.mipt.oop.api.rc.commands;

import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.SmartHome;

public class TurnOnLightInTheHallCommand implements RcCommand {
    private final SmartHome smartHome;

    public TurnOnLightInTheHallCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute((component) -> {
            if (component instanceof Light && smartHome.getRoomNameByLightId(((Light) component).getId()).equals("hall")) {
                ((Light) component).setOn(true);
                System.out.println("Light with id " + ((Light) component).getId() + " in the hall is turned on");
            }
        });
    }
}

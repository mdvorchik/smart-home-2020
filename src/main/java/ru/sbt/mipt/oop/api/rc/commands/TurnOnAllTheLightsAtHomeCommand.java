package ru.sbt.mipt.oop.api.rc.commands;

import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.SmartHome;

public class TurnOnAllTheLightsAtHomeCommand implements RcCommand{
    private final SmartHome smartHome;

    public TurnOnAllTheLightsAtHomeCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute((component) -> {
            if (component instanceof Light) {
                ((Light) component).setOn(true);
                System.out.println("Light with id " + ((Light) component).getId() + " is turned on");
            }
        });
    }
}

package ru.sbt.mipt.oop.command;

import ru.sbt.mipt.oop.utils.Logger;

public class SmartHomeCommandSender implements CommandSender {
    private final Logger logger;

    public SmartHomeCommandSender(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void sendCommand(Command command) {
        logger.log("Pretent we're sending command " + command);
    }
}

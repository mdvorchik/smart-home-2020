package ru.sbt.mipt.oop.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.utils.Logger;

@Component
public class SmartHomeCommandSender implements CommandSender {

    private final Logger logger;

    @Autowired
    public SmartHomeCommandSender(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void sendCommand(Command command) {
        logger.log("Pretent we're sending command " + command);
    }
}

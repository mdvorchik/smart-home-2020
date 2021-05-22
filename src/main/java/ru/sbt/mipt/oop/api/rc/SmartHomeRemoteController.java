package ru.sbt.mipt.oop.api.rc;

import rc.RemoteControl;
import ru.sbt.mipt.oop.api.rc.commands.RcCommand;

import java.util.Map;

public class SmartHomeRemoteController implements RemoteControl, Programmable {

    private final String id;
    private Map<String, RcCommand> rcCommandMap;

    public SmartHomeRemoteController(String id, Map<String, RcCommand> rcCommandMap) {
        this.id = id;
        this.rcCommandMap = rcCommandMap;
    }

    @Override
    public void onButtonPressed(String rcId, String buttonCode) {
        if (rcCommandMap.containsKey(buttonCode)) {
            if (rcCommandMap.get(buttonCode) != null)
                rcCommandMap.get(buttonCode).execute();
            else System.out.println("This button has not any command");
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void programButton(String buttonCode, RcCommand rcCommand) {
        if (rcCommandMap.containsKey(buttonCode)) {
            rcCommandMap.put(buttonCode, rcCommand);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getId() {
        return id;
    }
}

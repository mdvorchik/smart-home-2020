package ru.sbt.mipt.oop.api.rc;

import ru.sbt.mipt.oop.api.rc.commands.RcCommand;

public interface Programmable {
    void programButton(String buttonCode, RcCommand rcCommand);
}

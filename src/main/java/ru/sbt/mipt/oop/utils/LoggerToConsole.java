package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.utils.Logger;

public class LoggerToConsole implements Logger {
    @Override
    public void log(String stringToLog) {
        System.out.println(stringToLog);
    }
}

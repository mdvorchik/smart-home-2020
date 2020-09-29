package ru.sbt.mipt.oop;

public class LoggerToConsole implements ILogger {
    @Override
    public void log(String stringToLog) {
        System.out.println(stringToLog);
    }
}

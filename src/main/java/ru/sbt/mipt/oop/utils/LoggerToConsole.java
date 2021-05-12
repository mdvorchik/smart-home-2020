package ru.sbt.mipt.oop.utils;

public class LoggerToConsole implements Logger {
    @Override
    public void log(String stringToLog) {
        System.out.println(stringToLog);
    }
}

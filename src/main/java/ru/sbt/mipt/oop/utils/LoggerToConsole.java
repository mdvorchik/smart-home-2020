package ru.sbt.mipt.oop.utils;

import org.springframework.stereotype.Component;

@Component
public class LoggerToConsole implements Logger {
    @Override
    public void log(String stringToLog) {
        System.out.println(stringToLog);
    }
}

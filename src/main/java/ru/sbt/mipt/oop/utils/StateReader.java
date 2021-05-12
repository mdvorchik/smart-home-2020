package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.house.SmartHome;

public interface StateReader {

    SmartHome readStateOfHome(Object source);

}

package ru.sbt.mipt.oop.test.action;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.action.AllTheLightOffAction;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.SmartHomeCommandSender;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AllTheLightOffActionTest {

    @Test
    public void testAllTheLightsInAllTheRoomsTurnOffWhenReceivedAllTheLightOffAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        CommandSender commandSender = new SmartHomeCommandSender(logger);
        SmartHome smartHome = new SmartHome();
        Light lightIsOnInFirstRoom1 = new Light("1", true);
        Light lightIsOnInFirstRoom2 = new Light("2", true);
        Light lightIsOnInSecondRoom1 = new Light("3", true);
        Light lightIsOnInSecondRoom2 = new Light("4", true);
        smartHome.addRoom(new Room(Arrays.asList(lightIsOnInFirstRoom1, lightIsOnInFirstRoom2),
                Arrays.asList(new Door(true, "1")), "testHall"));
        smartHome.addRoom(new Room(Arrays.asList(lightIsOnInSecondRoom1, lightIsOnInSecondRoom2),
                Arrays.asList(new Door(true, "2")), "testRoom"));
        Field lightIsOnField = lightIsOnInFirstRoom1.getClass().getDeclaredField("isOn");
        lightIsOnField.setAccessible(true);
        //when
        smartHome.execute(new AllTheLightOffAction(commandSender));
        //verify
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInFirstRoom1));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInFirstRoom2));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInSecondRoom1));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInSecondRoom2));
    }
}
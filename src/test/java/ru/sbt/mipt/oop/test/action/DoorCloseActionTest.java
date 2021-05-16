package ru.sbt.mipt.oop.test.action;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.action.DoorCloseAction;
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

public class DoorCloseActionTest extends TestCase {

    @Test
    public void testExecuteMethodOfDoorCloseWhenReceivedDoorCloseAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        CommandSender commandSender = new SmartHomeCommandSender(logger);
        SmartHome smartHome = new SmartHome();
        Door doorIsOpen = new Door(true, "1");
        Door doorIsClosed = new Door(false, "2");
        smartHome.addRoom(new Room(Arrays.asList(new Light("1", true)),
                Arrays.asList(doorIsOpen, doorIsClosed), "testRoom"));
        Field doorIsOpenField = doorIsOpen.getClass().getDeclaredField("isOpen");
        doorIsOpenField.setAccessible(true);
        //when
        smartHome.execute(new DoorCloseAction("1", smartHome, commandSender, logger));
        smartHome.execute(new DoorCloseAction("2", smartHome, commandSender, logger));
        //verify
        Assert.assertFalse((Boolean) doorIsOpenField.get(doorIsOpen));
        Assert.assertFalse((Boolean) doorIsOpenField.get(doorIsClosed));
    }

    @Test
    public void testExecuteMethodOfDoorCloseNotAffectOnAnotherDoorsWhenReceivedDoorCloseAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        CommandSender commandSender = new SmartHomeCommandSender(logger);
        SmartHome smartHome = new SmartHome();
        Door doorIsOpen = new Door(true, "1");
        Door doorIsNotClosed = new Door(true, "2");
        smartHome.addRoom(new Room(Arrays.asList(new Light("1", true)),
                Arrays.asList(doorIsOpen, doorIsNotClosed), "testRoom"));
        Field doorIsOpenField = doorIsOpen.getClass().getDeclaredField("isOpen");
        doorIsOpenField.setAccessible(true);
        //when
        smartHome.execute(new DoorCloseAction("1", smartHome, commandSender, logger));
        //verify
        Assert.assertFalse((Boolean) doorIsOpenField.get(doorIsOpen));
        Assert.assertTrue((Boolean) doorIsOpenField.get(doorIsNotClosed));
    }

}
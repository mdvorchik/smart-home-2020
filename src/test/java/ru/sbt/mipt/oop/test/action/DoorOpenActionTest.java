package ru.sbt.mipt.oop.test.action;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.action.DoorOpenAction;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

import java.lang.reflect.Field;
import java.util.Arrays;

public class DoorOpenActionTest extends TestCase {

    @Test
    public void testExecuteMethodOfDoorOpenWhenReceivedDoorOpenAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        SmartHome smartHome = new SmartHome();
        Door doorIsOpen = new Door(true, "1");
        Door doorIsClosed = new Door(false, "2");
        smartHome.addRoom(new Room(Arrays.asList(new Light("1", true)),
                Arrays.asList(doorIsOpen, doorIsClosed), "testRoom"));
        Field doorIsOpenField = doorIsOpen.getClass().getDeclaredField("isOpen");
        doorIsOpenField.setAccessible(true);
        //when
        smartHome.execute(new DoorOpenAction("1", smartHome, logger));
        smartHome.execute(new DoorOpenAction("2", smartHome, logger));
        //verify
        Assert.assertTrue((Boolean) doorIsOpenField.get(doorIsOpen));
        Assert.assertTrue((Boolean) doorIsOpenField.get(doorIsClosed));
    }

    @Test
    public void testExecuteMethodOfDoorOpenNotAffectOnAnotherObjectsWhenReceivedDoorOpenAction() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        SmartHome smartHome = new SmartHome();
        Door doorIsClosed = new Door(false, "1");
        Door doorIsNotOpen = new Door(false, "2");
        smartHome.addRoom(new Room(Arrays.asList(new Light("1", true)),
                Arrays.asList(doorIsClosed, doorIsNotOpen), "testRoom"));
        Field doorIsOpenField = doorIsClosed.getClass().getDeclaredField("isOpen");
        doorIsOpenField.setAccessible(true);
        //when
        smartHome.execute(new DoorOpenAction("1", smartHome, logger));
        //verify
        Assert.assertTrue((Boolean) doorIsOpenField.get(doorIsClosed));
        Assert.assertFalse((Boolean) doorIsOpenField.get(doorIsNotOpen));
    }
}
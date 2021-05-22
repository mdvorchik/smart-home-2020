package ru.sbt.mipt.oop.test.rc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.api.rc.SmartHomeRemoteController;
import ru.sbt.mipt.oop.api.rc.commands.*;
import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.SmartHomeCommandSender;
import ru.sbt.mipt.oop.config.ApplicationConfig;
import ru.sbt.mipt.oop.house.Door;
import ru.sbt.mipt.oop.house.Light;
import ru.sbt.mipt.oop.house.Room;
import ru.sbt.mipt.oop.house.SmartHome;
import ru.sbt.mipt.oop.house.alarm.AlarmActivated;
import ru.sbt.mipt.oop.house.alarm.AlarmTriggered;
import ru.sbt.mipt.oop.utils.Logger;
import ru.sbt.mipt.oop.utils.LoggerToConsole;

import java.lang.reflect.Field;
import java.util.Arrays;

public class RcCommandsTest {

    private AbstractApplicationContext testContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    @Test
    public void testActivateAlarmWhenActivateAlarmCommandIsExecuted() {
        //given
        SmartHomeRemoteController remoteControl = testContext.getBean(SmartHomeRemoteController.class);
        SmartHome smartHome = testContext.getBean(SmartHome.class);
        //when
        remoteControl.onButtonPressed("test", "A"); // A -> ActivateAlarmCommand
        //verify
        Assert.assertTrue(smartHome.getAlarm().getAlarmState() instanceof AlarmActivated);
    }

    @Test
    public void testCloseHallDoorWhenCloseHallDoorCommandIsExecuted() throws NoSuchFieldException, IllegalAccessException {
        //given
        Logger logger = new LoggerToConsole();
        CommandSender commandSender = new SmartHomeCommandSender(logger);
        SmartHome smartHome = new SmartHome();
        Door doorIsOpen = new Door(true, "1");
        smartHome.addRoom(new Room(Arrays.asList(new Light("1", true)),
                Arrays.asList(doorIsOpen), "hall"));
        Field doorIsOpenField = doorIsOpen.getClass().getDeclaredField("isOpen");
        doorIsOpenField.setAccessible(true);
        RcCommand rcCommand = new CloseHallDoorCommand(smartHome, commandSender);
        //when
        rcCommand.execute();
        //verify
        Assert.assertFalse((Boolean) doorIsOpenField.get(doorIsOpen));
    }

    @Test
    public void testTriggerAlarmWhenTriggerAlarmCommandIsExecuted() {
        //given
        SmartHomeRemoteController remoteControl = testContext.getBean(SmartHomeRemoteController.class);
        SmartHome smartHome = testContext.getBean(SmartHome.class);
        //when
        remoteControl.onButtonPressed("test", "A"); // A -> ActivateAlarmCommand
        remoteControl.onButtonPressed("test", "C"); // C -> TriggerAlarmCommand
        //verify
        Assert.assertTrue(smartHome.getAlarm().getAlarmState() instanceof AlarmTriggered);
    }

    @Test
    public void testAllTheLightsInAllTheRoomsTurnOffWhenTurnOffAllTheLightsAtHomeCommandIsExecuted() throws NoSuchFieldException, IllegalAccessException {
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
        RcCommand rcCommand = new TurnOffAllTheLightsAtHomeCommand(smartHome, commandSender);
        //when
        rcCommand.execute();
        //verify
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInFirstRoom1));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInFirstRoom2));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInSecondRoom1));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOnInSecondRoom2));
    }

    @Test
    public void testAllTheLightsInAllTheRoomsTurnOnWhenTurnOnAllTheLightsAtHomeCommandIsExecuted() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Light lightIsOffInFirstRoom1 = new Light("1", false);
        Light lightIsOffInFirstRoom2 = new Light("2", false);
        Light lightIsOffInSecondRoom1 = new Light("3", false);
        Light lightIsOffInSecondRoom2 = new Light("4", false);
        smartHome.addRoom(new Room(Arrays.asList(lightIsOffInFirstRoom1, lightIsOffInFirstRoom2),
                Arrays.asList(new Door(true, "1")), "testHall"));
        smartHome.addRoom(new Room(Arrays.asList(lightIsOffInSecondRoom1, lightIsOffInSecondRoom2),
                Arrays.asList(new Door(true, "2")), "testRoom"));
        Field lightIsOnField = lightIsOffInFirstRoom1.getClass().getDeclaredField("isOn");
        lightIsOnField.setAccessible(true);
        RcCommand rcCommand = new TurnOnAllTheLightsAtHomeCommand(smartHome);
        //when
        rcCommand.execute();
        //verify
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOffInFirstRoom1));
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOffInFirstRoom2));
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOffInSecondRoom1));
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOffInSecondRoom2));
    }

    @Test
    public void testAllTheLightsInHallTurnOnWhenTurnOnLightInTheHallCommandIsExecuted() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Light lightIsOffInHallRoom1 = new Light("1", false);
        Light lightIsOffInHallRoom2 = new Light("2", false);
        Light lightIsOffInSecondRoom1 = new Light("3", false);
        Light lightIsOffInSecondRoom2 = new Light("4", false);
        smartHome.addRoom(new Room(Arrays.asList(lightIsOffInHallRoom1, lightIsOffInHallRoom2),
                Arrays.asList(new Door(true, "1")), "hall"));
        smartHome.addRoom(new Room(Arrays.asList(lightIsOffInSecondRoom1, lightIsOffInSecondRoom2),
                Arrays.asList(new Door(true, "2")), "testRoom"));
        Field lightIsOnField = lightIsOffInHallRoom1.getClass().getDeclaredField("isOn");
        lightIsOnField.setAccessible(true);
        RcCommand rcCommand = new TurnOnLightInTheHallCommand(smartHome);
        //when
        rcCommand.execute();
        //verify
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOffInHallRoom1));
        Assert.assertTrue((Boolean) lightIsOnField.get(lightIsOffInHallRoom2));
    }
    @Test
    public void testLightsInTheAnotherRoomNotTurnOnWhenTurnOnLightInTheHallCommandIsExecuted() throws NoSuchFieldException, IllegalAccessException {
        //given
        SmartHome smartHome = new SmartHome();
        Light lightIsOffInHallRoom1 = new Light("1", false);
        Light lightIsOffInHallRoom2 = new Light("2", false);
        Light lightIsOffInSecondRoom1 = new Light("3", false);
        Light lightIsOffInSecondRoom2 = new Light("4", false);
        smartHome.addRoom(new Room(Arrays.asList(lightIsOffInHallRoom1, lightIsOffInHallRoom2),
                Arrays.asList(new Door(true, "1")), "hall"));
        smartHome.addRoom(new Room(Arrays.asList(lightIsOffInSecondRoom1, lightIsOffInSecondRoom2),
                Arrays.asList(new Door(true, "2")), "testRoom"));
        Field lightIsOnField = lightIsOffInHallRoom1.getClass().getDeclaredField("isOn");
        lightIsOnField.setAccessible(true);
        RcCommand rcCommand = new TurnOnLightInTheHallCommand(smartHome);
        //when
        rcCommand.execute();
        //verify
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOffInSecondRoom1));
        Assert.assertFalse((Boolean) lightIsOnField.get(lightIsOffInSecondRoom2));
    }

}
